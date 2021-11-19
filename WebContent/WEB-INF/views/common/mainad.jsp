<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<style>
	
		.section { /* 메뉴바와에 간격 입니다 */
	margin-top: 1.5%;
}

.section input[id*="slide"] {
	display: none;
}

.section .slidewrap {
	max-width: 1280px;
	margin: 0 auto;
}

.section .slidelist {
	white-space: nowrap;
	font-size: 0;
	overflow: hidden;
	position: relative;
	height: 500px; 
}

.section .slidelist>li {
	display: inline-block;
	vertical-align: middle;
	width: 100%;
	transition: all .5s;
}

.section .slidelist>li>a {
	display: block;
	position: relative;
}

.section .slidelist>li>a img {
	width: 100%;
}

.section .slidelist label {
	position: absolute;
	z-index: 10;
	top: 50%;
	transform: translateY(-50%);
	padding: 50px;
	cursor: pointer;
}

.section .slidelist .textbox {
	position: absolute;
	z-index: 1;
	top: 15%;
	left: 50%;
	transform: translateX(-50%);
	line-height: 1.6;
	text-align: center;
}

.section .slidelist .textbox h3 {
	font-size: 36px;
	color: #fff;
	transform: translateY(30px);
	transition: all .5s;
}

.section .slidelist .textbox p {
	font-size: 16px;
	color: #fff;
	opacity: 0;
	transform: translateY(30px);
	transition: all .5s;
}

/* input에 체크되면 슬라이드 효과 */
.section input[id="slide01"]:checked ~.slidewrap .slidelist>li {
	transform: translateX(0%);
}

.section input[id="slide02"]:checked ~.slidewrap .slidelist>li {
	transform: translateX(-100%);
}

.section input[id="slide03"]:checked ~.slidewrap .slidelist>li {
	transform: translateX(-200%);
}

.section input[id="slide04"]:checked ~.slidewrap .slidelist>li {
	transform: translateX(-300%);
}

/* input에 체크되면 텍스트 효과 */
.section input[id="slide01"]:checked ~.slidewrap li:nth-child(1) .textbox h3
	{
	opacity: 1;
	transform: translateY(0);
	transition-delay: .2s;
}

.section input[id="slide01"]:checked ~.slidewrap li:nth-child(1) .textbox p
	{
	opacity: 1;
	transform: translateY(0);
	transition-delay: .4s;
}

.section input[id="slide02"]:checked ~.slidewrap li:nth-child(2) .textbox h3
	{
	opacity: 1;
	transform: translateY(0);
	transition-delay: .2s;
}

.section input[id="slide02"]:checked ~.slidewrap li:nth-child(2) .textbox p
	{
	opacity: 1;
	transform: translateY(0);
	transition-delay: .4s;
}

.section input[id="slide03"]:checked ~.slidewrap li:nth-child(3) .textbox h3
	{
	opacity: 1;
	transform: translateY(0);
	transition-delay: .2s;
}

.section input[id="slide03"]:checked ~.slidewrap li:nth-child(3) .textbox p
	{
	opacity: 1;
	transform: translateY(0);
	transition-delay: .4s;
}

.section input[id="slide04"]:checked ~.slidewrap li:nth-child(4) .textbox h3
	{
	opacity: 1;
	transform: translateY(0);
	transition-delay: .2s;
}

.section input[id="slide04"]:checked ~.slidewrap li:nth-child(4) .textbox p
	{
	opacity: 1;
	transform: translateY(0);
	transition-delay: .4s;
}

/* 좌,우 슬라이드 버튼 */


.section input[id="slide01"]:checked ~.slidewrap .slide-control>div:nth-child(1)
	{
	display: block;
}

.section input[id="slide02"]:checked ~.slidewrap .slide-control>div:nth-child(2)
	{
	display: block;
}

.section input[id="slide03"]:checked ~.slidewrap .slide-control>div:nth-child(3)
	{
	display: block;
}

.section input[id="slide04"]:checked ~.slidewrap .slide-control>div:nth-child(4)
	{
	display: block;
}

/* 페이징 */
/* section slidewrap slidelist slide-pagelist li label */
.section .slidewrap {
	position: relative;
}

.slide-pagelist {
	position: absolute;
	top: 95%; /*********/
	left: 50%;
	transform: translate(-50%, -50%);
	text-align: center;
	width: 120px;
	display: flex;
	justify-content: space-between;
}

.slide-pagelist>li {
	display: inline-block;
}

.slide-pagelist>li>label {
	display: block;
	padding: 8px;
	text-align: center;
	border-radius: 30px;
	background: white;
	cursor: pointer;
}

.section input[id="slide01"]:checked ~.slidewrap .slide-pagelist>li:nth-child(1)>label
	{
	background: rgb(135, 211, 124);
}

.section input[id="slide02"]:checked ~.slidewrap .slide-pagelist>li:nth-child(2)>label
	{
	background: rgb(135, 211, 124);
}

.section input[id="slide03"]:checked ~.slidewrap .slide-pagelist>li:nth-child(3)>label
	{
	background: rgb(135, 211, 124);
}

.section input[id="slide04"]:checked ~.slidewrap .slide-pagelist>li:nth-child(4)>label
	{
	background: rgb(135, 211, 124);
}
	
	</style>

</head>
<body>
	<div class="section">
		<input type="radio" name="slide" id="slide01" checked class="check">
		<input type="radio" name="slide" id="slide02" class="check"> <input
			type="radio" name="slide" id="slide03" class="check"> <input
			type="radio" name="slide" id="slide04" class="check">

		<!-- 5초마다 광고슬라이드를 위한 스크립트 -->
		<script>
			let radio = document.querySelectorAll(".check");
			let i = 0;
			let interval = setInterval(function() {
				if (radio[0].checked)
					radio[1].checked = true;
				else if (radio[1].checked)
					radio[2].checked = true;
				else if (radio[2].checked)
					radio[3].checked = true;
				else if (radio[3].checked)
					radio[0].checked = true;
			}, 5000);
		</script>

		<div class="slidewrap">

			<ul class="slidelist">
				<!-- 슬라이드 영역 -->
				<li class="slideitem">
					<a>
						<img src="${contextPath }/resources/images/madpt1.jpg">
					</a>
				</li>
				<li class="slideitem">
					<a>
						<img src="${contextPath }/resources/images/madpt2.jpg">
					</a>
				</li>
				<li class="slideitem">
					<a>
						<img src="${contextPath }/resources/images/madpt3.jpg">
					</a>
				</li>
				<li class="slideitem">
					<a>
						<img src="${contextPath }/resources/images/madpt4.jpg">
					</a>
				</li>
				</ul>

				<!-- 좌,우 슬라이드 버튼 -->
				<div class="slide-control">
					<div>
						<label for="slide04" class="left"></label> <label for="slide02"
							class="right"></label>
					</div>
					<div>
						<label for="slide01" class="left"></label> <label for="slide03"
							class="right"></label>
					</div>
					<div>
						<label for="slide02" class="left"></label> <label for="slide04"
							class="right"></label>
					</div>
					<div>
						<label for="slide03" class="left"></label> <label for="slide01"
							class="right"></label>
					</div>

				</div>

			<!-- 페이징 -->
			<ul class="slide-pagelist">
				<li><label for="slide01"></label></li>
				<li><label for="slide02"></label></li>
				<li><label for="slide03"></label></li>
				<li><label for="slide04"></label></li>
			</ul>
		</div>
	</div>
</body>
</html>