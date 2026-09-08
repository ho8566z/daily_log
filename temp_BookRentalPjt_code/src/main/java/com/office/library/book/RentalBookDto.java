package com.office.library.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalBookDto {

	// tbl_rental_book
	private int rb_no;
	private String rb_start_date;
	private String rb_end_date;
	private String rb_reg_date;
	private String rb_mod_date;
	
	// tbl_book
	private int b_no;				// 책 번호
	private String b_thumbnail;		// 책 표지 이미지 이름
	private String b_name;			// 책 이름
	private String b_author;		// 저자 이름
	private String b_pulisher;		// 출판사 이름
	private String b_pulish_year;	// 출판 년도
	private String b_isbn;			// ISBN
	private String b_call_number;	// 청구기호
	private int b_rantal_able;		// 대여 가능 여부(0:불가, 1:가능)
	private String b_reg_date;		// 등록일
	private String b_mod_date;		// 최근 수정일
	
	// tbl_user_member
	private int u_m_no;
	private String u_m_id;
	private String u_m_pw;
	private String u_m_name;
	private String u_m_gender;
	private String u_m_mail;
	private String u_m_phone;
	private String u_m_reg_date;
	private String u_m_mod_date;
	
}
