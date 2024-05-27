package com.kh.mountain_project.domain.managerBbs.dao;

import com.kh.mountain_project.domain.entity.ManagerBbs;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
public class ManagerBbsDAOImplTest {
  @Autowired
  ManagerBbsDAO managerBbsDAO;
  @Mock
  private JdbcTemplate jdbcTemplate;

  @Test
  void test(){
  log.info("managerBbsDAO={}",managerBbsDAO.getClass().getName());
  }

  //신고된 게시판만 조회하기
  void mreadcomplain(Long bbsId){
  };

  //삭제하기(상태만 변경)
  @Test
  void deleteComplain(){
    // Given
    List<Long> bbsIds = Arrays.asList(1L, 2L);
    int deletedRowCnt = managerBbsDAO.deleteComplain(bbsIds);
    log.info("{} 삭제 완료", deletedRowCnt);}

  @Test
  void testSearchByAll() {
    // Given
    String keyword = "광교"; // 검색 키워드

    // When
    List<ManagerBbs> actualList = managerBbsDAO.searchByAll(keyword);

    // Then
    assertEquals(6, actualList.size()); // 예상한 결과의 크기와 일치하는지 확인하고, 필요에 따라 추가적으로 검증합니다.
  }
}
