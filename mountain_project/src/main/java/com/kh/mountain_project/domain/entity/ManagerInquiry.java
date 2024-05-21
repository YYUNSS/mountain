package com.kh.mountain_project.domain.entity;

public class ManagerInquiry {

  // Inquiry_id	문의글 아이디			NUMBER	10
  private Long inquiryId;

  // MEMBER_ID	작성자멤버ID			NUMBER	8
  private Long memberId;

  // Inquiry_title	문의 제목			VARCHAR2	150
  private String inquiryTitle;

  // Inquiry_content	문의 내용			CLOB
  private String inquiryContent;

  // Inquiry_comment	문의 답변			CLOB
  private String inquiryComment;

  // Inquiry_state	문의 상태			VARCHAR2
  private Character inquiryState;
}
