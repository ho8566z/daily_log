package com.office.toypjt.memo;

import java.net.URL;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.office.toypjt.ToyPjtConfig;

public class MemoDao {

	public int insertMemo(MemoDto memoDto) {
		System.out.println("[MemoDao]insertMemo()");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;
		
		try {
			Class.forName(ToyPjtConfig.DRIVER);
			
			conn = DriverManager.getConnection(
					ToyPjtConfig.URL,
					ToyPjtConfig.USER,
					ToyPjtConfig.DRIVER
					);
			
			String sql = "INSERT INTO tbl_memo("
					+ "mem_id, "
					+ "memo_title, "
					+ "memo_conment) "
					+ "VALUES(?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memoDto.getMemId());
			pstmt.setString(1, memoDto.getMemoTitle());
			pstmt.setString(1, memoDto.getMemoComent());
			
			result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				
				if (pstmt != null)
					pstmt.close();
				
				if (conn != null)
					conn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return result;
		
	}
	
}
