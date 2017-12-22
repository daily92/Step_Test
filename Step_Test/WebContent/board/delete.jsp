<%@page import="test.board.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 1. GET 방식 파라미터로 전달되는 삭제할 회원의 번호 읽어오기
	int num=Integer.parseInt(request.getParameter("num"));

	// 2. DB 에서 삭제하기
	BoardDao.getInstance().delete(num);
	// 3. 응답하기 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/delete jsp</title>
</head>
<body>
<script>
	alert("<%=num%> 번 글을 삭제했돠!!!");
	location.href="list.jsp";
</script>
</body>
</html>












