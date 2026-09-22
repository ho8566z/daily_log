<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">

	function registerBookForm() {
		console.log('registerBookForm() CALLED!!');
		
		let form = document.register_book_form;
		
		if (form.b_name.value === '') {
			alert('INPUT BOOK NAME');
			form.b_name.focus();
			
		} else if (form.b_author.value === '') {
			alert('INPUT BOOK AUTHOR');
			form.b_author.focus();
			
		} else if (form.b_pulisher.value === '') {
			alert('INPUT BOOK PULISHER');
			form.b_pulisher.focus();
			
		} else if (form.b_pulish_year.value === '') {
			alert('INPUT BOOK PULISH YEAR');
			form.b_pulish_year.focus();
			
		} else if (form.b_isbn.value === '') {
			alert('INPUT BOOK ISBN');
			form.b_isbn.focus();
			
		} else if (form.b_call_number.value === '') {
			alert('INPUT BOOK CALL NUMBER');
			form.b_call_number.focus();
			
		} else if (form.b_rantal_able.value === '') {
			alert('SELECT BOOK RANTAL ABLE');
			form.b_rantal_able.focus();
			
		} else if (form.file.value === '') {
			alert('SELECT IMAGE FILE');
			form.file.focus();
			
		} else {
			form.submit();
			
		}
		
	}

</script>