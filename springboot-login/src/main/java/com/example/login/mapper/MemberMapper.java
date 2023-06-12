package com.example.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.login.model.Member;

@Mapper
public interface MemberMapper {

	// 회원가입
	public int joinMember(Member member);
	// 회원정보 조회(아이디)
	Member findMemberById(String id);

}
