package com.kh.mountain_project.domain.managerInquiry.dao;

import com.kh.mountain_project.domain.entity.ManagerInquiry;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
public class MangerInquiryDAOImplTest {

  @Autowired
  ManagerInquiryDAO managerInquiryDAO;

  @Test
  void test(){
    log.info("managerInquiryDao={}",managerInquiryDAO.getClass().getName());
  }

  @Test
  void testbyall() {
    List<ManagerInquiry> actualList = managerInquiryDAO.viewInquiryAll();
    // Then
    assertEquals(14, actualList.size());
  }
  @Test
  void testbynull() {
    List<ManagerInquiry> actualList = managerInquiryDAO.viewInquiryNull();
    // Then
    assertEquals(4, actualList.size());
  }
  @Test
  void testmViewByInquiryId(){
    Long inquiryId = 24L;
    Optional<ManagerInquiry> findedinquiry = managerInquiryDAO.mViewByInquiryId(inquiryId);
    ManagerInquiry managerInquiry = findedinquiry.orElse(null);
    log.info("managerInquiry={}", managerInquiry);
  }
  @Test
  void testcomment(){
    ManagerInquiry managerInquiry = new ManagerInquiry();
    Long inquiryId = 13L;
    managerInquiry.setInquiryState("P");
    managerInquiry.setInquiryComment("DAOImple 테스트입니다.");
    int updatedRowCnt = managerInquiryDAO.commentInquiry(inquiryId, managerInquiry);
    if(updatedRowCnt == 0) {
      Assertions.fail("변경 내역이 없습니다.");
    }
    Optional<ManagerInquiry> optionalManagerInquiry = managerInquiryDAO.mViewByInquiryId(inquiryId);
    if(optionalManagerInquiry.isPresent()){
      ManagerInquiry fidedInquiry= optionalManagerInquiry.get();
      org.assertj.core.api.Assertions.assertThat(fidedInquiry.getInquiryComment()).isEqualTo("DAOImple 테스트입니다.");
      org.assertj.core.api.Assertions.assertThat(fidedInquiry.getInquiryState()).isEqualTo("P");
    }else{
      Assertions.fail("해당 문의글이 존재하지 않음");
    }
  }
}
