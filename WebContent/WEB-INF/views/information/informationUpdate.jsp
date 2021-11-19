<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자취는 템빨</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">
<style>
/* 
body{
	font-family: 'Noto Sans KR', sans-serif;
} */
.second_menu {
color: rgb(135, 211, 124);
}
.outer {
	width: 1280px;
	margin: auto;
	margin-bottom: 2%;
	z-index:1;
	font-family: 'Nanum Gothic', sans-serif;
}

.wrap {
	width: 1240px;
	margin: auto;
}

.formcontrol {
	width: 100%;
	padding-left: 8px;
	border: 0.5px solid gray;
}

.formcontrol:focus {
	border: 0.5px solid #0bd;
	outline: none;
}

.container {
	width: 90%;
}

.btn {
	height: 50px;
	background: rgb(135, 211, 124);
	color: white;
}

.btn:hover {
	cursor: pointer;
	background: #0bd;
}

.btninsertarea {
	text-align: center;
	border-top: 0.5px solid #282A35;
	padding: 30px;
}

.input_area {
	
	padding: 10px 10px 14px 10px;
	
}


.input_area input:focus,
.input_area select:focus {
	outline: none;
}
.title_span {
	background-color: #282A35;
	margin-bottom: -100px;
}
.infocontainer{
	margin-top : 50px;
}

</style>
</head>
<body>


	<!-- 메뉴바 -->
	<jsp:include page="/WEB-INF/views/common/menubar.jsp">
		<jsp:param name="pName" value="test" />
	</jsp:include>

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
<style>
 
.main-nav {
	padding-bottom: 4px;
}

*, ::after, ::before {
    box-sizing: content-box;
}
.memberjoin {
    min-width: 65px;
}

 
</style>


	<div class="outer">
		<div class="wrap">
			<div class="infocontainer">
				<form action="${ contextPath }/info/update" method="POST" enctype="multipart/form-data">
				<input type="hidden" name="bid" value="${ board.bid }">
				<c:forEach items="${board.photoList }" var="photo">
				<input type="hidden" name="changeName" value="${ photo.changeName }">
				</c:forEach>
				<div class="content">
					<span class="title_span">&nbsp;</span> 분류 <span
							class="input_area"> <select name="category">
								<option value="1"
									<c:if test="${ board.cid == 1 }">selected</c:if>>청소</option>
								<option value="2"
									<c:if test="${ board.cid == 2 }">selected</c:if>>꾸미기</option>
								<option value="3"
									<c:if test="${ board.cid == 3 }">selected</c:if>>리폼</option>
								<option value="4"
									<c:if test="${ board.cid == 4 }">selected</c:if>>생활정보</option>

						</select>
						</span>
						<div class="formgroup">
						<br> <br> <label for="title">제목 : </label> 
						<input
							type="text" class="formcontrol" value="${ board.btitle }"
							id="title" name="title" maxlength="25"  required>
					</div>
					<br>

					<div class="formgroup">
						<label for="content">내용 : </label>
						<textarea id="summernote" class="formcontrol" rows="5"
							id="content" name="content" required>${ board.bcontent }</textarea>
					</div>
					<br>

					<div class="formgroup">
						<label for="thumbnail">썸네일 : </label> 
						<div class="image_area">
							<img src="${ contextPath }${ board.photoList.get(0).filePath }${ board.photoList.get(0).changeName }">
						</div>

						수정 파일 : <input type="file" name="thumbnail" accept="image/gif,image/jpeg,image/png">
					<br> <br>
					</div>
					</div>
					<div class="btninsertarea">
						<button type="submit" class="btn">수정하기</button>
						<br> <br>
					</div>
				</form>
			</div>



		</div>
	</div>



	<script>
$(document).ready(function() {
	  $('#summernote').summernote({
		    placeholder: '모든 작성 항목은 필수사항입니다. 게시글 내용은 최대 2048자까지 쓸 수 있습니다.',
		    tabsize: 2,
		    height: 700,
		    focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
			  lang: "ko-KR",					// 한글 설정
			  
		    toolbar: [
		    	['fontname', ['fontname']],
			    ['fontsize', ['fontsize']],
			    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
			    ['color', ['forecolor','color']],
			    ['table', ['table']],
			    ['para', ['paragraph']],
			    ['height', ['height']],
			    ['insert',['picture']],
			    ['view', ['fullscreen', 'help']]
		    ],
		    fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
			fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
			
				
	  });
	});

</script>










	<!-- 푸터 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp">
		<jsp:param name="pName" value="test" />
	</jsp:include>

</body>
</html>