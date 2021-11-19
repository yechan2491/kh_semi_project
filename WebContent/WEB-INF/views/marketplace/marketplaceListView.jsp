<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자취는 꿀템</title>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">



<style>
.third_menu {
color: rgb(135, 211, 124);
}
.outer {
	width: 1280px;
	margin: auto;
	margin-bottom: 3%;
	font-family: 'Nanum Gothic', sans-serif;
}
input:focus, select:focus {
	outline: none;
}

/* 이미지 크기 늘리기 */
.logo-image {
	width: 250px;
    height: 75px;
    margin-top: 13px;
}

.market_title {
	display:flex;
	justify-content: flex-end;
	align-items: baseline;
	/* display: grid;
	grid-template-columns: 1fr 1fr; */
	/* margin-top: 3%; */
}

.search_area {
	margin-top: 4%;
	width:400px;
	
}

.search_area select {
	height: 40px;
    width: 120px;
    border-top: none;
    border-right: none;
    border-left: none;
    font-size: 15px;
    margin-left:6px;
}

.search_area button {
	width: 80px;
    height: 40px;
    margin-top: -3%;
    margin-left: 7px;
    border-style: block;
    border-width: 1px;
    border-radius: 5%;
    background-color: white;
    cursor:pointer;
	
}

.market_list {
	display: grid;
	/* place-items: center; */
	grid-template-columns: repeat(4, 295px);
	gap: 30px;
	margin-top: 4%;
	min-height: 50vh; /* 게시물 없을때 최소 높이 */
	margin-bottom: 40px;
	padding-left:5px;
	padding-right:5px;
}

.item {
	background: white;
	/* border: 1px solid rgb(238, 238, 238); */
	/* width: 230px; */
	width: 100%;
	height: 330px;
	cursor: pointer;
}

.item:hover {
	cursor: pointer;
	/* transform: scale(1.05); */
}

.itemImage {
	position: relative;
	border-bottom: 1px solid rgb(238, 238, 238);
	width: 100%;
	height: 244px;
	overflow: hidden;
	margin: 0 auto;
}

.itemImage img {
	position: absolute;
	top: 0px;
	left: 0px;
	width: 100%;
	height: 100%;
}

.itemInfo {
	width: 90%;
	margin: 0 auto; /* 가로방향 세로방향 각각 0 auto  */
}

.item_title {
	/* 한줄로 표시되게 하고 길어지면 ... 하려면 해당 3줄있어야됨*/
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
	padding-bottom: 10px;
	padding-top:5px;
	font-size:16px;
}

.item_detail {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.item_detail div:nth-child(1) {
	
	/* line-height: 30px; */
	text-overflow: ellipsis;
	white-space: nowrap;
	width: 190px;
	overflow: hidden;
	font-weight: bold;
	font-size : 18px;
}

.item_detail div:nth-child(2) span {
	font-size : 14px;
	color: gray;
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
	font-weight: 900;
	margin-top: 3px;
	color: #707070;
	font-size: 15px;
}
/*****************************/

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
	z-index:30;
}

#pageUp img {
	width: 50px;
}

#pageDown img {
	width: 50px;
}

#pageUp, #pageDown {
	cursor: pointer;
	/* transform: scale(1.05); */
}
/***********************/
.MemberInfo2 {
    display: flex;
    font-weight: 700;
    margin-bottom: 20px;
}
.box{
	cursor:pointer;
}
#box {
    width: 40px;
    height: 40px;
    border-radius: 100%;
    overflow: hidden;
    margin-right: 16px;
    margin-top: 8px;
}
#profile {
    height: 100%;
    object-fit: cover;
   
}
img {
    max-width: 100%;
}
.image_area1 img {
    height: 250px;
    width:100%;
    max-height: 80%;
    border-radius: 3%;
}

.title {
    text-align: left;
    font-size: 18px;
    font-weight: 900;
}

#time {
    font-size: 12px;
    margin-top: -30px;
    float: right;
}

#titleFlex{
	display : flex;
	justify-content: space-between;
	align-items: center;
}

.na_ge_ca{
	width:78%;
}
#name{
	font-size: 18px;
    font-weight: 800;
}

#titleItem{
	font-size: 14px;
    color: #5E5E5E;
    text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
}
#category2{
	font-size: 12px;
    color: gray;
    text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
}


#findBtn{
	width: 40px;
    height: 40px;
    margin-left: 7px;
    border-style: block;
    border-width: 1px;
    border-radius: 5%;
    background-color: white;
    background-repeat: no-repeat;
    background-position: center;
    top: 14px;
    position: relative;
	background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' width='24' height='24' viewBox='0 0 24 24'%3E%3Cpath d='M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z'/%3E%3Cpath d='M0 0h24v24H0z' fill='none'/%3E%3C/svg%3E");
}
#textBtn:hover {
	background-color: rgb(135, 211, 124);
	border: none;
	color:white;
	
}

#findBtn:hover {
	background-color: rgb(135, 211, 124);
	border: none;
	width: 40px;
    height: 39px;
}


</style>
</head>
<body class="bd">

	<!-- 메뉴바 -->
	<jsp:include page="/WEB-INF/views/common/menubar.jsp" />

	<!-- 광고베너 -->
	<jsp:include page="/WEB-INF/views/common/ad.jsp">
		<jsp:param name="pName" value="test" />
	</jsp:include>


	<div class="outer">
		<div class="wrap">
			<div class="market_area">
				<div class="market_title">
					<%-- <img class="logo-image"
						src="${ contextPath }/resources/images/Logo_market.png"> --%>
					<div class="search_area">
						<c:if test="${ !empty param.category && !empty param.sort }">
							<c:set var="searchParam"
								value="&category=${ param.category}&sort=${ param.sort }" />
						</c:if>
						<form method="get" action="${ contextPath }/marketplace/list">
							<select id="category" name="category">
								<option value="all"
									<c:if test="${ param.category =='all' }">selected</c:if>>전체</option>
								<option value="clothes"
									<c:if test="${ param.category =='clothes' }">selected</c:if>>의류</option>
								<option value="shoes"
									<c:if test="${ param.category =='shoes' }">selected</c:if>>신발</option>
								<option value="bag"
									<c:if test="${ param.category =='bag' }">selected</c:if>>가방</option>
								<option value="beauty"
									<c:if test="${ param.category =='beauty' }">selected</c:if>>뷰티/미용</option>
								<option value="kitchen"
									<c:if test="${ param.category =='kitchen' }">selected</c:if>>주방용품</option>
								<option value="digital"
									<c:if test="${ param.category =='digital' }">selected</c:if>>디지털/가전</option>
								<option value="vehicle"
									<c:if test="${ param.category =='vehicle' }">selected</c:if>>차량/오토바이</option>
								<option value="etc"
									<c:if test="${ param.category =='etc' }">selected</c:if>>기타용품</option>
							</select> <select id="sort" name="sort">
								<option value="recent"
									<c:if test="${ param.sort =='recent' }">selected</c:if>>최신
									순</option>
								<option value="old"
									<c:if test="${ param.sort =='old' }">selected</c:if>>오래된
									순</option>
								<option value="priceAsc"
									<c:if test="${ param.sort =='priceAsc' }">selected</c:if>>가격
									낮은순</option>
								<option value="priceDesc"
									<c:if test="${ param.sort =='priceDesc' }">selected</c:if>>가격
									높은순</option>
							</select>

							<button type="submit" id="findBtn"></button>

							<button type="button" id="textBtn" onclick='insertMarketplace();'>글쓰기
								</button>

						</form>
					</div>
				</div>



				<div class="market_list">
					<c:forEach var="board" items="${ boardList }">
						<div class="box" onclick="detailView(${board.bid});">
							<div class="MemberInfo">
						    	<div class="MemberInfo2">
						        	<div id="box">
						   	        	<img id="profile" src="${contextPath }${board.profilePath }">
						            </div>
						            <div class="na_ge_ca">
						            	<div id="titleFlex">
						            		<div id="name">${board.nickname }</div>
						            		<div id="category2">${board.cname }</div>
						            	</div>
						            	<div id="titleItem">${ board.btitle}</div>
						            </div>
						    	</div>
						    </div>
						    <!-- 메인 사진 -->
						    <div class="image_area1">
						    	<img src="${ contextPath }${ board.imageList.get(0).filePath }${ board.imageList.get(0).changeName }">
						    </div>
						    <p class="title"><fmt:formatNumber value="${ board.price }" groupingUsed="true" />원</p>
						    <p id="time">
						    	<span>${board.timeView }</span>
						 	</p>
						</div>
						

					</c:forEach>


				</div>



				<ul class="board_paging">
					<!-- 맨 처음으로 이동하는 버튼(<<) -->
					<li><a
						href="${ contextPath }/marketplace/list?page=1${ searchParam }"
						class="arrow pprev"></a></li>

					<!-- 이전 페이지로(<) -->
					<li><c:choose>
							<c:when test="${pi.page>1 }">
								<a class="arrow prev"
									href="${ contextPath }/marketplace/list?page=${ pi.page-1 }${ searchParam }"></a>
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
										href="${ contextPath }/marketplace/list?page=${ p }${ searchParam }">${ p }</a>
								</c:otherwise>
							</c:choose></li>
					</c:forEach>

					<!-- 다음 페이지로(>) -->
					<li><c:choose>
							<c:when test="${ pi.page < pi.maxPage }">
								<a class="arrow next"
									href="${ contextPath }/marketplace/list?page=${ pi.page+1 }${ searchParam }"></a>
							</c:when>
							<c:otherwise>
								<a class="arrow next" href="#"></a>
							</c:otherwise>
						</c:choose></li>

					<!-- 맨 끝으로 이동하는 버튼(>>) -->
					<li><a class="arrow nnext"
						href="${ contextPath }/marketplace/list?page=${ pi.maxPage }${ searchParam }"></a></li>
				</ul>
				<div class="pagingcComment">
					<p>꿀템 게시판의 게시글 ${ pi.listCount }개가 검색되었습니다.</p>
				</div>
			</div>


		</div>
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




	<!-- 푸터 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp">
		<jsp:param name="pName" value="test" />
	</jsp:include>


	<script>
		function detailView(bid){
			location.href='${contextPath}/marketplace/detail?bid='+bid;
		}
	</script>
	<c:choose>
		<c:when test="${ !empty loginUser }">
			<script>
				function insertMarketplace(bid){
					location.href='${contextPath}/marketplace/insert';
				}
			</script>
		</c:when>
		<c:otherwise>
			<script>
				function insertMarketplace(bid){
					alert('로그인 후 이용 가능합니다');
					location.href='${contextPath}/login';
				}
			</script>
		</c:otherwise>
	</c:choose>

</body>
</html>











