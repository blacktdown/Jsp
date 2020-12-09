<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	
	//파라미터 수신
	request.setCharacterEncoding("UTF-8");
	String seq = request.getParameter("seq");

	// DB정보(HeidiSQL 접속)
	String host = "jdbc:mysql://192.168.10.114:3306/pjw";
	String user = "pjw";
	String pass = "1234";

	// 1단계
	Class.forName("com.mysql.jdbc.Driver");

	// 2단계
	Connection conn = DriverManager.getConnection(host, user, pass);
	
	// 3단계
	String sql = "DELETE FROM `JBOARD_ARTICLE` WHERE `seq`=?";
	PreparedStatement psmt = conn.prepareStatement(sql);
	psmt.setString(1, seq);
	
	// 4단계
	psmt.executeUpdate();
	
	// 5단계
	
	// 6단계
	conn.close();
	psmt.close();
	
	// 리다이렉트
	response.sendRedirect("/Jboard1/list.jsp");
	
%>