<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id= (String)session.getAttribute("id");
	String cPath= request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
<link rel="stylesheet" href="../css/bootstrap.css" />
</head>
<body>
	<h3>지희의 Test 페이지에 오신 것을 환영합니다!</h3>
	<%if(id!=null){ %>
		<p><a href="users/private/info.jsp"><%=id %>님</a> 현재 로그인 중입니다</p>
		<a href="users/logout.jsp">로그아웃</a><br />
		<a href="${pageContext.request.contextPath}/board/list.jsp">방명록 보기</a><br />
		<a href="${pageContext.request.contextPath}/file/list.jsp">자료실 가기</a>
	<%}else{ %>
		<ul>
			<li><p>아직 회원이 아니시라면 <a href="users/signup_form.jsp">회원가입</a>을 해주세욥</p></li>
			<li><p>회원이시라면 <a href="users/login_form.jsp">로그인</a>을 해주세욤</p></li>
		</ul>
	<%} %>
</body>
</html>