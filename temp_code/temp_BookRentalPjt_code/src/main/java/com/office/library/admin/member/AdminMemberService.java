package com.office.library.admin.member;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminMemberService {

	final private String CLASS_NAME = "[AdminMemberService] ";
	
	final static public int ADMIN_ACCOUNT_ALREADY_EXIST 	= 0;
	final static public int ADMIN_ACCOUNT_CREATE_SUCCESS 	= 1;
	final static public int ADMIN_ACCOUNT_CREATE_FAIL 		= -1;
	
	final private AdminMemberDao adminMemberDao;
	final private PasswordEncoder passwordEncoder;
	final private JavaMailSenderImpl javaMailSenderImpl;
	
	public AdminMemberService(
			AdminMemberDao adminMemberDao, 
			PasswordEncoder passwordEncoder, 
			JavaMailSenderImpl javaMailSenderImpl) {
		this.adminMemberDao = adminMemberDao;
		this.passwordEncoder = passwordEncoder;
		this.javaMailSenderImpl = javaMailSenderImpl;
		
	}

	public int createAccountConfirm(AdminMemberDto adminMemberDto) {
		System.out.println(CLASS_NAME.concat("createAccountConfirm()"));
		
		// 1. 아이디 중복 체크
		boolean isMember = adminMemberDao.isAdminMember(adminMemberDto.getA_m_id());
		System.out.println(CLASS_NAME.concat("isMember: " + isMember));
		
		// 2. 관리자 회원 가입
		if (!isMember) {
			
			// 비밀번호를 암호화 처리
			String encodedPassword = passwordEncoder.encode(adminMemberDto.getA_m_pw());
			adminMemberDto.setA_m_pw(encodedPassword);
			
			int result = adminMemberDao.insertAdminAccount(adminMemberDto);
			
			if (result > 0) {
				System.out.println(CLASS_NAME.concat("ADMIN NEW MEMBER CREATE ACCOUNT SUCCESS!!"));
				return ADMIN_ACCOUNT_CREATE_SUCCESS;
				
			} else {
				System.out.println(CLASS_NAME.concat("ADMIN NEW MEMBER CREATE ACCOUNT FAIL!!"));
				return ADMIN_ACCOUNT_CREATE_FAIL;
				
			}
			
		} else {
			System.out.println(CLASS_NAME.concat("ADMIN NEW MEMBER CREATE ACCOUNT FAIL!!"));
			return ADMIN_ACCOUNT_ALREADY_EXIST;
			
		}
		
	}

	public String loginConfirm(AdminMemberDto adminMemberDto) {			// 1234  == 1234
		System.out.println(CLASS_NAME.concat("loginConfirm()"));
		
		AdminMemberDto selectedAdminMemberDto =
				adminMemberDao.selectAdmin(adminMemberDto.getA_m_id());
		
		if (selectedAdminMemberDto != null) {
			
			if(passwordEncoder.matches(adminMemberDto.getA_m_pw(), selectedAdminMemberDto.getA_m_pw())) {
				System.out.println(CLASS_NAME.concat("ADMIN LOGIN SUCCESS!!"));
				
				return selectedAdminMemberDto.getA_m_id();
				
			} else {
				System.out.println(CLASS_NAME.concat("ADMIN LOGIN FAIL!!"));
				
				return null;
				
			}
			
		} else {
			System.out.println(CLASS_NAME.concat("ADMIN LOGIN FAIL!!"));
			
			return null;
			
		}
		
	}

	public List<AdminMemberDto> listupAdmin() {
		System.out.println(CLASS_NAME.concat("listupAdmin()"));
		
		return adminMemberDao.selectAdmins();
		
	}

	public void setAdminApproval(int a_m_no) {
		System.out.println(CLASS_NAME.concat("setAdminApproval()"));
		
		int result = adminMemberDao.updateAdminApproval(a_m_no);
		
		if (result > 0) {
			System.out.println(CLASS_NAME.concat("ADMIN APPROVAL UPDATE SUCCESS!!"));
			
		} else {
			System.out.println(CLASS_NAME.concat("ADMIN APPROVAL UPDATE FAIL!!"));
			
		}
		
	}

	public AdminMemberDto modifyAccountForm(String a_m_id) {
		System.out.println(CLASS_NAME.concat("modifyAccountForm()"));
		
		return adminMemberDao.selectAdmin(a_m_id);
		
	}
	
	public int modifyAccountConfirm(AdminMemberDto adminMemberDto) {
		System.out.println(CLASS_NAME.concat("modifyAccountConfirm()"));
		
		return adminMemberDao.updateAdminAccount(adminMemberDto);
		
	}

	public int findPasswordConfirm(AdminMemberDto adminMemberDto) {
		System.out.println(CLASS_NAME.concat("findPasswordConfirm()"));
		
		// 1. 인증
		AdminMemberDto selectedAdminMemberDto = 
				adminMemberDao.selectAdmin(
								adminMemberDto.getA_m_id(), 
								adminMemberDto.getA_m_name(), 
								adminMemberDto.getA_m_mail());
		
		int result = 0;
		
		if (selectedAdminMemberDto != null) {
			// 2. 새로운 비밀번호 생성
			String newPassword = createNewPassword();
			
			String encodedNewPassword = passwordEncoder.encode(newPassword);
			// 3. db update
			result = adminMemberDao.updatePassword(adminMemberDto.getA_m_id(), encodedNewPassword);
			
			// 4. 사용자 한테 메일 발송
			if (result > 0) {
				sendNewPasswordByMail(adminMemberDto.getA_m_mail(), newPassword);
				
			}
			
		}
		
		return result;
		
	}
	
	private String createNewPassword() {
		System.out.println(CLASS_NAME.concat("createNewPassword()"));
		
		char[] chars = new char[] {
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
				'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 
				'u', 'v', 'w', 'x', 'y', 'z'
				};
	
		StringBuffer stringBuffer = new StringBuffer();
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.setSeed(new Date().getTime());
		
		int index = 0;
		int length = chars.length;
		for (int i = 0; i < 8; i++) {                   // q2S1Ji8u
			index = secureRandom.nextInt(length);		// 2   -> 2, c -> C
		
			if (index % 2 == 0) 
				stringBuffer.append(String.valueOf(chars[index]).toUpperCase());
			else
				stringBuffer.append(String.valueOf(chars[index]).toLowerCase());
		
		}
		
		System.out.println(CLASS_NAME.concat("NEW PASSWORD: " + stringBuffer.toString()));
		
		return stringBuffer.toString();
		
	}
	
	private void sendNewPasswordByMail(String toMailAddr, String newPassword) {
		System.out.println(CLASS_NAME.concat("sendNewPasswordByMail()"));
		
		final MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				mimeMessageHelper.setTo("nikecafe@naver.com");
//				mimeMessageHelper.setTo(toMailAddr);
				mimeMessageHelper.setSubject("[DW Academy] 새 비밀번호 안내입니다.");
				mimeMessageHelper.setText("새 비밀번호 : " + newPassword, true);
				
			}
			
		};
		javaMailSenderImpl.send(mimeMessagePreparator);	
		
	}
	
	

}
