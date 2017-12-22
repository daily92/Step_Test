<%@page import="test.board.dto.BoardDto"%>
<%@page import="test.board.dao.BoardDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<BoardDto> list= BoardDao.getInstance().getList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/list.jsp</title>
<link rel="stylesheet" href="../css/bootstrap.css" />
</head>
<body>
	<button class="btn btn-primary"><a href="insertform.jsp" style="color:white">새 글 작성하기</a></button>

	<table class="table table-bordered table-hover">
		<colgroup>
    		<col class="col-xs-1"/>
    		<col class="col-xs-2"/>
    		<col class="col-xs-4"/>
    		<col class="col-xs-5"/>
    	</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>등록일</th>
			</tr>
		</thead>
		<tbody>
			<%for(BoardDto tmp:list){%>
			<tr>
				<td><%=tmp.getNum() %></td>
				<td><%=tmp.getWriter() %></td>
				<td><a href="detail.jsp?num=<%=tmp.getNum()%>"><%=tmp.getTitle() %></a></td>
				<td><%=tmp.getRegdate() %></td>
			</tr>
			<%} %>
		</tbody>
	</table>	
</body>
</html>