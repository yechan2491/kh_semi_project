<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>

<meta charset="UTF-8">
<title>꿀친게시판 상세페이지</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100&display=swap"
	rel="stylesheet">

<link href="<%=request.getContextPath() %>/resources/css/slide.css"
	rel="stylesheet">
<style>


.forth_menu {
color: rgb(135, 211, 124);
}

.container {
	width: 1280px;
	margin: auto;
	margin-bottom: 3%;
	font-family: 'Nanum Gothic', sans-serif;
}

/* 이미지 영역 */
.imagearea {
	/* background-color: gray; */
	margin-top: 3%;
	height: 450px;
	width: 100%;
	display: inline-flex;
}

.mainarea {
	border: 1px solid black;
	display: flex;
	width: 47%;
	justify-content: center;
	align-items: center;
	margin-left: 2%;
	max-width: 100%;
	overflow: hidden;
}

img {
	weight: 100%;
	object-fit: cover;
}

.subarea {
	border: 1px solid black;
	display: flex;
	width: 47%;
	height: 421px;
	justify-content: center;
	align-items: center;
	margin-left: 2%;
}

.subject {
	height: 55px;
	line-height: 55px;
	display: flex;
	border-bottom: 1px solid #f3f5f7;
}

.subject span:nth-child(2n) {
	height: 55px;
	line-height: 55px;
	display: flex;
	border-bottom: 1px solid #f3f5f7;
	margin-left: 80%;
}

.title {
	font-size: 40px;
	font-weight: bolder;
	margin-left: 1%;
	margin-top: -100px;
}

.content {
	font-size: 20px;
	margin-top: 3%;
	margin-left: 3%;
	min-height: 300px;
}

.content p {
font-family: 'Nanum Gothic', sans-serif;
}

.notify {
	width: 100px;
	height: 100px;
	display: flex;
	flex-direction: column;
	align-items: center;
	margin-top: 20px;
	float: right;
}

.notify img {
	width: 50px;
	height: 50px;
}

.notify hr {
	width: 60px;
	height: 1.5px;
	background-color: #999;
	border: 0px;
}

.likebox {
	float: right; /* 위치정렬 진짜 드럽게 안됨 ㅡㅡ;;;; */
	margin-top: -100px;
}

.likecontainer {
	position: relative;
}

.like {
	width: 60px;
	height: 60px;
	postion: absolute;
	margin-top: 150px;
	left: 150px;
	z-index: 0;
}

.likecheck {
	width: 60px;
	height: 60px;
	postion: absolute;
	margin-top: -60px;
	left: 150px;
	z-index: 1;
}

.likecheck {
	opacity: 0;
}

.likecheck:hover {
	opacity: 1;
}

.messagebox {
	float: right; /* 위치정렬 진짜 드럽게 안됨 ㅡㅡ;;;; */
	margin-top: -100px;
	margin-right: 2%;
}

.messagecontainer {
	position: relative;
}

.message {
	width: 60px;
	height: 60px;
	postion: absolute;
	margin-top: 150px;
	left: 150px;
	z-index: 0;
}

.messagetext {
	width: 60px;
	height: 60px;
	postion: absolute;
	margin-top: -60px;
	left: 150px;
	z-index: 1;
}

.messagetext {
	opacity: 0;
}

.messagetext:hover {
	opacity: 1;
}

.phonebox {
	float: right; /* 왜 혼자 정렬 안맞으신지~,,, */
	margin-top: -100px;
	margin-right: 2%;
	margin-left: 2%;
}

.phonecontainer {
	position: relative;
}

.phone {
	width: 60px;
	height: 60px;
	postion: absolute;
	margin-top: 150px;
	left: 150px;
	z-index: 0;
}

.phonetext {
	width: 60px;
	height: 60px;
	postion: absolute;
	margin-top: -60px;
	left: 150px;
	z-index: 1;
}

.phonetext {
	opacity: 0;
}

.phonetext:hover {
	opacity: 1;
}

#box {
	width: 100px;
	height: 100px;
	border-radius: 70%;
	overflow: hidden;
}
/* 프로필 사진 */
#profile {
	height: 100%;
	height: 100%;
	object-fit: cover;
}

.MemberInfo {
	margin-top: 3%;
}

.MemberInfo2 {
	float: left;
	margin-left: 30px;
}

.MemberInfo3 {
	float: left;
	margin-left: 30px;
}

#name {
	font-weight: bolder;
	margin-top: 10px;
	font-size: 30px;
}

#gender {
	font-size: 15px;
	margin-top: -10px;
	margin-left: 5px;
}

#location_name {
	font-size: 20px;
	margin-top: 100px;
	font-weight: bolder;
	color: rgb(135, 211, 124);
}

#hr1 {
	margin-top: 155px;
	height: 2px;
	background-color: #CACACA;
	border: 0px;
}

#hr2 {
	margin-top: 20px;
	height: 1px;
	background-color: #CACACA;
	border: 0px;
}

.listbutton {
	width: 120px;
	height: 40px;
	margin-top: -3%;
	margin-left: 1%;
	float: right;
	border: none;
	background-color: rgb(213, 213, 213);
}

.listbutton:hover {
	background-color: rgb(135, 211, 124);
	border: none;
}

.listbutton:active {
	position: relative;
	top: 1px;
}

.editbutton {
	width: 120px;
	height: 40px;
	margin-top: -3%;
	margin-left: 1%;
	float: right;
	border: none;
	background-color: rgb(213, 213, 213);
}

.editbutton:hover {
	background-color: rgb(135, 211, 124);
	border: none;
}

.editbutton:active {
	position: relative;
	top: 1px;
}

.removebutton {
	width: 120px;
	height: 40px;
	margin-top: -3%;
	margin-left: 1%;
	float: right;
	border: none;
	background-color: rgb(213, 213, 213);
}

.removebutton:hover {
	background-color: #FF2424;
	border: none;
}

.removebutton:active {
	position: relative;
	top: 1px;
}

.comment_ul {
	flex-direction: row;
	margin-left: 2%;
	margin-top: 3%;
	margin-bottom: 6%;
	border: 1px;
	height: 70px;
}

.commentprofile1 {
	height: 50px;
	width: 50px;
	border-radius: 100%;
	overflow: hidden;
}

.commentprofile2 {
	height: 100%;
	weight: 100%;
	object-fit: cover;
}

.commentwriter {
	font-size: 12px;
}

.commenttext {
	margin-left: 10%;
	font-size: 20px;
	margin-top: -5%;
}

.commentbtn{
	margin-top: -5%;
	display: flex;  
	align-items: center; 
	float:right;
}

/* 댓글신고 */
.commentnotify {
	text-decoration: underline;
	 margin: 5px;
}

/* 댓글 쪽지 */
.commentmessage {
	text-decoration: underline;
	 margin: 5px;
}

/* 댓글 삭제 */
.commentremove {
	text-decoration: underline;
	 margin: 5px;
}

/* 댓글 시간 */
.commenttime {
	display: flex;  
	color: #949494;
	font-size: 5px;
	margin-top:50px;
}

.commentInsert {
	display: flex;
	margin-left: 10px;
	align-items: center;
	padding: 20px;
}

.commentInsertprofile1 {
	width: 50px;
	height: 50px;
	border-radius: 100%;
	overflow: hidden;
}

.commentInsertprofile2 {
	width: 100%;
	height: 100%;
	object-fit: cover;
}

.comment1 {
	font-size: 20px;
	font-weight: bold;
	margin-top: 10px;
	margin-left: 20px;
}

.reply_comment {
	width: 1100px;	
	resize: none;
	font-size: 20px;
	margin-left: 40px;
}

.reply_ul {
	display: flex;
	justify-content: center;
	list-style: none;
	padding: 5px;
}

.commentbutton {
	margin-left:10px;
	border-top: none;	
	border-right: none;
	border-left: none;
	font-size:20px;
	width: 100px;
	height: 50px;
	background: white;
}

.commentbutton:hover {
	border-top: none;	
	border-right: none;
	border-left: none;
	color: rgb(135, 211, 124);
}

.commentbutton:active {
	border-top: none;	
	border-right: none;
	border-left: none;
	position: relative;
} 

.replaycomment {
	margin-left: 10%;
	margin-top: 7%;
}

#arrow {
	width: 30px;
	height: 30px;
}

#replaycommenttext {
	margin-left: 5%;
	margin-top: -3%;
	font-size: 20px;
}

.txt {
	background-color: black;
	width: 1000px;
	height: 50px;
	resize: none;
	font-size: 20px;
	margin-top: 30px;
}


/******페이지 업다운 버튼*********/
html {
	/* scroll-behavior: smooth; */
	
}

.fixed-pageUpDown {
	position: fixed;
	left: 85%;
	top: 80%;
	display: flex;
	flex-direction: column; /*수직 정렬*/	
}

#pageUp img {
	width: 50px;
}

#pageDown img {
	width: 50px;
}

#pageUp, #pageDown {
	cursor: pointer;
	transform: scale(1.05);
}
/***********************/

</style>
</head>
<body>

	<!-- header -->
	<%@ include file="/WEB-INF/views/common/menubar.jsp"%>

	<div class="container">
		<div class="subject">
			&nbsp;&nbsp;<span> 글번호 : ${board.bid}</span> <span>조회수 :
				${board.bcount}</span>&nbsp;&nbsp;|&nbsp;&nbsp;


			<c:choose>
				<c:when test="${ board.writetime  > 24 }">
					<span> ${ board.writeday } 일 전</span>
				</c:when>
				<c:when test="${ board.writemin  > 60 }">
					<span> ${ board.writetime } 시간 전</span>
				</c:when>
				<c:otherwise>
					<span>${ board.writemin } 분 전</span>
				</c:otherwise>
			</c:choose>

		</div>

		<!-- 신고하기 버튼 -->
		<div class="notify" onclick="replyLogin();">
			<img class="report_img" src="/kh_semi/resources/images/notify.png" id="show">
			<%@ include file="/WEB-INF/views/common/reportModal.jsp"%>
			<p>신고하기</p>
			<hr>
		</div>
		<p id="location_name">${board.cname}</p>
		<div class="title">
			<h4>
				<span class="title_span">&nbsp;</span>
			</h4>
			<p>${ board.btitle }</p>
		</div>

		<!-- 이미지 영역 -->
		<div class="imagearea">
			<!-- 메인 이미지 -->
			<c:forEach items="${ board.friendPhotoList }" var="photo">
				<c:choose>
					<c:when test="${ photo.fileLevel eq '0'}">
						<div class="mainarea">
							<img
								src="${ contextPath }${ photo.filePath }${ photo.changeName }">
						</div>
					</c:when>
				</c:choose>
			</c:forEach>
			<!-- 서브이미지 -->
			<div class="item_area1 subarea">
				<div class="item_imgBox">
					<div class="slider">

					    <input type="radio" name="slide" id="slide1" class="slide" checked>
						<input type="radio" name="slide" id="slide2" class="slide">
						<input type="radio" name="slide" id="slide3" class="slide">
						<input type="radio" name="slide" id="slide4" class="slide"> 

						<ul id="imgholder" class="imgs">

							<c:forEach items="${ board.friendPhotoList }" var="photo">
								<c:choose>
									<c:when test="${ photo.fileLevel eq '1'}">
										<li><img
											src="${contextPath }${ photo.filePath }${ photo.changeName }"></li>
									</c:when>
								</c:choose>
							</c:forEach>
						</ul>

						<div class="bullets">

							<c:forEach items="${ board.friendPhotoList }" var="photo">
								<c:choose>
									<c:when test="${ photo.fileLevel eq '1'}">
										<label for="slide1">&nbsp;</label>
									</c:when>
								</c:choose>
							</c:forEach>

						</div>

					</div>
				</div>
			</div>


		</div>
		<!-- 프로필 -->
		<div class="MemberInfo">
			<div class="MemberInfo2">

				<div id="box">
					<img id="profile" src="${ contextPath }${ board.profilePath }">&nbsp;
				</div>

			</div>
			<div class="MemberInfo3">
				<p id="name">${ board.nickname }</p>
				<p id="gender">
					${ board.age } |
					<c:choose>
						<c:when test="${board.gender eq 'F'}">여자</c:when>
						<c:when test="${board.gender eq 'M'}">남자</c:when>
						<c:otherwise>비공개</c:otherwise>
					</c:choose>

				</p>

			</div>
		</div>




		<!-- 연락처 -->
		<div class="messagebox">
			<div class="messagecontainer">
				<div class="message">
					<img src="${ contextPath }/resources/images/message.png">
				</div>
				<div class="messagetext">
					<img onclick="noteList('${ board.nickname }');" src="${ contextPath }/resources/images/messagetext.png">
				</div>
			</div>
		</div>
		<div class="phonebox">
			<div class="phonecontainer">
				<%@ include file="/WEB-INF/views/friend/friendPhonePopup.jsp"%>
				<div class="phone">
					<img src="${ contextPath }/resources/images/phone.png">
				</div>
				<div class="phonetext">
					<img src="${ contextPath }/resources/images/phonetext.png">
				</div>
			</div>
		</div>
		<!-- 	<div class="phonetext">연락처 보기</div> -->
		<div class="likebox">
			<div class="likecontainer">
			<c:choose>
			<c:when test="${ !empty loginUser and likeBid != board.bid }">
				<div class="like" onclick="location.href='${contextPath}/myscrap/insert?bid=${board.bid }'">
					<img src="${ contextPath }/resources/images/empty_heart.png" >
				</div>
			</c:when>
			<c:when test="${ !empty loginUser and likeBid == board.bid }">
				<div class="like" onclick="location.href='${contextPath}/myscrap/delete?bid=${board.bid }'">
					<img src="${ contextPath }/resources/images/full_heart.png">
				</div>
			</c:when>
			<c:when test="${ empty loginUser }">
				<div class="like" onclick="replyLogin();">
					<img src="${ contextPath }/resources/images/empty_heart.png" >
				</div>
			</c:when>
			</c:choose>
			</div>
		</div>	
		
		<hr id="hr1">
		<div class="content">
			<pre>${ board.bcontent}</pre>
			<%-- <pre class="content">${notice.ncontent }</pre> --%>
		</div>
<%-- ${ loginUser.userNo} |
${ board.bwriter} --%>
		<!-- 목록보기 버튼 -->
		<button type="button" class="listbutton"
			onclick="location.href='${ contextPath }/friend/main'">목록보기</button>
		<c:if test="${ loginUser.userNo == board.bwriter }">
			<button type="button" class="editbutton" onclick="updateBoard();">수정하기</button>
			<button type="button" class="removebutton" onclick="deleteBoard();">삭제하기</button>
		<</c:if>
		<hr id="hr2">


		<!-- 댓글 -->
		<div class="comment">
			<c:forEach items="${ board.friendAnswerList }" var="answer">

				<ul class="comment_ul">
					<li class="commentprofile1">
						<img class="commentprofile2" src="${ contextPath }${ answer.profilePath }">
					</li>
					<li class="commentwriter">${ answer.nickName }</li>
					<li class="commenttext">${ answer.acontent }</li>

					<div class="commentbtn">
						<c:if test="${ loginUser.userNo == answer.writer }">

							<li class="commentremove"
								onclick="deleteReply(${board.bid}, ${ answer.aid });">삭제</li>
						</c:if>
						<c:choose>
              <c:when test="${!empty loginUser }">
                 <li class="commentnotify" onclick="Rshow('${ answer.nickName}','${answer.acontent }',${ answer.aid },${ answer.writer });">신고</li>
              </c:when>
              <c:otherwise>
                 <li class="commentnotify" onclick="replyLogin();">신고</li>
              </c:otherwise>
						</c:choose>
					<li class="commenttime">
					<fmt:formatDate value="${ answer.createDate }" type="both" pattern="yyyy.MM.dd HH:mm:ss" /></li>

					</div>	



				</ul>
				
	</c:forEach>
		</div>
		<%@ include file="/WEB-INF/views/common/replyModal.jsp"%>
	<script>
    
    function Rshow(nick,content,aid,writer) {
  	   document.querySelector(".Rbackground").className = "Rbackground Rshow";
  	   
    	  $("#RnickName").text(nick);
          $("#Rcontent").text(content);
          let html = '<input type="hidden" value="'+aid+'" name="aid"><input type="hidden" value="'+writer+'" name="replyWriter">';
          $(".hiddenAid").html(html);	 	  
   		 }

      function Rclose() {
        document.querySelector(".Rbackground").className = "Rbackground";
      }

      document.querySelector(".Rcancel_btn").addEventListener("click", Rclose);
      document.querySelector(".Rreport_btn").addEventListener("click", Rclose);
      
    </script>
    	<c:if test="${ empty loginUser }">
			<script>
			function replyLogin(){
				if(confirm('로그인 후 이용 가능합니다. 로그인하시겠습니까?')){
					
				location.href='${contextPath}/login';
				}
			}
			</script>
		
		</c:if>
	

		<hr id="hr3">
		<!-- 댓글 입력 -->
		<p class="comment1">댓글</p>
		<div class="commentInsert">	
			<div class="commentInsertprofile1">
			<c:choose>
			<c:when test="${ !empty loginUser }">
				<img class="commentInsertprofile2" src="${ contextPath }${ loginUser.profilePath }">
			</c:when>
			<c:otherwise>
			<img class="commentInsertprofile2" src="${contextPath }/resources/images/mypage_profile.png">
			</c:otherwise>
			</c:choose>
			</div>
			<textarea class="reply_comment"></textarea>
			<button onclick="addReply(${ board.bid });" class="commentbutton">댓글</button>
		</div>

	</div>
	
	<!-- 페이지 업다운 버튼 -->
	<div class="fixed-pageUpDown">
		<a id="pageUp"><img
			src="${contextPath}/resources/images/pageUp.png"></a> <a
			id="pageDown"><img
			src="${contextPath}/resources/images/pageDown.png"></a>
	</div>
	
	
	<br>
	<!-- footer -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>

	<form name="boardForm" method="post">
		<input type="hidden" name="bid" value="${ board.bid }">
	</form>
	
	
		
	<script>
		function deleteBoard(){
				if(confirm("이 게시글을 삭제하시겠습니까?")){
					document.forms.boardForm.action = "${contextPath}/friend/delete";
					document.forms.boardForm.submit();
				}
			}
			
			function updateBoard(){
				if(confirm("이 게시글을 수정하시겠습니까?")){
					document.forms.boardForm.action = "${contextPath}/friend/updateView";
					document.forms.boardForm.submit();
				}
			}
	</script>
	
	
	
		<c:choose>
		<c:when test="${ !empty loginUser }">
			<script>
			/* 댓글 입력 */
			function addReply(bid) {	
				$.ajax({
					url : "${contextPath}/friend/insertReply",
					type : "post",
					dataType : "json",
					data : { bid : bid, acontent : $(".reply_comment").val() },
					success : function(data) {
						// console.log(data);
						var html = '';
						for(var i in data){
							html += '<ul class="comment_ul"></li><li class="commentprofile1"><img class="commentprofile2" src="${contextPath}'
								  + data[i].profilePath +'"><li class="commentwriter">' + data[i].nickName 
								  + '<li class="commenttext">' + data[i].acontent
								  + '</li><div class="commentbtn"><li class="commentremove" onclick="deleteReply(${board.bid}, ${ answer.aid });">삭제</li><li class="commentnotify" onclick=Rshow("'+data[i].nickName+'","'+data[i].acontent+'","'+data[i].aid+'","'+data[i].writer+'");>신고</li>'
								  + '<li class="commenttime">' + data[i].createDate + '</li></div></ul>';
							      
						}
						location.reload();
						/* 갱신 된 replyList를 테이블에 다시 적용 */
						$(".comment").html(html);
						/* 댓글 작성 부분 리셋 */
						$(".reply_comment").val("");
					},
					error : function(e) {
						console.log(e);
					}
				});
			}
			</script>
	</c:when>
		<c:otherwise>
			<script>
				function addReply(bid){
					alert('로그인 후 이용 가능합니다');
					location.href='${contextPath}/login';

				}
			</script>
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${ !empty loginUser.userNo }">
	<script>
	/* 댓글 삭제 */
		function deleteReply(bid, aid){
		
			let flag=confirm("댓글을 삭제하시겠습니까?");
				if(flag==true){
				
					$.ajax({
						url : "${contextPath}/friend/deleteReply",
						type : "post",
						dataType : "json",
						data : { bid : bid, aid : aid},
						success : function(data) {
							// console.log(data);
							var html = '';
		
							for(var i in data){
								html += '<ul class="comment_ul"></li><li class="commentprofile1"><img class="commentprofile2" src="${contextPath}'
									  + data[i].profilePath +'"><li class="commentwriter">' + data[i].nickName 
									  + '<li class="commenttext">' + data[i].acontent
									  + '</li><div class="commentbtn"><li class="commentremove" onclick="deleteReply(${board.bid}, ${ answer.aid });">삭제</li><li class="commentnotify" onclick=Rshow("'+data[i].nickName+'","'+data[i].acontent+'","'+data[i].aid+'","'+data[i].writer+'");>신고</li>'
									  + '<li class="commenttime">' + data[i].createDate + '</li></div></ul>';

							}
							location.reload();
							/* 갱신 된 replyList를 테이블에 다시 적용 */
							$(".comment").html(html);
							/* 댓글 작성 부분 리셋 */
							$(".reply_comment").val("");
							
						},
						error : function(e) {
							console.log(e);
						}
					});}
				}
	</script>
	</c:when>
		<c:otherwise>
			<script>
				function deleteReply(bid, aid){
					alert('로그인 후 이용 가능합니다');
					location.href='${contextPath}/login';
				}
			</script>
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${ !empty loginUser }">
			<script>
				function noteList(nickname){
					location.href='${contextPath}/note/write?receiveUser='+nickname;
				}
			</script>
		</c:when>
		<c:otherwise>
			<script>
				function noteList(){
					alert('로그인 후 이용 가능합니다');
					location.href="${contextPath }/login";	
				}
			</script>
		</c:otherwise>
		</c:choose>


	



	<script>
	/* 서브이미지 슬라이드 */
	if ( ${ board.friendPhotoList.size() } > 4) {	//서브 이미지 4개 일떄 
		 let radio = document.querySelectorAll(".slide");
			let i = 0;
				let interval = setInterval(function() {
					if (radio[0].checked)
						radio[1].checked = true;
					else if  (radio[1].checked)
						radio[2].checked = true;
					else if  (radio[2].checked)
						radio[3].checked = true;
					else if (radio[3].checked)
						radio[0].checked = true;
			}, 1500);
	 } else if ( ${ board.friendPhotoList.size() } > 3) {	//서브 이미지 3개 일떄 
		 let radio = document.querySelectorAll(".slide");
			let i = 0;
				let interval = setInterval(function() {
					if (radio[0].checked)
						radio[1].checked = true;
					else if (radio[1].checked)
						radio[2].checked = true;
					else if (radio[2].checked)
						radio[0].checked = true;
			}, 1500);
	 } else if ( ${ board.friendPhotoList.size() } > 2) { //서브 이미지 2개 일떄 
		 let radio = document.querySelectorAll(".slide");
			let i = 0;
				let interval = setInterval(function() {
					if (radio[0].checked)
						radio[1].checked = true;
					else if (radio[1].checked)
						radio[0].checked = true;
			}, 1500);
	 } 
	</script>

	
</body>
</html>