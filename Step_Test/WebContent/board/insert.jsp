<%@page import="test.board.dao.BoardDao"%>
<%@page import="test.board.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/insert.jsp</title>
</head>
<body>
	<%
		// 1. 폼 전송되는 내용을 읽어내서 
		// 한글 깨지지 않도록 
		request.setCharacterEncoding("utf-8");
		// 폼 전송 파라미터 추출
		String writer=request.getParameter("writer");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		// BoardDto 에 담기
			//BoardDto dto= new BoardDto(writer,title,content); 하면 오류남
			//dto 의 구성요소에는 writer, title, content, regdate 있는데 다 안 들어가서!
		BoardDto dto=new BoardDto();
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);
		// 2. DB 에 저장한다.
		BoardDao.getInstance().insert(dto);
		// 3. 응답한다.
	%>
	<h3>새글 정보를 저장했습니다.</h3>
	<a href="list.jsp">목록보기</a>
</body>
</html>





























