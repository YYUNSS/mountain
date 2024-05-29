package com.kh.mountain_project.domain.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.UUID;

@Slf4j
@RestController
public class UproadController {

  private static final String UPLOAD_FOLDER = "D:/attach/";
  private static final String UPLOADFILE_SEQ = "UPLOADFILE_UPLOADFILE_ID_SEQ";

  private static final String DB_URL = "jdbc:oracle:thin:@192.168.0.29:1521:xe";
  private static final String DB_USER = "c##mountain";
  private static final String DB_PASSWORD ="mountain1234";

  @PostMapping("/upload")
  public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("code") String code, @RequestParam("rid") String rid) {
    if (file.isEmpty()) {
      return "파일을 선택하세요.";
    }

    Connection conn = null;
    PreparedStatement pstmt = null;
    try {
      // DB 연결
      conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

      // INSERT 쿼리
      String sql = "INSERT INTO UPLOADFILE (UPLOADFILE_ID, CODE, RID, STORE_FILENAME, UPLOAD_FILENAME, FSIZE, FTYPE, CDATE) " +
              "VALUES (?, ?, ?, ?, ?, ?, ?, systimestamp)";

      // PreparedStatement 준비
      pstmt = conn.prepareStatement(sql);

      // 시퀀스로부터 다음 ID 가져오기
      long nextVal = getNextValueFromSequence(conn, UPLOADFILE_SEQ);

      // 파일 저장
      String storeFilename = saveFile(file);

      // 파라미터 설정
      pstmt.setLong(1, nextVal); // UPLOADFILE_ID
      pstmt.setString(2, code); // CODE
      pstmt.setString(3, rid); // RID
      pstmt.setString(4, storeFilename); // STORE_FILENAME
      pstmt.setString(5, file.getOriginalFilename()); // UPLOAD_FILENAME
      pstmt.setLong(6, file.getSize()); // FSIZE
      pstmt.setString(7, file.getContentType()); // FTYPE

      // 쿼리 실행
      pstmt.executeUpdate();

      return "파일 업로드 및 DB 저장 완료";
    } catch (IOException | SQLException e) {
      e.printStackTrace();
      return "파일 업로드 및 DB 저장 실패: " + e.getMessage();
    } finally {
      // 연결 및 자원 해제
      try {
        if (pstmt != null) pstmt.close();
        if (conn != null) conn.close();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
  }

  // 시퀀스로부터 다음 ID를 가져오는 메서드
  private long getNextValueFromSequence(Connection conn, String seqName) throws SQLException {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      pstmt = conn.prepareStatement("SELECT " + seqName + ".NEXTVAL FROM DUAL");
      rs = pstmt.executeQuery();
      if (rs.next()) {
        return rs.getLong(1);
      }
      throw new SQLException("시퀀스에서 다음 값을 가져오는 데 실패했습니다.");
    } finally {
      // ResultSet, PreparedStatement 닫기
      if (rs != null) rs.close();
      if (pstmt != null) pstmt.close();
    }
  }

  // 파일을 저장하는 메서드
  private String saveFile(MultipartFile file) throws IOException {
    String storeFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
    Path path = Paths.get(UPLOAD_FOLDER + storeFilename);
    Files.write(path, file.getBytes());
    return storeFilename;
  }

  @GetMapping("/images/{code}/{rid}")
  public ResponseEntity<Resource> serveImage(@PathVariable("code") String code, @PathVariable("rid") String rid) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      // Oracle DB에 연결
      conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

      // 이미지를 가져올 쿼리 작성
      String sql = " SELECT STORE_FILENAME FROM UPLOADFILE WHERE CODE = ? AND RID = ? ";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, code);
      pstmt.setString(2, rid);
      rs = pstmt.executeQuery();

      if (rs.next()) {
        // 이미지 데이터를 읽어와서 Resource 객체로 변환
        InputStream inputStream = rs.getBinaryStream("STORE_FILENAME");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        log.info("inputStream={}",inputStream);
        return new ResponseEntity<>(new InputStreamResource(inputStream), headers, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    } finally {
      // 연결 및 자원 해제
      try {
        if (rs != null) rs.close();
        if (pstmt != null) pstmt.close();
        if (conn != null) conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
