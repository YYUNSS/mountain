package com.kh.mountain_project.domain.managerInquiry.dao;

import com.kh.mountain_project.domain.entity.ManagerInquiry;
import lombok.extern.slf4j.Slf4j;
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
}
