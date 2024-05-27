package com.kh.mountain_project.domain.web;

import com.kh.mountain_project.domain.entity.ManagerBbs;
import com.kh.mountain_project.domain.entity.ManagerInquiry;
import com.kh.mountain_project.domain.managerBbs.svc.ManagerBbsSVC;
import com.kh.mountain_project.domain.managerInquiry.svc.ManagerInquirySVC;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {
  private final ManagerBbsSVC managerBbsSVC;
  private final ManagerInquirySVC managerInquirySVC;

  @GetMapping("/main")
  public String home() {
    return "manager/main";
  }

//  @GetMapping("/viewBbsAll")
//  public String getAllBbs(Model model) {
//    List<ManagerBbs> list = managerBbsSVC.viewBbsAll();
//    model.addAttribute("list", list);
//    log.info("list = {}", list);
//    return "manager/mReadBbs";
//  }

@GetMapping("/searchBbs")
public String search(
        @RequestParam(name = "searchType") String searchType,
        @RequestParam(name = "keyword", required = false) String keyword, // keyword를 필수로 받지 않음
        Model model
) {
  List<ManagerBbs> list;
  Long reqPage = 1L;
  Long reqCnt = 15L;
  if (keyword == null || keyword.isEmpty()) { // keyword가 없거나 비어있으면
    list = managerBbsSVC.viewBbsAll(reqPage, reqCnt); // 모든 게시글을 검색
  } else {
    switch (searchType) {
      case "all" :
        list = managerBbsSVC.searchByAll(keyword);
        break;
      case "title":
        list = managerBbsSVC.searchByTitle(keyword);
        break;
      case "mntnName":
        list = managerBbsSVC.searchBymntnNm(keyword);
        break;
      case "nickname":
        list = managerBbsSVC.searchBynickname(keyword);
        break;
      default:
        list = new ArrayList<>(); // 기본 빈 리스트
        break;
    }
  }

  model.addAttribute("list", list); // 모델에 list 속성 추가
  return "manager/msearchResults"; // 템플릿 이름 반환
}

  @GetMapping("/comBbs")
  public String mReadComplain(Model model){
    List<ManagerBbs> list = managerBbsSVC.mReadComplain();
    model.addAttribute("list", list);
    log.info("list={}",list);
    return "manager/mReadComplain";
  }

  @PatchMapping("/comBbs")
  public ResponseEntity<Void> deleteComplains(@RequestBody List<Long> bbsIds) {
    try {
      managerBbsSVC.deleteComplain(bbsIds);
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      log.error("신고글 삭제 실패! 사유: {}", e.getMessage(), e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/viewInquiryAll")
  public String viewInquiryAll(Model model) {
    List<ManagerInquiry> list = managerInquirySVC.viewInquiryAll();
    model.addAttribute("list", list);
    log.info("list = {}", list);
    return "manager/mReadInquiry";
  }
  @GetMapping("/plot")
  public String showPlot(Model model) {
    // 파이썬 설치 위치
    String INTERPRETER_PATH = "d:/kdt/anaconda3/python.exe";
    final String SOURCE_PATH = "d:/kdt/projects/pythonDemo2/visualization";

    // 파이썬 스크립트 실행
    ProcessBuilder processBuilder = new ProcessBuilder(INTERPRETER_PATH, "review.py");
    processBuilder.directory(new File(SOURCE_PATH));

    // 외부 스크립트 파일 실행 결과를 저장하기 위한 문자열 객체
    StringBuilder result = new StringBuilder();

    try {
      Process process = processBuilder.start();  // 외부 스크립트 실행
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line;
      while ((line = reader.readLine()) != null) {
        result.append(line).append("\n");
      }
      process.waitFor();
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }

    // 구분자를 기준으로 결과를 나눔
    String[] images = result.toString().split("---END---");

    // base64 인코딩된 이미지 데이터를 모델에 추가
    for (int i = 0; i < images.length; i++) {
      model.addAttribute("imageData" + (i + 1), images[i].trim());
    }

    return "manager/plot";
  }
 // 관리자 게시글 목록(페이징)
  @GetMapping("viewBbsAll")   // GET http://localhost:9090/products?reqPage=2&reqCnt=10
  public String findAllByPaging(
          Model model,
          @RequestParam(value = "reqPage", defaultValue = "1") Long reqPage, // 요청 페이지
          @RequestParam(value = "reqCnt", defaultValue = "15") Long reqCnt,   // 레코드 수
          @RequestParam(value = "cpgs", defaultValue = "1") Long cpgs,   //페이지 그룹 시작번호
          @RequestParam(value = "cp", defaultValue = "1") Long cp   // 현재 페이지
  ){

    List<ManagerBbs> list = managerBbsSVC.viewBbsAll(reqPage, reqCnt);
    int totalCnt = managerBbsSVC.totalCnt();

    model.addAttribute("list", list);
    model.addAttribute("totalCnt", totalCnt);
    model.addAttribute("cpgs", cpgs);
    model.addAttribute("cp", cp);

    return "manager/mreadBbs";
  }

}

