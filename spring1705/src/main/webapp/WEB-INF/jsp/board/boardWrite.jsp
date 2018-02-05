<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 작성</title>
 <script type="text/javascript">
//첨부파일 개수. 기본적으로 하나가 있기 때문에, 하나 추가하여 2개가 될 때
//1을 출력하고 gfv_count를 ++ 시켜서 2로 늘리는, 그 이상의 파일 추가시, 계속 ++된다.
 var gfv_count = 1; 
 
 $(document).ready(function(){
	 var date = new Date();
	 $("#date").text(date.format("yyyy-MM-dd a/p hh:mm:ss"));
	 
     $("#list").on("click", function(e){
         e.preventDefault();
         fn_openBoardList();
     });    
     
     $("#write").on("click", function(e){ //작성하기 버튼
         e.preventDefault();
         fn_insertBoard();
     });
     
     $("#addFile").on("click", function(e){ //파일 추가 버튼
         e.preventDefault();
         fn_addFile();
     });
      
     $("a[name='delete']").on("click", function(e){ //삭제 버튼
         e.preventDefault();
         fn_deleteFile($(this));
     });
 });//jQuery
  
 
function fn_openBoardList(){
    var comSubmit = new ComSubmit();
    comSubmit.setUrl("<c:url value='/board/openBoardList' />");
    comSubmit.submit();
}
 
function fn_insertBoard(){
    var comSubmit = new ComSubmit("frm");
    comSubmit.setUrl("<c:url value='/board/insertBoard' />");
    comSubmit.submit();
}
 
function fn_addFile(){
    var str = "<p><input type='file' id='file' style='margin-right:5px' name='file_"+(gfv_count++)+"'><a href='#this' class='btn' id='delete' name='delete'>삭제</a></p>";
    $("#fileDiv").append(str);
    $("a[name='delete']").on("click", function(e){ //삭제 버튼
        e.preventDefault();
        fn_deleteFile($(this));
    });
}
  
function fn_deleteFile(obj){
    obj.parent().remove();
}

</script>
</head>
<body><!-- 글자가 아닌 파일은 모두 multipart 형식의 데이터. -->
<form id="frm" name="frm" enctype="multipart/form-data">
        <table class="board_view">
            <colgroup>
                <col width="15%">
                <col width="*"/>
            </colgroup>
            <caption>게시글 작성<span id="date"></span></caption>
            <tbody>
                <tr>
                    <th scope="row">제목</th>
                    <td><input type="text" id="TITLE" name="TITLE" class="wdp_90"></input></td>
                </tr>
                <tr>
                    <td colspan="2" class="view_text">
                        <textarea rows="20" cols="100" title="내용" id="CONTENTS" name="CONTENTS"></textarea>
                    </td>
                </tr>
            </tbody>
        </table>
        <div id="fileDiv">
            <p>
                <input type="file" id="file" name="file_0">
                <a href="#this" class="btn" id="delete" name="delete">삭제</a>
            </p>
        </div>
        <br/><br/>
         
        <a href="#this" class="btn" id="addFile">파일추가</a>
        <a href="#this" class="btn" id="write" >작성하기</a>
        <a href="#this" class="btn" id="list" >목록으로</a>
    </form>
    
    <!-- 밑의 태그를 여기 놔둬야 해당 jsp의 내용이 body부분에 들어온다. -->
    <!-- 붙여넣은 곳에 위치되어진다. -->
    <%@ include file="/WEB-INF/include/include-body.jsp" %>
</body>
</html>