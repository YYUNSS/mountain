package com.kh.mountain_project.domain.managerBbs.dao;

import com.kh.mountain_project.domain.entity.ManagerBbs;

import java.util.List;

public interface ManagerBbsDAO {

  //게시판 목록 불러오기
  List<ManagerBbs> mreadAll();

  //신고된 게시판만 조회하기
  List<ManagerBbs> mreadcomplain(Long bbsId);

  //삭제하기(상태만 변경)
  int deletecomplain(List<Long> bbsIds);

}
