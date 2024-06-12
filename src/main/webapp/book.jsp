<%@page import="vo.BookVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<BookVO> list = (ArrayList<BookVO>) request.getAttribute("list");

list.forEach(vo -> {
	if(vo.getStat_fg().equals("0")) vo.setStat_fg("대여가능");
	else if (vo.getStat_fg().equals("1")) vo.setStat_fg("대여중");
});

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서대여관리 ver 1.0</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/member.css">
</head>
<body>
	<%@ include file="./component/header.jsp"%>
	<%@ include file="./component/nav.jsp"%>
	<section>
		<button onclick="location='BookAddCtrl'" style="margin : 0 auto">도서등록</button>
		<table border="1">
			<tr>
				<th></th>
				<th><strong>도서코드</strong></th>
				<th>도서명</th>
				<th>장르</th>
				<th>작가</th>
				<th>입고일자</th>
				<th>도서상태</th>
			<tr>
				<%
				for (int i = 0; i < list.size(); i++) {
					BookVO vo = list.get(i);
				%>
			
			<tr>
				<td><%=i + 1%></td>
				<td><a href="BookEditCtrl?book_code=<%=vo.getBook_code()%>"><%=vo.getBook_code()%></a></td>
				<td><%=vo.getBook_name()%></td>
				<td><%=vo.getBook_type()%></td>
				<td><%=vo.getBook_author()%></td>
				<td><%=vo.getIn_date()%></td>
				<td><%=vo.getStat_fg()%></td>
			</tr>
			<%
			}
			%>
		</table>
	</section>
	<%@ include file="./component/footer.jsp"%>
</body>
</html>