<%@page import="test.users.dao.UsersDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>users/private/delete.jsp</title>
</head>
<body>
	<%
		//ctrl+space 하면 L (Local Valuable) 여럿 뜸 => JSP 기본 객체 (<% 안에서 그냥 사용할 수 있는!)
		//out(클라이언트에게 출력할 수 있는 객체), request(HttpServletRequest), 
		//response(HttpServletResponse), session(HttpSession) 알아두세요!
		
		// 1. 삭제할 아이디를 읽어와서 (세션에서 읽어와서)
		String id= (String)session.getAttribute("id");
		
		// 2. DB 에서 삭제하고 (dao 에 delete() 만들어서 호출)
		UsersDao.getInstance().delete(id);
		session.invalidate();	// 세션 초기화
		
		// 3. 응답한다.
	%>
	<p> <strong><%=id %></strong> 회원님의 탈퇴가 성공적으로 처리 되었습니다.</p>
	<a href="${pageContext.request.contextPath }/">인덱스로 돌아가기</a>
</body>
</html>