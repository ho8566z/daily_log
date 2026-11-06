<%@page import="com.office.ex.VaccineConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>VACCINE MANAGEMENT SYSTEM</title>

<style type="text/css">

	table {
		width: 800px;
		border-collapse: collapse;
	}
	
	td {
		padding: 10px;
		border: 1px solid #dadada;
	}
	
	td:first-child {
		width: 385px;
		text-align: right;
	}
	
	tr:last-child td {
		text-align: center;
	}

</style>

<script>

function vaccineForm() {
	console.log('vaccineForm() CALLED!!');
	
	let form = document.vaccine_register_form;
	if (form.fvp_name.value === '') {
		alert('Please Enter NAME!!');
		form.fvp_name.focus();
		
	} else if (form.ss_number.value === '') {
		alert('Please Enter SOCIAL NUMBER!!');
		form.ss_number.focus();
		
	} else if (form.fvp_mail.value === '') {
		alert('Please Enter MAIL!!');
		form.fvp_mail.focus();
		
	} else if (form.fvp_phone.value === '') {
		alert('Please Enter PHONE!!');
		form.fvp_phone.focus();
		
	} else if (form.fv_location_no.value === '') {
		alert('Please Select LOCATION!!');
		form.fvp_location_no.focus();
		
	} else if (form.fv_type.value === '') {
		alert('Please Select TYPE!!');
		form.fv_type.focus();
		
	} else {
		form.submit();
		
	}
	
}

</script>

</head>
<body>

	<h1>백신 접종 현황 - 등록화면</h1>
	
	<jsp:include page="./include/nav.jsp" />

	<form
		action="<%= request.getContextPath().concat(VaccineConfig.VACCINE_RESISTER_CONFIRM) %>"
		name="vaccine_register_form"
		method="post">
		  
		 <table>
		 	<tr>
		 		<td>접종자 이름</td>
		 		<td><input type="text" name="fvp_name" placeholder="ENTER NAME!!"></td>
		 	</tr>
		 	<tr>
		 		<td>주민등록 번호</td>
		 		<td><input type="text" name="ss_number" placeholder="ENTER SOCIAL NUMBER!!"></td>
		 	</tr>
		 	<tr>
		 		<td>접종자 메일</td>
		 		<td><input type="email" name="fvp_mail" placeholder="ENTER MAIL!!"></td>
		 	</tr>
		 	<tr>
		 		<td>접종자 연락처</td>
		 		<td><input type="text" name="fvp_phone" placeholder="ENTER PHONE!!"></td>
		 	</tr>
		 	<tr>
		 		<td>접종처 번호</td>
		 		<td>
		 			<select name="fv_location_no">
		 				<option value="">-- 접종처 선택 --</option>
		 				<option value="1001">A접종처</option>
		 				<option value="1002">B접종처</option>
		 				<option value="1003">C접종처</option>
		 				<option value="1004">D접종처</option>
		 				<option value="1005">E접종처</option>
		 			</select>
		 		</td>
		 	</tr>
		 	<tr>
		 		<td>백신 타입</td>
		 		<td>
		 			<select name="fv_type">
		 				<option value="">-- 백신타입 선택 --</option>
		 				<option value="A">A타입</option>
		 				<option value="B">B타입</option>
		 				<option value="C">C타입</option>
		 				<option value="D">D타입</option>
		 				<option value="E">E타입</option>
		 			</select>
		 		</td>
		 	</tr>
		 	<tr>
		 		<td colspan="2">
		 			<input type="button" value="REGISTE" onclick="vaccineForm();">
		 			<input type="reset" value="RESET">
		 		</td>
		 	</tr>
		 </table>
	
	</form>

</body>
</html>