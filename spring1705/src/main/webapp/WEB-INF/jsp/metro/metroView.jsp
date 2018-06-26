<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chnag's Metro info.</title>
<script>
	$(function() {
		var $line = $("#line");
		var line_color = [ "003499", "37b42d", "fa5f2c", "3171d3", "893bb6",
				"9a4e0f", "606d00" ];//호선별 배경색
		var loadingImg ="<img id='loading' src='${pageContext.request.contextPath}/resources/image/loading.gif' style='display: none;'/>";
				
		$line.on('change', function() {
			var sel_line = $line.val();
			var $station_body = $("#station_body");
			$station_body.css("border-color", line_color[sel_line-1]);
			
			if(sel_line != ""){
				$.ajax({
					url : "<c:url value='/metro/getStations' />",
					//async : true,
					data : "LINE_NUM="+sel_line, //서버로 보낼 값들
					type : "POST",
					dataType : "json",
					error : function(xhr){alert(xhr.status + " " + xhr.statusText);},
					success : function(json_arr){ //json_arr 응답된 데이터를 받을 매개변수.
						$("#station").length = 1;
						//document.getElementById("station").length=1; //위와 같다.
						var link_station="";
						
						
						$("#station_body > div").html(loadingImg);
						$('#loading').show();
						
						setTimeout(function(){
							//아래의 for문과 결과가 같다.
							$.each(json_arr, function(i, station){
								//두번째 select~option에 채워질 역명 리스트
								$("#station").append("<option value='"+station.STATION_NM+"'>" + station.STATION_NM+ "</option>");
								link_station += "<a href='#'>"+ station.STATION_NM+"</a> ";
								
								$('#loading').css('display:none');
								
								//역명들을 보여줄 영역에 역명들을 채운다.
								$("#station_body > div").html(link_station);
								$("#station_body > div").hide().slideDown(300);
							});//$.each()
						}, 1000);
					}//success:
				});//$.ajax
			}//end if
		});//$line.on
	});//jquery
</script>
</head>
<body>
	<div id="container">
	<div></div>
		<div id="station_header">
			<label>호선 별 역 검색</label> 
			<select id="line">
				<option value="none">----호선선택----</option>
				<c:choose>
					<c:when test="${fn:length(list) > 0}">
						<c:forEach items="${list }" var="row">
							<option value="${row.LINE_NUM}">${row.LINE_NUM }호선</option>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<option value="none">없습니다.</option>
					</c:otherwise>
				</c:choose>
			</select> 
			
			<select id="station">
				<option value="none">----역 선택----</option>
			</select>
		</div>
		
		<!-- 지하철 역 리스트 출력부 -->
		<div id="station_body">
			<div></div>
		</div>
	</div>
</body>
</html>