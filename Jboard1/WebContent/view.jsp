<%@page import="java.util.List"%>
<%@page import="kr.co.jboard1.dao.ArticleDao"%>
<%@page import="kr.co.jboard1.bean.ArticleBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	
	// 파라미터 수신
	request.setCharacterEncoding("UTF-8");
	String seq = request.getParameter("seq"); // list 에서 따옴
	
	// 조회글 가져오기
	ArticleBean ab = ArticleDao.getInstance().selectArticle(seq);
	
	// 조회수 업데이트
	ArticleDao.getInstance().updateHit(seq);
	
	// 댓글 가져오기
	List<ArticleBean> comments = ArticleDao.getInstance().selectComments(seq);

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>글보기</title>
    <link rel="stylesheet" href="/Jboard1/css/style.css"/>
</head>
<body>
    <div id="wrapper">
        <section id="board" class="view">
            <h3>글보기</h3>
            <table>
                <tr>
                    <td>제목</td>
                    <td><input type="text" name="title" value="<%= ab.getTitle() %>" readonly/></td>
                </tr>
                <% if(ab.getFile() != 0){ %>
                <tr>
                    <td>첨부파일</td>
                    <td>
                        <a href="/Jboard1/proc/download.jsp?seq=<%= ab.getFileseq()%>"><%= ab.getOldName() %></a>
                        <span><%= ab.getDownload() %>회 다운로드</span>
                    </td>
               
                </tr>
                <% } %>
                <tr>
                    <td>내용</td>
                    <td>
                        <textarea name="content" readonly><%= ab.getContent() %></textarea>
                    </td>
                </tr>
            </table>
            <div>
                <a href="/Jboard1/proc/delete.jsp?seq=<%= ab.getSeq() %>" class="btnDelete">삭제</a>
                <a href="/Jboard1/modify.jsp?seq=<%= ab.getSeq() %>" class="btnModify">수정</a>
                <a href="/Jboard1/list.jsp" class="btnList">목록</a>
            </div>  
            
            <!-- 댓글리스트 -->
            <section class="commentList">
                <h3>댓글목록</h3>
                <% if(ab.getComment() > 0) { 
                	for(ArticleBean comment : comments){
                		
                	
                %>
                <article class="comment">
                    <span>
                        <span><%= comment.getNick() %></span>
                        <span><%= comment.getRdate().substring(2, 10) %></span>
                    </span>
                    <textarea name="comment" readonly><%= comment.getContent() %></textarea>
                    <div>
                        <a href="#">삭제</a>
                        <a href="#">수정</a>
                    </div>
                </article>
                <%
                	}
                }else{ %>
                <p class="empty">
                    등록된 댓글이 없습니다.
                </p>
                <% } %>
            </section>

            <!-- 댓글입력폼 -->
            <section class="commentForm">
                <h3>댓글쓰기</h3>
                <form action="/Jboard1/proc/comment.jsp" method="post">
                <input type="hidden" name="parent" value="<%= ab.getSeq() %>"/>
                    <textarea name="comment"></textarea>
                    <div>
                        <a href="#" class="btnCancel">취소</a>
                        <input type="submit" class="btnWrite" value="작성완료"/>
                    </div>
                </form>
            </section>

        </section>
    </div>    
</body>
</html>