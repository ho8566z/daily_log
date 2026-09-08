package com.office.library.book.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.office.library.book.BookDto;
import com.office.library.book.HopeBookDto;

//@Service("admin.BookService")
@Service
public class BookService {

	final private String CLASS_NAME = "[BookService] ";
	
	final static public int BOOK_ISBN_ALREADY_EXIST = 0;	// 이미 등록된 도서
	final static public int BOOK_REGISTER_SUCCESS 	= 1;	// 도서 등록 성공
	final static public int BOOK_REGISTER_FAIL 		= -1;	// 도서 등록 실패
	
	final private BookDao bookDao;
	
	public BookService(BookDao bookDao) {
		this.bookDao = bookDao;
		
	}

	public int registerBookConfirm(BookDto bookDto) {
		System.out.println(CLASS_NAME.concat("registerBookConfirm()"));
		
		// ISBN 중복 체크
		boolean isISBN = bookDao.isISBN(bookDto.getB_isbn());
		
		// 신규 도서 등록
		if (!isISBN) {
			int result = bookDao.insertBook(bookDto);
			
			if (result > 0)
				return BOOK_REGISTER_SUCCESS;
			else
				return BOOK_REGISTER_FAIL;
			
		} else {
			return BOOK_ISBN_ALREADY_EXIST;
			
		}
		
	}

	public List<BookDto> searchBookConfirm(String b_name) {
		System.out.println(CLASS_NAME.concat("searchBookConfirm()"));
		
		return bookDao.selectBooksBySearch(b_name);
		
	}

	public BookDto bookDetail(int b_no) {
		System.out.println(CLASS_NAME.concat("bookDetail()"));

		return bookDao.selectBookByBNo(b_no);
		
	}

	public BookDto modifyBookForm(int b_no) {
		System.out.println(CLASS_NAME.concat("modifyBookForm()"));
		
		return bookDetail(b_no);
		
	}

	public int modifyBookConfirm(BookDto bookDto) {
		System.out.println(CLASS_NAME.concat("modifyBookConfirm()"));
		
		int result = bookDao.updateBook(bookDto);
		
		return result;
		
	}

	public int deleteBookConfirm(int b_no) {
		System.out.println(CLASS_NAME.concat("deleteBookConfirm()"));
		
		int result = bookDao.deleteBook(b_no);
		
		return result;
		
	}

	public List<HopeBookDto> getHopeBooks() {
		System.out.println(CLASS_NAME.concat("getHopeBooks()"));
		
		List<HopeBookDto> hopeBookDtos = bookDao.selectHopeBooks();
		
		return hopeBookDtos;
		
	}

	public int registerHopeBookConfirm(BookDto bookDto, int hb_no) {
		System.out.println(CLASS_NAME.concat("registerHopeBookConfirm()"));
		
		boolean isISBN = bookDao.isISBN(bookDto.getB_isbn());
		if(!isISBN) {
			int result = bookDao.insertBook(bookDto);
			
			if (result > 0) {
				bookDao.updateHopeBookResult(hb_no);
				
				return BOOK_REGISTER_SUCCESS;
				
			} else {
				return BOOK_REGISTER_FAIL;
				
			} 
			
		} else {
			return BOOK_ISBN_ALREADY_EXIST;
			
		}
	}
}
