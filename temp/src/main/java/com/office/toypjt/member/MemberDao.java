package com.office.toypjt.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.office.toypjt.ToyPjtConfig;

public class MemberDao extends ToyPjtConfig implements IMemberDao {
	
	final private String CLASS_NAME = "[MemberDao] ";

	@Override
	public MemberDto selectMemberByMemId(String id) {
		System.out.println(CLASS_NAME.concat("selectMemberByMemId()"));
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberDto> memberDtos = new ArrayList<MemberDto>();
		
		try {
			// 1.드라이버 로딩(메모리에서)
			Class.forName(DRIVER);
			
			// 2.Connection 연결
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// 3.작업명세서 작성(sql)
			String sql = "SELECT * FROM TBL_MEMBER "
						+ "WHERE memId = ?";
			
			// 4.PreparedStatment
			pstmt = conn.prepareStatement(sql);
			
			// 5.데이터 주입 (=set)
			pstmt.setString(1, id);
			
			// 6.작업 명령
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int memNo = rs.getInt("memNo");
				String memId = rs.getString("memId");
				String memPw = rs.getString("memPw");
				String memMail = rs.getString("memMail");
				String memPhone = rs.getString("memPhone");
				String memRegDate = rs.getString("memRegDate");
				String memModDate = rs.getString("memModDate");
				
				MemberDto dto = new MemberDto(memNo, 
									memId, 
									memPw, 
									memMail, 
									memPhone, 
									memRegDate, 
									memModDate);
				
				memberDtos.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return memberDtos.size() > 0 ? memberDtos.get(0) : null;
	}

	
	@Override
	public int updateMemberByMemNo(MemberDto memberDto) {
		System.out.println(CLASS_NAME.concat("updateMemberByMemNo()"));
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;
		
		try {
			// 1.드라이버 로딩(메모리에서)
			Class.forName(DRIVER);
			
			// 2.Connection 연결
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// 3.작업명세서 작성(sql)
			String sql = "UPDATE TBL_MEMBER "
						+ "SET "
							+ "memPw = ?, "
							+ "memMail = ?, "
							+ "memPhone = ? "
						+ "WHERE "
							+ "memNo = ?";
			
			// 4.PreparedStatment
			pstmt = conn.prepareStatement(sql);
			
			// 5.데이터 주입 (=set)
			pstmt.setString(1, memberDto.getMemPw());
			pstmt.setString(2, memberDto.getMemMail());
			pstmt.setString(3, memberDto.getMemPhone());
			pstmt.setInt(4, memberDto.getMemNo());
			
			// 6.작업 명령
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return result;

	}


	@Override
	public int deleteMemberByMemId(String memId) {
		System.out.println(CLASS_NAME.concat("deleteMemberByMemId()"));
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;
		
		try {
			// 1.드라이버 로딩(메모리에서)
			Class.forName(DRIVER);
			
			// 2.Connection 연결
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// 3.작업명세서 작성(sql)
			String sql = "DELETE FROM TBL_MEMBER "
						+ "WHERE memId = ?";
			
			// 4.PreparedStatment
			pstmt = conn.prepareStatement(sql);
			
			// 5.데이터 주입 (=set)
			pstmt.setString(1, memId);
			
			// 6.작업 명령
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return result;
	}
	
}
