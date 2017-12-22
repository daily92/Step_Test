<%@page import="test.board.dao.BoardDao"%>
<%@page import="test.board.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1. GET 방식 파마미터로 전달되는 수정할 글의 번호를 읽어온다.
	int num=Integer.parseInt(request.getParameter("num"));

	//2. 수정할 글의 정보를 DB 에서 SELECT 한다.
	BoardDto dto=BoardDao.getInstance().getData(num);
	//3. SELECT 된 글 정보를 form 에 출력하는 응답을 한다.
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/updateform.jsp</title>
<link rel="stylesheet" href="../css/bootstrap.css" />
</head>
<body>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 style="color:#603"><%=num %> 번 글 수정 폼입니다.</h3>
		</div>
		<form class="form-inline" action="update.jsp" method="post">
			<div class="panel-body">
				<div class="form-group">
					<input type="hidden" name="num" value="<%=dto.getNum()%>"/>
				</div>
				<div class="form-group">
					<span style="color:blue">번호</span>
					<input type="text" value="<%=dto.getNum()%>" disabled/>
				</div>
				<div class="form-group">
					<span style="color:blue">작성자</span> 
					<input type="text" name="writer" value="<%=dto.getWriter()%>"/>
				</div>
				<div class="form-group">
					<span style="color:blue">제목</span> 
					<input type="text" name="title" 
							value="<%=dto.getTitle()%>"/>
				</div>
				<div class="form-group">
					<span style="color:blue">내용</span> 
					<textarea class="form-control" name="content" cols="30" rows="10"><%=dto.getContent() %></textarea>
				</div>
			</div>
			<div class="panel-footer">
				<button class="btn btn-primary" type="submit" style="color:white">수정 확인</button>
			</div>
		</form>
		
	</div>

</body>
</html>













