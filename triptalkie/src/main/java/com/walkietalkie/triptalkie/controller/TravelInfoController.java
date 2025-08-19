package com.walkietalkie.triptalkie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.walkietalkie.triptalkie.domain.TravelInfo;
import com.walkietalkie.triptalkie.mapper.TravelInfoMapper;
import com.walkietalkie.triptalkie.service.TravelInfoService;

@Controller
@RequestMapping("/travel-info")
public class TravelInfoController {
  private final TravelInfoService travelInforService;
  
  @Autowired
  public TravelInfoController(TravelInfoService travelInforService) {
    this.travelInforService = travelInforService;
  }
  
  // 2. 메서드 레벨 경로는 "/list"로 지정하여 명확하게 분리
  @GetMapping("/list")
  public String TravelInfoListPage(Model model) {
    List<TravelInfo> travelInfoList = travelInforService.findTravelInfoAllList();
    
    model.addAttribute("travelInfoList", travelInfoList);
    // Model 객체에 travelInfoList 데이터 저장
    // 뷰로 모델 객체가 전달되므로 뷰에서 모델객체 안에 있는 travelInfoList 데이터 사용이 가능해진다.
    
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