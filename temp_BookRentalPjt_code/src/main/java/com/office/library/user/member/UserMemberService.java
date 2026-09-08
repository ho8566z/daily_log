package com.office.library.user.member;

import java.security.SecureRandom;
import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserMemberService {
	
	final private String CLASS_NAME = "[UserMemberService] ";
	
	final static public int USER_ACCOUNT_ALREADY_EXIST 		= 0;
	final static public int USER_ACCOUNT_CREATE_SUCCESS 	= 1;
	final static public int USER_ACCOUNT_CREATE_FAIL 		= -1;
	
	final private UserMemberDao userMemberDao;
	final private PasswordEncoder passwordEncoder;
	final private JavaMailSenderImpl javaMailSenderImpl;

	public int createAccountConfirm(UserMemberDto userMemberDto) {
		System.out.println(CLASS_NAME.concat("createAccountConfirm()"));
		
		boolean isMember = userMemberDao.isUserMember(userMemberDto.getU_m_id());
		
		if (!isMember) {
			
			String encodedPassword = passwordEncoder.encode(userMemberDto.getU_m_pw());
			userMemberDto.setU_m_pw(encodedPassword);
			
			int result = userMemberDao.insertUserAccount(userMemberDto);
			
			if (result > 0)
				return USER_ACCOUNT_CREATE_SUCCESS;		
			else
				return USER_ACCOUNT_CREATE_FAIL;
			
		} else {
			return USER_ACCOUNT_ALREADY_EXIST;
		}
	}

	public String loginConfirm(UserMemberDto userMemberDto) {
		System.out.println(CLASS_NAME.concat("loginConfirm()"));
		
		UserMemberDto selectedUserMemberDto = userMemberDao.selectUser(userMemberDto.getU_m_id());
		
		if (selectedUserMemberDto != null) {
			if (passwordEncoder.matches(userMemberDto.getU_m_pw(), selectedUserMemberDto.getU_m_pw())) {
				System.out.println(CLASS_NAME.concat("USER LOGIN SUCCESS"));
				return selectedUserMemberDto.getU_m_id();
				
			} 
			System.out.println(CLASS_NAME.concat("USER LOGIN FAIL"));
			return null;
			
		} 
		System.out.println(CLASS_NAME.concat("USER LOGIN FAIL"));
		return null;
	}

	public UserMemberDto modifyAccountForm(String loginedUserMemberId) {
		System.out.println(CLASS_NAME.concat("modifyAccountForm()"));
		
		UserMemberDto loginedUserMemberDto =
				userMemberDao.selectUser(loginedUserMemberId);
		
		return loginedUserMemberDto;
	}
	
	public int modifyAccountConfirm(UserMemberDto userMemberDto) {
		System.out.println(CLASS_NAME.concat("modifyAccountConfirm()"));
		
		int result = userMemberDao.updateUserAccount(userMemberDto);
		
		return result;
	}
	
	// ==========================================================================
	// ==========================================================================
	public int findPasswordConfirm(UserMemberDto userMemberDto) {
		System.out.println(CLASS_NAME.concat("findPasswordConfirm()"));
		
		// 1. 인증
		UserMemberDto selecteduserMemberDto = 
				userMemberDao.selectUser(
						userMemberDto.getU_m_id(), 
						userMemberDto.getU_m_name(), 
						userMemberDto.getU_m_mail());
		
		int result = 0;
		
		if (selecteduserMemberDto != null) {
			// 2. 새로운 비밀번호 생성
			String newPassword = createNewPassword();
			
			String encodedNewPassword = passwordEncoder.encode(newPassword);
			// 3. db update
			result = userMemberDao.updatePassword(userMemberDto.getU_m_id(), encodedNewPassword);
			
			// 4. 사용자 한테 메일 발송
			if (result > 0) {
				sendNewPasswordByMail(userMemberDto.getU_m_mail(), newPassword);
				
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
