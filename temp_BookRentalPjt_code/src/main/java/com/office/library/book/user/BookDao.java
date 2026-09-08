package com.office.library.book.user;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.office.library.book.BookDto;
import com.office.library.book.HopeBookDto;
import com.office.library.book.RentalBookDto;

import lombok.RequiredArgsConstructor;

//@Repository("user.BookDao")
@Repository
@RequiredArgsConstructor
public class BookDao {

	final private String CLASS_NAME = "[BookDao] ";

	final private JdbcTemplate jdbcTemplate;
	
	
	public List<BookDto> selectBooksBySearch(String b_name) {
		System.out.println(CLASS_NAME.concat("selectBooksBySearch()"));
		
		String sql =  "SELECT * FROM tbl_book "
					+ "WHERE b_name LIKE ? ORDER BY b_no DESC";
		
		List<BookDto> bookDtos = null;
		
		try {
			
			RowMapper<BookDto> rowMapper = 
					BeanPropertyRowMapper.newInstance(BookDto.class);
			bookDtos = jdbcTemplate.query(sql, rowMapper, "%" + b_name + "%");			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return bookDtos.size() > 0 ? bookDtos : null;
		
	}


	public BookDto selectBookByBNo(int b_no) {
		System.out.println(CLASS_NAME.concat("selectBookByBNo()"));
		
		String sql =  "SELECT * FROM tbl_book "
					+ "WHERE b_no = ?";
		
		List<BookDto> bookDtos = null;
		try {
			RowMapper<BookDto> rowMapper = BeanPropertyRowMapper.newInstance(BookDto.class);
			bookDtos = jdbcTemplate.query(sql, rowMapper, b_no);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return bookDtos.size() > 0 ? bookDtos.get(0) : null;
		
	}


	public int insertRentalBook(int b_no, int u_m_no) {
		System.out.println(CLASS_NAME.concat("insertRentalBook()"));
		
		String sql =  "INSERT INTO tbl_rental_book(b_no, u_m_no) "
					+ "VALUES(?, ?)";
		
		int result = -1;
		
		try {
			result = jdbcTemplate.update(sql, b_no, u_m_no);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}


	public void updateRentalBookAble(int b_no) {
		System.out.println(CLASS_NAME.concat("updateRentalBookAble()"));
		
		String sql =  "UPDATE tbl_book "
					+ "SET b_rantal_able = 0 "
					+ "WHERE b_no = ?";
		
		try {
			jdbcTemplate.update(sql, b_no);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}


	public List<RentalBookDto> selectRentalBooks(int u_m_no) {
		System.out.println(CLASS_NAME.concat("selectRentalBooks()"));
		
		/*
		    SELECT * FROM tbl_rental_book rb
			JOIN tbl_book b
			ON rb.b_no = b.b_no 
			JOIN tbl_user_member um
			ON rb.u_m_no = um.u_m_no 
			WHERE rb.u_m_no = 1 AND rb.rb_end_date = '1000-01-01 00:00:00';
		 */
		
		String sql =  "SELECT * FROM tbl_rental_book rb "
					+ "JOIN tbl_book b "
					+ "ON rb.b_no = b.b_no "
					+ "JOIN tbl_user_member um "
					+ "ON rb.u_m_no = um.u_m_no "
					+ "WHERE rb.u_m_no = ? AND rb.rb_end_date = '1000-01-01 00:00:00'";
		
		List<RentalBookDto> rentalBookDtos = null;
		
		try {
			
			RowMapper<RentalBookDto> rowMapper = BeanPropertyRowMapper.newInstance(RentalBookDto.class);
			rentalBookDtos = jdbcTemplate.query(sql, rowMapper, u_m_no);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return rentalBookDtos;
		
	}


	public List<RentalBookDto> selectRentalBookHistory(int u_m_no) {
		System.out.println(CLASS_NAME.concat("selectRentalBookHistory()"));
		
		String sql =  "SELECT * FROM tbl_rental_book rb "
					+ "JOIN tbl_book b "
					+ "ON rb.b_no = b.b_no "
					+ "JOIN tbl_user_member um "
					+ "ON rb.u_m_no = um.u_m_no "
					+ "WHERE rb.u_m_no = ? "
					+ "ORDER BY rb.rb_reg_date DESC";
		
		List<RentalBookDto> rentalBookDtos = null;
		
		try {
			
			RowMapper<RentalBookDto> rowMapper = BeanPropertyRowMapper.newInstance(RentalBookDto.class);
			rentalBookDtos = jdbcTemplate.query(sql, rowMapper, u_m_no);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return rentalBookDtos;
		
	}


	public int insertHopeBook(HopeBookDto hopeBookDto) {
		System.out.println(CLASS_NAME.concat("insertHopeBook()"));
		
		String sql =  "INSERT INTO tbl_hope_book("
						+ "u_m_no, "
						+ "hb_name, "
						+ "hb_author, "
						+ "hb_publisher, "
						+ "hb_publish_year) "
					+ "VALUES(?, ?, ?, ?, ?)";
		
		int result = -1;
		try {
			result = jdbcTemplate.update(sql, 
											hopeBookDto.getU_m_no(), 
											hopeBookDto.getHb_name(), 
											hopeBookDto.getHb_author(), 
											hopeBookDto.getHb_publisher(), 
											hopeBookDto.getHb_publish_year());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return result;
		
	}


	public List<HopeBookDto> selectRequestHopeBooks(int u_m_no) {
		System.out.println(CLASS_NAME.concat("selectRequestHopeBooks()"));
		
		String sql =  "SELECT * FROM tbl_hope_book "
					+ "WHERE u_m_no = ?";
		
		List<HopeBookDto> hopeBookDtos = null;
		try {
			hopeBookDtos = jdbcTemplate.query(
											sql, 
											BeanPropertyRowMapper.newInstance(HopeBookDto.class), 
											u_m_no);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return hopeBookDtos;
		
	}
	
}
