<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쪽지</title>

<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">
</head>

<style>

.five_menu {
color: rgb(135, 211, 124);
}
.outer {
	width: 900px;
	/*1280px*/
	margin: auto;
	margin-bottom: 3%;
	font-family: 'Nanum Gothic', sans-serif;

}

.wrap h2 {
	height: 100px;
	font-size: 26px;
	display: flex;
	align-items: center;
	/* border-bottom: 2px solid rgb(30, 29, 41); */
}

.flexBox {
	display: flex;
	padding: 2rem 0px;
	border-bottom: 1px solid rgb(220, 219, 228);
	align-items: center;
	font-weight: 300;
}

.titleBox {
	width: 25%;
	font-size: 1.2rem;
	
}

.inputBox {
	width: 75%;
}

input :not([type="file"] ) :not([type="radio"] ) {
	height: 2rem;
	padding: 0px 1rem;
}



.inputBox>div {
	width: 100%;
	display: flex;
}

.inputBox>div>input {
	width: 100%;
	height: 2rem;
	padding: 0px 1rem;
}




.inputBox>#idSearchBox>input {
	width: 70%
}

.inputBox>#idSearchBox>button {
	flex-grow: 1;
	margin-left: 10px;
}

.descriptionInputBox {
	display: flex;
	flex-grow: 1;
	flex-direction: column;
}

.descriptionInputBox>textarea {
	resize: none;
	padding: 1rem;
	line-height: 1.35;
	height: 200px;
}

.inputBox button {
	font-size: 18px;
	height: 2.2rem;
	width: 200px;
	text-align: center;
	border: 1px solid rgb(195, 194, 204);
	margin-left: 1rem;
	border-radius: 2px;
	background-color: transparent;
	cursor: pointer;
	outline: none;
}

.inputBox button:hover {
	background-color: rgb(135, 211, 124);
	border: none;
	color:white;
}

.flexBoxEnd {
	display: flex;
	padding: 2rem 0px;
	align-items: center;
}

.inputBoxEnd {
	display: flex;
	flex-grow: 1;
}

.inputBoxEnd #sendButton {
	font-size: 18px;
	height: 2.2rem;
	text-align: center;
	border: 1px solid rgb(195, 194, 204);
	border-radius: 2px;
	background-color: transparent;
	cursor: pointer;
	outline: none;
	width: 100%;
}

.inputBoxEnd #sendButton:hover {
	background-color: rgb(135, 211, 124);
	border: none;
	color:white;
}

.moveBox {
	display: flex;
	justify-content: end;
}

.moveBox>span {
	text-decoration: underline;
	font-weight: bold;
	color: rgb(135, 211, 124);
	font-size: 15px;
}

.moveBox>span:hover {
	cursor: pointer;
}

input:focus, select:focus, textarea:focus {
	outline: none;
}

.roundBox{
	border:1px solid rgb(135, 211, 124);
	padding:25px;
	border-radius: 20px;
}

</style>

<body>

	<!-- 메뉴바 -->
	<jsp:include page="/WEB-INF/views/common/menubar.jsp">
		<jsp:param name="pName" value="test" />
	</jsp:include>


	<div class="outer">
		<div class="wrap">
			<h2>쪽지 보내기</h2>
			<div class="roundBox">
			<div class="moveBox">
				<span onclick="receiveList();">수신쪽지함</span>&nbsp;&nbsp;&nbsp;<span
					onclick="sendList();">송신쪽지함</span>
			</div>

			<form method="post" action="${ contextPath }/note/write">
				<div class="flexBox">
					<div class="titleBox">보내는 사람 :</div>
					<div class="inputBox">${member.nickName }</div>
					<input type="hidden" name="sendUserNickname" value="${member.nickName }">
				</div>

				<div class="flexBox">
					<div class="titleBox">수신자 닉네임 :</div>
					<div class="inputBox">
						<div id="idSearchBox">
							<c:choose>
								<c:when test="${receiveUser !=null }">
									<input id="titleInput" class="receiveUserNickname" type="text" placeholder="" value="${ receiveUser }" maxlength="15" name="receiveUserNickname" required>
								</c:when>
								<c:otherwise>
									<input id="titleInput" class="receiveUserNickname" type="text" placeholder="" value="" maxlength="15" name="receiveUserNickname" required>
								</c:otherwise>
							</c:choose>
							
							
							<button id="searchBtn" type="button">조회</button>
						</div>

					</div>
				</div>

				<div class="flexBox">
					<div class="titleBox">제목 :</div>
					<div class="inputBox">
						<div>
							<input id="titleInput" type="text" placeholder="제목은 최대 20자리까지 입력가능합니다." name="title" value="" maxlength=20 required>
						</div>
					</div>
				</div>


				<div class="flexBox" style="align-items: baseline;">
					<div class="titleBox">내용 :</div>
					<div class="descriptionInputBox">
						<textarea placeholder="메시지를 입력해주세요.(2000자 제한)" id="message" name="content" maxlength="2000" required></textarea>
					</div>
				</div>


				<div class="flexBoxEnd">
					<div class="titleBox"></div>
					<div class="inputBoxEnd">
						<button id="sendButton" disabled>보내기</button>
					</div>
				</div>
			</form>
			</div>
		</div>
	</div>


	<!-- 푸터 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp">
		<jsp:param name="pName" value="test" />
	</jsp:include>


	
	<script>
		function receiveList() {
			location.href = "${contextPath}/note/receive/list";
		}

		function sendList() {
			location.href = "${contextPath}/note/send/list";
		}
		
		function check(regExp, input, msg){
            // 정규 표현식이 만족할 경우 true 리턴
            if(regExp.test(input.val())) return true;
            // 정규 표현식 불만족 할 경우 false 리턴
            alert(msg);
            input.val('');
            input.focus();
            return false;
        }
		
		
		var userNickname=$("[name=receiveUserNickname]");
		// 아이디 중복시 false, 아이디 사용 가능시 true 값을 가지는 변수
		var isUsable = false;
		var flag=${receiveUser !=null};
		// 게시판에서 쪽지보내기 버튼을 눌렀을땐 아이디 조회필요없이 이미 조회될수 있도록한 처리
		if(flag){
			isUsable=true;
			userNickname.attr('readonly', true);
			$("#sendButton").removeAttr("disabled");
		}
		
		
		$("#searchBtn").on('click', function() {
			
			
			
			if(!check(/^[가-힣a-zA-Z0-9]{2,7}$/, userNickname,
	        "닉네임은 한글, 영대소문자, 숫자로 2~6자리만 가능합니다.")){
				/* console.log("실패"); */
	        } else {
	        	$.ajax({
					url : "${contextPath}/nicknameSearch",
					type : "post",
					data : {
						userNickname : userNickname.val()
					},
					success : function(result) {
						/* console.log(result); */
						if (result == 'fail') {
							alert("조회되지 않는 닉네임 입니다.");
							userNickname.val('');
							userNickname.focus();
						} else {
							if (confirm("닉네임 조회에 성공하였습니다. 사용하시겠습니까?")) {
								// 더 이상 아이디 입력 공간을 바꿀 수 없도록 처리
								userNickname.attr('readonly', true);
								/* 사용 가능한 아이디라는 flag */
								isUsable=true;
							} else {
								// 다시 아이디 입력 공간을 바꿀 수 있도록 처리
								userNickname.attr('readonly',false);
								userNickname.focus();
								isUsable=false;
							}
						}
						/* 아이디 중복 체크 후 중복이 아니면 사용하겠다고 선택한 경우
						버튼의 disabled 속성 제거 */
						if(isUsable){
							$("#sendButton").removeAttr("disabled");
						} else {
							$("#sendButton").attr("disabled", true);
							
						}
					},
					error : function(e) {
						console.log(e);
					}
				});
	        }
			
			
		});
	</script>
</body>

</html>