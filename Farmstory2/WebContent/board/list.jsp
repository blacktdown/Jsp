<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../_header.jsp" %>
<c:if test="${group eq 'croptalk'}"><%@ include file="./_aside_croptalk.jsp" %></c:if>
<c:if test="${group eq 'market'}"><%@ include file="./_aside_market.jsp" %></c:if>
<c:if test="${group eq 'event'}"><%@ include file="./_aside_event.jsp" %></c:if>
<c:if test="${group eq 'community'}"><%@ include file="./_aside_community.jsp" %></c:if>
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
               <c:forEach var="article" items="${articles}">
               <tr>
                   <td>${article.seq}</td>
                   <td><a href="/Farmstory2/board/view.do?group=${group}&cate=${cate}&seq=${article.seq}">${article.title}</a>&nbsp;[${article.comment}]</td>
                   <td>${article.nick}</td>
                   <td>${article.rdate}</td>
                   <td>${article.hit}</td>
               </tr>
               </c:forEach>
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
       <a href="/Farmstory2/board/write.do?group=${group}&cate=${cate}" class="btnWrite">글쓰기</a>

   </section>
    <!-- 내용 끝 -->

        </article>
    </section>

</div>
<%@ include file="../_footer.jsp" %>