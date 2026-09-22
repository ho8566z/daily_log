package com.office.library.user.member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserMemberDao {
	
	final private String CLASS_NAME = "[UserMemberDao] ";
	
	final private JdbcTemplate jdbcTemplate;

	public boolean isUserMember(String u_m_id) {
		System.out.println(CLASS_NAME.concat("isUserMember()"));
		
		String sql = "SELECT COUNT(*) FROM tbl_user_member "
					+ "WHERE u_m_id = ?";
		
		int result = jdbcTemplate.queryForObject(sql, Integer.class, u_m_id);
		
		return result > 0 ? true : false;
	}

	public int insertUserAccount(UserMemberDto userMemberDto) {
		System.out.println(CLASS_NAME.concat("insertUserAccount()"));
		
		String sql = "INSERT INTO tbl_user_member(u_m_id, "
												+ "u_m_pw, "
												+ "u_m_name, "
												+ "u_m_gender, "
												+ "u_m_mail, "
												+ "u_m_phone) "
												+ "VALUES(?, ?, ?, ?, ?, ?)";
		int result = -1;
		
		try {
			result = jdbcTemplate.update(sql, 
								userMemberDto.getU_m_id(),
								userMemberDto.getU_m_pw(),
								userMemberDto.getU_m_name(),
								userMemberDto.getU_m_gender(),
								userMemberDto.getU_m_mail(),
								userMemberDto.getU_m_phone());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}

	/*
	public UserMemberDto selectUser(String u_m_id) {
		System.out.println(CLASS_NAME.concat("selectUser()"));
		
		String sql = "SELECT * FROM tbl_user_member "
					+ "WHERE u_m_id = ?";
		
		List<UserMemberDto> userMemberDtos = new ArrayList<UserMemberDto>();
		
		try {
			userMemberDtos = jdbcTemplate.query(sql, new RowMapper<UserMemberDto>() {

				@Override
				public UserMemberDto mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					UserMemberDto userMemberDto = new UserMemberDto();
					
					userMemberDto.setU_m_no(rs.getInt("u_m_no"));
					userMemberDto.setU_m_id(rs.getString("u_m_id"));
					userMemberDto.setU_m_pw(rs.getString("u_m_pw"));
					userMemberDto.setU_m_name(rs.getString("u_m_name"));
					userMemberDto.setU_m_gender(rs.getString("u_m_gender"));
					userMemberDto.setU_m_mail(rs.getString("u_m_mail"));
					userMemberDto.setU_m_phone(rs.getString("u_m_phone"));
					userMemberDto.setU_m_reg_date(rs.getString("u_m_reg_date"));
					userMemberDto.setU_m_mod_date(rs.getString("u_m_mod_date"));
					
					return userMemberDto;
					
				}
				
			}, u_m_id);
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
		return userMemberDtos.size() > 0 ? userMemberDtos.get(0) : null;
	}
	*/

	public UserMemberDto selectUser(String u_m_id) {
		System.out.println(CLASS_NAME.concat("selectUser()"));
		
		String sql = "SELECT * FROM tbl_user_member "
					+ "WHERE u_m_id = ?";
		
		List<UserMemberDto> userMemberDtos = new ArrayList<UserMemberDto>();
		
		try {
			
			// 모든 멤버필드를 맵핑할때만 사용한다.
			RowMapper<UserMemberDto> rowMapper = BeanPropertyRowMapper.newInstance(UserMemberDto.class);
			userMemberDtos = jdbcTemplate.query(sql, rowMapper, u_m_id);
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
		return userMemberDtos.size() > 0 ? userMemberDtos.get(0) : null;
	}

	public int updateUserAccount(UserMemberDto userMemberDto) {
		System.out.println(CLASS_NAME.concat("updateUserAccount()"));
		
		String sql = "UPDATE "
						+ "tbl_user_member "
					+ "SET "
						+ "u_m_name = ?, "
						+ "u_m_gender = ?, "
						+ "u_m_mail = ?,  "
						+ "u_m_phone = ?"
					+ "WHERE "
						+ "u_m_no = ?";
		
		int result = -1;
		
		try {
			result = jdbcTemplate.update(sql, 
					userMemberDto.getU_m_name(), 
					userMemberDto.getU_m_gender(), 
					userMemberDto.getU_m_mail(), 
					userMemberDto.getU_m_phone(), 
					userMemberDto.getU_m_no());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
	}

	// ==========================================================================
	// ==========================================================================
	public UserMemberDto selectUser(String u_m_id, String u_m_name, String u_m_mail) {
		System.out.println(CLASS_NAME.concat("selectUser()"));
		
		String sql =  "SELECT "
						+ "* "
					+ "FROM "
						+ "tbl_user_member "
					+ "WHERE "
						+ "u_m_id = ? AND "
						+ "u_m_name = ? AND "
						+ "u_m_mail = ?";
		
		List<UserMemberDto> userMemberDtos = new ArrayList<UserMemberDto>();
		
		try {
			
			userMemberDtos = jdbcTemplate.query(sql, new RowMapper<UserMemberDto>() {

				@Override
				public UserMemberDto mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					UserMemberDto userMemberDto = new UserMemberDto();
					
					userMemberDto.setU_m_no(rs.getInt("u_m_no"));
					userMemberDto.setU_m_id(rs.getString("u_m_id"));
					userMemberDto.setU_m_pw(rs.getString("u_m_pw"));
					userMemberDto.setU_m_name(rs.getString("u_m_name"));
					userMemberDto.setU_m_gender(rs.getString("u_m_gender"));
					userMemberDto.setU_m_mail(rs.getString("u_m_mail"));
					userMemberDto.setU_m_phone(rs.getString("u_m_phone"));
					userMemberDto.setU_m_reg_date(rs.getString("u_m_reg_date"));
					userMemberDto.setU_m_mod_date(rs.getString("u_m_mod_date"));
					
					return userMemberDto;
					
				}
				
			}, u_m_id, u_m_name, u_m_mail);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return userMemberDtos.size() > 0 ? userMemberDtos.get(0) : null;
		
	}

	public int updatePassword(String u_m_id, String newPassword) {
		System.out.println(CLASS_NAME.concat("updatePassword()"));
		
		String sql = "UPDATE tbl_user_member SET u_m_pw = ? WHERE u_m_id = ?";
		
		int result = -1;
		
		try {
			result = jdbcTemplate.update(sql, newPassword, u_m_id);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}
	
	
	
	
}
