package com.walkietalkie.triptalkie.controller;

import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.walkietalkie.triptalkie.domain.CommonPage;
import com.walkietalkie.triptalkie.domain.TravelInfo;
import com.walkietalkie.triptalkie.mapper.TravelInfoMapper;
import com.walkietalkie.triptalkie.service.MemberService;
import com.walkietalkie.triptalkie.service.TravelInfoService;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Controller
@RequestMapping("/travel-info")
public class TravelInfoController {

  private final MemberService memberService;
  private final TravelInfoService travelInfoService;

  @Autowired
  public TravelInfoController(TravelInfoService travelInforService, MemberService memberService) {
    this.travelInfoService = travelInforService;
    this.memberService = memberService;
  }

  @GetMapping("/register")
  public String TravelInfoRegisterPage(Model model) {
    model.addAttribute("trvaleInfo", new TravelInfo());
    // th:object가 사용가능 하도록 비여있는 TravelInfo 객체를 만들어서 전달한다.
    return "pages/travel-info/register";
  }

  @PostMapping("/register")
  public String TravelInfoRegister(TravelInfo travelInfo, HttpSession session) {

    travelInfoService.registerTravelInfo(travelInfo, session);

    return "redirect:/travel-info/list";
  }

  @GetMapping("/list")
  public String travelInfoListPage(@RequestParam(defaultValue = "1") int page, Model model) {

    int pageSize = 10; // 한 페이지에 보여줄 글 수
    List<TravelInfo> allList = travelInfoService.findTravelInfoAllList(); // 전체 글 가져오기
    int totalItems = allList.size();
    int totalPage = (int) Math.ceil((double) totalItems / pageSize);

    int fromIndex = (page - 1) * pageSize;
    int toIndex = Math.min(fromIndex + pageSize, totalItems);
    List<TravelInfo> content = allList.subList(fromIndex, toIndex);

    int startPage = Math.max(1, page - 2);
    int endPage = Math.min(totalPage, page + 2);

    CommonPage<TravelInfo> pageData = new CommonPage<>(content, pageSize, page, totalPage, startPage, endPage);

    model.addAttribute("travelInfoList", pageData.getContent());
    model.addAttribute("pageData", pageData);

    return "pages/travel-info/list";
  }

  @GetMapping("/detail/{idx}")
  public String TravelInfoDetailPage(@PathVariable("idx") long idx, Model model) {
    TravelInfo info = travelInfoService.findTravelInfoIdx(idx);
    model.addAttribute("travelInfo", info);

    return "pages/travel-info/detail";
  }

  @GetMapping("/edit/{idx}")
  public String editPage(@PathVariable("idx") long idx, Model model, HttpSession session) {
    TravelInfo travelInfo = travelInfoService.findTravelInfoIdx(idx);
    
    if (travelInfo == null) {
      return "redirect:/travel-info/list";
    }

    String loginId = (String) session.getAttribute("loginId");
    if (!travelInfo.getMemberId().equals(loginId)) {
      return "redirect:/travel-info/detail/" + idx;
    }

    System.out.println(travelInfo);

    model.addAttribute("travelInfo", travelInfo);
    return "pages/travel-info/edit";
  }

  @PostMapping("/edit")
  public String editSubmit(TravelInfo travelInfo, HttpSession session, RedirectAttributes redirectAttributes) {
      // 1. 로그인 사용자 확인
      String loginId = memberService.getLoginId(session);

      // DB에서 원본 travelInfo 조회
      TravelInfo origin = travelInfoService.findTravelInfoIdx(travelInfo.getIdx());

      System.out.println("원본 데이터 : " + origin);
      
      if (!origin.getMemberId().equals(loginId)) {
          redirectAttributes.addFlashAttribute("msg", "작성자가 아닙니다.");
          return "redirect:/travel-info/detail/" + travelInfo.getIdx();
      }
      

      // 2. 년-월 -> LocalDateTime 변환
      if (travelInfo.getTempMonth() != null && !travelInfo.getTempMonth().isEmpty()) {
          YearMonth ym = YearMonth.parse(travelInfo.getTempMonth());
          travelInfo.setInfodate(ym.atDay(1).atStartOfDay());
      }
      
      travelInfo.setMemberId(loginId);

      System.out.println("업데이트 실행 데이터: " + travelInfo);
      
      // 3. 업데이트
      int success = travelInfoService.updateTravelInfoByIdxAndMemberId(travelInfo, session);

      if (success > 0) {
          redirectAttributes.addFlashAttribute("msg", "글이 수정되었습니다.");
          return "redirect:/travel-info/detail/" + travelInfo.getIdx();
      } else {
          redirectAttributes.addFlashAttribute("msg", "글 수정에 실패했습니다.");
          return "redirect:/travel-info/edit/" + travelInfo.getIdx();
      }
  }

  @PostMapping("/delete/{idx}")
  public String deleteTravelInfo(@PathVariable("idx") Long idx, 
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
    String loginId = memberService.getLoginId(session);

    int success = travelInfoService.deleteTravelInfoByIdxAndMemberId(idx, loginId);

    if (success > 0) {
      redirectAttributes.addFlashAttribute("msg", "글이 삭제되었습니다.");
      return "redirect:/travel-info/list";
    } else {
      redirectAttributes.addFlashAttribute("msg", "삭제 권한이 없습니다.");
      return "redirect:/travel-info/detail/" + idx;
    }
  }
  
}