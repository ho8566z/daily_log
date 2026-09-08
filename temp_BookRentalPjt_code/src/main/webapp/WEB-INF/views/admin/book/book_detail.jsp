<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link href="<c:url value='/resources/css/admin/book_detail.css' />" rel="stylesheet" type="text/css">

<jsp:include page="../include/book_detail_js.jsp" />

</head>
<body>

	<jsp:include page="../../include/header.jsp" />
	
	<jsp:include page="../include/nav.jsp" />
	
	<section>
	
		<div id="section_wrap">
			
			<div class="word">
			
				<h3>BOOK DETAIL</h3>
			
			</div>
			
			<div class="book_detail">
				<ul>
					<li>
						<img src="<c:url value='/libraryUploadImg/${bookDto.b_thumbnail}' />">
					</li>
					<li>
						<table>
							<tr>
								<td>도서명</td>
								<td>${bookDto.b_name}</td>
							</tr>
							<tr>
								<td>저자</td>
								<td>${bookDto.b_author}</td>
							</tr>
							<tr>
								<td>발행처</td>
								<td>${bookDto.b_pulisher}</td>
							</tr>
							<tr>
								<td>발행연도</td>
								<td>${bookDto.b_pulish_year}</td>
							</tr>
							<tr>
								<td>ISBN</td>
								<td>${bookDto.b_isbn}</td>
							</tr>
							<tr>
								<td>청구기호</td>
								<td>${bookDto.b_call_number}</td>
							</tr>
							<tr>
								<td>대출가능</td>
								<td>
								<c:choose>
									<c:when test="${bookDto.b_rantal_able eq '0'}"><c:out value='X'/></c:when>
									<c:when test="${bookDto.b_rantal_able eq '1'}"><c:out value='O'/></c:when>
									<c:otherwise><c:out value='X'/></c:otherwise>
								</c:choose>								
								</td>
							</tr>
							<tr>
								<td>등록일</td>
								<td>${bookDto.b_reg_date}</td>
							</tr>
							<tr>
								<td>수정일</td>
								<td>${bookDto.b_mod_date}</td>
							</tr>
						</table>
					</li>
				</ul>
			</div>
			
			<div class="buttons">
				
				<c:url value='/book/admin/modifyBookForm' var='modify_url'>
					<c:param name="b_no" value="${bookDto.b_no}"/>
				</c:url>
				<a href="${modify_url}">도서수정</a>
				
				<!--
				<c:url value='/book/admin/deleteBookForm' var='delete_url'>
					<c:param name="b_no" value="${bookDto.b_no}"/>
				</c:url>
				-->
				<a href="#none" onclick="deleteBook(${bookDto.b_no}, '${bookDto.b_name}');">도서삭제</a>
				
			</div>
		
		</div>

	</section>
	
	<jsp:include page="../../include/footer.jsp" />

</body>
</html>