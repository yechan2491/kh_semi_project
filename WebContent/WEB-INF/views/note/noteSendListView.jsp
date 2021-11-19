<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쪽지</title>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">

<style>

.five_menu {
color: rgb(135, 211, 124);
}
.outer {
	width: 900px;
	/*1280px*/
	margin: auto;
	margin-bottom: 3%;
	font-family: 'Nanum Gothic', sans-serif;
	/* font-family: 'Noto Sans KR', sans-serif;
        font-weight: normal; */
}

.wrap h2 {
	height: 100px;
	font-size: 26px;
	display: flex;
	align-items: center;
	
}

ul, li {
	margin: 0;
	padding: 0;
}

.note_list {
	/* margin: 20px 30px; */
	min-height: 565px;
	padding-bottom: 50px;
}

.note_list>ul {
	border-bottom: 1px solid rgb(220, 219, 228);
	height: 50px;
	line-height: 50px;
	display: flex;
	justify-content: space-around;
	list-style: none;
}

.note_header {
	background: rgb(135, 211, 124);
	color: white;
	font-weight: bold;
	font-weight: 500;
	font-size: 20px;
}

.note_list .id {
	width: 60px;
}

.note_ul:hover {
	background: #f3f5f7;
	cursor: pointer;
}

.note_list .title {
	width: 350px; /* 405 */
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

.note_list .receive_user {
	width: 100px; /* 150 */
}
.note_list .readCheck {
	width: 80px;
	display: flex;
	align-items: center;
	justify-content: center;
}

.note_list .date {
	width: 100px;
}

.note_list>ul>li {
	text-align: center;
}

/*페이징 버튼*/
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
	/* border: 1px solid #e6e6e6; */
	width: 40px;
	height: 40px;
	text-align: center;
	/* color: #999999; */
	text-decoration: none;
}

boadr_paging a:hover {
	cursor: pointer;
}

.board_paging a.current_page { /*현재 페이지 */
	/* background-color: #030066; */
	/* color: #fff; */
	border: 2px solid rgb(135, 211, 124);/* #030066; */
	border-radius: 50%;
	margin-top: -3%
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
	font-weight: 500;
	margin-top: 3px;
	color: #707070;
	font-size: 15px;
}

/* 페이지 이동버튼 수신 송신 쪽지보내기 이동 */
.pageButton {
	width: 100%;
	display: flex;
	justify-content: flex-end;
	padding-top: 50px;
	padding-bottom: 60px;
}

.pageButton>button {
	height: 3.0rem;
	width: 100px;
	text-align: center;
	border: 1px solid rgb(195, 194, 204);
	margin-left: 1rem;
	border-radius: 2px;
	background-color: transparent;
	cursor: pointer;
	outline: none;
}

.pageButton>button:hover {
	background-color: rgb(135, 211, 124);
	border: none;
	color:white;
}


/* 검색버튼 */
.search_area button {
	width: 120px;
	height: 47px;
	border: 0px;
	color: white;
	background: rgb(135, 211, 124);
	margin-left: 5px;
	
	
	
    font-weight: 300;
    font-size: 15px;
}
.search_area button:hover{
	cursor : pointer;
}

.search_area {
	
	display:flex;
	align-items: flex-end;
	justify-content: center;
	padding: 30px;
}

.search_area select {
	width: 150px;
	height: 47px;
	border: 1px solid #dadada;
}

.input_area {
	border: solid 1px #dadada;
	padding: 10px 10px 14px 10px;
	/* margin-right: 20px; */
	background: white;
}

.input_area input {
	width: 250px;
	height: 30px;
	border: 0px;
}

.input_area input:focus, .search_area select:focus {
	outline: none;
}


</style>
</head>
<body>

	<!-- 메뉴바 -->
	<jsp:include page="/WEB-INF/views/common/menubar.jsp">
		<jsp:param name="pName" value="test" />
	</jsp:include>

	<div class="outer">
		<div class="wrap">
			<h2>송신 쪽지함 &gt; 목록보기</h2>

			<div class="note_list">
				<ul class="note_header">
					<li class="id">번호</li>
					<li class="title">제목</li>
					<li class="receive_user">받은이</li>
					<li class="readCheck">수신여부</li>
					<li class="date">전송일</li>
				</ul>
				
				
				<c:forEach var="note" items="${ noteSendList }">
				<ul class="note_ul" onclick="detailView(${note.nno})">
					<li class="id">${note.rnum}</li>
					<li class="title">${note.ntitle }</li>
					<li class="receive_user">${note.nickName}</li>
					<c:choose>
						<c:when test="${note.readCheck == 'Y' }">
						<li class="readCheck"><img style="width: 40px; height: 40px;"
						src="${contextPath }/resources/images/read.png"></li>
						</c:when>
						<c:otherwise>
						<li class="readCheck"><img style="width: 40px; height: 40px;"
						src="${contextPath }/resources/images/read_not.png"> </li>
						</c:otherwise>
					</c:choose>
					<li class="date">${note.timeView }</li>
				</ul>
				</c:forEach>
				
				
				
	
				
			</div>

			<c:if test="${ !empty param.searchCondition && !empty param.searchValue }">
				<c:set var="searchParam" value="&searchCondition=${ param.searchCondition}&searchValue=${ param.searchValue }" />
			</c:if>
			<ul class="board_paging">
				<!-- 맨 처음으로 이동하는 버튼(<<) -->
				<li><a class="arrow pprev" href="${contextPath }/note/send/list?page=1${ searchParam }"></a></li>
				
				<!-- 이전 페이지로(<) -->
				<c:choose>
					<c:when test="${ pi.page >1 }">
						<li><a class="arrow prev" href="${contextPath }/note/send/list?page=${ pi.page-1 }${ searchParam }"></a></li>
					</c:when>
					<c:otherwise>
						<li><a class="arrow prev" href="#"></a></li>
					</c:otherwise>
				</c:choose>
				
			
			
				<!-- 최대 10개의 페이지 목록 -->
				<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
					<li>
					<c:choose>
						<c:when test="${ p eq pi.page }">
							<a class="current_page" href="#">${ p }</a>
						</c:when>
						<c:otherwise>
							<a href="${ contextPath }/note/send/list?page=${ p }${ searchParam }">${ p }</a>
						</c:otherwise>
					</c:choose>
					</li>
				</c:forEach>

				
				<!-- 다음 페이지로(>) -->
				<li>
				<c:choose>
					<c:when test="${ pi.page < pi.maxPage }">
						<a class="arrow next" href="${ contextPath }/note/send/list?page=${ pi.page+1 }${ searchParam }"></a>
					</c:when>
					<c:otherwise>
						<a class="arrow next" href="#"></a>
					</c:otherwise>
				 </c:choose>
				 </li>

				 <!-- 맨 끝으로 이동하는 버튼(>>) -->
				<li><a class="arrow nnext" href="${ contextPath }/note/send/list?page=${ pi.maxPage }${ searchParam }"></a>
				
				
				
			</ul>
			<div class="pagingcComment">
				<p>송신 쪽지 ${ pi.listCount }개가 검색되었습니다.</p>
			</div>
			
			<!-- 검색버튼 -->
			<div class="search_area">
				<form method="get" action="${ contextPath }/note/send/list">
					<select id="searchCondition" name="searchCondition">
						<option value="title"
							<c:if test="${ param.searchCondition =='title' }">selected</c:if>>제목</option>
						<option value="content"
							<c:if test="${ param.searchCondition =='content' }">selected</c:if>>내용</option>
						<option value="writer"
							<c:if test="${ param.searchCondition =='writer' }">selected</c:if>>받은이</option>
					</select> <span class="input_area"> <input type="search"
						name="searchValue" value="${ param.searchValue }">
					</span>
					<button type="submit">검색하기</button>
				</form>
			</div>


			<!-- 페이지 이동버튼 수정하기 목록이동-->
			<div class="pageButton">
				<button class="receiveNoteBtn" type="button"
					onclick="location.href='${contextPath}/note/receive/list'">수신
					쪽지함</button>
				<button class="sendNoteBtn" type="button"
					onclick="location.href='${contextPath}/note/send/list'">송신
					쪽지함</button>
				<button class="sendBtn" type="button"
					onclick="location.href='${contextPath}/note/write'">쪽지 보내기</button>
			</div>
		</div>
	</div>



	<!-- 푸터 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp">
		<jsp:param name="pName" value="test" />
	</jsp:include>


	<script>
		function detailView(nno){
			location.href='${contextPath}/note/send/detail?nno='+nno;
		}
	</script>
</body>

</html>