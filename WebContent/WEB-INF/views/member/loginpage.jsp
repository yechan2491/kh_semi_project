<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
.outer {
	width: 500px;
	height: 500px;
	border: 1px solid lightgray;
	border-radius: 10px;
	margin: 70px auto;
	display: flex;
	justify-content: center;
	align-items: center;
}

.id_area {
	display: block;
	position: relative;
	height: 30px;
	border-top: solid 1px #dadada;
	border-left: solid 1px #dadada;
	border-right: solid 1px #dadada;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
	padding: 10px 5px 10px 5px;
	background: white;
}

.pwd_area {
	display: block;
	position: relative;
	height: 30px;
	border: solid 1px #dadada;
	border-bottom-left-radius: 10px;
	border-bottom-right-radius: 10px;
	padding: 10px 5px 10px 5px;
	background: white;
}

span div {
	position: absolute;
	padding: 1px;
	width: 25px;
	height: 25px;
}

#login_img {
	width: 25px;
	height: 25px;
	padding: 3px;
}

.id_area input {
	font-family: 'Nanum Gothic', sans-serif;
	padding-left: 50px;
	width: 320px;
	height: 35px;
	border: 0px;
	font-size: 16px;
}

.pwd_area input {
	font-family: 'Nanum Gothic', sans-serif;
	padding-left: 50px;
	width: 320px;
	height: 35px;
	border: 0px;
	font-size: 16px;
}

.id_area input:focus {
	outline: none;
}

.pwd_area input:focus {
	outline: none;
}

#joinBtn {
	font-family: 'Nanum Gothic', sans-serif;
	font-size: 24px;
	font-weight: bold;
	color: white;
	width: 100%;
	height: 50px;
	border: white;
	border-radius: 10px;
	background-color: rgb(135, 211, 124);
}

#joinBtn:hover {
	cursor: pointer;
}

h4 label, #login_img, #login_label {
	-ms-user-select: none;
	-moz-user-select: -moz-none;
	-khtml-user-select: none;
	-webkit-user-select: none;
	user-select: none;
}

#login_label {
	font-family: 'Nanum Gothic', sans-serif;
	font-weight: bold;
	font-size: 40px;
	padding-bottom: 50px;
	width: 120px;
	display: block;
	margin: auto;
}

#member {
	padding: 0;
	width: 250px;
	display: flex;
	margin: auto;
	margin-top: 20px;
	list-style-type: none;
	justify-content: space-around;
	line-height: 30px;
}

#member a {
	font-family: 'Nanum Gothic', sans-serif;
	font-size: 16px;
	text-decoration: none;
	color: black;
	font-weight: bold;
	display: block;
	text-align: center;
}

#id_remember {
	font-family: 'Nanum Gothic', sans-serif;
	padding-top: 20px;
	padding-bottom: 20px;
}
</style>

<title>login</title>
</head>
<body>
	<!-- 모든 페이지에 include할 menubar.jsp -->
	<%@ include file="/WEB-INF/views/common/menubar.jsp"%>

	<div class="wrapper">
		<div class="outer">
			<form class="loginArea" action="<%= request.getContextPath()%>/login"
				method="post">
				<label id="login_label">로그인</label> <span class="id_area">
					<div>
						<img src="${ contextPath }/resources/images/id.png" id="login_img">
					</div> <input type="text" name="userId" id="userId" maxlength="15"
					placeholder="아이디를 입력하세요" required>
				</span> <span class="pwd_area">
					<div>
						<img src="${ contextPath }/resources/images/password.png"
							id="login_img">
					</div> <input type="password" name="userPwd" id="userPwd" maxlength="15"
					placeholder="비밀번호를 입력하세요" required>
				</span>
				<h4 id="id_remember">
					<input type="checkbox" name="remember" id="remember"> <label
						for="remember">아이디 기억하기</label>
				</h4>
				<button id="joinBtn">로그인</button>
				<ul id="member">
					<li><a href="${ contextPath }/idfind">아이디 찾기</a></li>
					<li><a href="${ contextPath }/pwdfind">비밀번호 찾기</a></li>
				</ul>
			</form>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/common/footer.jsp"%>

	<!-- 쿠키로 아이디 기억하기 기능 -->
	<script
		src="<%= request.getContextPath()%>/resources/js/rememberUserId.js"></script>
</body>
</html>