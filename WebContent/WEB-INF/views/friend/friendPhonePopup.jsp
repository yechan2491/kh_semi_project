<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>꿀친 게시판 전화 팝업</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100&display=swap"
	rel="stylesheet">
<style>
body {
	font-family: 'Noto Sans KR', sans-serif;
}
/*RESET*/
* {
	margin: 0;
	padding: 0;
}

a {
	text-decoration: none;
	color: #404040;
}

li {
	list-style: none;
}

/*BODY*/

/*팝업창*/
#popup {
	display: none; /*숨기기*/
	position: fixed;
	width: 500px;
	height: 300px;
	margin-top: -800px;
	margin-left: -700px;
	background-color: white;
	border: 1px solid black;
	z-index: 1;
}

#popmenu {
	position: absolute;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	width: 300px;
	height: 200px;
	text-align: center;
	background: #fff;
}

#u1 {
	margin-top: 5px;
	font-size: 20px;
}

#u2 {
	margin-top: 5px;
	font-size: 30px;
}

.exit {
	position: absolute;
	left: 50%;
	bottom: 10px;
	transform: translate(-50%, 0);
	width: 90px;
	height: 40px;
	text-align: center;
	line-height: 30px;
	cursor: pointer;
	background: rgb(135, 211, 124);
	color: #FFF;
}

/*본문내용*/
#contents {
	position: absolute;
	width: 50px;
	height: 50px;
	cursor: pointer;
}
</style>
</head>
<body>

	<!--pop up-->
	<div id="popup">
		<div id="popmenu">
			<p id="u1">${ board.userName }님의 전화번호 입니다.</p>
			<p id="u2">${ board.phone }</p>
			<div class="exit">닫기</div>
		</div>
	</div>

	<!--contents-->
	<div id="contents"></div>

	<script>
$(document).ready(function(){
    $("#contents").click(function(){
        $("#popup").show();
    });
    $("#popup").click(function(){
        $("#popup").hide();
    });
});

</script>

</body>
</html>