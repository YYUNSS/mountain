package com.kh.mountain_project.domain.managerBbs.svc;

import com.kh.mountain_project.domain.entity.ManagerBbs;

import java.util.List;

public interface ManagerBbsSVC {
  //게시판 목록 불러오기
  List<ManagerBbs> mReadAll();

  //신고된 게시판만 조회하기
  List<ManagerBbs> mReadComplain();

  //삭제하기(상태만 변경)
  int deleteComplain(List<Long> bbsIds);
}
