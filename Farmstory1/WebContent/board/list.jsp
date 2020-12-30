<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.farmstory1.bean.ArticleBean"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="kr.co.farmstory1.db.Sql"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.co.farmstory1.db.DBConfig"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%
	request.setCharacterEncoding("utf-8");
	String gnb = request.getParameter("gnb");
	String cate =request.getParameter("cate");
	
	String path = "./_aside_"+gnb+".jsp";
	
	// 1, 2단계
	Connection conn = DBConfig.getInstance().getConnection();
	
	// 3단계
	PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_ARTICLES);
	psmt.setString(1, cate);
	
	// 4단계
	ResultSet rs = psmt.executeQuery();
	
	// 5단계
	List<ArticleBean> articles = new ArrayList<>();
	while(rs.next()){
		ArticleBean article = new ArticleBean();
		article.setSeq(rs.getInt(1));
		article.setParent(rs.getInt(2));
		article.setComment(rs.getInt(3));
		article.setCate(rs.getString(4));
		article.setTitle(rs.getString(5));
		article.setContent(rs.getString(6));
		article.setFile(rs.getInt(7));
		article.setHit(rs.getInt(8));
		article.setUid(rs.getString(9));
		article.setRegip(rs.getString(10));
		article.setRdate(rs.getString(11));
		
		articles.add(article);
	}
	
	// 6단계
	conn.close();
	psmt.close();
	rs.close();
%>

<jsp:include page="<%= path %>">
	<jsp:param value="<%= cate %>" name="cate"/>
</jsp:include>

        <section id="board" class="list">
            <h3>글목록</h3>
            <article>
                <table border="0">
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>글쓴이</th>
                        <th>날짜</th>
                        <th>조회</th>
                    </tr>
                    <% for(ArticleBean article : articles){ %>
                    <tr>
                        <td><%= article.getSeq() %></td>
                        <td><a href="/Farmstory1/board/view.jsp?gnb=<%= gnb %>&cate=<%= cate %>"><%= article.getTitle() %></a>&nbsp;[<%= article.getComment() %>]</td>
                        <td><%= article.getUid() %></td>
                        <td><%= article.getRdate().substring(2, 10) %></td>
                        <td><%= article.getHit() %></td>
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
            <a href="/Farmstory1/board/write.jsp?gnb=<%= gnb %>&cate=<%= cate %>" class="btnWrite">글쓰기</a>
     </section>
       
        <!-- 내용 끝 -->
        </article>
    </section>
</div>
<%@ include file="../_footer.jsp" %>