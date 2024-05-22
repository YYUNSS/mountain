package com.kh.mountain_project.domain.web.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginMember {
  private Long memberId;            //   NUMBER(8,0)
  //아이디
  private String id;             //   VARCHAR2(40 BYTE)
  //닉네임
  private String nickname;          //   VARCHAR2(30 BYTE)
  //구분(일반 U, 관리자 M)
  private String gubun;             //   VARCHAR2(11 BYTE)



}
