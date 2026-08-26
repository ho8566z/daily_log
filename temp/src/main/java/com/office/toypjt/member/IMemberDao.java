package com.office.toypjt.member;

public interface IMemberDao {
	
//	public int insertNewMember(MemberDto memberDto);

	public MemberDto selectMemberByMemId(String memId);

	public int updateMemberByMemNo(MemberDto memberDto);

	public int deleteMemberByMemId(String memId);

}
