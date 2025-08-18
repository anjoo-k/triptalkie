package com.walkietalkie.triptalkie.test.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.walkietalkie.triptalkie.mapper.MemberMapper;

@SpringBootTest
@Transactional
public class YehMemberMapperTest {
	@Autowired
	private MemberMapper memberMapper;
	

}
