<%@page import="test.board.dao.BoardDao"%>
<%@page import="test.board.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1. 파라미터로 전달되는 글의 번호를 읽어와서 
	int num=Integer.parseInt(request.getParameter("num"));
	//2. DB 에서 해당 글의 정보를 읽어와서 
	BoardDto dto=BoardDao.getInstance().getData(num);
	//3. 응답한다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/detail.jsp</title>
<link rel="stylesheet" href="../css/bootstrap.css" />
<style>
	#myModal {
		position: absolute;
		top: 30%;
		left: 30%;
	}
</style>
</head>
<body>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3><%=num %> 번 글의 정보 입니다.</h3>
		</div>
		<div class="panel-body">
			<ul class="list-group">
				<li class="list-group-item">
					<p><span style="color:blue">작성자 :</span> <strong><%=dto.getWriter() %></strong></p>
				</li>
				<li class="list-group-item">
					<p><span style="color:blue">제목 :</span> <strong><%=dto.getTitle() %></strong></p>
				</li>
				<li class="list-group-item">
					<label for="contents" style="color:blue">내용</label>
					<textarea class="form-control" cols="30" rows="10"><%=dto.getContent() %></textarea>
				</li>
				<li class="list-group-item">
					<p><span style="color:blue">작성일 :</span> <strong><%=dto.getRegdate() %></strong></p>
				</li>
			</ul>
		</div>
		<div class="panel-footer">
			<button class="btn btn-warning">
				<a href="updateform.jsp?num=<%=dto.getNum()%>" style="color:black">수정</a>
			</button>
			<button class="btn btn-danger" data-toggle="modal" data-target="#myModal" id="myBtn" style="color:white; font-weight:bold">
			삭제
			</button>
		</div>
	</div>

	<div class="madal fade" id="myModal">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
						<span class="sr-only">모달 닫기</span>
					</button>
					<h4 class="modal-title">글 삭제하기</h4>		
				</div>
				
				<div class="modal-body">
					<p><%=num %> 번째 글을 정말 삭제 하시겠습니까?</p>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger"><a href="delete.jsp?num=<%=dto.getNum()%>" style="color:white">삭제</a></button>
				</div>
				
			</div>
		</div>
	</div>
	
	<script src="../js/jquery-3.2.1.js"></script>
	<script src="../js/bootstrap.js"></script>	
	
	<script>
		$("#myModal").modal({
			backdrop: false,
			show: false
		});
		
		
	</script>
</body>
</html>






















