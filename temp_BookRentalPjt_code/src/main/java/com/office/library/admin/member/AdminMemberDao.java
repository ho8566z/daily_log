package com.office.library.admin.member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AdminMemberDao {
	
	final private String CLASS_NAME = "[AdminMemberDao] ";
	
	final private JdbcTemplate jdbcTemplate;
	
//	@Autowired
	public AdminMemberDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		
	}
	
	public boolean isAdminMember(String a_m_id) {
		System.out.println(CLASS_NAME.concat("isAdminMember()"));

		String sql =  "SELECT COUNT(*) FROM tbl_admin_member "
					+ "WHERE a_m_id = ?";
		
		int result = jdbcTemplate.queryForObject(sql, Integer.class, a_m_id);
		
		return result > 0 ? true : false;
		
	}

	public int insertAdminAccount(AdminMemberDto adminMemberDto) {
		System.out.println(CLASS_NAME.concat("insertAdminAccount()"));
		
		// super admin
		
//		String sql =  "INSERT INTO tbl_admin_member(a_m_id, a_m_pw, a_m_name, a_m_gender, a_m_part, a_m_position, a_m_mail, a_m_phone) "
//					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
//		String sql =  "INSERT INTO tbl_admin_member(a_m_approval, a_m_id, a_m_pw, a_m_name, a_m_gender, a_m_part, a_m_position, a_m_mail, a_m_phone) "
//				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
//		
//		int result = jdbcTemplate.update(sql, 
//				1,
//				adminMemberDto.getA_m_id(),
//				adminMemberDto.getA_m_pw(),
//				adminMemberDto.getA_m_name(),
//				adminMemberDto.getA_m_gender(),
//				adminMemberDto.getA_m_part(),
//				adminMemberDto.getA_m_position(),
//				adminMemberDto.getA_m_mail(),
//				adminMemberDto.getA_m_phone());
		
		List<String> args = new ArrayList<String>();
		
		String sql = "INSERT INTO tbl_admin_member(";
		if (adminMemberDto.getA_m_id().equals("super admin")) {
			sql += "a_m_approval, ";
			args.add("1");
			
		}
		
		sql += "a_m_id, ";
		args.add(adminMemberDto.getA_m_id());
		
		sql += "a_m_pw, ";
		args.add(adminMemberDto.getA_m_pw());
		
		sql += "a_m_name, ";
		args.add(adminMemberDto.getA_m_name());
		
		sql += "a_m_gender, ";
		args.add(adminMemberDto.getA_m_gender());
		
		sql += "a_m_part, ";
		args.add(adminMemberDto.getA_m_part());
		
		sql += "a_m_position, ";
		args.add(adminMemberDto.getA_m_position());
		
		sql += "a_m_mail, ";
		args.add(adminMemberDto.getA_m_mail());
		
		sql += "a_m_phone) ";
		args.add(adminMemberDto.getA_m_phone());
		
		if (adminMemberDto.getA_m_id().equals("super admin")) {
			sql += "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
		} else {
			sql += "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			
		}
		
		int result = jdbcTemplate.update(sql, args.toArray());
		
		
		return result;
		
	}

	public AdminMemberDto selectAdmin(String a_m_id) {
		System.out.println(CLASS_NAME.concat("selectAdmin()"));
		
		String sql =  "SELECT * FROM tbl_admin_member "
					+ "WHERE a_m_id = ? AND a_m_approval > 0";
		
		List<AdminMemberDto> adminMemberDtos = new ArrayList<AdminMemberDto>();  // Ox123
		
		try {
			
			adminMemberDtos = jdbcTemplate.query(sql, new RowMapper<AdminMemberDto>() {	// Ox456

				@Override
				public AdminMemberDto mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					AdminMemberDto adminMemberDto = new AdminMemberDto();
					
					adminMemberDto.setA_m_no(rs.getInt("a_m_no"));
					adminMemberDto.setA_m_approval(rs.getInt("a_m_approval"));
					adminMemberDto.setA_m_id(rs.getString("a_m_id"));
					adminMemberDto.setA_m_pw(rs.getString("a_m_pw"));
					adminMemberDto.setA_m_name(rs.getString("a_m_name"));
					adminMemberDto.setA_m_gender(rs.getString("a_m_gender"));
					adminMemberDto.setA_m_part(rs.getString("a_m_part"));
					adminMemberDto.setA_m_position(rs.getString("a_m_position"));
					adminMemberDto.setA_m_mail(rs.getString("a_m_mail"));
					adminMemberDto.setA_m_phone(rs.getString("a_m_phone"));
					adminMemberDto.setA_m_reg_date(rs.getString("a_m_reg_date"));
					adminMemberDto.setA_m_mod_date(rs.getString("a_m_mod_date"));
					
					return adminMemberDto;
					
				}
				
			}, a_m_id);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return adminMemberDtos.size() > 0 ? adminMemberDtos.get(0) : null;
		
	}

	public List<AdminMemberDto> selectAdmins() {
		System.out.println(CLASS_NAME.concat("selectAdmins()"));
		
		String sql =  "SELECT * FROM tbl_admin_member "
					+ "ORDER BY a_m_no DESC";  // ASC or DESC
		
		List<AdminMemberDto> adminMemberDtos = new ArrayList<AdminMemberDto>();
		
		try {
			
			adminMemberDtos = jdbcTemplate.query(sql, new RowMapper<AdminMemberDto>() {

				@Override
				public AdminMemberDto mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					AdminMemberDto adminMemberDto = new AdminMemberDto();
					
					adminMemberDto.setA_m_no(rs.getInt("a_m_no"));
					adminMemberDto.setA_m_approval(rs.getInt("a_m_approval"));
					adminMemberDto.setA_m_id(rs.getString("a_m_id"));
					adminMemberDto.setA_m_pw(rs.getString("a_m_pw"));
					adminMemberDto.setA_m_name(rs.getString("a_m_name"));
					adminMemberDto.setA_m_gender(rs.getString("a_m_gender"));
					adminMemberDto.setA_m_part(rs.getString("a_m_part"));
					adminMemberDto.setA_m_position(rs.getString("a_m_position"));
					adminMemberDto.setA_m_mail(rs.getString("a_m_mail"));
					adminMemberDto.setA_m_phone(rs.getString("a_m_phone"));
					adminMemberDto.setA_m_reg_date(rs.getString("a_m_reg_date"));
					adminMemberDto.setA_m_mod_date(rs.getString("a_m_mod_date"));
					
					return adminMemberDto;
					
				}
				
			});
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return adminMemberDtos;
		
	}

	public int updateAdminApproval(int a_m_no) {
		System.out.println(CLASS_NAME.concat("updateAdminApproval()"));
		
		String sql =  "UPDATE tbl_admin_member "
					+ "SET a_m_approval = 1 "
					+ "WHERE a_m_no = ?";
		
		int result = -1;
		
		try {
			
			result = jdbcTemplate.update(sql, a_m_no);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}

	public int updateAdminAccount(AdminMemberDto adminMemberDto) {
		System.out.println(CLASS_NAME.concat("updateAdminAccount()"));
		
		String sql =  "UPDATE "
						+ "tbl_admin_member "
					+ "SET "
						+ "a_m_name = ?, "
						+ "a_m_gender = ?, "
						+ "a_m_part = ?, "
						+ "a_m_position = ?, "
						+ "a_m_mail = ?, "
						+ "a_m_phone = ? "
					+ "WHERE "
						+ "a_m_no = ?";
		
		int result = -1;
		
		try {
			result = jdbcTemplate.update(sql, 
											adminMemberDto.getA_m_name(),
											adminMemberDto.getA_m_gender(),
											adminMemberDto.getA_m_part(),
											adminMemberDto.getA_m_position(),
											adminMemberDto.getA_m_mail(),
											adminMemberDto.getA_m_phone(), 
											adminMemberDto.getA_m_no());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}

	public AdminMemberDto selectAdmin(String a_m_id, String a_m_name, String a_m_mail) {
		System.out.println(CLASS_NAME.concat("selectAdmin()"));
		
		String sql =  "SELECT "
						+ "* "
					+ "FROM "
						+ "tbl_admin_member "
					+ "WHERE "
						+ "a_m_id = ? AND "
						+ "a_m_name = ? AND "
						+ "a_m_mail = ?";
		
		List<AdminMemberDto> adminMemberDtos = new ArrayList<AdminMemberDto>();
		
		try {
			
			adminMemberDtos = jdbcTemplate.query(sql, new RowMapper<AdminMemberDto>() {

				@Override
				public AdminMemberDto mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					AdminMemberDto adminMemberDto = new AdminMemberDto();
					
					adminMemberDto.setA_m_no(rs.getInt("a_m_no"));
					adminMemberDto.setA_m_approval(rs.getInt("a_m_approval"));
					adminMemberDto.setA_m_id(rs.getString("a_m_id"));
					adminMemberDto.setA_m_pw(rs.getString("a_m_pw"));
					adminMemberDto.setA_m_name(rs.getString("a_m_name"));
					adminMemberDto.setA_m_gender(rs.getString("a_m_gender"));
					adminMemberDto.setA_m_part(rs.getString("a_m_part"));
					adminMemberDto.setA_m_position(rs.getString("a_m_position"));
					adminMemberDto.setA_m_mail(rs.getString("a_m_mail"));
					adminMemberDto.setA_m_phone(rs.getString("a_m_phone"));
					adminMemberDto.setA_m_reg_date(rs.getString("a_m_reg_date"));
					adminMemberDto.setA_m_mod_date(rs.getString("a_m_mod_date"));
					
					return adminMemberDto;
					
				}
				
			}, a_m_id, a_m_name, a_m_mail);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return adminMemberDtos.size() > 0 ? adminMemberDtos.get(0) : null;
		
	}

	public int updatePassword(String a_m_id, String newPassword) {
		System.out.println(CLASS_NAME.concat("updatePassword()"));
		
		String sql = "UPDATE tbl_admin_member SET a_m_pw = ? WHERE a_m_id = ?";
		
		int result = -1;
		
		try {
			result = jdbcTemplate.update(sql, newPassword, a_m_id);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}

}










