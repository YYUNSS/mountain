package com.kh.mountain_project.domain.web;

import com.kh.mountain_project.domain.entity.ManagerBbs;
import com.kh.mountain_project.domain.managerBbs.svc.ManagerBbsSVC;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/mountain/manager")
@RequiredArgsConstructor
public class ManagerBbsController {
  private final ManagerBbsSVC managerBbsSVC;

  // 조회
  @GetMapping("/bbs")
  public String mReadAll(Model model){
    List<ManagerBbs> list = managerBbsSVC.mReadAll();
    model.addAttribute("list", list);
    return "manager/mRead"; // HTML 파일의 경로를 반환
  }
  @GetMapping("/comBbs")
  public String mReadComplain(Model model){
    List<ManagerBbs> list = managerBbsSVC.mReadComplain();
    model.addAttribute("list", list);
    return "manager/mReadComplain";
  }

  // 여러 게시글의 신고 삭제
  @PatchMapping("/comBbs")
  public ResponseEntity<Void> deleteComplains(@RequestBody List<Long> bbsIds) {
    try {
      managerBbsSVC.deleteComplain(bbsIds);
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      log.error("신고글 삭제 실패! 사유: {}", e.getMessage(), e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
