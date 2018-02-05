<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>게시판 목록</title>
<script>
$(function(){
	$("#write").on('click', function(e){
		e.preventDefault();
		fn_openBoardWrite();
	});//write
	
	$("a[name='title']").on('click', function(e){
		e.preventDefault();
		fn_openBoardDetail($(this));
	});//a[name='title']
	
	$("#metro").on('click', function(e){
		e.preventDefault();
		fn_openMetroView();
	});//metro
});//jQuery

function fn_openBoardWrite(){
    var comSubmit = new ComSubmit();
    comSubmit.setUrl("<c:url value='/board/openBoardWrite' />");
    comSubmit.submit();
}//fn_openBoardWrite()
 
function fn_openBoardDetail(obj){
    var comSubmit = new ComSubmit();
    comSubmit.setUrl("<c:url value='/board/openBoardDetail' />");
    comSubmit.addParam("IDX", obj.parent().find("#IDX").val());
    comSubmit.submit();
}//fn_openBoardDetail()

function fn_search(pageNo){
    var comSubmit = new ComSubmit();
    comSubmit.setUrl("<c:url value='/board/openBoardList' />");
    comSubmit.addParam("currentPageNo", pageNo);
    comSubmit.submit();
}//fn_search()

function fn_openMetroView(){
	var comSubmit = new ComSubmit();
	comSubmit.setUrl("<c:url value='/metro/openMetroView' />");
	comSubmit.submit();
}

</script>
</head>
<body>
<h2>게시판 목록</h2>
<div>
	<a id="metro" style="cursor: pointer; font-weight: bold;">지하철 정보</a>
</div>
<table class="board_list">
    <colgroup>
        <col width="10%"/>
        <col width="*"/>
        <col width="15%"/>
        <col width="20%"/>
    </colgroup>
    <thead>
        <tr>
            <th scope="col">글번호</th>
            <th scope="col">제목</th>
            <th scope="col">조회수</th>
            <th scope="col">작성일</th>
        </tr>
    </thead>
    <tbody>
        <c:choose>
            <c:when test="${fn:length(list) > 0}">
                <c:forEach items="${list }" var="row">
                    <tr>
                        <td>${row.IDX }</td>
                        <td class="title">
                        	<a href="#this" name="title">${row.TITLE }</a>
                        	<input type="hidden" id="IDX" value="${row.IDX }" />
                        </td>
                        <td>${row.HIT_CNT }</td>
                        <td>${row.CREA_DTM }</td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="4">조회된 결과가 없습니다.</td>
                </tr>
            </c:otherwise>
        </c:choose>
    </tbody>
</table>

<!-- 페이징(전자정부프레임워크) -->
<c:if test="${not empty paginationInfo}">
	<ui:pagination paginationInfo = "${paginationInfo}" type="text" jsFunction="fn_search"/>
</c:if>
<input type="hidden" id="currentPageNo" name="currentPageNo"/>

<br/>
<a href="#this" class="btn" id="write">글쓰기</a>

<%@ include file="/WEB-INF/include/include-body.jsp" %>
</body>
</html>
