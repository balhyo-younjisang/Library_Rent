<%@page import="java.util.ArrayList"%>
<%@page import="vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<MemberVO> list = (ArrayList<MemberVO>) request.getAttribute("list");

list.forEach(vo -> {
	if(vo.getStat_fg().equals("0")) vo.setStat_fg("정상");
	else if (vo.getStat_fg().equals("1")) vo.setStat_fg("휴면");
	else vo.setStat_fg("탈퇴");
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
		<button onclick="location='memberAdd.jsp'">회원등록</button>
		<table border="1">
			<tr>
				<th></th>
				<th><strong>회원번호</strong></th>
				<th>회원성명</th>
				<th>핸드폰번호</th>
				<th>주소</th>
				<th>가입일자</th>
				<th>상태구분</th>
			<tr>
				<%
				for (int i = 0; i < list.size(); i++) {
					MemberVO vo = list.get(i);
				%>
			
			<tr>
				<td><%=i + 1%></td>
				<td><a href="MemberEditCtrl?<%=vo.getCust_no()%>"><%=vo.getCust_no()%></a></td>
				<td><%=vo.getCust_name()%></td>
				<td><%=vo.getPhone()%></td>
				<td><%=vo.getAddress()%></td>
				<td><%=vo.getJoin_date()%></td>
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