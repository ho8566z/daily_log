package com.office.toypjt.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MemberService {
	
	final private String CLASS_NAME = "[MemberService] ";
	
	private IMemberDao memberDao;
	
	public MemberService(IMemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	
	public MemberDto getCurrentSigninedMember(String signinedMemId) {
		System.out.println(CLASS_NAME.concat("getCurrentSigninedMember()"));
		
		MemberDto signinedMemberDto = memberDao.selectMemberByMemId(signinedMemId);
		
		return signinedMemberDto;
	}


	public int modifyMemberByMemNo(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(CLASS_NAME.concat("modifyMemberByMemNo()"));
		
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		
		HttpSession session = request.getSession();
		Object object = session.getAttribute(MemberConfig.SINGINED_MEMBERID);
		String memId = String.valueOf(object);
		
		String memPw = request.getParameter("memPw");
		String memMail = request.getParameter("memMail");
		String memPhone = request.getParameter("memPhone");
		
		MemberDto memberDto = new MemberDto(memNo, memId, memPw, memMail, memPhone, null, null);
		int result = memberDao.updateMemberByMemNo(memberDto);
		
		if (result > 0) {
			System.out.println(CLASS_NAME.concat("MEMBER MODIFY SUCCESS"));
		
		} else {
			System.out.println(CLASS_NAME.concat("MEMBER MODIFY FAIL"));
			
		}
		
		return result;
	}


	public int removeMemberByMemId(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(CLASS_NAME.concat("removeMemberByMemId()"));
		
		String memId = request.getParameter("memId");
		
		int result = memberDao.deleteMemberByMemId(memId);
		
		if (result > 0) {
			System.out.println(CLASS_NAME.concat("MEMBER DELETE SUCCESS"));
			
		} else {
			System.out.println(CLASS_NAME.concat("MEMBER DELETE FAIL"));
		
		}
		
		return result;
	}

}
