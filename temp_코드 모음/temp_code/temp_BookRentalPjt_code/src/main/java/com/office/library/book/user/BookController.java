package com.office.library.book.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.office.library.book.BookDto;
import com.office.library.book.HopeBookDto;
import com.office.library.book.RentalBookDto;
import com.office.library.user.member.UserMemberDto;

import lombok.RequiredArgsConstructor;

//@Controller("user.BookController")
@Controller
@RequiredArgsConstructor
@RequestMapping("/book/user")
public class BookController {

	final private String CLASS_NAME = "[BookController] ";
	
	final private BookService bookService;
	
	/*
	 * 도서 검색 확인
	 * /book/user/searchBookConfirm
	 */
	@GetMapping("/searchBookConfirm")
	public String searchBookConfirm(
			@RequestParam(value="b_name", required=false) String b_name, 
			Model model) {
		System.out.println(CLASS_NAME.concat("searchBookConfirm()"));
		
		b_name = b_name == null ? "with" : b_name;
		
		String nextPage = "user/book/search_book";
		
		List<BookDto> bookDtos = bookService.searchBookConfirm(b_name);
		model.addAttribute("bookDtos", bookDtos);
		
		return nextPage;
		
	}
	
	/*
	 * 도서 상세 화면
	 * /book/user/bookDetail
	 */
	@GetMapping("/bookDetail")
	public String bookDetail(@RequestParam("b_no") int b_no, Model model) {
		System.out.println(CLASS_NAME.concat("bookDetail()"));
		
		String nextPage = "user/book/book_detail";
		
		BookDto bookDto = bookService.bookDetail(b_no);
		model.addAttribute("bookDto", bookDto);
		
		return nextPage;
		
	}
	
	/*
	 * 도서 대출 확인
	 * /book/user/rentalBookConfirm
	 */
	@GetMapping("/rentalBookConfirm")
	public String rentalBookConfirm(
			@RequestParam("b_no") int b_no, 
			HttpSession session) {
		System.out.println(CLASS_NAME.concat("rentalBookConfirm()"));
		
		String nextPage = "user/book/rental_book_ok";
		
		/*
		Object object = session.getAttribute("loginedUserMemberId");
		
		if (object != null) {
			int result = bookService.rentalBookConfirm(b_no, 0);
			
		} else {
			nextPage = "redirect:/user/member/loginForm";
			
		}
		*/
		
		String loginedUserMemberId = String.valueOf(session.getAttribute("loginedUserMemberId"));
		
		UserMemberDto loginedUserMemberDto = bookService.selectUser(loginedUserMemberId);
		
		int result = bookService.rentalBookConfirm(b_no, loginedUserMemberDto.getU_m_no());
		if (result <= 0)
			nextPage = "user/book/rental_book_ng";
		
		return nextPage;
		
	}
	
	/*
	 * 나의 책장 보기
	 * /book/user/enterBookshelf
	 */
	@GetMapping("/enterBookshelf")
	public String enterBookshelf(HttpSession session, Model model) {
		System.out.println(CLASS_NAME.concat("enterBookshelf()"));
		
		String nextPage = "user/book/bookshelf";
		
		String loginedUserMemberId = String.valueOf(session.getAttribute("loginedUserMemberId"));
		UserMemberDto loginedUserMemberDto = bookService.selectUser(loginedUserMemberId);
		
		List<RentalBookDto> rentalBookDtos =
				bookService.enterBookshelf(loginedUserMemberDto.getU_m_no());
		model.addAttribute("rentalBookDtos", rentalBookDtos);
		
		return nextPage;
		
	}
	
	/*
	 * 전체 대출 이력
	 * /book/user/listupRentalBookHistory
	 */
	@GetMapping("/listupRentalBookHistory")
	public String listupRentalBookHistory(HttpSession session, Model model) {
		System.out.println(CLASS_NAME.concat("listupRentalBookHistory()"));
		
		String nextPage = "user/book/rental_book_history";
		
		String loginedUserMemberId = String.valueOf(session.getAttribute("loginedUserMemberId"));
		UserMemberDto loginedUserMemberDto = bookService.selectUser(loginedUserMemberId);
		
		List<RentalBookDto> rentalBookDtos = bookService.listupRentalBookHistory(loginedUserMemberDto.getU_m_no());
		model.addAttribute("rentalBookDtos", rentalBookDtos);
		
		return nextPage;
		
		
	}
	
	/*
	 * 희망 도서 요청
	 * /book/user/listupRentalBookHistory
	 */
	@GetMapping("/requestHopeBookForm")
	public String requestHopeBookForm() {
		System.out.println(CLASS_NAME.concat("requestHopeBookForm()"));
		
		String nextPage = "user/book/request_hope_book_form";
		
		return nextPage;
		
	}
	
	/*
	 * 희망 도서 요청 확인
	 * /book/user/requestHopeBookConfirm
	 */
	@GetMapping("/requestHopeBookConfirm")
	public String requestHopeBookConfirm(
			HopeBookDto hopeBookDto, 
			HttpSession session) {
		System.out.println(CLASS_NAME.concat("requestHopeBookConfirm()"));
		
		String nextPage = "user/book/request_hope_book_ok";
		
		String loginedUserMemberId = String.valueOf(session.getAttribute("loginedUserMemberId"));
		UserMemberDto loginedUserMemberDto = bookService.selectUser(loginedUserMemberId);
		hopeBookDto.setU_m_no(loginedUserMemberDto.getU_m_no());
		
		int result = bookService.requestHopeBookConfirm(hopeBookDto);
		if (result <= 0)
			nextPage = "user/book/request_hope_book_ng";
		
		return nextPage;
		
	}
	
	/*
	 * 희망 도서 요청 목록
	 * /book/user/listupRentalBookHistory
	 */
	@GetMapping("/listupRequestHopeBook")
	public String listupRequestHopeBook(HttpSession session, Model model) {
		System.out.println(CLASS_NAME.concat("listupRequestHopeBook()"));
		
		String nextPage = "user/book/list_hope_book";
		
		String loginedUserMemberId = 
				String.valueOf(session.getAttribute("loginedUserMemberId"));
		UserMemberDto loginedUserMemberDto = 
				bookService.selectUser(loginedUserMemberId);
		
		List<HopeBookDto> hopeBookDtos =
				bookService.listupRequestHopeBook(loginedUserMemberDto.getU_m_no());
		
		model.addAttribute("hopeBookDtos", hopeBookDtos);
		
		return nextPage;
		
	}
	
}
