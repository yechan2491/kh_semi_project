<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자취는 꿀친 글쓰기</title>

<!-- header -->
<%@ include file="/WEB-INF/views/common/menubar.jsp"%>

<!-- 썸머노트 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote-bs4.css"
	rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote-bs4.js"></script>

<!-- 글씨체 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100&display=swap"
	rel="stylesheet">


<style>
@charset "UTF-8";

.memberjoin {
	min-width: 65px;
}

.forth_menu {
color: rgb(135, 211, 124);
}

.container {
	width: 1280px;
	margin: auto;
	margin-bottom: 3%;
}

/* 이미지 영역 */
.imagearea {
	margin-top: 5%;
	height: 450px;
	width: 100%;
	display: inline-flex;
}

.image_area {
	border: 1px solid black;
	display: flex;
	justify-content: center;
	align-items: center;
	overflow: hidden;
}

.image_area img {
	width: 100%;
	object-fit: cover;
	justify-content: center;
	align-items: center;
}

#image_area1 {
	float: left;
	height: 450px;
	width: 47%;
	color: red;
}


.files {
	display: none;
}

.subarea {
	margin-left: 3%;
	height: 450px;
	width: 50%;
	flex-wrap: wrap;
	posiion: relative;
}

#image_area2 {
	height: 210px;
	width: 300px;
	float: right;
}

#image_area3 {
	height: 210px;
	width: 300px;
	color: red;
}

#image_area4 {
	height: 210px;
	width: 300px;
	margin-top: 30px;
}

#image_area5 {
	height: 210px;
	width: 300px;
	float: right;
	margin-top: -210px;
}

.container_writer {
	top-margin: 5%;
}

.title {
	width: 1280px;
	
	margin-bottom: 3%;
}

#counter {
	margin-left: 95%;
	font-size: 25px;
	font-style: border;
}

.maintitle {
	border-top: none;
	border-right: none;
	border-left: none;
	border-bottom: 1px solid;
	width: 1280px;
	height: 40px;
	font-size: 30px;
	font-weight: bolder;
	margin-top: 100px;
}

#locationtext {
	position: flex;
	font-weight: bolder;
	font-size: 20px;
	justify-content: center;
	float: left;
}

#select {
	height: 40px;
	width: 200px; /* 너비설정 */
	border: 1px solid #999; /* 테두리 설정 */
	font-size: 18px;
	margin-left: 150px;
}

#phonetext {
	position: flex;
	font-weight: bolder;
	font-size: 20px;
	margin-top: 40px;
}

#gender {
	position: relative;
	font-weight: bolder;
	font-size: 20px;
	margin-top: 40px;
}

.genderradio {
	position: relative;
	font-weight: bolder;
	font-size: 20px;
	margin-top: -60px;
	margin-left: 185px;
}

#phoneNum {
	position: relative;
	width: 200px;
	height: 40px;
	border: 1px solid #999;
	top: -55px;
	font-size: 18px;
	margin-left: 185px;
}

/* 토글 */
.toggle {
	margin-top: 2%;
	border: 1px solid;
}

.toggle a {
	display: block;
	padding: 15px 22px;
	cursor: pointer;
	width: 100%;
	transition: .4s ease;
	font-weight: bolder;
	font-size: 20px;
}

.toggle a:hover {
	transform: scale(1.01);
}

.toggle a.triggerToggle {
	border: 1px #999;
	color: #333;
	text-decoration: none;
}

.target {
	padding: 1em 2em 2em;
	font-weight: 700;
	box-sizing: border-box;
	display: boeder;
	width: 100%;
	margin: 0 auto;
	color: #333;
	margin-left: 10px;
	
}

.textarea {
	font-size: 30px;
	margin-top: 1%;
}

.cancelbutton {
	width: 120px;
	height: 40px;
	margin-top: 3.2%;
	float: right;
	border: none;
	background-color: rgb(213, 213, 213);
}

.cancelbutton:hover {
	background-color: #FF2424;
	border: none;
}

.cancelbutton:active {
	position: relative;
	top: 1px;
}

.previewbutton {
	width: 120px;
	height: 40px;
	margin-top: 1px;
	margin-left: 23%;
	border: none;
	background-color: rgb(213, 213, 213);
}

.previewbutton:hover {
	background-color: rgb(135, 211, 124);
	border: none;
}

.previewbutton:active {
	position: relative;
	top: 1px;
}

.enrollbutton {
	width: 120px;
	height: 40px;
	margin-top: 3.2%;
	float: right;
	border: none;
	background-color: rgb(213, 213, 213);
	margin-left: 1%;
}

.enrollbutton:hover {
	background-color: rgb(135, 211, 124);
	border: none;
}

.enrollbutton:active {
	position: relative;
	top: 1px;
}

.star {
	width: 50px;
}

.toggledown {
	width: 30px;
	float: right;
	margin-top: 10px;
}

.toggleup {
	width: 30px;
	float: right;
	margin-top: 10px;
}

*, ::after, ::before {
    box-sizing: content-box;
}

button {
line-height:normal;
}

body {
font-family: 'Noto Sans KR', sans-serif;
}

</style>
</head>


<body>

	<form method="post" enctype="multipart/form-data"
		action="${ contextPath }/friend/writer">

		<div class="container">

			<div class="container_writer">
				<!-- <p id="title">자취는 꿀친 - 글쓰기</p> -->
				<br>
				<div class="wirter">

					<!-- 토글 슬라이드 다운 -->
					<!-- 글 작성 방법 -->
					<div class="toggle">
						<a href="#" class="triggerToggle">
						 <img class="star" src="${contextPath}/resources/images/star.png">글 작성 주의사항
						</a>
						<div class="target">	
							<ul>
								<li>*는 필수입력입니다</li>
								<li>비속어, 욕설은 금지입니다.</li>
								<li>상대방을 위헙하는 언행이나 기분을 상하게 하는 태도는 하지 않도록 합니다.</li>
								<li>사진에 과도한 노출등 불쾌함을 줄 수있는 사진은 안됩니다.</li>
								<li>명함, 이름표, 학생증등 개인정보가 노출되지 않도록 주의하세요.</li>
							</ul>
						</div>
					</div>

					<!-- 기본 정보 입력 -->
					<div class="toggle">
						<a href="#" class="triggerToggle">
						<img class="star" src="${contextPath}/resources/images/check.png">기본 정보 입력
						</a>

						<div class="target">
							<p id="locationtext">지역</p>
							<select id="select" name="category">
								<option value="31">홍대</option>
								<option value="32">강남</option>
								<option value="33">종로</option>
								<option value="34">잠실</option>
								<option value="35">부천</option>
								<option value="36">인천</option>
								<option value="37">전주</option>
								<option value="38">부산</option>
								<option value="39">제주도</option>
							</select>

							<p id="gender">성별</p>
							<div class="genderradio">
								<input type="radio" id="female" name="gender" value="F" checked>
								<label for="female">여자</label>&nbsp;&nbsp;
								<input type="radio" id="male" name="gender" value="M">
								<label for="male">남자</label>&nbsp;&nbsp;
								<input type="radio" id="male" name="gender" value="N">
								<label for="none">비공개</label>
							</div>

						</div>
					</div>

					<!-- 이미지 영역 -->
					<div class="imagearea">
						<!-- 메인 이미지 -->
						<div class="image_area" id="image_area1" required>* 메인이미지</div>

						<div class="subarea">
							<!-- 서브이미지 -->
							<div class="image_area" id="image_area2" required>서브이미지2</div>
							<div class="image_area" id="image_area3">* 서브이미지1</div>
							<div class="image_area" id="image_area4">서브이미지3</div>
							<div class="image_area" id="image_area5">서브이미지4</div>
						</div>
					</div>

					<!-- 파일업로드 -->
					<div class="files">
						<!-- 메인 -->
						<input type="file" name="thumbnail" class="imgUpload" id="thumbnail" accept="image/jpeg,image.png" required>
						<!-- 서브 -->
						<input type="file" name="contentImg1" class="imgUpload" accept="image/jpeg,image.png">
						<input type="file" name="contentImg2" class="imgUpload" accept="image/jpeg,image.png">
						<input type="file" name="contentImg3" class="imgUpload" accept="image/jpeg,image.png">
						<input type="file" name="contentImg4" class="imgUpload" accept="image/jpeg,image.png">
					</div>
				</div>
			</div>

			<div class="title">
				<input type="text" name="title" class="maintitle" placeholder="*제목을 입력해주세요." maxlength="30" required>
				<p>
					<span id="counter">0</span>/30
				</p>

				<textarea id="summernote2" class="textarea" name="content"></textarea>



				<button type="submit" class="enrollbutton">등록</button>
	<button type="button" class="cancelbutton">취소</button>
			</div>
		</div>
	</form>
	

	<script>
	//toggle slide
	$(".triggerToggle").click(function(){
		console.log($(this));
	      if($(this).next("div").css("display") == "none"){
	         // $("div.triggerToggle").slideUp();
	         // $(this).next("div").slideDown();
	         $(this).next("div").show();
	      } else {
	        //$(this).next("div").slideUp();
	        $(this).next("div").hide();
	      }
	});	

    $(document).ready(function(){
 	   //key up 눌렀을때
        $("input").keyup(function(){
             // 현재 요소의 값의 길이 알기
             let inputLength = $(this).val().length;
             $("#counter").text(inputLength);

             let remain = 30 - inputLength;

             if(remain >= 0)
                 $("#counter").css("color", "black");
             else
                 $("#counter").css("color", "red");
        });       
	});
    

</script>




	<script>
    document.querySelectorAll(".image_area").forEach(item => item.addEventListener('click', fileUploadOpen));
    document.querySelectorAll(".imgUpload").forEach(item => item.addEventListener('change', preview));
   
    function fileUploadOpen(){
        let index = Array.from(document.querySelectorAll(".image_area")).indexOf(this); 
        document.querySelectorAll(".imgUpload")[index].click();
    }

    function preview(){
         if (this.files && this.files[0]) {
            let index = Array.from(document.querySelectorAll(".imgUpload")).indexOf(this); 
            let reader = new FileReader();
            reader.readAsDataURL(this.files[0]);
            reader.onload = function () {
                document.querySelectorAll(".preview")[index].innerHTML = "<img src=" + reader.result + ">";
            }
        }
    }
    
    
</script>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<script
		src="<%=request.getContextPath()%>/resources/js/summernote_func.js"></script>
	<script src="${ contextPath }/resources/js/imagePreview.js"></script>
</body>

<!-- footer -->
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</html>