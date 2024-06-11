<%@page import="vo.MemberVO"%>
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
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/memberAdd.css">
</head>
<body>
	<%@ include file="./component/header.jsp"%>
	<%@ include file="./component/nav.jsp"%>
	<section>
	<h2>회원정보관리(등록)</h2>
	<form method="POST" action="MemberAddCtrl">
			<table border="1">
				<tr>
					<th>회원번호</th>
					<td><input value="<%=vo.getCust_no() %>"
						name="cust_no" disabled></td>
				</tr>
				<tr>
					<th>회원성명</th>
					<td><input value=""
						name="cust_name"></td>
				</tr>
				<tr>
					<th>핸드폰번호</th>
					<td><input value="" name="phone"></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input value="" name="address"></td>
				</tr>
				<tr>
					<th>가입일자</th>
					<td><input value="<%=vo.getJoin_date().toString().replace("-", "") %>"
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
						<button type="submit">등록</button> 
						<button type="button" onclick="location.href='MemberCtrl'">조회</button>
					</td>
				</tr>
			</table>
		</form>
	</section>

	<%@ include file="./component/footer.jsp"%>
</body>
</html>