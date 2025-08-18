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

    if (member != null && passwordEncoder.matches(password, member.getPassword())) {
      session.setAttribute("MemberId", member.getId());
      session.setAttribute("MemberNickname", member.getNickname());
      logger.info("로그인 성공: {}", member.getId());
      return true;
    }
    logger.warn("로그인 실패: {}", id);
    return false;
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

}
