<%@page import="vo.BookVO"%>
<%@page import="vo.MemberVO"%>
<%@page import="vo.RentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
BookVO vo = (BookVO) request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서대여관리 ver 1.0</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/rent.css">
</head>
<body>
	<%@ include file="./component/header.jsp"%>
	<%@ include file="./component/nav.jsp"%>
	<section>
		<h2>회원정보관리(수정)</h2>
		<form method="POST" action="BookEditCtrl?book_code=<%=vo.getBook_code()%>">
			<table border="1">
				<tr>
					<th>도서코드(자동채번)</th>
					<td><input value="<%=vo != null ? vo.getBook_code() : ""%>"
						name="book_code" disabled></td>
				</tr>
				<tr>
					<th>도서명</th>
					<td><input value="<%=vo != null ? vo.getBook_name() : ""%>"
						name="book_name"></td>
				</tr>
				<tr>
					<th>장르(A:에세이,B:인문,C:소설)</th>
					<td><input value="<%=vo.getBook_type()%>" name="book_type"></td>
				</tr>
				<tr>
					<th>작가</th>
					<td><input value="<%=vo.getBook_author() %>" name="book_author"></td>
				</tr>
				<tr>
					<th>입고일자</th>
					<td><input value="<%=vo != null ? vo.getIn_date() : ""%>"
						disabled name="in_date"></td>
				</tr>
				<tr>
					<th>도서상태</th>
					<td>
						<select name="stat_fg">
							<option value="0">대여중</option>
							<option value="1">대여가능</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button type="submit">수정</button> 
						<button  type="button" onclick="location.href='BookCtrl'">조회</button>
					</td>
				</tr>
			</table>
		</form>
	</section>
	<%@ include file="./component/footer.jsp"%>
</body>
</html>