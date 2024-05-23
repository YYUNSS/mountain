package com.kh.mountain_project.domain.managerInquiry.dao;

import com.kh.mountain_project.domain.entity.ManagerInquiry;

import java.util.List;

public interface ManagerInquiryDAO {
  //문의 글 목록 전체 보기
  List<ManagerInquiry> viewInquiryAll();

  //처리 중인 글의 목록만 보기

  //처리 완료인 목록만 보기

  //null인 글 목록만 보기(답변 전)

  //상세 조회(+댓글)

  //검색어 입력

}
