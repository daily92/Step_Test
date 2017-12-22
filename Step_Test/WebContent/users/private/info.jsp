<%@page import="test.users.dao.UsersDao"%>
<%@page import="test.users.dto.UsersDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 1. 세션에 저장된 id 를 읽어온다.	
	String id= (String)session.getAttribute("id");
	
	// 2. id 를 이용해서 DB 에 가입된 회원 정보를 select 해 온다.
	UsersDto dto= UsersDao.getInstance().getData(id);
	
	// 3. 응답한다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>users/private/info.jsp</title>
<link rel="stylesheet" href="../../css/bootstrap.css" />
</head>
<body>
	<div class="container">
		<h3>지희의 Test 페이지에 회원 가입된 개인 정보에유</h3>
		<table class="table table-hover table-bordered">
			<thead>
				<tr>
					<th>항목</th>
					<th>정보</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>아이디</th>
					<td><%=dto.getId() %></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><%=dto.getEmail() %></td>
				</tr>
				<tr>
					<th>가입일</th>
					<td><%=dto.getRegdate() %></td>
				</tr>
			</tbody>
		</table>
	</div>

	<button class="btn btn-success"><a href="updateform.jsp" style="color:black">가입 정보 수정</a></button>	
	<button class="btn btn-danger"><a href="javascript:deleteConfirm()" style="color:black; font-weight:bold">회원 탈퇴</a></button>
	<button class="btn btn-info"><a href="../../index.jsp" style="color:black">인덱스 페이지로 돌아가기</a></button>
	
	<script>
		function deleteConfirm(){
			var isDelete= confirm("정말 탈퇴할 겨??!");
			if(isDelete){
				location.href= "delete.jsp"
			}
		}
	</script>
</body>
</html>