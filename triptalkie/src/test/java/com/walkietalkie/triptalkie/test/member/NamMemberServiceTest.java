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
			    "java",              // id
			    "자바킹",              // nickname
			    "1234",              // password
			    "자바천재",            // name
			    LocalDate.of(2022, 1, 1), // birth
			    "male",              // gender
			    "010-1234-5678",     // phonenumber
			    "email@email.com",   // email
			    "서울",               // address
			    "맛집/카페",           // travelConcept
			    50.0                 // credit
			);
		
		int result = memberService.register(member);
		assertEquals(1, result, "회원가입 성공하면 1");
	}

}
