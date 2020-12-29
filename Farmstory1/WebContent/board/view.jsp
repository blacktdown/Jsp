<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%
	request.setCharacterEncoding("utf-8");
	String gnb = request.getParameter("gnb");
	String cate =request.getParameter("cate");
	
	String path = "./_aside_"+gnb+".jsp";
%>

<jsp:include page="<%= path %>">
	<jsp:param value="<%= cate %>" name="cate"/>
</jsp:include>

        <section id="board" class="view">
            <h3>글보기</h3>
            <table>
                <tr>
                    <td>제목</td>
                    <td><input type="text" name="title" value="제목입니다." readonly/></td>
                </tr>
                <tr>
                    <td>첨부파일</td>
                    <td>
                        <a href="#">2020매출현황.xls</a>
                        <span>5회 다운로드</span>
                    </td>
               
                </tr>
                <tr>
                    <td>내용</td>
                    <td>
                        <textarea name="content" readonly>내용입니다.</textarea>
                    </td>
                </tr>
            </table>
            <div>
                <a href="#" class="btnDelete">삭제</a>
                <a href="/Farmstory1/board/modify.jsp?gnb=<%= gnb %>&cate=<%= cate %>" class="btnModify">수정</a>
                <a href="/Farmstory1/board/list.jsp?gnb=<%= gnb %>&cate=<%= cate %>" class="btnList">목록</a>
            </div>  
            
            <!-- 댓글리스트 -->
            <section class="commentList">
                <h3>댓글목록</h3>
                <article class="comment">
                    <span>
                        <span>길동이</span>
                        <span>20-12-29</span>
                    </span>
                    <textarea name="comment" readonly></textarea>
		               <div>
		                   <a href="#" class="cmtDelete">삭제</a>
		                   <a href="#" class="cmtModify">수정</a>
		               </div>
                </article> 
                <p class="empty">
                    등록된 댓글이 없습니다.
                </p>
            </section>

            <!-- 댓글입력폼 -->
            <section class="commentForm">
                <h3>댓글쓰기</h3>
                <form action="#" method="post">
                <input type="hidden" name="parent" value=""/>
                    <textarea name="comment"></textarea>
                    <div>
                        <a href="#" class="btnCancel">취소</a>
                        <input type="submit" class="btnWrite" value="작성완료"/>
                    </div>
                </form>
            </section>

        </section>
        
        <!-- 내용 끝 -->
        </article>
    </section>
</div>
       
<%@ include file="../_footer.jsp" %>