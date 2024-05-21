package com.kh.mountain_project.domain.web;

import com.kh.mountain_project.domain.entity.ManagerBbs;
import com.kh.mountain_project.domain.managerBbs.svc.ManagerBbsSVC;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mountain/manager")
@RequiredArgsConstructor
public class ManagerBbsController {
  private final ManagerBbsSVC managerBbsSVC;

  //조회
  @GetMapping("/bbs") //http://localhost:9090//mountain/manager/bbs
  public String mReadAll(Model model){
    List<ManagerBbs> list = managerBbsSVC.mReadAll();
    model.addAttribute("list", list);
    return "manager/mRead";
  }
}
