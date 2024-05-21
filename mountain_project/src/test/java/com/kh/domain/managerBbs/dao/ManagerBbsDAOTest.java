package com.kh.domain.managerBbs.dao;

import com.kh.mountain_project.domain.managerBbs.dao.ManagerBbsDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@Slf4j

@SpringBootTest
public class ManagerBbsDAOTest {
  @Autowired
  ManagerBbsDAO managerBbsDAO;

  @Test
    //게시판 목록 불러오기
  void mreadAll(){

  };

  //신고된 게시판만 조회하기
  void mreadcomplain(Long bbsId){

  };

  //삭제하기(상태만 변경)
  @Test
  void deletecomplain(){
    // Given
    List<Long> bbsIds = Arrays.asList(1L, 2L);
    int deletedRowCnt = managerBbsDAO.deletecomplain(bbsIds);
    log.info("deletedRowCnt={} 삭제 완료");
  }
}
