<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>글보기</title>
    <link rel="stylesheet" href="./css/style.css"/>
</head>
<body>
    <div id="wrapper">
        <section id="board" class="view">
            <h3>글보기</h3>
            <table>
                <tr>
                    <td>제목</td>
                    <td><input type="text" name="title" value="${article.title}" readonly/></td>
                </tr>
                <c:if test="${article.file == 1}">
                <tr>
                    <td>첨부파일</td>
                    <td>
                        <a href="#">2020년 상반기 매출자료.xls</a>
                        <span>7회 다운로드</span>
                    </td>
                </tr>
                </c:if>
                <tr>
                    <td>내용</td>
                    <td>
                        <textarea name="content" readonly>${article.content}</textarea>
                    </td>
                </tr>
            </table>
            <div>
                <a href="/Jboard2/delete.do" class="btnDelete">삭제</a>
                <a href="/Jboard2/modify.do" class="btnModify">수정</a>
                <a href="/Jboard2/list.do" class="btnList">목록</a>
            </div>  
            
            <!-- 댓글리스트 -->
            <section class="commentList">
                <h3>댓글목록</h3>
                <c:if test="${fn:length(comments) > 0}">
                	<c:forEach var="vo" items="${comments}">
	                <article class="comment">
	                    <span>
	                        <span>${vo.uid}</span>
	                        <span>${vo.rdate.substring(2, 10)}</span>
	                    </span>
	                    <textarea name="comment" readonly>${vo.content}</textarea>
	                    <div>
	                        <a href="#">삭제</a>
	                        <a href="#">수정</a>
	                    </div>
	                </article>
	                </c:forEach>
                </c:if>
                <c:if test="${fn:length(comments) == 0}">
	                <p class="empty">
	                    등록된 댓글이 없습니다.
	                </p>
                </c:if>
            </section>

            <!-- 댓글입력폼 -->
            <section class="commentForm">
                <h3>댓글쓰기</h3>
                <form action="/Jboard2/comment.do" method="post">
                	<input type="hidden" name="uid" value="${sessionScope.smember.uid}"/>
                	<input type="hidden" name="parent" value="${article.seq}"/>
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