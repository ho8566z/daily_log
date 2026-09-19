package com.office.library.book.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.office.library.book.BookDto;
import com.office.library.book.HopeBookDto;
import com.office.library.book.RentalBookDto;
import com.office.library.user.member.UserMemberDao;
import com.office.library.user.member.UserMemberDto;

import lombok.RequiredArgsConstructor;

//@Service("user.BookService")
@Service
@RequiredArgsConstructor
public class BookService {

	final private String CLASS_NAME = "[BookService] ";
	
	final private BookDao bookDao;
	final private UserMemberDao userMemberDao;

	public List<BookDto> searchBookConfirm(String b_name) {
		System.out.println(CLASS_NAME.concat("searchBookConfirm"));
		
		return bookDao.selectBooksBySearch(b_name);
		
	}

	public BookDto bookDetail(int b_no) {
		System.out.println(CLASS_NAME.concat("bookDetail()"));
			
		return bookDao.selectBookByBNo(b_no);
		
	}

	public int rentalBookConfirm(int b_no, int u_m_no) {
		System.out.println(CLASS_NAME.concat("rentalBookConfirm()"));
		
		int result = bookDao.insertRentalBook(b_no, u_m_no);  // tbl_rental_book
		
		if (result >= 0) {
			bookDao.updateRentalBookAble(b_no);
		}
		
		return result;
		
	}

	public UserMemberDto selectUser(String loginedUserMemberId) {
		System.out.println(CLASS_NAME.concat("selectUser()"));
		
		return userMemberDao.selectUser(loginedUserMemberId);
		
	}

	public List<RentalBookDto> enterBookshelf(int u_m_no) {
		System.out.println(CLASS_NAME.concat("enterBookshelf()"));
		
		return bookDao.selectRentalBooks(u_m_no);
		
	}

	public List<RentalBookDto> listupRentalBookHistory(int u_m_no) {
		System.out.println(CLASS_NAME.concat("listupRentalBookHistory()"));
		
		return bookDao.selectRentalBookHistory(u_m_no);
		
	}

	public int requestHopeBookConfirm(HopeBookDto hopeBookDto) {
		System.out.println(CLASS_NAME.concat("requestHopeBookConfirm()"));
		
		return bookDao.insertHopeBook(hopeBookDto);
		
	}

	public List<HopeBookDto> listupRequestHopeBook(int u_m_no) {
		System.out.println(CLASS_NAME.concat("listupRequestHopeBook()"));
		
		return bookDao.selectRequestHopeBooks(u_m_no);
		
	}
	
}
