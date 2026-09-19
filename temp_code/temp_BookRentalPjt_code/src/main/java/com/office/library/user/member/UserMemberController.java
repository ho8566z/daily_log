package com.office.library.user.member;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user/member")
@RequiredArgsConstructor
public class UserMemberController {
	
	final private String CLASS_NAME = "[UserMemberController] ";
	
	final private UserMemberService userMemberService;
	
	/*
	@Autowired
	public UserMemberController(UserMemberService userMemberService) {
		this.userMemberService = userMemberService;
	}
	*/
	
	/*
	 * 사용자 가입 양식
	 * /user/member/createAccountForm
	 */
	@GetMapping("/createAccountForm")
	public String createAccountForm() {
		System.out.println(CLASS_NAME.concat("createAccountForm()"));
		
		String nextPage = "user/member/create_account_form";
		
		return nextPage;
		
	}

	/*
	 * 사용자 가입 확인
	 * /user/member/createAccountConfirm
	 */
	@PostMapping("/createAccountConfirm")
	public String createAccountConfirm(UserMemberDto userMemberDto) {
		System.out.println(CLASS_NAME.concat("createAccountConfirm()"));
		
		String nextPage = "user/member/create_account_ok";
		
		int result = userMemberService.createAccountConfirm(userMemberDto);
		
		if (result <= 0) {
			nextPage = "user/member/create_account_ng";
		}
		
		return nextPage;	
	}
	
	/*
	 * 사용자 회원 로그인 양식
	 * /user/member/loginForm
	 */
	@GetMapping("/loginForm")
	public String loginForm() {
		System.out.println(CLASS_NAME.concat("loginForm()"));
		
		String nextPage = "user/member/login_form";
		
		return nextPage;
		
	}
	
	/*
	 * 사용자 로그인 확인
	 * /user/member/loginConfirm
	 */
	@PostMapping("/loginConfirm")
	public String loginConfirm(UserMemberDto userMemberDto, HttpSession session) {
		System.out.println(CLASS_NAME.concat("loginConfirm()"));
		
		String nextPage = "user/member/login_ok";
		
		String loginedUserMemberId = userMemberService.loginConfirm(userMemberDto);
		
		if (loginedUserMemberId != null) {
			session.setAttribute("loginedUserMemberId", loginedUserMemberId);
			session.setMaxInactiveInterval(60 * 30);
			
		} else {
			nextPage = "user/member/login_ng";
			
		}
		
		return nextPage;
		
	}
	
	/*
	 * 사용자 로그 아웃 화인
	 * /user/member/logoutConfirm
	 */
	@GetMapping("/logoutConfirm")
	public String logoutConfirm(HttpSession session) {
		System.out.println(CLASS_NAME.concat("logoutConfirm()"));
		
		String nextPage = "redirect:/";
		
//		session.removeAttribute("loginedUserMemberId");
		session.invalidate();	// 무효화
		
		return nextPage;
		
	}
	
	/*
	 * 사용자 회원 계정 수정 양식
	 * /user/member/modifyAccountForm
	 */
	@GetMapping("/modifyAccountForm")
	public String modifyAccountForm(HttpSession session, Model model) {
		System.out.println(CLASS_NAME.concat("modifyAccountForm()"));
		
		String nextPage = "user/member/modify_account_form";
		
		/*
		String loginedUserMemberId;
		Object object = session.getAttribute("loginedUserMemberId");
		if (object != null) {
			loginedUserMemberId = String.valueOf(object);
			
		} else {
			return "redirect:/user/member/loginForm";
			
		}
		*/
		
		String loginedUserMemberId = String.valueOf(session.getAttribute("loginedUserMemberId"));
		
		UserMemberDto loginedUserMemberDto =
				userMemberService.modifyAccountForm(loginedUserMemberId);
		model.addAttribute("loginedUserMemberDto", loginedUserMemberDto);
		
		return nextPage;
		
	}
	
	/*
	 * 사용자 회원 정보 수정 확인
	 * /user/member/modifyAccountConfirm
	 */
	@PostMapping("/modifyAccountConfirm")
	public String modifyAccountConfirm(UserMemberDto userMemberDto) {
		System.out.println(CLASS_NAME.concat("modifyAccountConfirm()"));
		
		String nextpage = "user/member/modify_account_ok";
		
		int result = userMemberService.modifyAccountConfirm(userMemberDto);
		if (result <= 0) {
			nextpage = "user/member/modify_account_ng";
		}
		
		return nextpage;
		
	}
	
	// ==========================================================================
	// ==========================================================================
	/*
	 * 비밀번호 찾기
	 * /user/member/findPassword
	 */
	@GetMapping("/findPassword")
	public String findPassword() {
		System.out.println(CLASS_NAME.concat("findPassword()"));
		
		String nextPage = "user/member/find_password_form";
		
		return nextPage;
		
	}
	
	/*
	 * 비밀번호 찾기 확인
	 * /user/member/findPasswordConfirm
	 */
	@PostMapping("/findPasswordConfirm")
	public String findPasswordConfirm(UserMemberDto userMemberDto) {
		System.out.println(CLASS_NAME.concat("findPasswordConfirm()"));
		
		String nextPage = "User/member/find_password_ok";
		
		int result = userMemberService.findPasswordConfirm(userMemberDto);
		
		if (result <= 0) {
			nextPage = "user/member/find_password_ng";
		}
		
		return nextPage;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
