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

//Spring JUnit Test 설정 
@SpringBootTest
//테스트 후 자동 롤백 설정 
@Transactional
public class NamMemberServiceTest {
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberMapper memberMapper;
//	@Autowired
//	private Logger logger = LoggerFactory.getLogger(NamMemberServiceTest.class);
	
	@Test
	void testMemberRegister() {
		Member member = new Member(
			    "java",
			    "자바킹",
			    "1234",
			    "자바천재",
			    LocalDate.of(2022, 1, 1),
			    "010-1234-5678",
			    "email@email.com",
			    "서울",
			    "맛집/카페",
			    50.0
			);
		
		int result = memberService.register(member);
		assertEquals(1, result, "회원가입 성공하면 1");
	}

}
