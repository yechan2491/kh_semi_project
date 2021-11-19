<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>꿀친게시판</title>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100&display=swap"
	rel="stylesheet">

<style>
@charset "UTF-8";
.forth_menu {
	color: rgb(135, 211, 124);
}

body {
	font-family: 'Noto Sans KR', sans-serif;
	padding-top: 120px;
}

.container {
	width: 100%;
	margin: 0 auto;
}

header {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1;
}

/**********페이징바**************/
.board_paging {
	height: 40px;
	line-height: 40px;
	display: flex;
	justify-content: center;
	list-style: none;
	width: 480px;
	margin: auto;

}

.board_paging a {
	text-decoration: none;
	line-height: 40px;
	display: block;
	text-align: center;
	font-weight: bold;
 	margin: 0 3px; 
	width: 40px;
	height: 40px;
	text-decoration: none;
}

boadr_paging a:hover {
	cursor: pointer;
}

.board_paging a.current_page { /*현재 페이지 */
	border: 2px solid rgb(135, 211, 124);
	border-radius: 50%;
	margin-top:-3%;
}

.board_paging .pprev {
	background:
		url('<%=request.getContextPath()%>/resources/images/page_pprev.png')
		no-repeat center center;
}

.board_paging .prev {
	background: url('${contextPath}/resources/images/page_prev.png')
		no-repeat center center;
	margin-right: 7px;
}

.board_paging .next {
	background: url('${contextPath}/resources/images/page_next.png')
		no-repeat center center;
	margin-left: 7px;
}

.board_paging .nnext {
	background:	url('${contextPath}/resources/images/page_nnext.png') no-repeat center center;
	margin-right: 0;
}

.pagingcComment {
	text-align:center;
	font-weight: 900;
	margin-top: 3px;
	color: #707070;
	font-size: 15px;
}

/*****************************/


.findbutton {
	width: 40px;
	height: 40px;
	margin-top: -3%;
	margin-left: 1%;
	float: right;
	border-style: block;
	border-width: 1px;
	border-radius: 5%;
	background-color: white;
	background-repeat: no-repeat;
	background-position:  center;
	background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' width='24' height='24' viewBox='0 0 24 24'%3E%3Cpath d='M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z'/%3E%3Cpath d='M0 0h24v24H0z' fill='none'/%3E%3C/svg%3E");
}

.findbutton:hover {
	background-color: rgb(135, 211, 124);
	border: none;
}

.findbutton:active {
	background-color: rgb(135, 211, 124);
	position: relative;
	top: 1px;
}

.writerbutton {
	width: 80px;
	height: 40px;
	margin-top: -3%;
	margin-left: 1%;
	float: right;
	border-style: block;
	border-width: 1px;
	border-radius: 5%;
	background-color: white;
}

.writerbutton:hover {
	background-color: rgb(135, 211, 124);
	border: none;
}

.writerbutton:active {
	background-color: rgb(135, 211, 124);
	position: relative;
	top: 1px;
}

.friend_list {
	place-items: center;
	margin-top: 100px;
	display: grid;
	grid-template-columns: 23.5% 23.5% 23.5% 23.5%;
	column-gap: 2%;
	row-gap: 5%;
	min-height: 50vh;
	margin-bottom: 40px;
	display: grid;
}

/* 사진에 마우스 오버 시 */
.friend_list .image_area1:hover {
	cursor: pointer;
}

.image_area1 {
	height: 250px;	
	width: 100%;
	border-radius: 3%;
	overflow: hidden;
}

.image_area1 img {
	height: 250px;	
	width: 320px;
	object-fit: cover;
}
/* 지역 셀렉트 박스 */
#select {
	height: 40px;
	width: 120px; /* 너비설정 */
	border-top: none;	
	border-right: none;
	border-left: none;
	font-size: 15px;
	margin-top: -3%;
	float: right;
}

.board_list {
	margin-top: 50px;
	border: 1px solid black;
	width: 300px;
	height: 400px;
}

.itemImage {
	width: 60px;
	height: 60px;
	margin-top: 5px;
	margin-left: 10px;
	border-radius: 100%;
}

#text {
	width: 95%;
	margin-left: 2.5%;
	margin-top: 1%;
	font-size: 14px;
}

/* 지역 분류 */
.location_selection {
	margin-top: 300px; /* 보기 편할라고 위 조정*/
	margin-left: 200px; /* 이것도*/
	width: 3cm;
	height: 1cm;
	font-size: 15px;
}

.location_button {
	margin-left: 10px;
	width: 2cm;
	height: 1cm;
	font-size: 15px;
	font-weight: bolder;
	background-color: rgb(135, 211, 124);
	border-color: rgb(135, 211, 124);
	border-style: none;
	border-radius: 5%;
}


/* 사진 하단 정보 */
/* 제목  */
.title {
	text-align: left;
	font-size: 18px;
	font-weight: 900;
	
}

#box {
	width: 40px;
	height: 40px;
	border-radius: 100%;
	overflow: hidden;
	margin-right:16px;
}


#name {
	margin-top:-10%;
	font-size: 18px;
	font-weight: 800;
}

.ge_ca {
	font-size: 14px;
	color:#5E5E5E;
	margin-top:-10%;
	display: flex;
}

#category {
	margin: right;
	margin-left: 4px;
}

/* 프로필 사진 */
#profile {
	height: 100%;
	height: 100%;
	object-fit: cover;
}

.MemberInfo2 {
	display: flex;
	justify-content: left;
	font-weight: 700;
	margin-bottom: 20px;
}
	
#time {
	font-size: 14px;
	margin-top: -334px;
	float: right;
}

/******페이지 업다운 버튼*********/
html {
	/* scroll-behavior: smooth; */
	
}


.fixed-pageUpDown {
	position: fixed;
	left: 85%;
	top: 80%;
	display: flex;
	flex-direction: column; /*수직 정렬*/
}

#pageUp img {
	width: 50px;
}

#pageDown img {
	width: 50px;
}

#pageUp, #pageDown {
	cursor: pointer;
	transform: scale(1.05);
}
/***********************/

</style>
</head>
<body>
	<!-- 메뉴바 / 슬라이드 광고 영역 -->
	<jsp:include page="/WEB-INF/views/common/menubar.jsp">
		<jsp:param name="pName" value="test" />
	</jsp:include>

	<jsp:include page="/WEB-INF/views/common/ad.jsp">
		<jsp:param name="pName" value="test" />
	</jsp:include>


	<br>
	<br>

	<div class="container">
		<div class="outer">
			
			<div class="notice_title">
				<img src="<%-- ${ contextPath }/resources/images/Logo_friend.png --%>">
			</div>
			
			<!-- 글쓰기 -->
			<c:if test="${ !empty loginUser }"> 
				<button type="button" class="writerbutton"
					onclick="location.href='${ contextPath }/friend/writer'">글쓰기</button>
			</c:if> 

			<form method="get" action="${ contextPath }/friend/main">
				<button type="submit" class="findbutton"></button>
				<select id="select" name="searchCondition" >
					<option value="all"
						<c:if test="${param.searchCondition == 'all'}">selected</c:if>>전체보기</option>
					<option value="31" 
						<c:if test="${param.searchCondition == '31'}">selected</c:if>>홍대</option>
					<option value="32" 
						<c:if test="${param.searchCondition == '32'}">selected</c:if>>강남</option>
					<option value="33" 
						<c:if test="${param.searchCondition == '33'}">selected</c:if>>종로</option>
					<option value="34" 
						<c:if test="${param.searchCondition == '34'}">selected</c:if>>잠실</option>
					<option value="35" 
						<c:if test="${param.searchCondition == '35'}">selected</c:if>>부천</option>
					<option value="36" 
						<c:if test="${param.searchCondition == '36'}">selected</c:if>>인천</option>
					<option value="37" 
						<c:if test="${param.searchCondition == '37'}">selected</c:if>>전주</option>
					<option value="38" 
						<c:if test="${param.searchCondition == '38'}">selected</c:if>>부산</option>
					<option value="39" 
						<c:if test="${param.searchCondition == '39'}">selected</c:if>>제주도</option>
				</select>

			</form>
			<ul class="friend_list">
				<c:forEach var="board" items="${boardList}">
					<div class="box" onclick="detailView(${ board.bid });">
						
						<div class="MemberInfo">
							<div class="MemberInfo2">
								<div id="box">
									<img id="profile" src="${ contextPath }${ board.profilePath }">
								</div>
								<div class="na_ge_ca">
									<p id="name">${ board.nickname }</p>
									<div class="ge_ca">
										<p id="gender">
											<c:choose>
												<c:when test="${board.gender eq 'F'}">여자</c:when>
												<c:when test="${board.gender eq 'M'}">남자</c:when>
												<c:otherwise>비공개</c:otherwise>
											</c:choose>
										</p><p id="category">${board.cname}</p>
										
									</div>
								</div>
							</div>
						</div>
						<!-- 메인 사진 -->
						<div class="image_area1">					
							<img src="${ contextPath }${ board.friendPhotoList.get(0).filePath }${board.friendPhotoList.get(0).changeName}">
						</div>
							<p class="title">${ board.btitle }</p>
						
							<p id="time">
								<c:choose>
									<c:when test="${ board.writetime  > 24 }">
										<span> ${ board.writeday } 일 전</span>
									</c:when>
									<c:when test="${ board.writemin  > 60 }">
										<span> ${ board.writetime } 시간 전</span>
									</c:when>
									<c:otherwise>
										<span>${ board.writemin } 분 전</span>
									</c:otherwise>
								</c:choose>
							</p>
					</div>
				</c:forEach>
			</ul>
		</div>
	</div>





	<br>
	<br>
	<br>
	<br>
	<!-- 페이징 -->
	<ul class="board_paging">
	
	<!-- 1번 페이지 -->	
		<li><a href="${ contextPath }/friend/main?page=1${ searchParam }" class="arrow pprev"></a></li>
		<!-- 이전페이지 -->
		<li><c:choose>
				<c:when test="${ pi.page > 1 }">
					<a class="arrow prev" href="${ contextPath }/friend/main?page=${ pi.page - 1 }${ searchParam }"></a>
				</c:when>
				<c:otherwise>
					<a class="arrow prev" href="#"></a>
				</c:otherwise>
			</c:choose></li>

		<!-- 최대 5개의 페이지 목록 -->
		<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
			<li><c:choose>
					<c:when test="${ p eq pi.page }">
						<a class="current_page" href="#">${ p }</a>
					</c:when>
					<c:otherwise>
						<a href="${ contextPath }/friend/main?page=${ p }${ searchParam }">${ p }</a>
					</c:otherwise>
				</c:choose></li>
		</c:forEach>

		<!-- 다음페이지 -->
		<li><c:choose>
				<c:when test="${ pi.page < pi.maxPage }">
					<a class="arrow next" href="${ contextPath }/friend/main?page=${ pi.page + 1 }${ searchParam }"></a>
				</c:when>
				<c:otherwise>
					<a class="arrow next" href="#"></a>
				</c:otherwise>
			</c:choose></li>
			
		<!-- 마지막 페이지 -->
			<li><a class="arrow nnext" href="${ contextPath }/friend/main?page=${ pi.maxPage }${ searchParam }"></a></li>
	</ul>

	<div class="pagingcComment">
		<p>꿀친 게시판의 게시글 ${ pi.listCount }개가 검색되었습니다.</p>
	</div>
	
	
		<!-- 페이지 업다운 버튼 -->
	<div class="fixed-pageUpDown">
		<a id="pageUp"><img
			src="${contextPath}/resources/images/pageUp.png"></a> <a
			id="pageDown"><img
			src="${contextPath}/resources/images/pageDown.png"></a>
	</div>


	<script>
    	$(document).ready(function(){
	        
	        $("#pageUp").click(function(){
	        	$('html, body').animate({scrollTop:0}, 200);
	        });
	        
	        $("#pageDown").click(function(){
	        	$('html, body').animate({scrollTop:$('body').height()}, 200);
	        });
	            
    		
    	});
    </script>

	<br>

	<!-- footer -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>

	<script>
   		function detailView(bid) {
   			location.href='${contextPath}/friend/detail?bid=' + bid;
   		}
   </script>
   

</body>

</html>