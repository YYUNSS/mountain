package com.kh.mountain_project.domain.managerBbs.dao;

import com.kh.mountain_project.domain.entity.ManagerBbs;

import java.util.List;

public interface ManagerBbsDAO {

  //게시판 목록 불러오기
  List<ManagerBbs> mReadAll();


  //신고된 게시판만 조회하기
  List<ManagerBbs> mReadComplain();

  //삭제하기(상태만 변경)
  int deleteComplain(List<Long> bbsIds);

  //단건 조회(상세조회)

  //조인해서 불러오기(게시글, 회원정보, 산)
  List<ManagerBbs> viewBbsAll();

  // 제목으로 게시글 찾기
  List <ManagerBbs> searchByTitle(String title);

  //산 이름으로 게시글 찾기
  List <ManagerBbs> searchBymntnNm(String mntnNm);

  // 회원 닉네임으로 게시글 찾기
  List <ManagerBbs> searchBynickname(String nickname);

}
