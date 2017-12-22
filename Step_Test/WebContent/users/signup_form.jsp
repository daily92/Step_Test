<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>users/signup_form.jsp</title>
<link rel="stylesheet" href="../css/bootstrap.css"/>
</head>
<body>
	<div class="container">
		<h3>회원 가입 페이지 입니다.</h3>
		<form class="form-inline" action="signup.jsp" method="post">
			<div class="form-group">
				<label for="id">아이디</label>
				<input class="form-control" type="text" name="id" id="id"/>
			</div>
			<div class="form-group">
				<label for="pwd">비밀번호</label>
				<input class="form-control" type="password" name="pwd" id="pwd"/>
			</div>
			<div class="form-group">
				<label for="email">이메일</label>
				<input class="form-control" type="text" name="email" id="email"/>
			</div>
			<button class="btn btn-primary" type="submit">가입!</button>
		</form>
	</div>	
</body>
</html>