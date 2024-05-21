package com.kh.mountain_project.domain.entity;

import java.time.LocalDateTime;

public class ManagerBbs {
  // BBS_ID	게시글번호  NUMBER	10
  private Long bbsId;
  // MNTN_CODE	산 아이디 NUMBER	10

  private Long mntnCode;

  // TITLE	제목 VARCHAR2	150
  private String bbstitle;

  // MEMBER_ID	작성자멤버ID NUMBER	8
  private Long memberId;

  // HIT	조회수 NUMBER	5
  private int hit;

  // BCONTENT	본문내용 CLOB
  private String bcontent;

  // STARING	별점 NUMBER	1
  private int staring;

  // CTIME	등반 시간 NUMBER	4
  private int ctime;

  // BPIC	산 사진 BLOB

  // STATUS	게시글 상태 VARCHAR2	1
  private String status;
  // CDATE	작성일 TIMESTAMP
  private LocalDateTime cdate;
  // UDATE	수정일 TIMESTAMP
  private LocalDateTime udate;

}
