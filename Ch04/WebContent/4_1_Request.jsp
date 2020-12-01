<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 전송되는 파라미터 인코딩 설정(한글파일이 깨지기 때문)
	request.setCharacterEncoding("UTF-8");
	
	// 전송되어 전달하는 데이터(파라미터) 수신
	String uid = request.getParameter("uid");
	String pass = request.getParameter("pass");
	String name = request.getParameter("name");
	String gender = request.getParameter("gender");
	String[] hobbies = request.getParameterValues("hobby");
	String addr = request.getParameter("addr");
	
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>4-1</title>
</head>
<body>
	<h3>4-1.JSP request 내장객체</h3>
	
	<% if(uid != null){ %>
	<h4>로그인 정보</h4>
	<p>
		입력한 아이디 : <%= uid %><br />
		입력한 비밀번호 : <%= pass %><br />
	</p>
	
	<a href="./user/login.jsp">로그인 하기</a>
	<a href="/Ch04/user/register.jsp">회원가입 하기</a>
	
	<% }else { %>
	
	<h4>회원가입 정보</h4>
	<p>
		입력한 이름 : <%= name %><br />
		입력한 성별 : <%= gender %><br />
		입력한 취미 : <%
		for(String hobby : hobbies){
			out.print(hobby+", ");
			}
		%>
		<br />
		입력한 주소 : <%= addr %><br />
	</p>
	
	<a href="/Ch04/user/register.jsp">다시 회원가입 하기</a>
	
	<% } %>
	
</body>
</html>