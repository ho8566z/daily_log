package com.office.toypjt.member;

import java.io.IOException;

import com.office.toypjt.ToyPjtConfig;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("*.mem")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private String CLASS_NAME = "[MemberController] ";
   
    public MemberController() {
        super();
       
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		StringBuffer stringBuffer = request.getRequestURL();
		System.out.println("stringBuffer: " + stringBuffer.toString());
		
		String requestURI = request.getRequestURI();
		System.out.println("requestURI: " + requestURI);
		
		String contextPath = request.getContextPath();
		System.out.println("contextPath: " + contextPath);
		
		String command = requestURI.substring(contextPath.length());
		System.out.println("command: " + command);
		
		String nextPage = null;
		MemberService memberService = null;
		
//		request.getSession().setAttribute(MemberConfig.SINGINED_MEMBERID, "gildong");
		
		switch (command) {
			
			case MemberConfig.MEMBER_SIGNUP_FORM:
				// sign_up_form
				break;
			
			case MemberConfig.MEMBER_SIGNUP_CONFIRM:
				// sign_up_confirm
				break;
			
			case MemberConfig.MEMBER_SIGNIN_FORM:
				// sign_in_form
				break;
			
			case MemberConfig.MEMBER_SIGNIN_CONFIRM:
				// sign_in_confirm
				break;
			
			case MemberConfig.MEMBER_MODIFY_FORM:
				System.out.println(CLASS_NAME.concat(MemberConfig.MEMBER_MODIFY_FORM));
				
				// modify_form
				memberService = new MemberService(new MemberDao());
				
				HttpSession session = request.getSession();
				String signinedMemId = String.valueOf(session.getAttribute(MemberConfig.SINGINED_MEMBERID));
				
				MemberDto currentSigninedMember = memberService.getCurrentSigninedMember(signinedMemId);
				request.setAttribute("currentSigninedMember", currentSigninedMember);
				
				nextPage = generateViewName("/member_modify_form");
				
				break;
			
			case MemberConfig.MEMBER_MODIFY_CONFIRM:
				System.out.println(CLASS_NAME.concat(MemberConfig.MEMBER_MODIFY_CONFIRM));
			
				// modify_confirm
				memberService = new MemberService(new MemberDao());
				int resultForModify = memberService.modifyMemberByMemNo(request, response);
				
				if (resultForModify > 0) {
					System.out.println(CLASS_NAME.concat("MEMBER MODIFY SUCCESS"));
					nextPage = generateViewName("/member_modify_ok");
				
				} else {
					System.out.println(CLASS_NAME.concat("MEMBER MODIFY FAIL"));
					nextPage = generateViewName("/member_modify_ng");
					
				}
				
				break;
			
			case MemberConfig.MEMBER_DELETE:
				System.out.println(CLASS_NAME.concat(MemberConfig.MEMBER_DELETE));
				
				// delete
				memberService = new MemberService(new MemberDao());
				int resultForDelete = memberService.removeMemberByMemId(request, response);
				
				if (resultForDelete > 0) {
					System.out.println(CLASS_NAME.concat("MEMBER DELETE SUCCESS"));
					
					request.getSession().invalidate();
					
				} else {
					System.out.println(CLASS_NAME.concat("MEMBER DELETE FAIL"));
					
				}
				
				response.sendRedirect(request.getContextPath().concat(ToyPjtConfig.HOME));
				
				return;
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
	
	// 뷰 이름 만들기
	private String generateViewName(String viewName) {
		
		return ToyPjtConfig.DEFAULT_VIEW_PATH
				.concat(viewName)
				.concat(ToyPjtConfig.DEFAULT_VIEW_SUFFIX);
	}

}
