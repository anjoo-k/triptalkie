package com.walkietalkie.triptalkie.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.walkietalkie.triptalkie.domain.Member;

@Mapper
public interface MemberMapper {

  int register(Member member);

  int checkMemberById(String id);

  int checkMemberByNickname(String nickname);

  int checkMemberByPhonenumber(String phonenumber);

  int checkMemberByEmail(String email);
  
  Member findById(String id);
}
