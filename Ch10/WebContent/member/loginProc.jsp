<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");	// 한국어 지정
	
	// 데이터 수신
	String uid = request.getParameter("uid");
	String pass = request.getParameter("pass");
	
	// 1단계
	// 2단계
	// 3단계
	// 4단계
	// 5단계
	// 6단계
	
	if(uid.equals("abcd") && pass.equals("1234")){
		//회원이 맞을 경우
		session.setAttribute("uid", uid);
		session.setAttribute("pass", pass);
		
		response.sendRedirect("./loginSuccess.jsp"); // 성공했을 때의 경로
		
	} else{
		//회원이 틀릴 경우
		response.sendRedirect("./login.jsp");	// 실패했을 때의 경로
	}
	
%>