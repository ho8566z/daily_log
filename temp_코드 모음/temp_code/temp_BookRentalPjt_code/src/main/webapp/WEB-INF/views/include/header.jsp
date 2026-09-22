<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<c:url value='/resources/css/include/header.css' />" rel="stylesheet" type="text/css">
<header>

	<div id="header_wrap">
		<div class="menu">
			<ul>
				<li><a class="user" href="<c:url value='/'/>">USER HOME</a></li>
				<li><a class="admin" href="<c:url value='/admin'/>">ADMIN HOME</a></li>
			</ul>
		</div>
		<div class="title">
			<h3>대전 도서관 - 도서 대여 서비스</h3>
		</div>
	</div>

</header>