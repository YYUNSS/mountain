package com.kh.mountain_project.domain.managerInquiry.svc;

import com.kh.mountain_project.domain.entity.ManagerInquiry;

import java.util.List;

public interface ManagerInquirySVC {
  List<ManagerInquiry> viewInquiryAll();

  //처리 중인 글의 목록만 보기
  List<ManagerInquiry> viewInquiryNull();
  List<ManagerInquiry> viewInquiryComplete();

  List<ManagerInquiry> viewInquiryProgress();
}
