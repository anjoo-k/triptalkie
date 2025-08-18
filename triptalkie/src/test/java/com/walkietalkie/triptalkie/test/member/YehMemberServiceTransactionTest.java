package com.walkietalkie.triptalkie.test.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.walkietalkie.triptalkie.service.MemberService;

@SpringBootTest
public class YehMemberServiceTransactionTest {
	@Autowired
	private MemberService memberService;

}
