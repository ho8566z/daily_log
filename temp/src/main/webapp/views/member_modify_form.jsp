<%@page import="java.util.List"%>
<%@page import="com.office.toypjt.member.MemberConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="./include/title.jsp" />

<link href="./css/index.css" rel="stylesheet" type="text/css">

<script>

function memberDelete(memId) {
	console.log('memberDelete() called');
	console.log('memId : ', memId);
	
	let result = confirm('정말 삭제하겠습니까?');
	console.log('result : ', result);
	
	if (result) {
		location.href = '<%= request.getContextPath() %><%= MemberConfig.MEMBER_DELETE %>?memId=' + memId;
	}
	
}

</script>

</head>
<body>

	<h1>MEMBER MODIFY FORM</h1>
	
	<jsp:include page="./include/nav.jsp" />
	
	<div>
	
		<form 
			action="<%= request.getContextPath().concat(MemberConfig.MEMBER_MODIFY_CONFIRM) %>" 
			name="member_modify_form" 
			method="post">
			
			<input type="hidden" name="memNo" value="${currentSigninedMember.memNo}">
			<input type="hidden" name="memId" value="${currentSigninedMember.memId}">

			<input type="text" value="${currentSigninedMember.memId}" readonly disabled>
			<br>
			<input type="password" name="memPw" value="${currentSigninedMember.memPw}" placeholder="Input Member PW">
			<br>
			<input type="email" name="memMail" value="${currentSigninedMember.memMail}" placeholder="Input Member MAIL">
			<br>
			<input type="text" name="memPhone" value="${currentSigninedMember.memPhone}" placeholder="Input Member PHONE">
			<input type="submit" value="MODIFY">
			<input type="reset" value="RESET">
		</form>
		
		<a href="#none" 
			class="delete_btn"
			onclick="memberDelete('${currentSigninedMember.memId}')">
			DELETE
		</a>
	
	</div>

</body>
</html>