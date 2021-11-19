<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<title>회원가입</title>
<style>
        body{
            margin: 0;
        }

        .outer{
            width : 750px;
            border-radius: 10px;
            margin: 30px auto;
        }
        
        #joinForm {
            width : 400px;
            margin: auto;
            padding: 10px;
        }
        
        #joinForm h1 {
            text-align:center;
        }

        .input_area {
            display: block;
            position: relative;
            width: 100%;
            height: 30px;
            border: solid 1px #dadada;
            padding : 10px 5px 10px 5px;
            background : white;
        }
        
        .input_area input:focus {
            outline: none;
        }
        
        .input_area input {
            width : 400px;
            height : 30px;
            border: 0px;
            font-size: 13px;
        }
        
        .btnArea{
            width: 412px;
            padding-top: 20px;
        }

        .postcodify_search_div{
            width: 412px;
            display: flex;
        }

        #postcodify_search_button{
            margin-top: 14px;
            margin-left: auto;
            width : 70px;
            height : 27px;
            border : 0px;
            color:white;
            background:#041102;
            cursor : pointer;
        }

        #joinBtn {
            width : 412px;
            height : 50px;
            border : 0px;
            color:white;
            font-size: 20px;
            background:rgb(135, 211, 124);
            cursor : pointer;
        }

        #pwdCheck_div, #pwdCheck_div2, #idCheck_div, #emailCheck_div,
        #phoneCheck_div, #nameCheck_div, #nickCheck_div{
            font-size: 13px;
            padding-left: 5px;
            padding-top: 3px;
        }

        .invalid{
		    color: red;
        }

        .valid{
            color: green;
        }
        
        .profileArea{
            margin-top: 20px;
            border: 1px solid #dadada;
            background-color: white;
            width: 410px;
            height: 410px;
            cursor: pointer;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        #profile_label{
            font-size: 18px;
            cursor: pointer;
        }

        header{
            padding-top: 30px;
            height: 100px;
            display: flex;
        }

        #login_logo{
            width: 250px;
            margin: auto;
            cursor: pointer;
        }
        
        .year_div{
			display: block;
        	width: 412px;
        	height: 30px;
        	border: none;
            position: relative;
        }
        
        #year{
        	border: solid 1px #dadada;
        	width: 150px;
        	height: 50px;
        	font-size: 15px;
        }

		#month, #day{
			border: solid 1px #dadada;
			margin-left: 15px;
			width: 110px;
			height: 50px;
			font-size: 15px;
		}

    </style>


</head>
<body>

	<%-- <%@ include file="/WEB-INF/views/common/menubar.jsp" %> --%>

	<header>
        <img src="${pageContext.servletContext.contextPath }/resources/images/Logo.png" id="login_logo" onclick="location.href='${contextPath}'">
	</header>


	<div class="outer">
		<div id="joinInfoArea">
			<form id="joinForm" method="post" enctype="multipart/form-data">
				<h4>* 아이디</h4>
				<span class="input_area"><input type="text" maxlength="15"
					name="userId" id="userId" required></span>
				<div id="idCheck_div">&nbsp;</div>

				<h4>* 비밀번호</h4>
				<span class="input_area"><input type="password"
					maxlength="15" name="userPwd" id="userPwd" required></span>
				<div id="pwdCheck_div">&nbsp;</div>
				<h4>* 비밀번호 확인</h4>
				<span class="input_area"><input type="password"
					maxlength="15" name="userPwd2" id="userPwd2" required></span> <label
					id="pwdResult"></label>
				<div id="pwdCheck_div2">&nbsp;</div>
				<h4>* 이름</h4>
				<span class="input_area"><input type="text" maxlength="5"
					name="userName" id="userName" required></span>
				<div id="nameCheck_div">&nbsp;</div>

         		<h4>* 닉네임</h4>
				<span class="input_area"><input type="text" maxlength="6" name="nickName" id="nickName" required></span>

				<div id="nickCheck_div">&nbsp;</div>
				<h4>* 생년월일</h4>
				<div class="year_div">
					<c:set var="current" value="<%= new java.util.Date() %>"></c:set>
					<fmt:formatDate value="${ current }" pattern="yyyy" var="thisYear" />

					<select name="year" id="year">
						<c:forEach var="i" begin="1900" end="${ thisYear }">
							<option value=${ i }>${ i }년</option>
						</c:forEach>
					</select> <select name="month" id="month">
						<c:forEach var="j" begin="1" end="12">
							<option value=${ j }>${ j }월</option>
						</c:forEach>
					</select> <select name="day" id="day">

					</select>
				</div>

				<br>

				<h4>* 연락처</h4>
				<span class="input_area"><input type="tel" maxlength="11"
					name="phone" id="phone" placeholder="(-없이)01012345678" required></span>
				<div id="phoneCheck_div">&nbsp;</div>

				<h4>* 이메일</h4>
				<span class="input_area"><input type="email" name="email"
					id="email" required></span>
				<div id="emailCheck_div">&nbsp;</div>

				<div class="postcodify_search_div">
					<h4>우편번호</h4>
					<button type="button" id="postcodify_search_button">검색</button>
				</div>
				<span class="input_area"><input type="text" name="address"
					class="postcodify_postcode5" readonly></span>
				<h4>도로명주소</h4>
				<span class="input_area"><input type="text" name="address"
					class="postcodify_address" readonly></span>
				<h4>상세주소</h4>
				<span class="input_area"><input type="text" name="address"
					class="postcodify_details"></span>

				<h4>프로필 사진</h4>
				<input type="file" id="profile" name="profile" hidden>
				<div class="profileArea" id="profileArea">
					<label id="profile_label">이 곳을 클릭하세요.</label>
				</div>

				<div class="btnArea">
					<button id="joinBtn" type="button" onclick="validate();">가입하기</button>
				</div>
			</form>
		</div>
	</div>

	<!-- jQuery와 Postcodify를 로딩한다 -->
	<!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script> -->
	<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>

	<!-- "검색" 단추를 누르면 팝업 레이어가 열리도록 설정한다 -->
	<script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>

	<script>
		let A = [];
		let B = [];
		let C = [];
		for(let i = 1 ; i <= 29 ; i++)
			A.push(i);
		for(let i = 1 ; i <= 30 ; i++)
			B.push(i);
		for(let i = 1 ; i <= 31 ; i++)
			C.push(i);
		
		let month = document.getElementById("month");
		let day = document.getElementById("day");
		let inputOption = C;
		for(let count = 1; count <= inputOption.length; count++){                
            day.innerHTML += '<option value = ' + count + '>' + count + '일' + '</option>';
        }
		month.addEventListener('change', () => {
			if(month.value == 2) {
				inputOption = A;
			}
			else if(month.value == 4 || month.value == 6 || month.value == 9 || month.value == 11) {
				inputOption = B;
			}
			else {
				inputOption = C;
			}
			day.options.length = 0;
			for(let count = 1; count <= inputOption.length; count++){                
                day.innerHTML += '<option value = ' + count + '>' + count + '일' + '</option>';
            }
        })
        
        let userId = document.getElementById("userId");
        let userPwd = document.getElementById("userPwd");
        let userPwd2 = document.getElementById("userPwd2");
        let userName = document.getElementById("userName");
        let nickName = document.getElementById("nickName");
        let phone = document.getElementById("phone");
        let email = document.getElementById("email");
        let userIdresult = document.getElementById("idCheck_div");
        let userPwdresult = document.getElementById("pwdCheck_div");
        let userPwd2result = document.getElementById("pwdCheck_div2");
        let userNameresult = document.getElementById("nameCheck_div");
        let nickNameresult = document.getElementById("nickCheck_div");
        let phoneresult = document.getElementById("phoneCheck_div");
        let emailresult = document.getElementById("emailCheck_div");
        let idCheck = false;
        let pwdCheck = false;
        let pwdCheck2 = false;
        let nameCheck = false;
        let nickCheck = false;
        let phoneCheck = false;
        let emailCheck = false;
        
        userId.onblur = function(){
        	var userId2 = $("[name=userId]");
            let regExp = /^[a-z\d]{4,15}$/;
            if(regExp.test(this.value)) {
            	$.ajax({
					url : "${contextPath}/idCheck",
					type : "post",
					data : { userId : userId2.val() },
					success : function(result){
						if(result == "fail"){
							userIdresult.classList.remove('valid');
			                userIdresult.classList.add('invalid');
			                userIdresult.innerHTML = '사용중인 아이디입니다.';
			                idCheck = false;
						}
						else{
							userIdresult.classList.remove('invalid');
			                userIdresult.classList.add('valid');
			                userIdresult.innerHTML = '사용하실 수 있는 아이디입니다.';
			                idCheck = true;
						}
					},
					error : function(e){
						console.log(e);
					}
				})
            }
            else {
                userIdresult.classList.remove('valid');
                userIdresult.classList.add('invalid');
                userIdresult.innerHTML = '영문 소문자 및 숫자 4~15 자리로 입력하세요';
                idCheck = false;
            }
            if(this.value == "") {
                userIdresult.innerHTML = '&nbsp;';
                idCheck = false;
            }
        };
        userId.onfocus = function(){
            if(this.classList.contains('invalid')){
                this.classList.remove('invalid');
                userIdresult.innerHTML = '&nbsp;';
            }
        }

        userPwd.onblur = function(){
            let regExp = /(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,}/;
            if(regExp.test(this.value)) {
                userPwdresult.classList.remove('invalid');
                userPwdresult.classList.add('valid');
                userPwdresult.innerHTML = '사용하실 수 있는 비밀번호입니다.';
                pwdCheck = true;
            }
            else {
                userPwdresult.classList.remove('valid');
                userPwdresult.classList.add('invalid');
                userPwdresult.innerHTML = '영문 대소문자 숫자 특수문자 포함 8~15 자리로 입력하세요';
                pwdCheck = false;
            }
            if(this.value == "") {
                userPwdresult.innerHTML = '&nbsp;';
                pwdCheck = false;
            }
        };
        userPwd.onfocus = function(){
            if(this.classList.contains('invalid')){
                this.classList.remove('invalid');
                userPwdresult.innerHTML = '&nbsp;';
                pwdCheck = false;
            }
        }

        userPwd2.onblur = function(){
            if(this.value == userPwd.value) {
                userPwd2result.classList.remove('invalid');
                userPwd2result.classList.add('valid');
                userPwd2result.innerHTML = '비밀번호가 일치합니다.';
                pwd2Check = true;
            }
            else {
                userPwd2result.classList.remove('valid');
                userPwd2result.classList.add('invalid');
                userPwd2result.innerHTML = '비밀번호가 일치하지 않습니다.';
                pwd2Check = false;
            }
            if(this.value == "") {
                userPwd2result.innerHTML = '&nbsp;';
                pwd2Check = false;
            }
        };
        userPwd2.onfocus = function(){
            if(this.classList.contains('invalid')){
                this.classList.remove('invalid');
                userPwd2result.innerHTML = '&nbsp;';
                pwd2Check = false;
            }
        }
   
        userName.onblur = function(){
            let regExp = /^[가-힣]{2,}$/;
            if(regExp.test(this.value)) {
                userNameresult.classList.remove('invalid');
                userNameresult.classList.add('valid');
                userNameresult.innerHTML = '올바른 형식입니다.';
                nameCheck = true;
            }
            else {
                userNameresult.classList.remove('valid');
                userNameresult.classList.add('invalid');
                userNameresult.innerHTML = '한글로 2~5 자리로 입력하세요.';
                nameCheck = false;
            }
            if(this.value == "") {
                userNameresult.innerHTML = '&nbsp;';
                nameCheck = false;
            }
        };
        userName.onfocus = function(){
            if(this.classList.contains('invalid')){
                this.classList.remove('invalid');
                userNameresult.innerHTML = '&nbsp;';
                nameCheck = false;
            }
        }

        email.onblur = function(){
            let regExp = /^([a-zA-z0-9])+@([a-zA-Z])+\.[a-zA-Z]{2,3}/;
            if(regExp.test(this.value)) {
                emailresult.classList.remove('invalid');
                emailresult.classList.add('valid');
                emailresult.innerHTML = '올바른 형식입니다.';
                emailCheck = true;
            }
            else {
                emailresult.classList.remove('valid');
                emailresult.classList.add('invalid');
                emailresult.innerHTML = '이메일 형식을 확인하세요.';
                emailCheck = false;
            }
            if(this.value == "") {
                emailresult.innerHTML = '&nbsp;';
                emailCheck = false;
            }
        };
        email.onfocus = function(){
            if(this.classList.contains('invalid')){
                this.classList.remove('invalid');
                emailresult.innerHTML = '&nbsp;';
                emailCheck = false;
            }
        }

        phone.onblur = function(){
            let regExp = /^0[0-9]{1,}$/;
            if(regExp.test(this.value)) {
                phoneresult.classList.remove('invalid');
                phoneresult.classList.add('valid');
                phoneresult.innerHTML = '올바른 형식입니다.';
                phoneCheck = true;
            }
            else {
                phoneresult.classList.remove('valid');
                phoneresult.classList.add('invalid');
                phoneresult.innerHTML = '전화번호 형식을 확인하세요.';
                phoneCheck = false;
            }
            if(this.value == "") {
                phoneresult.innerHTML = '&nbsp;';
                phoneCheck = false;
            }
        };

        phone.onfocus = function(){
            if(this.classList.contains('invalid')){
                this.classList.remove('invalid');
                phoneresult.innerHTML = '&nbsp;';
                phoneCheck = false;
            }
        }

        nickName.onblur = function(){
        	var userNick2 = $("[name=nickName]");
            let regExp = /^[가-힣a-zA-Z0-9]{2,8}$/;
            if(regExp.test(this.value)) {
            	$.ajax({
    				url : "${contextPath}/nickCheck",
    				type : "post",
    				data : { nickName : userNick2.val() },
    				success : function(result){
    					if(result == "fail"){
    						nickNameresult.classList.remove('valid');
    						nickNameresult.classList.add('invalid');
    						nickNameresult.innerHTML = '사용중인 닉네임입니다.';
    						nickCheck = false;
    					}
    					else{
    						nickNameresult.classList.remove('invalid');
    						nickNameresult.classList.add('valid');
    						nickNameresult.innerHTML = '사용하실 수 있는 닉네임입니다.';
    						nickCheck = true;
    					}
    				},
					error : function(e){
						console.log(e);
					}
				})
            }
            else {
                nickNameresult.classList.remove('valid');
                nickNameresult.classList.add('invalid');
                nickNameresult.innerHTML = '영어 대소문자 한글 단어 숫자만 입력하세요.';
				nickCheck = false;
            }
            if(this.value == "") {
                nickNameresult.innerHTML = '&nbsp;';
                nickCheck = false;
            }
        };
        nickName.onfocus = function(){
            if(this.classList.contains('invalid')){
                this.classList.remove('invalid');
                nickNameresult.innerHTML = '&nbsp;';
                nickCheck = false;
            }
        }
        
        document.getElementById('profileArea').addEventListener('click',function(){
            let file = document.getElementById('profile');
            file.click();
        });

        document.getElementById('profile').addEventListener('change', function(){
            if(this.files && this.files[0]){
                let reader = new FileReader();

                reader.onload = function(){
                    document.getElementById('profileArea').innerHTML = '<img src="' + reader.result + '" style="width: 410px; height: 410px;" >';
                }
                reader.readAsDataURL(this.files[0]);
            }
        });

		function validate(){
			if(idCheck == false){
				console.log("회원가입 불가");
				userId.focus();
				return;
			}
			if(pwdCheck == false){
				console.log("회원가입 불가");
				userPwd.focus();
				return;
			}
			if(pwd2Check == false){
				console.log("회원가입 불가");
				userPwd2.focus();
				return;
			}
			if(nameCheck == false){
				console.log("회원가입 불가");
				userName.focus();
				return;
			}
			if(nickCheck == false){
				console.log("회원가입 불가");
				console.log(nickCheck);
				nickName.focus();
				return;
			}
			if(phoneCheck == false){
				console.log("회원가입 불가");
				phone.focus();
				return;
			}
			if(emailCheck == false){
				console.log("회원가입 불가");
				email.focus();
				return;
			}
			else{
				console.log("회원가입 가능");
				document.forms.joinForm.submit();
			}
		}
	</script>

</body>
</html>










