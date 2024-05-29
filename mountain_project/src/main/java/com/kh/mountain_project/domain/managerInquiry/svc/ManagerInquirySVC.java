package com.kh.mountain_project.domain.managerInquiry.svc;

import com.kh.mountain_project.domain.entity.ManagerInquiry;

import java.util.List;
import java.util.Optional;

public interface ManagerInquirySVC {
  List<ManagerInquiry> viewInquiryAll();

  //처리 중인 글의 목록만 보기
  List<ManagerInquiry> viewInquiryNull();
  List<ManagerInquiry> viewInquiryComplete();

  List<ManagerInquiry> viewInquiryProgress();

  //상세 조회(+댓글)
  Optional<ManagerInquiry> mViewByInquiryId(Long inquiryId);
}
