<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

	function deleteBook(b_no, b_name) {
		console.log('deleteBook() CALLED!!');
		console.log('b_no: ' + b_no);
		
		let result = confirm('도서(' + b_name +')를(을) 정말 삭제 할거니?');
		
		if (result)
			location.href = "<c:url value='/book/admin/deleteBookConfirm?b_no='/>"+b_no;
		
	}

</script>