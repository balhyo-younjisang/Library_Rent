<%@page import="java.util.ArrayList"%>
<%@page import="vo.RentBookVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<RentBookVO> list = (ArrayList<RentBookVO>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서대여관리 ver 1.0</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/view.css">
</head>
<body>
	<%@ include file="./component/header.jsp"%>
	<%@ include file="./component/nav.jsp"%>
	<section>
		<h2>도서대여관리 프로그램</h2>
		<table border="1">
			<tr>
				<th>대여일자</th>
				<th>대여번호</th>
				<th>도서코드</th>
				<th>대여도서명</th>
				<th>고객번호</th>
				<th>대여고객명</th>
				<th>반납기한</th>
			<tr>
			<% for(RentBookVO vo : list) {%>
				<tr>
					<td><%=vo.getRent_ymd() %></td>
					<td><%=vo.getRent_no() %></td>
					<td><%=vo.getBook_code() %></td>
					<td><%=vo.getBook_name() %></td>
					<td><%=vo.getCust_no() %></td>
					<td><%=vo.getCust_name() %></td>
					<td><%=vo.getClose_ymd() %></td>
				</tr>
			<% } %>
		</table>
	</section>
	<%@ include file="./component/footer.jsp"%>
</body>
</html>