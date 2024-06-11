<%@page import="vo.RentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
RentVO vo = (RentVO) request.getAttribute("vo");
String alert = (String) request.getAttribute("msg");
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
	<script>
	<%if (alert != null) {%>
		alert('<%=alert%>
		');
		location = "RentCtrl";
	<%}%>
		
	</script>
	<%@ include file="./component/header.jsp"%>
	<%@ include file="./component/nav.jsp"%>
	<section>
		<h2>대여도서 등록</h2>
		<form method="POST" action="RentCtrl">
			<table border="1">
				<tr>
					<th>대여일자</th>
					<td><input value="<%=vo != null ? vo.getRent_ymd() : ""%>"
						name="rent_ymd" disabled></td>
				</tr>
				<tr>
					<th>대여번호(자동채번)</th>
					<td><input value="<%=vo != null ? vo.getRent_no() : ""%>"
						name="rent_no" disabled></td>
				</tr>
				<tr>
					<th>도서코드</th>
					<td><input value="" name="rent_book"></td>
				</tr>
				<tr>
					<th>고객번호</th>
					<td><input value="" name="rent_rent"></td>
				</tr>
				<tr>
					<th>반납기한(대여일 7일 후)</th>
					<td><input value="<%=vo != null ? vo.getClose_ymd() : ""%>"
						disabled name="return_ymd"></td>
				</tr>
				<tr>
					<td colspan="2">
						<button type="submit">등록</button> 
						<button  type="button" onclick="location.href='ViewCtrl'">조회</button>
					</td>
				</tr>
			</table>
		</form>
	</section>
	<%@ include file="./component/footer.jsp"%>
</body>
</html>