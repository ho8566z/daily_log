package com.office.library.book.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.office.library.book.BookDto;
import com.office.library.book.HopeBookDto;

//@Repository("admin.BookDao")
@Repository
public class BookDao {

	final private String CLASS_NAME = "[BookDao] ";
	
	final private JdbcTemplate jdbcTemplate;
	
	public BookDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		
	}
	
	public boolean isISBN(String b_isbn) {
		System.out.println(CLASS_NAME.concat("isISBN()"));
		
		String sql =  "SELECT COUNT(*) FROM "
					+ "tbl_book WHERE b_isbn = ?";
		
		int result = jdbcTemplate.queryForObject(sql, Integer.class, b_isbn);
		
		return result > 0 ? true : false;
		
	}

	public int insertBook(BookDto bookDto) {
		System.out.println(CLASS_NAME.concat("insertBook()"));
		
		String sql =  "INSERT INTO "
						+ "tbl_book("
							+ "b_thumbnail, "
							+ "b_name, "
							+ "b_author, "
							+ "b_pulisher, "
							+ "b_pulish_year, "
							+ "b_isbn, "
							+ "b_call_number, "
							+ "b_rantal_able) "
						+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		int result = -1;
		try {
			result = jdbcTemplate.update(sql, 
											bookDto.getB_thumbnail(), 
											bookDto.getB_name(), 
											bookDto.getB_author(), 
											bookDto.getB_pulisher(), 
											bookDto.getB_pulish_year(), 
											bookDto.getB_isbn(), 
											bookDto.getB_call_number(), 
											bookDto.getB_rantal_able()
											);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}

	public List<BookDto> selectBooksBySearch(String b_name) {
		System.out.println(CLASS_NAME.concat("selectBooksBySearch()"));
		
		String sql = "SELECT * FROM tbl_book WHERE b_name LIKE ? ORDER BY b_no DESC";
		
		List<BookDto> bookDtos = null;
		try {
			bookDtos = jdbcTemplate.query(sql, new RowMapper<BookDto>() {

				@Override
				public BookDto mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					BookDto bookDto = new BookDto();
					
					bookDto.setB_no(rs.getInt("b_no"));
					bookDto.setB_thumbnail(rs.getString("b_thumbnail"));
					bookDto.setB_name(rs.getString("b_name"));
					bookDto.setB_author(rs.getString("b_author"));
					bookDto.setB_pulisher(rs.getString("b_pulisher"));
					bookDto.setB_pulish_year(rs.getString("b_pulish_year"));
					bookDto.setB_isbn(rs.getString("b_isbn"));
					bookDto.setB_call_number(rs.getString("b_call_number"));
					bookDto.setB_rantal_able(rs.getInt("b_rantal_able"));
					bookDto.setB_reg_date(rs.getString("b_reg_date"));
					bookDto.setB_mod_date(rs.getString("b_mod_date"));
					
					return bookDto;
					
				}
				
			}, "%" + b_name + "%");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return bookDtos.size() > 0 ? bookDtos : null;
		
	}

	public BookDto selectBookByBNo(int b_no) {
		System.out.println(CLASS_NAME.concat("selectBookByBNo()"));
		
		String sql = "SELECT * FROM tbl_book WHERE b_no = ?";
		
		List<BookDto> bookDtos = null;
		try {
			bookDtos = jdbcTemplate.query(sql, new RowMapper<BookDto>() {

				@Override
				public BookDto mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					BookDto bookDto = new BookDto();
					
					bookDto.setB_no(rs.getInt("b_no"));
					bookDto.setB_thumbnail(rs.getString("b_thumbnail"));
					bookDto.setB_name(rs.getString("b_name"));
					bookDto.setB_author(rs.getString("b_author"));
					bookDto.setB_pulisher(rs.getString("b_pulisher"));
					bookDto.setB_pulish_year(rs.getString("b_pulish_year"));
					bookDto.setB_isbn(rs.getString("b_isbn"));
					bookDto.setB_call_number(rs.getString("b_call_number"));
					bookDto.setB_rantal_able(rs.getInt("b_rantal_able"));
					bookDto.setB_reg_date(rs.getString("b_reg_date"));
					bookDto.setB_mod_date(rs.getString("b_mod_date"));
					
					return bookDto;
					
				}
				
			}, b_no);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return bookDtos.size() > 0 ? bookDtos.get(0) : null;
		
	}

	public int updateBook(BookDto bookDto) {
		System.out.println(CLASS_NAME.concat("updateBook()"));
		
		List<String> args = new ArrayList<String>();
		
		String sql = "UPDATE TBL_BOOK SET ";
				if (bookDto.getB_thumbnail() != null) {
					sql += "b_thumbnail = ?, ";
					args.add(bookDto.getB_thumbnail());
				}
				
				sql += "b_name = ?, ";
				args.add(bookDto.getB_name());
				
				sql += "b_author = ?, ";
				args.add(bookDto.getB_author());
				
				sql += "b_pulisher = ?, ";
				args.add(bookDto.getB_pulisher());
				
				sql += "b_pulish_year = ?, ";
				args.add(bookDto.getB_pulish_year());
				
				sql += "b_isbn = ?, ";
				args.add(bookDto.getB_isbn());
				
				sql += "b_call_number = ?, ";
				args.add(bookDto.getB_call_number());
				
				sql += "b_rantal_able = ? ";
				args.add(Integer.toString(bookDto.getB_rantal_able()));
				
				sql += "WHERE b_no = ?";
				args.add(Integer.toString(bookDto.getB_no()));
				
		int result = -1;
		try {
			
			result = jdbcTemplate.update(sql, args.toArray());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}

	public int deleteBook(int b_no) {
		System.out.println(CLASS_NAME.concat("deleteBook()"));
		
		String sql = "DELETE FROM tbl_book WHERE b_no = ?";
		
		int result = -1;
		
		try {
			result = jdbcTemplate.update(sql, b_no);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}

	public List<HopeBookDto> selectHopeBooks() {
		System.out.println(CLASS_NAME.concat("selectHopeBooks()"));
		
		String sql =  "SELECT * FROM tbl_hope_book hb "
					+ "JOIN tbl_user_member um "
					+ "ON hb.u_m_no = um.u_m_no "
					+ "ORDER BY hb.hb_no DESC";
		
		List<HopeBookDto> hopeBookDtos = new ArrayList<HopeBookDto>();
		
		try {
			RowMapper<HopeBookDto> rowMapper = BeanPropertyRowMapper.newInstance(HopeBookDto.class);
			hopeBookDtos = jdbcTemplate.query(sql, rowMapper);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return hopeBookDtos;
		
	}

	public void updateHopeBookResult(int hb_no) {
		System.out.println(CLASS_NAME.concat("updateHopeBookResult()"));
		
		String sql =  "UPDATE tbl_hope_book "
					+ "SET hb_result = 1 "
					+ "WHERE hb_no = ?";
		
		try {
			jdbcTemplate.update(sql, hb_no);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}

}
