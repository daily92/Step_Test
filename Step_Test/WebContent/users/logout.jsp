<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>users/logout.jsp</title>
</head>
<body>
	<%
		// 세션 영역에 id 라는 키값으로 저장된 값 삭제
		session.removeAttribute("id");
		// session.invalidate();		// 세션에 저장된 모든 내용 삭제
	%>
	<script>
		alert("안전하게 로그아웃 되었습니다!");
		location.href="${pageContext.request.contextPath}/";
	</script>
</body>
</html>