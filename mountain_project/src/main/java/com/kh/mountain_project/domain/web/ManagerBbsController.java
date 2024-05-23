package com.kh.mountain_project.domain.web;

import com.kh.mountain_project.domain.entity.ManagerBbs;
import com.kh.mountain_project.domain.managerBbs.svc.ManagerBbsSVC;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerBbsController {
  private final ManagerBbsSVC managerBbsSVC;

  @GetMapping("/main")
  public String home() {
    return "manager/main";
  }
  @GetMapping("/viewBbsAll")
  public String getAllBbs(Model model) {
    List<ManagerBbs> list = managerBbsSVC.viewBbsAll();
    model.addAttribute("list", list);
    log.info("list = {}", list);
    return "manager/mReadBbs";
  }
  @GetMapping("/comBbs")
  public String mReadComplain(Model model){
    List<ManagerBbs> list = managerBbsSVC.mReadComplain();
    model.addAttribute("list", list);
    return "manager/mReadComplain";
  }

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

  @GetMapping("/search")
  public String search(
          @RequestParam String searchType,
          @RequestParam String keyword,
          Model model
  ) {
    List<ManagerBbs> list;

    switch (searchType) {
      case "title":
        list = managerBbsSVC.searchByTitle(keyword);
        break;
      case "mntnName":
        list = managerBbsSVC.searchBymntnNm(keyword);
        break;
      case "nickname":
        list = managerBbsSVC.searchBynickname(keyword);
        break;
      case "all":
        list = managerBbsSVC.viewBbsAll();
      default:
        list = new ArrayList<>(); // 기본 빈 리스트
        break;
    }

    model.addAttribute("list", list);
    return "manager/msearchResults";
  }
}

