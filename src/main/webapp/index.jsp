<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서대여관리 ver 1.0</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/index.css">
</head>
<body>
<%@ include file="./component/header.jsp" %>
<%@ include file="./component/nav.jsp" %>
<section>
<h2>도서대여관리 프로그램</h2>
<p>도서서관 회원정보, 도서정보, 대여정보 데이터베이스를 구축하고 도서대여관리 프로그램을 작성하는 프로그램이다.<p>
<p>프로그램작성순서</p>
<ol>
<li>1. 회원정보 테이블을 생성한다.</li>
<li>2. 도서정보 테이블을 생성한다.</li>
<li>3. 대여정보 테이블을 생성한다.</li>
<li>4. 회원정보, 도서정보, 대여정보 테이블에 제시된 문제지의 참조데이터를 추가 생성한다</li>
<li>5. 도서대여정보입력화면 프로그램을 작성한다.</li>
<li>6. 회원관리 프로그램을 작성한다.</li>
<li>7. 도서관리 프로그램을 작성한다.</li>
<li>8. 도서대여정보 조회 프로그램을 작성한다.</li>
</ol>
</section>
<%@ include file="./component/footer.jsp" %>
</body>
</html>