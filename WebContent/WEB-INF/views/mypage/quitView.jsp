<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

.mypage_nav >li > a {
    font-family: 'Gothic A1', sans-serif;
}

.mypage_nav>li:not(:first-child ) {
	margin-left: 40px;
}

.mypage_nav>li:nth-child(4)>a {
	border-radius: 10px;
	background-color: rgb(135, 211, 124);
	padding: 5px 5px;
}

.quit {
	display: inline-block;
	border-radius: 5px;
	margin-top: 60px;
	width: 450px;
	height: 550px;
	background-color: rgb(245, 245, 245);
	margin-bottom: 100px;
}

.quit>h2 {
	margin: 20px;
}

.quit>h3 {
	margin: 20px;
}

.text_area {
	resize: none;
	width: 300px;
	height: 200px;
	border-radius: 3px;
	padding: 10px 10px;
	border: 1px solid lightgray;
	font-family: 'Nanum Gothic', sans-serif;
}

.select_reason {
	padding: 10px 10px;
	border: 1px solid lightgray;
	width: 300px;
	border-radius: 3px;
}

.quit_btn {
	width: 100px;
	border: none;
	font-size: 15px;
	border-radius: 5px;
	cursor: pointer;
	padding: 10px 10px;
	background: rgb(135, 211, 124);
	color: #FFF;
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
		<div class="quit">
			<h2>탈퇴하기</h2>
			<select class="select_reason">
				<option>탈퇴 사유를 선택하세요.</option>
				<option>계정에 문제가 있습니다.</option>
				<option>사이트가 마음에 들지 않습니다.</option>
			</select>
			<h3>직접 작성하기</h3>
			<textarea class="text_area" placeholder="내용을 입력해주세요."></textarea>
			<br>
			<br>
			<button class="quit_btn" onclick="quitView();">탈퇴하기</button>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	<script>

function quitView(){
	  if(confirm("정말로 탈퇴하시겠습니까?")){
		  location.href='${contextPath}/mypage/quit';
	  }
}

</script>
</body>
</html>