<%@page import="kr.co.farmstory1.db.Sql"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.co.farmstory1.db.DBConfig"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%	
	request.setCharacterEncoding("utf-8");
	
	String filePath = request.getServletContext().getRealPath("/file");
	int maxFileSize = 1024 * 1024 * 10;
	MultipartRequest mRequest = new MultipartRequest(request, 
												 	 filePath, 
		                                         	 maxFileSize, 
		                                         	 "UTF-8", 
		                                         	 new DefaultFileRenamePolicy());
	
	
	String gnb     = mRequest.getParameter("gnb");
	String cate    = mRequest.getParameter("cate");
	String title   = mRequest.getParameter("title");
	String content = mRequest.getParameter("content");
	String fName   = mRequest.getParameter("fName");
	String uid     = mRequest.getParameter("uid");
	String regip   = request.getRemoteAddr();
	
	int file = fName == null ? 0 : 1;
	
	// 1, 2단계
	Connection conn = DBConfig.getInstance().getConnection();
	
	// 3단계	
	PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_ARTICLE);
	psmt.setString(1, cate);
	psmt.setString(2, title);
	psmt.setString(3, content);
	psmt.setInt(4, file);
	psmt.setString(5, uid);
	psmt.setString(6, regip);
	
	// 4단계
	psmt.executeUpdate();
	
	// 5단계
	// 6단계
	psmt.close();
	conn.close();
	
	response.sendRedirect("/Farmstory1/board/list.jsp?gnb="+gnb+"&cate="+cate);
	
%>
