<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
.mypage_nav> li:not(:first-child ){
	margin-left: 40px;
}

.mypage_nav>li:first-child>a {
	border-radius: 10px;
	background-color: rgb(135, 211, 124);
	padding: 5px 0;
	padding-left: 5px;
}

.profile {
	display: inline-block;
	margin-top: 40px;
	border-radius: 50px;
	width: 350px;
	height: 610px;
	background-color: rgb(245, 245, 245);
	margin-bottom: 100px;
}

.profile>h1 {
	position: relative;
	bottom: 20px;
	color: #432;
}

.setting {
	padding: 10px 50px;
	border-style: none;
	background-color: #575756;
	border-radius: 10px;
	color: white;
	font-size: 20px;
	cursor: pointer;
}

a {
	text-decoration: none;
}

.icon>a>img {
	width: 35px;
	margin: 0 40px;
	margin-top: 40px;
}

.count>h2 {
	display: inline-block;
	margin: 0 52px;
	color: #432;
}

.both>h2 {
   margin: 0 43px;
	margin-left :45px;
}

.likeOnly>h2{
	margin: 0 51px;
	margin-left :45px;
}
.productOnly>h2{
	margin: 0 45px;
	margin-left :51px;
}

.image_area {
	margin-top: 50px;
	display: inline-block;
	width: 250px;
	height: 250px;
	background-color: white;
	border-radius: 70%;
	margin-bottom: 35px;
	overflow: hidden;
}

.image_area img {
	width: 100%;
	height: 100%;
	object-fit: cover;
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
		<div class="profile">
			<div class="image_area">
				<c:if
					test="${ loginUser.getProfilePath() != '/resources/uploadFiles/null' }">
					<img src="${contextPath }${ loginUser.getProfilePath()}">
				</c:if>
				<c:if
					test="${ loginUser.getProfilePath() == '/resources/uploadFiles/null' }">
					<img src="${contextPath }/resources/images/mypage_profile.png">
				</c:if>
			</div>
			<h1><%= loginUser.getNickName()%>님
			</h1>
			<button class="setting"
				onclick="location.href='${ contextPath }/mysetting'">설정</button>
			<div class="icon">
				<a href="${contextPath }/myproduct"> <img
					src="${contextPath }/resources/images/heart.png">
				</a> <a href="${contextPath }/myscrap"> <img
					src="${contextPath }/resources/images/like.png">
				</a>
			</div>
			<c:choose>
			<c:when test="${ likeCount < 10 and productCount < 10}">
			<div class="count">
				<h2>${ likeCount }</h2>
				<h2>${ productCount }</h2>
			</div>
			</c:when>
			<c:when test="${ likeCount > 10 and productCount < 10}">
			<div class="count likeOnly">
				<h2>${ likeCount }</h2>
				<h2>${ productCount }</h2>
			</div>
			</c:when>
			<c:when test="${ likeCount < 10 and productCount > 10}">
			<div class="count productOnly">
				<h2>${ likeCount }</h2>
				<h2>${ productCount }</h2>
			</div>
			</c:when>
			<c:when test="${ likeCount >= 10 and productCount >= 10}">
			<div class="count both">
				<h2>${ likeCount }</h2>
				<h2>${ productCount }</h2>
			</div>
			</c:when>
			</c:choose>
		</div>
	</div>


	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	
</body>
</html>