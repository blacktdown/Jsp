<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 데이터(Parameter)수신
	request.setCharacterEncoding("UTF-8");
	
	String uid = request.getParameter("uid");
	String name = request.getParameter("name");
	String hp = request.getParameter("hp");
	String pos = request.getParameter("pos");
	String dep = request.getParameter("dep");
	
	// DB정보(HeidiSQL 접속)
	String host = "jdbc:mysql://192.168.10.114:3306/pjw";
	String user = "pjw";
	String pass = "1234";
	
	// 1단계	- JDBC 드라이버 로드
	Class.forName("com.mysql.jdbc.Driver");
	
	// 2단계 - 데이터베이스 접속
	Connection conn = DriverManager.getConnection(host, user, pass);
	
	// 3단계 - SQL 실행 객체 생성
	Statement stmt = conn.createStatement();
	
	// 4단계 - SQL 실행
	String sql = "UPDATE `MEMBER` SET";
			sql += "`name`='"+name+"',";
			sql += "`hp`='"+hp+"',";
			sql += "`pos`='"+pos+"',";
			sql += "`dep`="+dep+" ";
			sql += "WHERE `uid`='"+uid+"';";
	stmt.executeUpdate(sql);
	
	// 5단계 - 결과셋처리(INSERT일 경우)
	// 6단계 - 데이터베이스 종료
	conn.close();
	stmt.close();
	
	// 리다이렉트
	response.sendRedirect("/Ch09/employee/list.jsp");


%>