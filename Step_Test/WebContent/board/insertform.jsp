<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/insertform.jsp</title>
<link rel="stylesheet" href="../css/bootstrap.css" />
</head>
<body>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>새글 작성 페이지 입니다</h3>
		</div>
		<form class="form-inline" action="insert.jsp" method="post">
			<div class="panel-body">
				<div class="form-group">
					<label for="writer">작성자</label>
					<input type="text" name="writer" id="writer"/><br/>
				</div>
				<div class="form-group">
					<label for="title">제목</label>
					<input type="text" name="title" id="title"/><br/>
				</div>
				<div class="form-group">
					<label for="content">내용</label>
					<textarea class="form-control" name="content" id="content" cols="30" rows="10"></textarea>
				</div>
				<div class="panel-footer">
					<button class="btn btn-primary" type="submit" style="color:white">저장</button>
				</div>
			</div>
		</form>	
	</div>		
</body>
</html>






