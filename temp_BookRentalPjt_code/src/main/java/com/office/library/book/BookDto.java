package com.office.library.book;

public class BookDto {

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
	
	public int getB_no() {
		return b_no;
	}
	public void setB_no(int b_no) {
		this.b_no = b_no;
	}
	public String getB_thumbnail() {
		return b_thumbnail;
	}
	public void setB_thumbnail(String b_thumbnail) {
		this.b_thumbnail = b_thumbnail;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public String getB_author() {
		return b_author;
	}
	public void setB_author(String b_author) {
		this.b_author = b_author;
	}
	public String getB_pulisher() {
		return b_pulisher;
	}
	public void setB_pulisher(String b_pulisher) {
		this.b_pulisher = b_pulisher;
	}
	public String getB_pulish_year() {
		return b_pulish_year;
	}
	public void setB_pulish_year(String b_pulish_year) {
		this.b_pulish_year = b_pulish_year;
	}
	public String getB_isbn() {
		return b_isbn;
	}
	public void setB_isbn(String b_isbn) {
		this.b_isbn = b_isbn;
	}
	public String getB_call_number() {
		return b_call_number;
	}
	public void setB_call_number(String b_call_number) {
		this.b_call_number = b_call_number;
	}
	public int getB_rantal_able() {
		return b_rantal_able;
	}
	public void setB_rantal_able(int b_rantal_able) {
		this.b_rantal_able = b_rantal_able;
	}
	public String getB_reg_date() {
		return b_reg_date;
	}
	public void setB_reg_date(String b_reg_date) {
		this.b_reg_date = b_reg_date;
	}
	public String getB_mod_date() {
		return b_mod_date;
	}
	public void setB_mod_date(String b_mod_date) {
		this.b_mod_date = b_mod_date;
	}

}
