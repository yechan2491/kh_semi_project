<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
.outer {
	width: 450px;
	height: 450px;
	margin: auto;
	display: flex;
	justify-content: center;
	align-items: center;
}

h3{
	font-family: 'Nanum Gothic', sans-serif;
}

.inner {
	margin-top: 90px;
	display: flex;
	justify-content: center;
	align-items: center;
}

button {
	font-family: 'Nanum Gothic', sans-serif;
	width: 120px;
	height: 40px;
	font-size: 16px;
	border: 0px;
	color: white;
	background: rgb(135, 211, 124);
	cursor: pointer;
}
</style>

<title>Insert title here</title>
</head>
<body>
	<div class="outer">
		<div>
			<h3>등록된 이메일로 임시 비밀번호를 전송하였습니다.</h3>
			<div class="inner">
				<button onclick="login()" id="login_btn">로그인</button>
			</div>
		</div>
	</div>

	<script>
	function login(){
		opener.location.href='${contextPath}/login';
		window.close();
	}
	</script>
</body>
</html>