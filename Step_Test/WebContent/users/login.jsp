<%@page import="test.users.dao.UsersDao"%>
<%@page import="test.users.dto.UsersDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>users/login.jsp</title>
</head>
<body>
	<%
		// 1. 폼 전송 되는 아이디, 비밀번호를 읽어와서
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		
		UsersDto dto= new UsersDto();
		dto.setId(id);
		dto.setPwd(pwd);
		
		// 2. 유효한 정보인지 확인해서
		boolean isValid= UsersDao.getInstance().isValid(dto);
		
		// 3. 로그인 성공 후 이동할 경로
		String url= request.getParameter("url");
		
		// 4. 응답한다.
	%>
	<%if(isValid){
		// 세션에 로그인 정보를 담는다 (세션에 담으면 페이지 이동해도 살아있돠!!  
			//vs request(servlet 사용하면 request.setAttribute("key",value));)
			//c.f. session.removeAttribute() => 로그 아웃
		session.setAttribute("id", id);
		%>
		<p><strong><%=id %> 회원님 로그인 성공!!</strong></p>
			<!-- 위에서 얻은 url 을 같이 전달  -->
		<a href="<%=url%>">확인(메인으로 돌아가기)</a>
	<%}else{ %>
		<p>아이디 혹은 비밀 번호가 틀려요</p>
			<!-- 위에서 얻은 url 을 같이 전달  -->
		<a href="login_form.jsp?url=<%=url%>">로그인 폼으로 돌아가기</a><br />
		<a href="../index.jsp">인덱스로 돌아가기</a>
	<%} %>
</body>
</html>