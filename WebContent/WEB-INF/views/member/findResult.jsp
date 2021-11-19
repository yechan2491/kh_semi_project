<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
.outer {
	width: 450px;
	height: 350px;
	margin: auto;
	justify-content: center;
	align-items: center;
	text-align: center;
}

.inner {
	margin-top: 60px;
	display: flex;
	justify-content: center;
	align-items: center;
}

button {
	font-family: 'Nanum Gothic', sans-serif;
	width: 110px;
	height: 40px;
	border: 0px;
	color: white;
	background: rgb(135, 211, 124);
	cursor: pointer;
}

#find_btn {
	margin-left: 20px;
}

input {
	font-family: 'Nanum Gothic', sans-serif;
	font-weight: bold;
	width: 200px;
	font-size: 16px;
	border: none;
}

input:focus {
	outline: none;
}

#span {
	border: 1px solid lightgray;
	padding: 10px 5px 10px 5px;
}

h2 {
	font-family: 'Nanum Gothic', sans-serif;
	margin-top: 95px;
	margin-bottom: 45px;
}
</style>

<title>아이디 찾기</title>
</head>
<body>
	<div class="outer">
		<h2>일치하는 아이디</h2>
		<span id="span"> <input id="findResult" readonly>
		</span>
		<div class="inner">
			<button onclick="login()" id="login_btn">로그인</button>
			<button onclick="findPwd()" id="find_btn">비밀번호 찾기</button>
		</div>
	</div>

	<script>
		document.getElementById("findResult").value = opener.document.getElementById("id_hidden").value;
		
		function login(){
			opener.location.href='${contextPath}/login';
			window.close();
		}
		
		function findPwd(){
			opener.location.href='${contextPath}/pwdfind';
			window.close();
		}
		
	</script>
</body>
</html>