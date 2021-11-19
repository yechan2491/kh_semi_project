<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자취는 템빨</title>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">
<style>


.outer {
	width: 1280px;
	margin: auto;
	margin-bottom: 2%;
	font-family: 'Nanum Gothic', sans-serif;
}

.wrap {
	width: 1240px;
	margin: auto;
}

.information_title {
	margin-top: 2%;
}

a {
	font-family: 'Noto Sans KR', sans-serif;
	text-decoration: none;
	text-align: center;
} 

.wdiv {
	margin-top: -0.8%;
	font-weight: 700;
	display: flex;
	height: 30px;
	position: relative;
	left: 90%;
}

.informationLogo {
	margin-top: 4%;
	margin-bottom: 1%;
	font-weight: 700;
	width: 150px;
	border-bottom: 1px solid rgb(235, 233, 233);
	display: inline-block;
}

.infobtn {
	background: rgb(235, 233, 233);
	color: black;
	border-radius: 15px;
	border: none;
	cursor: pointer;
	padding-left: 7px;
	padding-right: 7px;
	padding-top: 3px;
	padding-bottom: 3px;
	margin-right: 10px;
	height: 30px;
}

.infobtn:hover {
	background: #0bd;
}

.wbtn:hover {
	background: #0bd;
}

.wbtn {
	width: 80px;
	height: 40px;
	background: rgb(135, 211, 124);
	border-radius: 10px;
	border: none;
	color: white;
	cursor: pointer;
	display: inline-block;
	justify-content: flex-end;
	font-size: 15px;

}

.board_title {
	border-bottom: 1px solid #282A35;
}

ul, li {
	margin: 0;
	padding: 0;
}  

.list_div {
	width: 100%;
}

.board_list {
	list-style: none;
	margin: 50px 15px;
	display: grid;
	grid-template-columns: 250px 250px 250px 250px;
	gap: 70px;
}

.board_list .box:hover {
	cursor: pointer;
	transform: scale(1.05);
	color: #0bd;
}

.board_list .category {
	color: lightgray;
	font-size: 0.8rem;
}

.board_list .title {
	font-weight: bold;
	text-align: center;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

.board_list .writer {
	color: lightgray;
	text-align: center;
	font-size: 0.8rem;
}

.board_list img {
	max-width: 260px;
	height: 260px;
}

.btn_area {
	text-align: center;
}

.btn_area button {
	width: 100px;
	height: 35px;
	border: 0px;
	color: white;
	background: #282A35;
	margin: 5px;
	cursor: pointer;
}

.logoP{
	position:absolute;
	background: rgb(235, 233, 233);
	color: black;
	border-radius: 15px;
	
}







.search_area select {
	width: 150px;
	height: 40px;
	border: 1px solid grey;
	font-family: 'Noto Sans KR', sans-serif;
	font-weight: 400;
	border-radius: 5px;
	padding-left: 5px;
	margin-right: 10px;
	
}

.search_area select:focus {
	outline: none;
}





/**********페이징바**************/
.board_paging {
	height: 50px;
	line-height: 50px;
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
	margin: 0 3px;
	/* border: 1px solid #e6e6e6; */
	width: 40px;
	height: 40px;
	text-align: center;
	color: #999999;
	text-decoration: none;
}

boadr_paging a:hover {
	cursor: pointer;
}

.board_paging a.current_page { /*현재 페이지 */
	/* border-bottom: 2px solid #282A35;
        font-weight: bold; */
	/* background-color: #42454c; */
	color: black;
	border: 2px solid rgb(135, 211, 124);
	border-radius: 50%;
	padding : -14px;
	margin-top: -2px;
}

.board_paging .pprev {
	background: /* #f8f8f8 */
		url('<%=request.getContextPath()%>/resources/images/page_pprev.png')
		no-repeat center center;
}

 .board_paging .prev {
	background: /* #f8f8f8 */ url('${contextPath}/resources/images/page_prev.png')
		no-repeat center center;
	margin-right: 7px;
} 

.board_paging .next {
	background: /* #f8f8f8 */ url('${contextPath}/resources/images/page_next.png')
		no-repeat center center;
	margin-left: 7px;
}

.board_paging .nnext {
	background: /* #f8f8f8 */
		url('${contextPath}/resources/images/page_nnext.png') no-repeat center
		center;
	margin-right: 0;
}

.pagingcComment {
	text-align:center;
	font-weight: 300;
	margin-top: 20px;
	color: #707070;
	font-size: 15px;
}
/*****************************/


/******페이지 업다운 버튼*********/
html {
	scroll-behavior: smooth;
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

.second_menu {
color: rgb(135, 211, 124);
}

/***********************/
</style>
</head>
<body>
	<!-- 메뉴바 -->
	<jsp:include page="/WEB-INF/views/common/menubar.jsp">
		<jsp:param name="pName" value="test" />
	</jsp:include>
	<!-- 광고 -->
	<jsp:include page="/WEB-INF/views/common/ad.jsp">
		<jsp:param name="pName" value="test" />
	</jsp:include>




	<div class="outer">
		<div class="wrap">
			<div class="information_area">
				<div class="information_title">
					<img class="informationLogo"
						src="${ contextPath }/resources/images/infoBest.png">
					<p class="logoP">　꿀팁 인기글 BEST 4　</p>
				</div><br>
				<div class="list_div">
					<ul class="board_list">
						<c:forEach var="board" items="${ bestBoardList }">
							<li>
								<div class="box" onclick="detailView(${ board.bid })">
									<img
										src="${ contextPath }${ board.photoList.get(0).filePath}${ board.photoList.get(0).changeName }">
									<p class="category">[ ${ board.cname } ]</p>
									<p class="title">${ board.btitle }</p>
								</div>
							</li>
						</c:forEach> 

					</ul>
				</div>
				
				



				
				<div class="information_title">
					<img class="informationLogo"
						src="${ contextPath }/resources/images/infocategory.png">
				</div>
				<div class="search_area">
					<c:if test="${ !empty param.category }">
							<c:set var="searchParam"
								value="&category=${ param.category}" />
						</c:if>
				
				
					<!-- <form name="catagory"> -->
					<div class="search_area">
					<form method="get" action="${ contextPath }/info/list">
						<select id="category" name="category">
						<option value="all"
									<c:if test="${ param.category =='all' }">selected</c:if>>전체</option>
						<option value="clean"
									<c:if test="${ param.category =='clean' }">selected</c:if>>청소</option>
						<option value="decorate"
									<c:if test="${ param.category =='decorate' }">selected</c:if>>꾸미기</option>
						<option value="reform"
									<c:if test="${ param.category =='reform' }">selected</c:if>>리폼</option>
						<option value="lifeInformation"
									<c:if test="${ param.category =='lifeInformation' }">selected</c:if>>생활정보</option>												
						
						
						</select>
						<button type="submit" class="wbtn">조회</button>
					
						
					

					
						<div class="wdiv">
							<button type="button" class="wbtn"
								onclick="insertInfo();">글쓰기</button>
						</div>
					</form>
					</div>
				</div>
				
				
				
				
				
				
				<div class="list_div">
					<ul class="board_list">
						<c:forEach var="board" items="${ boardList }">
							<li>
								<div class="box" onclick="detailView(${ board.bid })">
									<img
										src="${ contextPath }${ board.photoList.get(0).filePath}${ board.photoList.get(0).changeName }">
									<p class="category">[ ${ board.cname } ]</p>
									<p class="title">${ board.btitle }</p>
								</div>
							</li>
						</c:forEach> 

					</ul>
				</div>
				
				<br><br>
				
					<ul class="board_paging">
					<!-- 맨 처음으로 이동하는 버튼(<<) -->
					<li><a
						href="${ contextPath }/info/list?page=1${ searchParam }"
						class="arrow pprev"></a></li>

					<!-- 이전 페이지로(<) -->
					<li><c:choose>
							<c:when test="${pi.page>1 }">
								<a class="arrow prev"
									href="${ contextPath }/info/list?page=${ pi.page-1 }${ searchParam }"></a>
							</c:when>
							<c:otherwise>
								<a class="arrow prev" href="#"></a>
							</c:otherwise>
						</c:choose></li>

					<!-- 최대 10개의 페이지 목록 -->
					<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
						<li><c:choose>
								<c:when test="${ p eq pi.page }">
									<a class="current_page" href="#">${ p }</a>
								</c:when>
								<c:otherwise>
									<a
										href="${ contextPath }/info/list?page=${ p }${ searchParam }">${ p }</a>
								</c:otherwise>
							</c:choose></li>
					</c:forEach>

					<!-- 다음 페이지로(>) -->
					<li><c:choose>
							<c:when test="${ pi.page < pi.maxPage }">
								<a class="arrow next"
									href="${ contextPath }/info/list?page=${ pi.page+1 }${ searchParam }"></a>
							</c:when>
							<c:otherwise>
								<a class="arrow next" href="#"></a>
							</c:otherwise>
						</c:choose></li>

					<!-- 맨 끝으로 이동하는 버튼(>>) -->
					<li><a class="arrow nnext"
						href="${ contextPath }/info/list?page=${ pi.maxPage }${ searchParam }"></a></li>
				</ul>
				<div class="pagingcComment">
		<p> 꿀팁 게시판 [ ${ pi.listCount } ] 개의 게시글이 조회되었습니다.</p>
	</div>







			</div>
		</div>
	</div>


	
	
	<script>
		function detailView(bid){
			location.href='${contextPath}/info/detail?bid='+bid;
		}
	</script>
	<c:choose>
		<c:when test="${ !empty loginUser }">
			<script>
				function insertInfo(bid){
					location.href='${contextPath}/info/insert';
				}
			</script>
		</c:when>
		<c:otherwise>
			<script>
				function insertInfo(bid){
					alert('로그인 후 이용 가능합니다');
					location.href='${contextPath}/login';
				}
			</script>
		</c:otherwise>
	</c:choose>

	









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



	<!-- 푸터 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp">
		<jsp:param name="pName" value="test" />
	</jsp:include>

</body>
</html>