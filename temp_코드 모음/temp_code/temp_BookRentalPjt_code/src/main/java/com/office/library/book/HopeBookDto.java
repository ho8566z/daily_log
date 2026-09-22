package com.office.library.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HopeBookDto {

	private int hb_no;
	private String hb_name;
	private String hb_author;
	private String hb_publisher;
	private String hb_publish_year;
	private String hb_reg_date;
	private String hb_mod_date;
	private int hb_result;
	private String hb_result_last_date;
	
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
