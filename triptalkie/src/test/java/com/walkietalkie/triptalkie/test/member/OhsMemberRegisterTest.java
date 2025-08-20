package com.walkietalkie.triptalkie.test.member;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.walkietalkie.triptalkie.domain.Member;
import com.walkietalkie.triptalkie.mapper.MemberMapper;
import com.walkietalkie.triptalkie.service.MemberService;

@SpringBootTest
// @Transactional // 자동 롤백 처리 안함
public class OhsMemberRegisterTest {
  @Autowired
  private MemberService memberService;
  @Autowired
  private MemberMapper memberMapper;
  private static final Logger logger = LoggerFactory.getLogger(OhsMemberRegisterTest.class);

  @Test
  void registerDummyMembers() {
    String logMessage = "5명의 더미 회원 등록을 시작합니다.";
    System.out.println(logMessage);
    logger.info(logMessage);

    // 첫 번째 회원
    Member member1 = new Member();
    member1.setId("user11");
    member1.setNickname("여행가이드");
    member1.setPassword("1234"); // 평문 비밀번호
    member1.setName("김민수");
    member1.setBirth(LocalDate.of(1990, 1, 1));
    member1.setGender("남자");
    member1.setPhonenumber("010-1234-5678");
    member1.setEmail("minsu@example.com");
    member1.setAddress("서울시 강남구");
    member1.setTravelConcept("자유여행");
    int result = memberService.register(member1);
    assertEquals(1, result, "회원가입 성공하면 1");
    String member1Log = String.format("회원 '%s' 등록 완료.", member1.getId());
    System.out.println(member1Log);
    logger.info(member1Log);

    // 두 번째 회원
    Member member2 = new Member();
    member2.setId("user12");
    member2.setNickname("방랑자");
    member2.setPassword("1234");
    member2.setName("박서준");
    member2.setBirth(LocalDate.of(1992, 5, 15));
    member2.setGender("남자");
    member2.setPhonenumber("010-9876-5432");
    member2.setEmail("seojun@example.com");
    member2.setAddress("경기도 성남시");
    member2.setTravelConcept("배낭여행");
    int result2 = memberService.register(member2);
    assertEquals(1, result2, "회원가입 성공하면 1");
    String member2Log = String.format("회원 '%s' 등록 완료.", member2.getId());
    System.out.println(member2Log);
    logger.info(member2Log);

    // 세 번째 회원
    Member member3 = new Member();
    member3.setId("user13");
    member3.setNickname("탐험가");
    member3.setPassword("1234");
    member3.setName("이유진");
    member3.setBirth(LocalDate.of(1995, 11, 20));
    member3.setGender("남자");
    member3.setPhonenumber("010-1111-2222");
    member3.setEmail("yujin@example.com");
    member3.setAddress("부산광역시 해운대구");
    member3.setTravelConcept("힐링여행");
    int result3 = memberService.register(member3);
    assertEquals(1, result3, "회원가입 성공하면 1");
    String member3Log = String.format("회원 '%s' 등록 완료.", member3.getId());
    System.out.println(member3Log);
    logger.info(member3Log);

    // 네 번째 회원
    Member member4 = new Member();
    member4.setId("user14");
    member4.setNickname("떠돌이");
    member4.setPassword("1234");
    member4.setName("최민지");
    member4.setBirth(LocalDate.of(1998, 8, 30));
    member4.setGender("남자");
    member4.setPhonenumber("010-3333-4444");
    member4.setEmail("minji@example.com");
    member4.setAddress("대구광역시 수성구");
    member4.setTravelConcept("미식여행");
    int result4 = memberService.register(member4);
    assertEquals(1, result4, "회원가입 성공하면 1");
    String member4Log = String.format("회원 '%s' 등록 완료.", member4.getId());
    System.out.println(member4Log);
    logger.info(member4Log);

    // 다섯 번째 회원
    Member member5 = new Member();
    member5.setId("user15");
    member5.setNickname("배낭여행객");
    member5.setPassword("1234");
    member5.setName("정우성");
    member5.setBirth(LocalDate.of(2000, 3, 10));
    member5.setGender("남자");
    member5.setPhonenumber("010-5555-6666");
    member5.setEmail("woosung@example.com");
    member5.setAddress("대전광역시 유성구");
    member5.setTravelConcept("자유여행");
    int result5 = memberService.register(member5);
    assertEquals(1, result5, "회원가입 성공하면 1");
    String member5Log = String.format("회원 '%s' 등록 완료.", member5.getId());
    System.out.println(member5Log);
    logger.info(member5Log);

    String allDoneLog = "모든 더미 회원 등록이 완료되었습니다.";
    System.out.println(allDoneLog);
    logger.info(allDoneLog);
  }
}