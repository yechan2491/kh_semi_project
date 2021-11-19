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

.inner {
	margin-top: 90px;
	display: flex;
	justify-content: center;
	align-items: center;
}

h3{
	font-family: 'Nanum Gothic', sans-serif;
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
			<h3>일치하는 결과가 없습니다.</h3>
			<div class="inner">
				<button onclick="closeWindow()">창 닫기</button>
			</div>
		</div>
	</div>

	<script>
		function closeWindow(){
			window.close();
		}
	</script>
</body>
</html>