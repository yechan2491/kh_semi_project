<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="marketplace.model.vo.Board"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자취는 꿀템</title>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>

<style>

.third_menu {
color: rgb(135, 211, 124);
}

.outer {
	width: 1000px; /* 1000px */
	/*1280px*/
	margin: auto;
	margin-bottom: 3%;
	font-family: 'Nanum Gothic', sans-serif;
}

.item {
	display: flex;
	padding: 30px 0px;
}

.item_area1 {
	margin-right: 40px;
	flex-shrink: 0;
	width: 430px;/* 430px; */
	height: 450px;/* 450px; */
}

.item_imgBox { /* 이미지 div에 꽉채우는 방법  */
	border: 1px solid rgb(238, 238, 238);
	position: relative;
	width: 100%;
	height: 100%;
	border-radius:8%;
}

.slider {
	width: 100%;
	height: 100%;
	position: relative;
	margin: 0 auto;
}

.slider input[type=radio] {
	display: none;
}

ul.imgs {
	padding: 0;
	margin: 0;
}

ul.imgs img {
	position: absolute;
	top: 0;
	left: 0; /* 이미지 div에 꽉채우는 방법  */
	width: 100%;
	height: 100%;
	border-radius:8%;
}

ul.imgs li {
	position: absolute;
	opacity: 0;
	width: 100%;
	height: 100%;
	list-style: none;
	transition-delay: 0.3s;
}


.bullets {
	position: absolute;
	display: flex;
	justify-content: space-between;
	left: 50%;
	transform: translateX(-50%);
	bottom: 20px;
	z-index: 2;
}

.bullets label {
	display: inline-block;
	border-radius: 50%;
	background-color: rgba(0, 0, 0, 0.9);
	border: 1px solid rgb(136, 136, 136);
	width: 20px;
	height: 20px;
	cursor: pointer;
}

.slider input[type=radio]:nth-child(1):checked ~.bullets>label:nth-child(1)
{
	background-color: #fff;
}

.slider input[type=radio]:nth-child(2):checked ~.bullets>label:nth-child(2)
	{
	background-color: #fff;
}

.slider input[type=radio]:nth-child(3):checked ~.bullets>label:nth-child(3)
	{
	background-color: #fff;
}

.slider input[type=radio]:nth-child(4):checked ~.bullets>label:nth-child(4)
	{
	background-color: #fff;
}

.slider input[type=radio]:nth-child(1):checked ~ul.imgs>li:nth-child(1)
	{
	opacity: 1;
	transition: 0.3s;
	z-index: 1;
}

.slider input[type=radio]:nth-child(2):checked ~ul.imgs>li:nth-child(2)
	{
	opacity: 1;
	transition: 0.3s;
	z-index: 1;
}

.slider input[type=radio]:nth-child(3):checked ~ul.imgs>li:nth-child(3)
	{
	opacity: 1;
	transition: 0.3s;
	z-index: 1;
}

.slider input[type=radio]:nth-child(4):checked ~ul.imgs>li:nth-child(4)
	{
	opacity: 1;
	transition: 0.3s;
	z-index: 1;
}

/* 좌,우 슬라이드 버튼 */
.slider input[type=radio]:nth-child(1):checked ~.slide-control>div:nth-child(1)
	{
	display: block;
}

.slider input[type=radio]:nth-child(2):checked ~.slide-control>div:nth-child(2)
	{
	display: block;
}

.slider input[type=radio]:nth-child(3):checked ~.slide-control>div:nth-child(3)
	{
	display: block;
}

.slider input[type=radio]:nth-child(4):checked ~.slide-control>div:nth-child(4)
	{
	display: block;
}

.slide-control>div {
	display: none;
}

.right {
	position: absolute;
	top: 40%;
	background: url("${contextPath }/resources/images/right.png") center
		center/100% no-repeat;
	width: 100px;
	height: 100px;
	right: 0px;
	z-index: 2;
	cursor: pointer;
}

.left {
	position: absolute;
	top: 40%;
	background: url("${contextPath }/resources/images/left.png") center
		center/100% no-repeat;
	width: 100px;
	height: 100px;
	left: 0px;
	z-index: 2;
	cursor: pointer;
}
/************************************************************************/
.item_area2 {
	width: 530px;	/* 100%; */
	height: 100%;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
}

.item_area2_flexgrow {
	flex-grow: 1;
}

.title_price_box {
	padding-bottom: 30px;
	border-bottom: 1px solid rgb(238, 238, 238);
	width: 100%;
}

.item_title {
	margin-bottom: 25px;
	line-height: 1.4;
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
	width: 530px;
	font-size: 28px;;
	font-weight: 600;
	margin-top: 5px;
}

.item_price {
	font-size: 40px;
	font-weight: 500;
	letter-spacing: -3px;
}

.icon_box {
	display: flex;
	justify-content: space-between;
}

.icon {
	display: flex;
	width: 300px;
	align-items: center
}

.icon>div {
	display: flex;
	align-items: center;
	margin-right: 10px;
}

.detail_info_box {
	margin-top: 20px;
}

.reportBtn {
	display: flex;
	align-items: center;
	justify-content: space-around;
	background-color: white;
	cursor: pointer;
}

.reportBtn img {
	width: 20px;
	height: 30px;
}

.text_box {
	margin-top: 20px;
	font-size:18px;
}

.text_box ul {
	list-style-type: none;
}

.text_box>div {
	display: flex;
	align-items: center;
}

.text_box>div:nth-child(3) {
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
}

.text_box>div:nth-child(3)>span {
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
	width: 430px;
}
.text_box  li{
	width:130px;
	color : rgb(190, 190, 190);
}

.text_box>div>ul {
	width: 100px;
}

.item_button_box {
	display: flex;
	align-items: center;
	justify-content: space-between;
}

.item_button_box button {
	background: rgb(135, 211, 124);
	border: 0px;
	width: 48%;
	height: 50px;
	font-weight: bold;
	background: rgb(135, 211, 124);
	color: rgb(255, 255, 255);
	display: flex;
	align-items: center;
	font-size: 25px;
	justify-content: center;
	line-height: 1;
	padding: 10px 0;
	cursor: pointer;
}

/* 프로필 */
.profile {
	display: flex;
	border-bottom: 1px solid rgb(196, 196, 196);
	width: 100%;
	height: 100%;
	align-items: center;
	padding-bottom: 20px;
}

.profile_box {
	width: 60px;
	height: 60px;
	overflow: hidden;
	/* border: 0.1px solid black; */
	margin-left: 0px;
}

.profile_img {
	width: 100%;
	height: 100%;
	object-fit: cover;
	border-radius: 70%;
}

.profileInfo_box {
	margin-left: 20px;
}

.profileInfo_box>div:nth-child(1) {
	font-size: 25px;
	font-weight:bold;
}

.profileInfo_box>div:nth-child(2) {
	font-size: 15px;
	
}

.profileInfo_box>div:nth-child(3) {
	font-size: 15px;
}

/* 제품정보 */
.item_context {
	position: relative;
	min-height: 200px;
	border-bottom: 1px solid rgb(196, 196, 196);
	font-size:17px;
}

.item_context>pre {
	font-weight: normal;
}

/* 상품문의 */
.item-ask {
	width: 100%;
	margin: auto;
}

.itemTitle-ask {
	padding: 60px 0px 15px;
	font-size: 18px;
}

.itemComment-ask {
	border: 1px solid rgb(196, 196, 196);
	flex-direction: column;
}

.itemCommentBox-ask {
	display: flex;
	padding: 20px;
	border-bottom: 1px solid rgb(196, 196, 196);;
}

.textarea-ask {
	width: 100%;
	height: 80px;
	resize: none;
	border: none;
	font-size:16px;
}

.textarea-ask:focus {
	outline: none;
}

.itemCommentCheck-ask {
	display: flex;
	/* width: 100%; */
	height: 50px;
	align-items: center;
	justify-content: space-between;
	padding: 0px 10px 0 20px;
}

.inputBtn-ask button {
	display: flex;
	align-items: center;
	justify-content: space-around;
	background-color: white;
	cursor: pointer;
	border: 1px solid rgb(196, 196, 196);
	font-size:15px;
}

.inputBtn-ask button:hover {
	transform: scale(1.05);
}

.inputBtn-ask img {
	width: 30px;
	height: 30px;
}

.itemTitle-ask>div {
	font-size: 20px;
	font-weight: bold;
}

/* 상품문의 댓글 */
.itemCommentList-ask {
	margin-top: 10px;
}

.commentList2-ask {
	padding-top: 25px;
}

.commentList3-ask {
	display: flex;
	width: 100%;
	margin-bottom: 20px;
}

.commentProfileBox {
	display: block;
	margin-right: 15px;
}

.commentProfile {
	border-radius: 50%;
	width: 48px;
	height: 48px;
}

.commentBox {
	width: 100%;
	border-bottom: 1px solid rgb(238, 238, 238);
}

.commentIdBox {
	display: flex;
	width: 100%;
	justify-content: space-between;
	color: rgb(136, 136, 136);
	margin-bottom: 10px;
	align-items: center;
}

.commentId {
	margin: 0px;
	padding: 0px;
	border: 0px;
	font: inherit;
	vertical-align: baseline;
	font-size: 18px;
}

.commentDate {
	font-size: 15px;
	color: rgb(204, 204, 204);
}

.commentTextBox {
	margin-bottom: 20px;
	line-height: 1.5;
}

.commentTextBox>span {
	font-size: 15px;
}

.commentReportBox {
	display: flex;
	margin-bottom: 20px;
	justify-content: flex-end;
}

.commentDelete {
	display: flex;
	align-items: center;
	justify-content: space-around;
	background-color: white;
	cursor: pointer;
}

.Rshow {
	display: flex;
	align-items: center;
	justify-content: space-around;
	background-color: white;
	cursor: pointer;
}

/* 연관상품 */
.itemBox-ad {
	display: grid;
	grid-template-columns: repeat(4, 235px);
	column-gap: 20px;
	place-items: center;
	padding-bottom: 60px;
}

.item-ad {
	width: 235px;
	cursor: pointer;
}

.item-ad>img {
	width: 100%;
	height: 245px;
}

.title-ad {
	width: 100%;
	overflow-x: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	line-height: 1.4;
	font-size: 20px;
}

.categoryTitle-ad {
	display: flex;
	justify-content: space-between;
	align-items: flex-end;
	padding: 60px 0px 15px;
}

.categoryTitle-ad>div:nth-child(1) {
	font-size: 20px;
	font-weight: bold;
}

.categoryTitle-ad>div:nth-child(2) {
	border-bottom: 1px solid black;
	font-size: 15px;
	font-weight: 500;
	vertical-align: middle;
	/* margin-bottom : 5px; */
	height: 20px;
	line-height: 18px;
	cursor: pointer;
}

/* 페이지 이동버튼 수정하기 목록이동 */
.pageButton {
	width: 100%;
	display: flex;
	justify-content: flex-end;
	padding-bottom: 60px;
}

.pageButton>button {
	height: 3rem;
	width: 200px;
	text-align: center;
	border: 1px solid rgb(195, 194, 204);
	margin-left: 1rem;
	border-radius: 2px;
	background-color: transparent;
	cursor: pointer;
	outline: none;
	font-weight: 400;
	font-size: 18px;
}

.pageButton>button:hover {
	background-color: rgb(135, 211, 124);
	border: none;
	color: white;
}

.moreAndReduce{
	color: rgb(136, 136, 136);
	cursor: pointer;
	border: 1px solid rgb(200, 200, 200	);
	margin-bottom:50px;
}

</style>
</head>
<body>

	
	<script>
		let reduceState = true; // 전역변수로 사용해서 ajax 통신할때 줄이기 상태인지 더보기 상태인지 판별
	</script>

	<%
		String[] address;
	Board board = (Board) request.getAttribute("board");
	if (board.getLocation() != null) {
		address = board.getLocation().split("[|]");
	} else {
		address = new String[]{"", "", ""};
	}
	%>

	<!-- 메뉴바 -->
	<jsp:include page="/WEB-INF/views/common/menubar.jsp">
		<jsp:param name="pName" value="test" />
	</jsp:include>





	<div class="outer">
		<div class="wrap">
			<div class="item">
				<div class="item_area1">

					<div class="item_imgBox"></div>
				</div>

				<div class="item_area2_flexgrow">
					<div class="item_area2">
						<div class="item_info_box">
							<div class="title_price_box">
								<div class="item_title">${ board.btitle }</div>
								<div class="item_price">
									<fmt:formatNumber value="${ board.price }" groupingUsed="true" />
									<span style="font-size: 30px">원</span>
								</div>
							</div>




							<div class="detail_info_box">
								<div class="icon_box">
									<div class="icon">
										<div>
											<img src="${contextPath }/resources/images/black_heart.png"
												width="25" height="25"> &nbsp; <span style="font-size:17px; ">${ board.likeCount }</span>
										</div>
										<div>
											<img src="${contextPath }/resources/images/eye.png"
												width="25" height="25"> &nbsp; <span style="font-size:17px">${ board.bcount }</span>
										</div>

										<div>
											<img src="${contextPath }/resources/images/time.png"
												width="25" height="25"> &nbsp; <span id="date" style="font-size:17px">${board.timeView }</span>
										</div>


									</div>
									<%-- <button class="reportBtn">
										<img src="${contextPath }/resources/images/siren.png">
										<span>신고하기</span>
									</button> --%>
									<div onclick="replyLogin();">
									 <img class="report_img" src="/kh_semi/resources/images/notify.png" id="show">
									<%@ include file="/WEB-INF/views/common/reportModal.jsp"%>
									</div>


								</div>


								<div class="text_box">
									<div>
										<ul>
											<li>&#183;&nbsp;상품상태</li>
										</ul>
										<span>&nbsp;&nbsp;&nbsp;&nbsp;${ board.condition }</span>
									</div>
									<div>
										<ul>
											<li>&#183;&nbsp;카테고리</li>
										</ul>
										<span>&nbsp;&nbsp;&nbsp;&nbsp;${ board.cname }</span>
									</div>
									<div>
										<ul>
											<li>&#183;&nbsp;거래지역</li>
										</ul>
										<span>&nbsp;&nbsp;&nbsp;&nbsp;<%=address[1]%>&nbsp;<%=address[2]%></span>
									</div>

								</div>


							</div>
						</div>


						<div class="item_button_box">
						<c:choose>
						<c:when test="${ likeBid != board.bid }">
							<button onclick="addMyListBoard(${board.mid});" id="likeBtn">
								<img src="${contextPath }/resources/images/empty_heart.png" width="30"
									height="30">&nbsp;찜
							</button>
						</c:when>
						<c:otherwise>
							<button onclick="deleteMyListBoard(${board.mid});" id="likeBtn">
								<img src="${contextPath }/resources/images/red_heart.png" width="30"
									height="30">&nbsp;찜
							</button>
						</c:otherwise>
						</c:choose>
							<button onclick="noteList2('${ board.nickname }');">쪽지</button>
						</div>
						
						
					</div>

				</div>
			</div>
			<c:choose>
				<c:when test="${ !empty loginUser }">
				<script>
					function addMyListBoard(mid){
						location.href="${contextPath}/myproduct/insert?mid="+mid;
					}
					
					function deleteMyListBoard(mid){
						location.href="${contextPath}/myproduct/delete?mid="+mid;
					}
				</script>
				</c:when>
				<c:otherwise>
				<script>
					function addMyListBoard(mid){
						alert('로그인 후 이용 가능합니다');
						location.href="${contextPath }/login";	
					}
					
					function deleteMyListBoard(mid){
						alert('로그인 후 이용 가능합니다');
						location.href="${contextPath }/login";	
					}
					
				</script>
				</c:otherwise>
			</c:choose>
			

			<!-- 프로필 -->
			<div class="profile">
				<div class="profile_box">
					<img class="profile_img" src="${contextPath }${board.profilePath}">
					
					
				</div>

				<div class="profileInfo_box">
					<div>${ board.nickname }</div>
					<div><%=address[1]%>&nbsp;<%=address[2]%></div>
					<div id="date2">
						<fmt:formatDate value="${board.modifyDate}" type="both"
							pattern="yyyy.MM.dd hh:mm:ss" />
						&nbsp;(${board.timeView })
					</div>
				</div>

			</div>

			<div class="item_context">
				<p>${ board.bcontent }</p>



			</div>

			<!-- 상품문의 -->
			<div class="item-ask">
				<div class="itemTitle-ask">
					<div>
						상품문의
						<!-- <span>3</span> -->
					</div>
				</div>

				<div class="itemComment-ask">
					<div class="itemCommentBox-ask">
						<textarea placeholder="상품문의 입력" class="textarea-ask"></textarea>
					</div>
					<div class="itemCommentCheck-ask">
						<div class="inputCheck-ask">
							<span id="counter">0</span>/<span>200</span> &nbsp; &nbsp; &nbsp;
							<span id="warning"></span>
						</div>
						<div class="inputBtn-ask">
							<button onclick="addReply(${board.bid });">
								<img src="${contextPath }/resources/images/pencil.png"> <span
									style="width: 40px">등록</span>
							</button>
						</div>
					</div>
				</div>

				<script>
					$(document).ready(function() {
						$("textarea").keyup(function() {
							// 현재 요소의 값의 길이 알기
							let inputLenth = $(this).val().length;
							$("#counter").text(inputLenth);

							let remain = 200 - inputLenth;
							if (remain >= 0) {
								$("#counter").css("color", "black");
								$("#warning").text("")
							} else {
								$("#counter").css("color", "red");
								$("#warning").text("*200자 이내로 입력하세요.")
								$("#warning").css("color", "red");
							}
						});
					});
				</script>



				<div class="itemCommentList-ask">
					<div class="commentList2-ask">


						<c:forEach items="${ board.replyList }" var="reply" begin="0" end="3" >
						

							<div class="commentList3-ask">
								<div class="commentProfileBox">
									<img class="commentProfile"
										src="${contextPath }${reply.profilePath}">
								</div>
								<div class="commentBox">
									<div class="commentIdBox">
										<div class="commentId">${reply.nickName}</div>
										<div class="commentDate">${reply.timeView }</div>
									</div>
									<div class="commentTextBox">
										<span class="dasd">${reply.acontent}</span>
									</div>
									<div class="commentReportBox">
										<c:if test="${ loginUser.userNo == reply.writer || loginUser.manager== 'Y' }">
											<div class="commentDelete"
												onclick="deleteReply(${board.bid}, ${ reply.aid });">
												<img style="width: 30px; height: 30px;"
													src="${contextPath }/resources/images/trash.png"> <span>삭제하기</span>
											</div>
										</c:if>
										&nbsp; &nbsp; &nbsp;
										<div onclick="replyLogin();">
										<div class="Rshow" onclick="Rshow('${ reply.nickName}','${reply.acontent }',${ reply.aid },${ reply.writer });">
											<img style="width: 25px; height: 25px;" src="${contextPath }/resources/images/siren2.png"><span>신고하기</span>										
										</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>

					

						<c:if test="${board.replyList.size()>4 }">
						<div class="moreAndReduce" onclick="moreReply(${board.bid})" style="text-align: center;"><h2>더보기 &#x25BC;</h2></div>
						</c:if>
						<c:choose>
							<c:when test="${ !empty loginUser }">
								<script>
									function moreReply(bid){
										reduceState=false;
										$.ajax({
											url : "${contextPath}/marketplace/moreReply",
											type : "post",
											dataType : "json",
											data : { bid : bid},
											success : function(data){
												
												var html='';
												
												for(var i in data){
													let number1=data[i].writer;
													let number2=${loginUser.userNo};
													let flag= number1== number2;
													
													if(flag){
														html+=
															   '<div class="commentList3-ask">'
															   +'<div class="commentProfileBox">'
																+'<img class="commentProfile" src="${contextPath }'+data[i].profilePath+'">'
																+'</div>'
																+'<div class="commentBox">'
															    +'<div class="commentIdBox">'
																	+'<div class="commentId">'+data[i].nickName+'</div>'
																	+'<div class="commentDate"  >'+data[i].timeMessage+'</div>'
																	
																+'</div>'
																+'<div class="commentTextBox">'
																	+'<span class="dasd">'+data[i].acontent+'</span>'
																+'</div>'
																+'<div class="commentReportBox">'
																	+'<div class="commentDelete" onclick="deleteReply(${board.bid},'+data[i].aid+');">'
																		+'<img style="width: 30px; height: 30px;" src="${contextPath }/resources/images/trash.png"> <span>삭제하기</span>'
																	+'</div>'
																	+'&nbsp; &nbsp; &nbsp;'
																	+'<div class="Rshow" onclick=Rshow("'+data[i].nickName+'","'+data[i].acontent+'","'+data[i].aid+'","'+data[i].writer+'")>'
																		+'<img style="width: 25px; height: 25px;" src="${contextPath }/resources/images/siren2.png"> <span>신고하기</span>'
																	+'</div>'
																+'</div>'
															 +'</div>'
															+'</div>';
															
													} else {
														html+=
															   '<div class="commentList3-ask">'
															   +'<div class="commentProfileBox">'
																+'<img class="commentProfile" src="${contextPath }'+data[i].profilePath+'">'
																+'</div>'
																+'<div class="commentBox">'
															    +'<div class="commentIdBox">'
																	+'<div class="commentId">'+data[i].nickName+'</div>'
																	+'<div class="commentDate"  >'+data[i].timeMessage+'</div>'
																	
																+'</div>'
																+'<div class="commentTextBox">'
																	+'<span class="dasd">'+data[i].acontent+'</span>'
																+'</div>'
																+'<div class="commentReportBox">'
																	
																	+'&nbsp; &nbsp; &nbsp;'
																	+'<div class="Rshow" onclick=Rshow("'+data[i].nickName+'","'+data[i].acontent+'","'+data[i].aid+'","'+data[i].writer+'")>'
																		+'<img style="width: 25px; height: 25px;" src="${contextPath }/resources/images/siren2.png"> <span>신고하기</span>'
																	+'</div>'
																+'</div>'
															 +'</div>'
															+'</div>';
													}
													
													
												
													
											   
												}
												html+='<div class="moreAndReduce" onclick="reduceReply(${board.bid})" style="text-align: center;"><h2>줄이기 &#x25B2;</h2></div>'
												/* 갱신 된 replyList를 테이블에 다시 적용 */
												$(".commentList2-ask").html(html);
												/* 댓글 작성 부분 리셋 */
												$(".textarea-ask").val("");
												
												
											},
											error : function(e){
												console.log(e);
											}
										});
									}
									
									function reduceReply(bid){
										reduceState=true;
										$.ajax({
											url : "${contextPath}/marketplace/reduceReply",
											type : "post",
											dataType : "json",
											data : { bid : bid},
											success : function(data){
												
												var html='';
												
												for(var i in data){
													let number1=data[i].writer;
													let number2=${loginUser.userNo};
													let flag= number1== number2;
													
													if(flag){
														html+=
															   '<div class="commentList3-ask">'
															   +'<div class="commentProfileBox">'
																+'<img class="commentProfile" src="${contextPath }'+data[i].profilePath+'">'
																+'</div>'
																+'<div class="commentBox">'
															    +'<div class="commentIdBox">'
																	+'<div class="commentId">'+data[i].nickName+'</div>'
																	+'<div class="commentDate"  >'+data[i].timeMessage+'</div>'
																	
																+'</div>'
																+'<div class="commentTextBox">'
																	+'<span class="dasd">'+data[i].acontent+'</span>'
																+'</div>'
																+'<div class="commentReportBox">'
																	+'<div class="commentDelete" onclick="deleteReply(${board.bid},'+data[i].aid+');">'
																		+'<img style="width: 30px; height: 30px;" src="${contextPath }/resources/images/trash.png"> <span>삭제하기</span>'
																	+'</div>'
																	+'&nbsp; &nbsp; &nbsp;'
																	+'<div class="Rshow" onclick=Rshow("'+data[i].nickName+'","'+data[i].acontent+'","'+data[i].aid+'","'+data[i].writer+'")>'
																		+'<img style="width: 25px; height: 25px;" src="${contextPath }/resources/images/siren2.png"> <span>신고하기</span>'
																	+'</div>'
																+'</div>'
															 +'</div>'
															+'</div>';			
													} else {
														html+=
															   '<div class="commentList3-ask">'
															   +'<div class="commentProfileBox">'
																+'<img class="commentProfile" src="${contextPath }'+data[i].profilePath+'">'
																+'</div>'
																+'<div class="commentBox">'
															    +'<div class="commentIdBox">'
																	+'<div class="commentId">'+data[i].nickName+'</div>'
																	+'<div class="commentDate"  >'+data[i].timeMessage+'</div>'
																	
																+'</div>'
																+'<div class="commentTextBox">'
																	+'<span class="dasd">'+data[i].acontent+'</span>'
																+'</div>'
																+'<div class="commentReportBox">'
																	
																	+'&nbsp; &nbsp; &nbsp;'
																	+'<div class="Rshow" onclick=Rshow("'+data[i].nickName+'","'+data[i].acontent+'","'+data[i].aid+'","'+data[i].writer+'")>'
																		+'<img style="width: 25px; height: 25px;" src="${contextPath }/resources/images/siren2.png"> <span>신고하기</span>'
																	+'</div>'
																+'</div>'
															 +'</div>'
															+'</div>';
													}
				
												}
												html+='<div class="moreAndReduce" onclick="moreReply(${board.bid})" style="text-align: center;"><h2>더보기 &#x25BC;</h2></div>'
												/* 갱신 된 replyList를 테이블에 다시 적용 */
												$(".commentList2-ask").html(html);
												/* 댓글 작성 부분 리셋 */
												$(".textarea-ask").val("");
											},
											error : function(e){
												console.log(e);
											}
										});
									}
								</script>
							</c:when>
							<c:otherwise>
								<script>
									function moreReply(bid){
										alert('로그인 후 이용 가능합니다');
										location.href="${contextPath }/login";
									}
									
									function reduceReply(bid){
										alert('로그인 후 이용 가능합니다');
										location.href="${contextPath }/login";
									}
								</script>
							</c:otherwise>
						</c:choose>
						<script>
							
						</script>
						
					</div>
				</div>

			</div>
			
		<c:if test="${ empty loginUser }">
			<script>
			function replyLogin(){
				if(confirm('로그인 후 이용 가능합니다. 로그인하시겠습니까?')){
					
				location.href='${contextPath}/login';
				}
			}
			</script>
		</c:if>
	

			<!-- 연관상품 -->
			<div class="category-ad">
				<!-- 연관상품이 없을땐 숨기기 위함 -->
				<c:if test="${ !empty board.relationList }">
					<div class="categoryTitle-ad">
						<div>연관상품</div>
						<div onclick="relationView(${board.relationList.get(0).cid})">더보기</div>
					</div>
					<div class="itemBox-ad">
						<c:forEach items="${ board.relationList }" var="relation">
							<div class="item-ad" onclick="detailView(${relation.bid})">
								<img
									src="${contextPath }${relation.filePath}${relation.changeName}">
								<div class="title-ad">${relation.btitle}</div>
							</div>
						</c:forEach>
					</div>
				</c:if>
			</div>
			<script>
				function detailView(bid){
					location.href='${contextPath}/marketplace/detail?bid='+bid;
				}
				function relationView(cid){
					let category;
					let sort='recent';
					switch(cid){
					case 21: category='clothes'; break;
					case 22: category='shoes'; break;
					case 23: category='bag'; break;
					case 24: category='beauty'; break;
					case 25: category='kitchen'; break;
					case 26: category='digital'; break;
					case 27: category='vehicle'; break;
					case 28: category='etc'; break;
					}
				
					location.href='${contextPath}/marketplace/list?category='+category+'&sort='+sort;
				}
			</script>


			<!-- 페이지 이동버튼 수정하기 목록이동-->
			<div class="pageButton">
				<button class="listBtn"
					onclick="location.href='${contextPath}/marketplace/list'">목록으로</button>
				<c:if test="${ loginUser.userNo == board.bwriter || loginUser.manager== 'Y' }">
					<button class="updateBtn" onclick="updateBoardView();">수정하기</button>
					<button class="deleteBtn" onclick="deleteBoard();">삭제하기</button>
				</c:if>
			</div>

		</div>


	</div>

	<c:choose>
		<c:when test="${ !empty loginUser.userNo }">
			<script>
				function deleteReply(bid, aid){
					
					let flag=confirm("댓글을 삭제하시겠습니까?");
					if(flag==true){
						$.ajax({
							url : "${contextPath}/marketplace/deleteReply",
							type : "post",
							dataType : "json",
							data : { bid : bid, aid : aid},
							success : function(data){
								
								var html='';
								var reduceCount=0;
								for(var i in data){
									let number1=data[i].writer;
									let number2=${loginUser.userNo};
									let flag= number1== number2;
									
									if(reduceState==true && reduceCount==4) break;
									reduceCount++;
									
									if(flag){
										html+=
											   '<div class="commentList3-ask">'
											   +'<div class="commentProfileBox">'
												+'<img class="commentProfile" src="${contextPath }'+data[i].profilePath+'">'
												+'</div>'
												+'<div class="commentBox">'
											    +'<div class="commentIdBox">'
													+'<div class="commentId">'+data[i].nickName+'</div>'
													+'<div class="commentDate"  >'+data[i].timeMessage+'</div>'
													
												+'</div>'
												+'<div class="commentTextBox">'
													+'<span class="dasd">'+data[i].acontent+'</span>'
												+'</div>'
												+'<div class="commentReportBox">'
													+'<div class="commentDelete" onclick="deleteReply(${board.bid},'+data[i].aid+');">'
														+'<img style="width: 30px; height: 30px;" src="${contextPath }/resources/images/trash.png"> <span>삭제하기</span>'
													+'</div>'
													+'&nbsp; &nbsp; &nbsp;'
													+'<div class="Rshow" onclick=Rshow("'+data[i].nickName+'","'+data[i].acontent+'","'+data[i].aid+'","'+data[i].writer+'")>'
														+'<img style="width: 25px; height: 25px;" src="${contextPath }/resources/images/siren2.png"> <span>신고하기</span>'
													+'</div>'
												+'</div>'
											 +'</div>'
											+'</div>';			
									} else {
										html+=
											   '<div class="commentList3-ask">'
											   +'<div class="commentProfileBox">'
												+'<img class="commentProfile" src="${contextPath }'+data[i].profilePath+'">'
												+'</div>'
												+'<div class="commentBox">'
											    +'<div class="commentIdBox">'
													+'<div class="commentId">'+data[i].nickName+'</div>'
													+'<div class="commentDate"  >'+data[i].timeMessage+'</div>'
													
												+'</div>'
												+'<div class="commentTextBox">'
													+'<span class="dasd">'+data[i].acontent+'</span>'
												+'</div>'
												+'<div class="commentReportBox">'
													
													+'&nbsp; &nbsp; &nbsp;'
													+'<div class="Rshow" onclick=Rshow("'+data[i].nickName+'","'+data[i].acontent+'","'+data[i].aid+'","'+data[i].writer+'")>'
														+'<img style="width: 25px; height: 25px;" src="${contextPath }/resources/images/siren2.png"> <span>신고하기</span>'
													+'</div>'
												+'</div>'
											 +'</div>'
											+'</div>';
									}
									
								
									
							   
								}
								var replyLen=data.length;
								if(replyLen>4){
									if(reduceState==true){
										html+='<div class="moreAndReduce" onclick="moreReply(${board.bid})" style="text-align: center;"><h2>더보기 &#x25BC;</h2></div>';
									} else{
										html+='<div class="moreAndReduce" onclick="reduceReply(${board.bid})" style="text-align: center;"><h2>줄이기 &#x25B2;</h2></div>';
									}
								} 
								
								/* 갱신 된 replyList를 테이블에 다시 적용 */
								$(".commentList2-ask").html(html);
								/* 댓글 작성 부분 리셋 */
								$(".textarea-ask").val("");
							},
							error : function(e){
								console.log(e);
							}
						});
					} 
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
				function noteList2(nickname){
					location.href='${contextPath}/note/write?receiveUser='+nickname;
				}
			</script>
		</c:when>
		<c:otherwise>
			<script>
				function noteList2(){
					alert('로그인 후 이용 가능합니다');
					location.href="${contextPath }/login";	
				}
			</script>
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${ !empty loginUser }">
			<script>
				/* 댓글 달기 버튼 클릭시 Reply 테이블에 insert 기능 수행 후
				비동기적으로 새로 갱신 된 댓글 목록을 Reply 테이블에서 select해서 화면에 적용시키는 기능 */
				function addReply(bid){
					
					let len = $(".textarea-ask").val().length;
					
					if(len>0){
						$.ajax({
							url : "${contextPath}/marketplace/insertReply",
							type : "post",
							dataType : "json",
							data : { bid : bid, rcontent : $(".textarea-ask").val()},
							success : function(data){
								
								var html='';
								var reduceCount=0;
								
								for(var i in data){
									let number1=data[i].writer;
									let number2=${loginUser.userNo};
									let flag= number1== number2;
									
									if(reduceState==true && reduceCount==4) break;
									reduceCount++;
									
									if(flag){
										let nick=data[i].nickName;
										let content=data[i].acontent;
										console.log(nick+content);
							   html+=
								   '<div class="commentList3-ask">'
								   +'<div class="commentProfileBox">'
									+'<img class="commentProfile" src="${contextPath }'+data[i].profilePath+'">'
									+'</div>'
									+'<div class="commentBox">'
								    +'<div class="commentIdBox">'
										+'<div class="commentId">'+data[i].nickName+'</div>'
										+'<div class="commentDate" >'+data[i].timeMessage+'</div>'
									+'</div>'
									+'<div class="commentTextBox">'
										+'<span class="dasd">'+data[i].acontent+'</span>'
									+'</div>'
									+'<div class="commentReportBox">'
										
									+'<div class="commentDelete" onclick="deleteReply(${board.bid},'+data[i].aid+');">'
										+'<img style="width: 30px; height: 30px;" src="${contextPath }/resources/images/trash.png"> <span>삭제하기</span>'
									+'</div>'
										
										+'&nbsp; &nbsp; &nbsp;'
										+'<div class="Rshow" onclick=Rshow("'+data[i].nickName+'","'+data[i].acontent+'","'+data[i].aid+'","'+data[i].writer+'")>'
											+'<img style="width: 25px; height: 25px;" src="${contextPath }/resources/images/siren2.png"> <span>신고하기</span>'
										+'</div>'
									+'</div>'
								 +'</div>'
								+'</div>';
									} else {
										html+=
											   '<div class="commentList3-ask">'
											   +'<div class="commentProfileBox">'
												+'<img class="commentProfile" src="${contextPath }'+data[i].profilePath+'">'
												+'</div>'
												+'<div class="commentBox">'
											    +'<div class="commentIdBox">'
													+'<div class="commentId">'+data[i].nickName+'</div>'
													+'<div class="commentDate" >'+data[i].timeMessage+'</div>'
												+'</div>'
												+'<div class="commentTextBox">'
													+'<span class="dasd">'+data[i].acontent+'</span>'
												+'</div>'
												+'<div class="commentReportBox">'
													
												
													
													+'&nbsp; &nbsp; &nbsp;'
													+'<div class="Rshow" onclick=Rshow("'+data[i].nickName+'","'+data[i].acontent+'","'+data[i].aid+'","'+data[i].writer+'")>'
														+'<img style="width: 25px; height: 25px;" src="${contextPath }/resources/images/siren2.png"> <span>신고하기</span>'
													+'</div>'
												+'</div>'
											 +'</div>'
											+'</div>';
									}
									
								}
								var replyLen=data.length;
								if(replyLen>4){
									if(reduceState==true){
										html+='<div class="moreAndReduce" onclick="moreReply(${board.bid})" style="text-align: center;"><h2>더보기  &#x25BC;</h2></div>';
									} else{
										html+='<div class="moreAndReduce" onclick="reduceReply(${board.bid})" style="text-align: center;"><h2>줄이기 &#x25B2;</h2></div>';
									}
								} 
								
								
								
								
								
								/* 갱신 된 replyList를 테이블에 다시 적용 */
								$(".commentList2-ask").html(html);
								/* 댓글 작성 부분 리셋 */
								$(".textarea-ask").val("");
							},
							error : function(e){
								console.log(e);
							}
						});
						
						$("#counter").text(0);
					} else {
						alert('입력하신 댓글이 없습니다.');
					}
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







	<!-- 푸터 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp">
		<jsp:param name="pName" value="test" />
	</jsp:include>

	<form name="boardForm" method="post">
		<input type="hidden" name="bid" value="${ board.bid }">
	</form>
	<script>
		function updateBoardView(){
			document.forms.boardForm.action="${contextPath}/marketplace/updateView";
			document.forms.boardForm.submit();
		}
		
		function deleteBoard(){
			if(confirm("이 게시글을 삭제하시겠습니까?")){
				document.forms.boardForm.action ="${contextPath}/marketplace/delete";
				document.forms.boardForm.submit();
			}
		}
	</script>


	<script>
		
		let box= document.querySelector(".item_imgBox");
		let count = ${imageCount};
		
		if(count==3){
			box.innerHTML='<div class="slider">'
			+'<input type="radio" name="slide" id="slide1" checked>'
			+'<input type="radio" name="slide" id="slide2">' 
			+'<input type="radio" name="slide" id="slide3">'
			+'<ul id="imgholder" class="imgs">'
			
			+'</ul>'
			+'<div class="bullets" style="width:100px;">'
			+'	<label for="slide1">&nbsp;</label>'
			+'	<label for="slide2">&nbsp;</label>'
			+'	<label for="slide3">&nbsp;</label>' 
			+'</div>'
			+'<div class="slide-control">'
			+'	<div>'
			+'		<label for="slide3" class="left"> </label>'
			+'		<label for="slide2" class="right"> </label>'
			+'	</div>'
			+'	<div>'
			+'		<label for="slide1" class="left"> </label>' 
			+'		<label for="slide3" class="right"> </label>'
			+'	</div>'
			+'	<div>'
			+'		<label for="slide2" class="left"> </label>'
			+'		<label for="slide1" class="right"> </label>'
			+'	</div>'
		
			+'</div>'
			+'</div>';
			
			
		} else if(count==2){
			box.innerHTML='<div class="slider">'
				+'<input type="radio" name="slide" id="slide1" checked>'
				+'<input type="radio" name="slide" id="slide2">' 
				+'<ul id="imgholder" class="imgs">'
				
				+'</ul>'
				+'<div class="bullets" style="width:60px;">'
				+'	<label for="slide1">&nbsp;</label>'
				+'	<label for="slide2">&nbsp;</label>'
				+'</div>'
				+'<div class="slide-control">'
				+'	<div>'
				+'		<label for="slide2" class="left"> </label>'
				+'		<label for="slide2" class="right"> </label>'
				+'	</div>'
				+'	<div>'
				+'		<label for="slide1" class="left"> </label>' 
				+'		<label for="slide1" class="right"> </label>'
				+'	</div>'
				+'</div>'
				+'</div>';
		} else {
			box.innerHTML='<div class="slider">'
				+'<input type="radio" name="slide" id="slide1" checked>'
				+'<ul id="imgholder" class="imgs">'

				+'</ul>'
				+'</div>';
		}

	</script>

	<script>
		let imgholder= document.getElementById("imgholder");
	</script>
	<c:forEach items="${ board.imageList }" var="photo">
		<script>
		imgholder.innerHTML+='<li><img src="${ contextPath }${ photo.filePath}${ photo.changeName}"></li>';
	</script>
	</c:forEach>
		<%@ include file="/WEB-INF/views/common/replyModal.jsp"%>

	  <script>
    let Rshows = document.querySelectorAll(".Rshow");
    
      function Rclose() {
        document.querySelector(".Rbackground").className = "Rbackground";
      }
      
      function Rshow(nick,content,aid,writer) {
    	   document.querySelector(".Rbackground").className = "Rbackground Rshow";
    	   
      	  $("#RnickName").text(nick);
           $("#Rcontent").text(content);
          let html = '<input type="hidden" value="'+aid+'" name="aid"><input type="hidden" value="'+writer+'" name="replyWriter">';
           $(".hiddenAid").html(html);	 	  
      }
      
        
      document.querySelector(".Rcancel_btn").addEventListener("click", Rclose);
      document.querySelector(".Rreport_btn").addEventListener("click", Rclose);
      
    </script>


</body>
</html>







