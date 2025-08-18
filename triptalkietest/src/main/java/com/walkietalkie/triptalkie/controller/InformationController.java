package com.walkietalkie.triptalkie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inforamtion")
public class InformationController {
  // 2. 메서드 레벨 경로는 "/list"로 지정하여 명확하게 분리
  @GetMapping("/list")
  public String getInformationList(Model model) {
      return "information/list";
  }
}