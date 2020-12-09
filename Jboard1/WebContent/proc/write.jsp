<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.co.jboard1.bean.MemberBean"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 파라미터 수신
	request.setCharacterEncoding("UTF-8");
	
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String regip = request.getRemoteAddr();
	
	// 세션에서 사용자 구하기
	MemberBean mb = (MemberBean) session.getAttribute("smember");
	String uid = mb.getUid();

	
	// DB정보(HeidiSQL 접속)
	String host = "jdbc:mysql://192.168.10.114:3306/pjw";
	String user = "pjw";
	String pass = "1234";
	
	// 1단계
		Class.forName("com.mysql.jdbc.Driver");
		// 2단계
		Connection conn = DriverManager.getConnection(host, user, pass);
		// 3단계
		Statement stmt = conn.createStatement();
		
		// 4단계
		String sql  = "INSERT INTO `JBOARD_ARTICLE` SET ";
			   sql += "`title`='"+title+"',";
			   sql += "`content`='"+content+"',"; 
			   sql += "`uid`='"+uid+"',";
			   sql += "`regip`='"+regip+"',";
			   sql += "`rdate`=NOW();"; 
			    
		stmt.executeUpdate(sql);
		
		// 5단계
		// 6단계
		stmt.close();
		conn.close();
		
		// 리다이렉트
		response.sendRedirect("/Jboard1/list.jsp");
	
%>