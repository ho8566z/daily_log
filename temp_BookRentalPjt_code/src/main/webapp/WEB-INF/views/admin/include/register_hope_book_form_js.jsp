<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

	function registerHopeBookForm() {
		console.log("registerHopeBookForm() called");
		
		let form = document.register_hope_book_form;
		
		if (form.b_name.value === '') {
			alert('INPUT HOPE BOOK NAME');
			form.b_name.focus();
			
		} else if (form.b_author.value === '') {
			alert('INPUT HOPE BOOK AUTHOR');
			form.b_author.focus();
			
		} else if (form.b_pulisher.value === '') {
			alert('INPUT HOPE BOOK PULISHER');
			form.b_pulisher.focus();
			
		} else if (form.b_pulish_year.value === '') {
			alert('INPUT HOPE BOOK PULISH YEAR');
			form.b_pulish_year.focus();
			
		} else if (form.b_isbn.value === '') {
			alert('INPUT HOPE BOOK ISBN');
			form.b_isbn.focus();
			
		} else if (form.b_call_number.value === '') {
			alert('INPUT HOPE BOOK CALL NUMBER');
			form.b_call_number.focus();
			
		} else if (form.b_rantal_able.value === '') {
			alert('SELECT HOPE BOOK RANTAL ABLE');
			form.b_rantal_able.focus();
			
		} else if (form.file.value === '') {
			alert('SELECT HOPE IMAGE FILE');
			form.file.focus();
			
		} else {
			form.submit();
			
		}
		
	}

</script>