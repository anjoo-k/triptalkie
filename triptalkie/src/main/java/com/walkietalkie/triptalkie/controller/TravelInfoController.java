package com.walkietalkie.triptalkie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/travel-info")
public class TravelInfoController {
  // 2. 메서드 레벨 경로는 "/list"로 지정하여 명확하게 분리
  @GetMapping("/list")
  public String getTravelInfoList(Model model) {
      return "pages/travel-info/list";
  }
  @GetMapping("/register")
  public String TravelInfoRegister() {
    return "pages/travel-info/register";
  }
  @GetMapping("/detail")
  public String TravelInfoDetail() {
    return "pages/travel-info/detail";
  }
  @GetMapping("/update")
  public String TravelInfoUpdate() {
    return "pages/travel-info/update";
  }
}