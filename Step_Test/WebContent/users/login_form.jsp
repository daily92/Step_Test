<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>users/login_form.jsp</title>
<link rel="stylesheet" href="../css/bootstrap.css"/>
</head>
<body>
	<%
		// 로그인 후 redirect 이동할 url 주소를 읽어온다.
		String url= request.getParameter("url");
		if(url==null){	// 만일 없으면 인덱스 페이지로 이동될 수 있도록
			url= request.getContextPath();
		}
		String savedId="";
		Cookie[] cookies= request.getCookies();
		
		if(cookies!=null && cookies.length>0){
			for(Cookie tmp:cookies){
				if(tmp.getName().equals("savedId")){
					savedId= tmp.getValue();
				}
			}
		}
	%>
	<div class="container">
		<h3>로그인 페이지 입니다.</h3>
			<!-- 위에서 얻은 url 을 login.jsp 로 get 방식으로  전달 -->
		<form class="form-inline" action="login.jsp?url=<%=url %>" method="post">
			<div class="form-group">
				<label for="id">아이디</label>
				<input class="form-control" type="text" name="id" id="id"/>
			</div>
			<div class="form-group">
				<label for="pwd">비밀번호</label>
				<input class="form-control" type="password" name="pwd" id="pwd"/>
			</div>
			<div class="form-group">
				<label>
					<input type="checkbox" name="isSave" value="yes"/>
					아이디 저장
				</label>
			</div>
			<button class="btn btn-primary" type="submit">로그인</button>
		</form>
	</div>
		
</body>
</html>