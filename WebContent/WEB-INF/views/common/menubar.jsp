
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="member.model.vo.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	Member loginUser = (Member)session.getAttribute("loginUser");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>main</title>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>

<!-- jQuery와 Postcodify를 로딩한다 -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Batang:wght@700&display=swap" rel="stylesheet">

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/header_style.css" media="print" onload="this.media='all'">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Batang:wght@700&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@200&display=swap" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gowun+Batang&display=swap" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300&display=swap" rel="stylesheet">

<% if(session.getAttribute("message")!= null) { %>
<script>
	alert('<%= session.getAttribute("message") %>');
</script>
<% session.removeAttribute("message");
	}
%>

<style>
.memberjoin {
	min-width: 80px;
	position: absolute;
	margin-left: 60px;
	border: none;
	font-size: 15px;
	border-radius: 5px;
	cursor: pointer;
	padding: 10px;
	color: #000;
	font-family: 'Nunito', sans-serif;
}

.invisible{
	position: absolute;
	opacity:0;
	width: 40px;
	height: 40px;
	cursor: pointer;
	margin-left: 180px;
	top: 50px;
}
.page-header .container {
	display: flex;
	border-bottom: 2px solid rgb(135, 211, 124);
	background-color: white;
}
header {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 800;
}
body {
	font-family: 'Noto Sans KR', sans-serif;
	padding-top: 120px;
}

</style>
</head>
<body>

	<c:set var="contextPath"
		value="${ pageContext.servletContext.contextPath }"
		scope="application" />
	<!-- 어떤 페이지 에서든 사용이 가능 -->
	<div id="home" class="big-bg">
		<!-- 2. header 만들기 -->
		<header class="page-header wrapper">
			<div class="container">
				<a href="${contextPath }"> <img class="logo"
					src="${contextPath }/resources/images/Logo.png" alt="HOME">
				</a>
				<nav>
					<ul class="main-nav">
						<form name="findForm" method="get">
						<li>
							<input class="search__input" type="text" name="search" id="search" placeholder="Search" value="${ param.search }">
							<button class="invisible" onclick="searchBoard();"></button>
						</li>
						</form>

							<li><a class="first_menu" href="${ contextPath }/total/list"> 최근 게시물 </a></li>
							<li><a class="second_menu" href="${contextPath }/info/list"> 자취는 꿀팁 </a></li>
							<li><a class="third_menu" href="${contextPath }/marketplace/list"> 자취는 꿀템 </a></li>
							<li><a class="forth_menu" href="${contextPath }/friend/main"> 자취는 꿀친 </a></li>


						<li><a class="five_menu" href="#" onclick="noteList();"> 쪽지 </a></li>

						<li>
						
							<c:if  test="${ empty loginUser }">
							<button class="memberjoin" onclick="location.href='${ contextPath }/memberjoin'">회원가입</button>
							<button class="login" onclick="location.href='${ contextPath }/login'">로그인</button>
							</c:if>
							
							<%-- <button class="membermodify" onclick="location.href='${ contextPath }/memberModify'">정보수정</button> --%>
							
							<c:if  test="${ !empty loginUser }">
							<button class="memberjoin" onclick="location.href='${ contextPath }/logout'">Logout</button>
							</c:if>
							<c:if  test="${ !empty loginUser && loginUser.userId != 'admin' }">
							<button class="login after" onclick="location.href='${ contextPath }/myprofile'">Mypage</button>
							</c:if>
							
							<c:if test="${ !empty loginUser && loginUser.userId == 'admin' }">
							<button class="login after" onclick="location.href='${ contextPath }/report/list'">Admin</button>
							</c:if>
							
						</li>

					</ul>
				</nav>
			</div>
		</header>

		</div>
	
		<c:choose>
		<c:when test="${ !empty loginUser }">
			<script>
				function noteList(){
					location.href="${contextPath }/note/receive/list"	
				}
			</script>
		</c:when>
		<c:otherwise>
			<script>
				function noteList(){
					alert('로그인 후 이용 가능합니다');
					location.href="${contextPath }/login";	
				}
			</script>
		</c:otherwise>
		</c:choose>

		
		<script>
			function searchBoard(){
				document.forms.findForm.action = "${contextPath}/total/list";
				document.forms.findForm.submit();
			}
		</script>


</body>
</html>
