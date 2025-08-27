package com.walkietalkie.triptalkie.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.walkietalkie.triptalkie.DTO.MyPostsDTO;
import com.walkietalkie.triptalkie.DTO.SearchCriteria;
import com.walkietalkie.triptalkie.domain.Member;
import com.walkietalkie.triptalkie.domain.MemberImage;
import com.walkietalkie.triptalkie.service.MakemateService;
import com.walkietalkie.triptalkie.service.MemberImageService;
import com.walkietalkie.triptalkie.service.MemberService;
import com.walkietalkie.triptalkie.service.MypageService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/mypage")
public class MypageController {

	private final MypageService mypageService;
	private final MemberImageService memberImageService;
	private final MemberService memberService;
	private final MakemateService makemateService;

	public MypageController(MypageService mypageService, MemberImageService memberImageService, MakemateService makemateService
			, MemberService memberService) {
		super();
		this.mypageService = mypageService;
		this.memberImageService = memberImageService;
		this.memberService = memberService;
		this.makemateService = makemateService;
	}

	// 마이페이지 화면
	@GetMapping("/myinformation")
	public String mypage(HttpSession session, Model model) {
		String id = (String) session.getAttribute("loginId");
		if (id == null)
			return "redirect:/member/loginPage";

		Member member = mypageService.findMemberById(id);

		// 등록된 이미지 URL 가져오기
		String profileImageUrl = memberImageService.getImageUrlByMemberId(id);

		model.addAttribute("member", member);
		model.addAttribute("profileImageUrl", profileImageUrl);
		// model 객체를 사용해서 profileImageUrl 값 저장, 뷰로 전달된다.

		return "pages/mypage/myinformation";
	}

	// 내 정보 수정 화면
	@GetMapping("/update-myinfo")
	public String myinfoupdatePageTest(HttpSession session, Model model) {
		String id = (String) session.getAttribute("loginId");
		if (id == null)
			return "redirect:/member/loginPage";

		Member member = mypageService.findMemberById(id);

		// 등록된 이미지 URL 가져오기
		String profileImageUrl = memberImageService.getImageUrlByMemberId(id);

		model.addAttribute("member", member);
		model.addAttribute("profileImageUrl", profileImageUrl);
		// model 객체를 사용해서 profileImageUrl 값 저장, 뷰로 전달된다.
		return "pages/mypage/update-myinfo";
	}

	// 내정보 수정 처리
	@PostMapping("/update-myinfo")
	public String myinfoupdateTest(HttpSession session, Member member) {
		String id = (String) session.getAttribute("loginId");
		if (id == null)
			return "redirect:/member/loginPage";

		member.setId(id);
		mypageService.updateMemberById(member);
		return "redirect:/mypage/myinformation";
	}

	// 비밀번호 체크 화면
	@GetMapping("/password-check")
	public String checkPasswordPage() {
		return "pages/mypage/password-check";
	}

	// 비밀번호 체크 처리
	@PostMapping("/password-check")
	public String checkPassword(HttpSession session, String password, RedirectAttributes ra) throws Exception {
		String id = (String) session.getAttribute("loginId");
		if (id == null)
			return "redirect:/member/loginPage";

		Boolean result = mypageService.checkPassword(id, password);
		if (result) {
			return "redirect:/mypage/update-myinfo";
		} else {
			ra.addFlashAttribute("error", true);
			return "redirect:/mypage/password-check";
		}
	}

	@PostMapping("/updateProfileImage")
	public String updateProfileImage(@RequestParam("profileImage") MultipartFile profileImage, Model model,
			HttpSession session) throws IOException {

		// 1. 업로드 파일이 없으면 아무 작업도 하지 않고 마이페이지로 리다이렉트
		if (profileImage == null || profileImage.isEmpty()) {
			return "redirect:/mypage/myinformation";
		}

		String loginId = memberService.getLoginId(session);

		// 1. 기존 이미지 삭제
		memberImageService.deleteImageByMemberId(loginId);

		// 2. 새 이미지 업로드
		MemberImage newImage = memberImageService.uploadImage(profileImage, loginId);

		// 새 이미지 URL 조회
		String profileImageUrl = memberImageService.getImageUrlByMemberId(loginId);

		// 뷰에 전달
		session.setAttribute("profileImageUrl", profileImageUrl);

		return "redirect:/mypage/myinformation";
	}
	

	// 내 여행 리스트
	@GetMapping("/my-travel-list")
	public String myTravelList(@RequestParam(defaultValue = "1") int page, HttpSession session,
			Model model) {
		String loginId = (String) session.getAttribute("loginId");
		if (loginId == null)
			return "redirect:/member/loginPage";
		
		int size = 4; // 한 페이지에 보이는 글 수
		Map<String, Object> result = mypageService.findMakematesAllListByMemberId(page, size, loginId);

		model.addAttribute("page", result.get("commonPage"));
		model.addAttribute("combinedList", result.get("combinedList"));
		return "pages/mypage/my-travel-list";
	}

	@GetMapping("/my-posts")
	public String myPostPage(Model model, HttpSession session) {
		String loginMember = (String) session.getAttribute("loginId");
		System.out.println(loginMember);
		// 세션이 비어있을 경우 에러 처리 추가
		if (loginMember == null) {
			return "redirect:/member/loginPage";
		}

		MyPostsDTO myPosts = mypageService.findMyPosts(loginMember);

		if (myPosts.getCommunityList() == null) {
			myPosts.setCommunityList(new ArrayList<>());
		}
		if (myPosts.getTravelInfoList() == null) {
			myPosts.setTravelInfoList(new ArrayList<>());
		}
		if (myPosts.getTravelReviewList() == null) {
			myPosts.setTravelReviewList(new ArrayList<>());
		}

		System.out.println("myPosts" + myPosts);
		model.addAttribute("myPosts", myPosts);

		return "pages/mypage/my-posts";
	}

	// 비밀번호 체크 화면
	@GetMapping("/password-check-withdraw")
	public String checkPasswordPageWithdraw() {
		return "pages/mypage/password-check-withdraw";
		// 비밀번호 췍해서 맞으면 회원 삭제 수행
	}

	// 회원 삭제 로직 처리
	@PostMapping("/password-check-withdraw")
	public String checkPasswordPageWithdraw(HttpSession session, String password, RedirectAttributes ra)
			throws Exception {
		String loginId = (String) session.getAttribute("loginId");
		if (loginId == null) {
			ra.addFlashAttribute("error", "로그인이 필요합니다.");
			return "redirect:/member/loginPage";
		}

		boolean passwordValid = mypageService.checkPassword(loginId, password);
		// id와 패스워드를 확인

		if (!passwordValid) {
			ra.addFlashAttribute("error", "비밀번호가 올바르지 않습니다.");
			return "redirect:/mypage/password-check-withdraw";
		}

		// 비밀번호가 맞으면 바로 회원 탈퇴 수행
		boolean success = memberService.withdrawMemberById(loginId);
		if (success) {
			session.invalidate(); // 세션 종료
			ra.addFlashAttribute("message", "회원 탈퇴가 완료되었습니다.");
			return "redirect:/"; // 홈으로 이동
		} else {
			ra.addFlashAttribute("error", "회원 탈퇴 처리 중 오류가 발생했습니다.");
			return "redirect:/mypage";
		}
	}

//	@PostMapping("/password-check-withdraw")
//	public String checkPasswordPageWithdraw2(HttpSession session, String password, RedirectAttributes ra)
//			throws Exception {
//		String id = (String) session.getAttribute("loginId");
//		if (id == null)
//			return "redirect:/member/loginPage";
//
//		Boolean result = mypageService.checkPassword(id, password);
//		// id와 패스워드를 확인
//
//		System.out.println("실행 체크 1");
//		if (result) {
//			return "redirect:/member/deactivate";
//		} else {
//			ra.addFlashAttribute("error", true);
//			return "redirect:/mypage/password-check-withdraw";
//		}
//	}
}
