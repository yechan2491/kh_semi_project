<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>mypage</title>

<style>
.home-content {
	display: inline-block;
	margin-top: 50px;
	text-align: center;
	font-family: 'Nanum Gothic', sans-serif;
}

.mypage_nav>li {
	display: inline;
	font-weight: 700;
	font-size: 20px;
}

.mypage_nav>li>a {
	font-family: 'Gothic A1', sans-serif;

	/*  font-family: 'Nanum Gothic', sans-serif;  */
	/* font-family: 'Gowun Batang', serif; */
}

.mypage_nav>li:not(:first-child ) {
	margin-left: 40px;
}

.mypage_nav>li:nth-child(2)>a {
	border-radius: 10px;
	background-color: rgb(135, 211, 124);
	padding: 5px 0;
	padding-left: 5px;
}

.my_product h1 {
	margin-top: 30px;
	font-family: 'Nanum Gothic', sans-serif;
}

.board_box {
	width: 220px;
	margin-top: 35px;
	height: 290px;
	border: 1px solid rgb(230, 230, 230);
	font-family: 'Nanum Gothic', sans-serif;
	cursor: pointer;
}

.product_name {
	margin-left: 10px;
	  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.product_img {
	width: 220px;
	height: 210px;
	border-bottom : 1px solid rgb(230, 230, 230);
}

.product_info {
	display: flex;
	margin-left: 10px;
	justify-content: space-between;
}

.post_date {
	margin-right: 5px;
	color: gray;
	font-size: 14px;
	margin-top: 3px;
}

li {
	list-style: none;
}

.board_list {
	display: flex;
	flex-wrap: wrap;
	min-height: 350px;
}

.board_box:not(:nth-child(5n)){
	margin-right: 42px;
}

.board_paging {
	height: 40px;
	line-height: 40px;
	display: flex;
	justify-content: center;
	list-style: none;
	width: 480px;
	margin: 30px auto;

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
	background: url('${contextPath}/resources/images/page_pprev.png') no-repeat center center;
}

.board_paging .prev {
	background: url('${contextPath}/resources/images/page_prev.png') no-repeat center center;
	margin-right: 7px;
}

.board_paging .next {
	background: url('${contextPath}/resources/images/page_next.png') no-repeat center center;
	margin-left: 7px;
}

.board_paging .nnext {
	background:	url('${contextPath}/resources/images/page_nnext.png') no-repeat center center;
	margin-right: 0;
}


</style>
</head>
<body>

	<%@ include file="/WEB-INF/views/common/menubar.jsp"%>


	<div class="home-content wrapper">
		<nav>
			<ul class="mypage_nav">
				<li><a href="${contextPath }/myprofile"> 프로필 </a></li>
				<li><a href="${contextPath }/myproduct"> 찜한상품 </a></li>
				<li><a href="${contextPath }/myscrap"> 스크랩 </a></li>
				<li><a href="${contextPath }/mysetting"> 설정 </a></li>
			</ul>
		</nav>
	</div>
	<div class="my_product container">
		<h1>찜한 상품</h1>

		<div class="board_list">
			<c:forEach var="board" items="${boardList }">
				<ul class="board_box" onclick="detailView(${board.bid})">
					<img class="product_img"  src="${ contextPath }${ board.friendPhotoList.get(0).filePath }${board.friendPhotoList.get(0).changeName}">
					<p class="product_name">${ board.btitle }</p>
					<div class="product_info">
						<h4 class="product_price">
							<fmt:formatNumber value="${ board.price }" groupingUsed="true" /> 원</h4>
						<p class="post_date"><c:choose>
									<c:when test="${ board.writetime  > 24 }">
										<span> ${ board.writeday }일 전</span>
									</c:when>
									<c:when test="${ board.writemin  > 60 }">
										<span> ${ board.writetime }시간 전</span>
									</c:when>
									<c:when test="${ board.writemin < 2 }">
										<span>방금 전</span>
									</c:when>
									<c:otherwise>
										<span>${ board.writemin }분 전</span>
									</c:otherwise>
								</c:choose></p>
					</div>
				</ul>
			</c:forEach>
		</div>
		<script>
		function detailView(bid){
				location.href="${contextPath}/marketplace/detail?bid="+bid;
		}
		</script>
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


	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>