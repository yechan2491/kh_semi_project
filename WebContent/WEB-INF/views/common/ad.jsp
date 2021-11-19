<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/ad_style.css">

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
				<li class="slideitem"><a>
						<div class="textbox">
							<h3>첫번째 슬라이드</h3>
							<p>첫번째 슬라이드 입니다.</p>
						</div>
					<img src="${contextPath }/resources/images/ad3.jpg">
				</a></li>
				<li class="slideitem"><a>
						<div class="textbox">
							<h3>두번째 슬라이드</h3>
							<p>두번째 슬라이드 입니다.</p>
						</div> <img src="${contextPath }/resources/images/ad2.png">
				</a></li>
				<li class="slideitem"><a>
						<div class="textbox">
							<h3>세번째 슬라이드</h3>
							<p>세번째 슬라이드 입니다.</p>
						</div> <img src="${contextPath }/resources/images/ad1.jpg">
				</a></li class="slideitem">
				<li class="slideitem"><a>
						<div class="textbox">
							<h3>네번째 슬라이드</h3>
							<p>네번째 슬라이드 입니다.</p>
						</div> <img src="${contextPath }/resources/images/ad4.jpg">
				</a></li class="slideitem">

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


			</ul>
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