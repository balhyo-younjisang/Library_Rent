<%@page import="vo.MemberVO"%>
<%@page import="vo.RentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
MemberVO vo = (MemberVO) request.getAttribute("vo");
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
		<form method="POST" action="MemberEditCtrl?cust_no=<%=vo.getCust_no()%>">
			<table border="1">
				<tr>
					<th>회원번호</th>
					<td><input value="<%=vo != null ? vo.getCust_no() : ""%>"
						name="cust_no" disabled></td>
				</tr>
				<tr>
					<th>회원성명</th>
					<td><input value="<%=vo != null ? vo.getCust_name() : ""%>"
						name="cust_name"></td>
				</tr>
				<tr>
					<th>핸드폰번호</th>
					<td><input value="<%=vo.getPhone()%>" name="phone"></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input value="<%=vo.getAddress() %>" name="address"></td>
				</tr>
				<tr>
					<th>가입일자</th>
					<td><input value="<%=vo != null ? vo.getJoin_date() : ""%>"
						disabled name="join_date"></td>
				</tr>
				<tr>
					<th>상태구분</th>
					<td>
						<select name="stat_fg">
							<option value="0">정상</option>
							<option value="1">휴면</option>
							<option value="2">탈퇴</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button type="submit">수정</button> 
						<button  type="button" onclick="location.href='ViewCtrl'">조회</button>
					</td>
				</tr>
			</table>
		</form>
	</section>
	<%@ include file="./component/footer.jsp"%>
</body>
</html>