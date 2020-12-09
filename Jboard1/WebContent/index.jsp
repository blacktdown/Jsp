<%@page import="kr.co.jboard1.bean.MemberBean"%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	MemberBean mb = (MemberBean)session.getAttribute("smember");

	if(mb == null){
		pageContext.forward("./user/login.jsp");	
	}else{
		pageContext.forward("./list.jsp");
	}
	
%>