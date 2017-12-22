<%@page import="test.users.dto.UsersDto"%>
<%@page import="test.users.dao.UsersDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>users/private/update.jsp</title>
</head>
<body>
	<%
		/*
			UsersDao 에 update 메소드를 만들어 보세요.
				날짜와 아이디는 수정하지 않음
				public boolean update(UsersDto dto){
			
				}
		*/
		
		// 폼 전송되는 내용을 읽어와서
		request.setCharacterEncoding("utf-8");
		
		String id= request.getParameter("id");
		String pwd= request.getParameter("pwd");
		String email= request.getParameter("email");
		
		UsersDto dto= new UsersDto();
		dto.setId(id);
		dto.setPwd(pwd);
		dto.setEmail(email);
		
		// DB 에 반영하고
		UsersDao.getInstance().update(dto);
		// 응답한다.
	%>
	<p><%=dto.getId() %> 회원님 정보를 수정했습니다.</p>
	<a href="info.jsp">확인</a>
</body>
</html>