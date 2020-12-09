<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.jboard1.bean.ArticleBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.co.jboard1.bean.MemberBean"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 현재 로그인 사용자 정보 확인
	MemberBean mb = (MemberBean) session.getAttribute("smember");

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
	String sql = "SELECT a.*, b.nick FROM `JBOARD_ARTICLE` AS a ";
			sql += "JOIN `JBOARD_MEMBER` AS b ";
			sql += "ON a.uid = b.uid ";
			sql += "ORDER BY `seq` DESC;";
	ResultSet rs = stmt.executeQuery(sql);
	
	// 5단계
	List<ArticleBean> articles = new ArrayList();
	
	while(rs.next()){
			
		ArticleBean ab = new ArticleBean();
		
		ab.setSeq(rs.getInt(1));
		ab.setParent(rs.getInt(2));
		ab.setComment(rs.getInt(3));
		ab.setCate(rs.getString(4));
		ab.setTitle(rs.getString(5));
		ab.setContent(rs.getString(6));
		ab.setFile(rs.getInt(7));
		ab.setHit(rs.getInt(8));
		ab.setUid(rs.getString(9));
		ab.setRegip(rs.getString(10));
		ab.setRdate(rs.getString(11));
		ab.setNick(rs.getString(12));
		
		articles.add(ab);
	}
	
	// 6단계
	conn.close();
	stmt.close();
	rs.close();
	
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>글목록</title>
    <link rel="stylesheet" href="/Jboard1/css/style.css">    
</head>
<body>
    <div id="wrapper">
        <section id="board" class="list">
            <h3>글목록</h3>
            <article>
                <p>
                    <%= mb.getName() %>님 반갑습니다.
                    <a href="/Jboard1/user/proc/logout.jsp" class="logout">[로그아웃]</a>
                </p>
                <table border="0">
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>글쓴이</th>
                        <th>날짜</th>
                        <th>조회</th>
                    </tr>
                  	<% for(ArticleBean ab : articles){ %>
                    <tr>
                        <td><%= ab.getSeq() %></td>
                        <td><a href="/Jboard1/view.jsp?seq=<%= ab.getSeq() %>"><%= ab.getTitle() %></a>&nbsp;[<%= ab.getComment() %>]</td>
                        <td><%= ab.getNick() %></td>
                        <td><%= ab.getRdate().substring(2, 10) %></td>
                        <td><%= ab.getHit() %></td>
                    </tr>
                    <% } %>
                </table>
            </article>

            <!-- 페이지 네비게이션 -->
            <div class="paging">
                <a href="#" class="prev">이전</a>
                <a href="#" class="num current">1</a>                
                <a href="#" class="num">2</a>                
                <a href="#" class="num">3</a>                
                <a href="#" class="next">다음</a>
            </div>

            <!-- 글쓰기 버튼 -->
            <a href="/Jboard1/write.jsp" class="btnWrite">글쓰기</a>

        </section>
    </div>    
</body>
</html>