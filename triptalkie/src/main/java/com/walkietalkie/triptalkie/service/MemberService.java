package com.walkietalkie.triptalkie.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walkietalkie.triptalkie.domain.Member;
import com.walkietalkie.triptalkie.mapper.MemberMapper;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MemberService {
  private static final Logger logger = LoggerFactory.getLogger(MemberService.class);
  private final BCryptPasswordEncoder passwordEncoder; // 비밀번호 암호화
  private final MemberMapper memberMapper;
  
  public MemberService(BCryptPasswordEncoder passwordEncoder, MemberMapper memberMapper) {
    super();
    this.passwordEncoder = passwordEncoder;
    this.memberMapper = memberMapper;
  }

  public int register(Member member) {
    String hashedPassword = passwordEncoder.encode(member.getPassword());
    member.setPassword(hashedPassword);
    member.setCredit(50.0);  // default credit 처리
    return memberMapper.register(member);
  }
  
  public boolean login(String id, String password, HttpSession session) {
    Member member = memberMapper.findById(id);
    
    boolean active = member.isActive();
    
    System.out.println("active 값 출력 : "+active);
    
    if (member != null && active && passwordEncoder.matches(password, member.getPassword())) {
      session.setAttribute("loginId", member.getId());
      session.setAttribute("loginNickname", member.getNickname());
      logger.info("로그인 성공: {}", member.getId());
      return true;
    }
    logger.warn("로그인 실패: {}", id);
    return false;
  }
  
  public void logout(HttpSession session) {
    // 세션에 저장된 사용자 정보 제거
    //session.removeAttribute("loginId");
    //session.removeAttribute("loginNickname");
    
    session.invalidate();
    // 세션 전체를 초기화하고 싶다면 아래를 사용
    
    logger.info("로그아웃 완료");
  }
  
  
  
  public String getLoginId(HttpSession session) {
    String loginId = (String) session.getAttribute("loginId");
    return loginId;
  }
  
  public String getLoginNickname(HttpSession session) {
    String loginNickname = (String) session.getAttribute("loginNickname");
    return loginNickname;
  }
  
  /**
   * @Transactional(readOnly = true) 
   * 위 설정이 명시된 메서드는 트랜잭션 상에서 데이터 변경을 하지 않을 것임을 알림
   * ==> 읽기 작업에만 집중하게 하여 성능 향상
   * 
   * @param id
   * @return
   */
  @Transactional(readOnly = true)
  public int checkMemberById(String id) {
    if(id == null || id.trim().isEmpty()) {
      throw new IllegalArgumentException("아이디를 입력하세요");
    }
    return memberMapper.checkMemberById(id);
  }
  
  @Transactional(readOnly = true)
  public int checkMemberByNickname(String nickname) {
    if (nickname == null || nickname.trim().isEmpty()) {
      throw new IllegalArgumentException("닉네임을 입력하세요");
    }
    return memberMapper.checkMemberByNickname(nickname);
  }

  @Transactional(readOnly = true)
  public int checkMemberByPhonenumber(String phonenumber) {
    if (phonenumber == null || phonenumber.trim().isEmpty()) {
      throw new IllegalArgumentException("전화번호를 입력하세요");
    }
    return memberMapper.checkMemberByPhonenumber(phonenumber);
  }
  
  @Transactional(readOnly = true)
  public int checkMemberByEmail(String email) {
    if (email == null || email.trim().isEmpty()) {
      throw new IllegalArgumentException("이메일을 입력하세요");
    }
    return memberMapper.checkMemberByEmail(email);
  }

  public Member findMemberById(String memberId) {
	  Member member = memberMapper.findMemberById(memberId);
	  logger.info(memberId);
	if (member == null) {
		throw new IllegalArgumentException("프로필 보기 실패");
	}
	return member;
  }
  
  public boolean withdrawMemberById(String id) {
	  int result = memberMapper.withdrawMemberById(id);
	  return result>0;
  }

}
