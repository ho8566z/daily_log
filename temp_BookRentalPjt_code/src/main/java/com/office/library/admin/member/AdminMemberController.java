package com.office.library.admin.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/member")
public class AdminMemberController {

	final private String CLASS_NAME = "[AdminMemberController] ";
	
	final private AdminMemberService adminMemberService;
	
	public AdminMemberController(AdminMemberService adminMemberService) {
		this.adminMemberService = adminMemberService;
		
	}
	
	/*
	 * 회원(관리자) 가입 양식
	 */
//	@RequestMapping(value = "/createAccountForm", method = RequestMethod.GET)
//	@RequestMapping(value = "/createAccountForm")
//	@RequestMapping("/createAccountForm")
	@GetMapping("/createAccountForm")
	public String createAccountForm() {
		System.out.println(CLASS_NAME.concat("createAccountForm()"));
		
		String nextPage = "admin/member/create_account_form";
		
		return nextPage;
		
	}
	
	/*
	 * 회원(관리자) 가입 확인
	 */
//	@RequestMapping(value = "/createAccountConfirm", method = RequestMethod.POST)
	@PostMapping("/createAccountConfirm")
	public String createAccountConfirm(AdminMemberDto adminMemberDto) {
		System.out.println(CLASS_NAME.concat("createAccountConfirm()"));
		
		String nextPage = "admin/member/create_account_ok";
		
		int result = adminMemberService.createAccountConfirm(adminMemberDto);
		if (result <= AdminMemberService.ADMIN_ACCOUNT_ALREADY_EXIST) 
			nextPage = "admin/member/create_account_ng";
		
		return nextPage;
		
	}
	
	/*
	 * 회원(관리자) 로그인 양식  (/admin/member/loginForm)
	 */
	@GetMapping("/loginForm")
	public String loginForm() {
		System.out.println(CLASS_NAME.concat("loginForm()"));
		
		String nextPage = "admin/member/login_form";
		
		return nextPage;
		
	}
	
	
	/*
	 * 회원(관리자) 로그인 확인  (/admin/member/loginConfirm)
	 */
	@PostMapping("/loginConfirm")
	public String loginConfirm(AdminMemberDto adminMemberDto, HttpSession session) {
		System.out.println(CLASS_NAME.concat("loginConfirm()"));
		
		String nextPage = "admin/member/login_ok";
		
		String loginedAdminMemberId = adminMemberService.loginConfirm(adminMemberDto);
		if (loginedAdminMemberId == null) {
			nextPage = "admin/member/login_ng";
			
		} else {
			session.setAttribute("loginedAdminMemberId", loginedAdminMemberId);
			session.setMaxInactiveInterval(60 * 30);
			
		}
		
		return nextPage;
		
	}
	
	/*
	 * 관리자 로그 아웃 화인
	 */
	@GetMapping("/logoutConfirm")
	public String logoutConfirm(HttpSession session) {
		System.out.println(CLASS_NAME.concat("logoutConfirm()"));
		
		String nextPage = "redirect:/admin";
		
//		session.removeAttribute("loginedAdminMemberId");
		session.invalidate();	// 무효화
		
		return nextPage;
		
	}
	
	/*
	 * 관리자 목록 (/admin/member/listupAdmin)
	 */
	/*
	@GetMapping("/listupAdmin")
	public String listupAdmin(Model model) {
		System.out.println(CLASS_NAME.concat("listupAdmin()"));
		
		String nextPage = "admin/member/listup_admins";
		
		List<AdminMemberDto> adminMemberDtos = adminMemberService.listupAdmin();
		
		model.addAttribute("adminMemberDtos", adminMemberDtos);
		
		return nextPage;
		
	}
	*/
	
	// by ModelAndView = view + model
	@GetMapping("/listupAdmin")
	public ModelAndView listupAdmin(HttpSession session) {
		System.out.println(CLASS_NAME.concat("listupAdmin()"));
		
		String nextPage = "admin/member/listup_admins";
		
		Object object = session.getAttribute("loginedAdminMemberId");
		if (object == null) {
			
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("redirect:/admin/member/loginForm");
			
			return modelAndView;
		}
		
		List<AdminMemberDto> adminMemberDtos = adminMemberService.listupAdmin();
		
		ModelAndView modelAndView = new ModelAndView();
		// 데이터 주입
		modelAndView.addObject("adminMemberDtos", adminMemberDtos);
		// 뷰 설정
		modelAndView.setViewName(nextPage);
		
		return modelAndView;
		
	}
	
	// /admin/member/setAdminApproval
	@GetMapping("/setAdminApproval")
	public String setAdminApproval(
			@RequestParam("a_m_no") int a_m_no, 
			HttpSession session) {
		System.out.println(CLASS_NAME.concat("setAdminApproval()"));
		
		Object object = session.getAttribute("loginedAdminMemberId");
		if (object == null) {
			return "redirect:/admin/member/loginForm";
		}
		
		String nextPage = "redirect:/admin/member/listupAdmin";
		
		adminMemberService.setAdminApproval(a_m_no);
		
		return nextPage;
		
	}
	
	/*
	 * 관리자 회원 정보 수정 양식
	 * /modifyAccountForm
	 */
	@GetMapping("/modifyAccountForm")
	public String modifyAccountForm(HttpSession session, Model model) {
		System.out.println(CLASS_NAME.concat("modifyAccountForm()"));
		
		if (session.getAttribute("loginedAdminMemberId") == null) {
			return "redirect:/admin/member/loginForm";
		}
		
		String nextPage = "admin/member/modify_account_form";
		
		Object object = session.getAttribute("loginedAdminMemberId");
		AdminMemberDto adminMemberDto = adminMemberService.modifyAccountForm(String.valueOf(object));
		
		model.addAttribute("adminMemberDto", adminMemberDto);
		
		return nextPage;
		
	}
	
	/*
	 * 관리자 회원 정보 수정 확인
	 * /admin/member/modifyAccountConfirm
	 */
	@PostMapping("/modifyAccountConfirm")
	public String modifyAccountConfirm(AdminMemberDto adminMemberDto) {
		System.out.println(CLASS_NAME.concat("modifyAccountConfirm()"));
		
		String nextpage = "admin/member/modify_account_ok";
		
		int result = adminMemberService.modifyAccountConfirm(adminMemberDto);
		if (result <= 0) {
			nextpage = "admin/member/modify_account_ng";
		}
		
		return nextpage;
		
	}
	
	/*
	 * 비밀번호 찾기
	 * /admin/member/findPassword
	 */
	@GetMapping("/findPassword")
	public String findPassword() {
		System.out.println(CLASS_NAME.concat("findPassword()"));
		
		String nextPage = "admin/member/find_password_form";
		
		return nextPage;
		
	}
	
	/*
	 * 비밀번호 찾기 확인
	 * /admin/member/findPasswordConfirm
	 */
	@PostMapping("/findPasswordConfirm")
	public String findPasswordConfirm(AdminMemberDto adminMemberDto) {
		System.out.println(CLASS_NAME.concat("findPasswordConfirm()"));
		
		String nextPage = "admin/member/find_password_ok";
		
		int result = adminMemberService.findPasswordConfirm(adminMemberDto);
		
		if (result <= 0) {
			nextPage = "admin/member/find_password_ng";
		}
		
		return nextPage;
		
	}
	
}








