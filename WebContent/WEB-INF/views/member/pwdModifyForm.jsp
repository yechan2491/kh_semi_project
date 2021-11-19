<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<title>비밀번호 변경</title>
     <style>
        .outer{
            width:500px;
            margin:auto;
        }

        #pwdModifyForm {
            width: 380px;
            margin:auto;
        }
        
        .input_area {
            border: solid 1px #dadada;
            border-radius: 10px;
            padding : 10px 10px 14px 10px;
            background : white;
        }
        
        .input_area input:focus {
            outline: none;
        }
        
        .input_area input{
            width : 350px;
            height : 30px;
            border: 0px;
        }
        
        .btnArea {
            text-align:center;
            padding : 30px;
        }
        
        button:hover {
            cursor:pointer
        }
        
        button {
            border-radius: 10px;
            width : 140px;
            height : 45px;
            border : 0px;
            font-size: 16px;
            font-weight: bold;
            color:white;
            background:rgb(135, 211, 124);
            margin : 5px;
            cursor : pointer;
        }

        .title{
            width: 300px;
            height: 50px;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: auto;
            margin-top: 30px;
            margin-bottom: 40px;
        }

        #title{
            font-size: 30px;
            font-weight: bold;
        }

        #img{
            display: flex;
            position:absolute;
            padding-left: 100px;
            padding-top: 5px;
            width: 40px;
        }
        
        #pwd_check1, #pwd_check2, #pwd_check3{
        	margin-top : 8px;
        	padding-top: 8px;
        	margin-left : 5px;
        	font-size: 12px;
        }

        .invalid{
		    color: red;
        }

        .valid{
            color: green;
        }
    </style>


<%
	if(request.getAttribute("result") != null) {
		if(request.getAttribute("result").equals("success")){
%>
<script>
	alert("성공적으로 비밀번호를 변경하였습니다.");
	window.close();
</script>
<% } else { %>
<script>
	alert("비밀번호 변경에 실패하였습니다.");
</script>
<%		}
	}
%>
</head>
<body>
	<div class="outer">
	 	<img src="${ contextPath }/resources/images/password.png" id="img">
        <div class="title">
            <label id="title">비밀번호 변경</label>
        </div>
	        <form id="pwdModifyForm" method="post">
                    <h4>현재 비밀번호</h4>
                    <span class="input_area">
                    <input type="password" name="userPwd" id="userPwd" maxlength="15">
                    </span>
                    <div id="pwd_check1">&nbsp;</div>
                    
                    <h4>변경할 비밀번호</h4>
                    <span class="input_area">
                    <input type="password" name="newPwd" id="newPwd" maxlength="15">
                    </span>
                    <div id="pwd_check2">&nbsp;</div>
                    
                    <h4>변경할 비밀번호 확인</h4>
                    <span class="input_area">
                    <input type="password" name="newPwd2" id="newPwd2" maxlength="15">
                    </span>
                    <div id="pwd_check3">&nbsp;</div>
                    
	                <div class="btnArea">
	                <button id="updatePwdBtn" type="button" onclick="validate()">변경하기</button>
	                </div>
	        </form>
        </div>
	
	<script>
		let pwd1 = document.getElementById("userPwd");
		let pwdResult1 = document.getElementById("pwd_check1");
		let pwdCheck1 = false;
		
		let pwd2 = document.getElementById("newPwd");
		let pwdResult2 = document.getElementById("pwd_check2");
		let pwdCheck2 = false;
		
		let pwd3 = document.getElementById("newPwd2");
		let pwdResult3 = document.getElementById("pwd_check3");
		let pwdCheck3 = false;
		
		pwd1.onblur = function(){
        	var userPwd2 = $("[name=userPwd]");    
           	$.ajax({
				url : "${contextPath}/pwdCheck",
				type : "post",
				data : { userPwd : userPwd2.val() },
				success : function(result){
					if(result == "fail"){
						pwdResult1.classList.remove('valid');
						pwdResult1.classList.add('invalid');
						pwdResult1.innerHTML = '현재 비밀번호와 일치하지 않습니다.';
						pwdCheck1 = false;
					}
					else{
						pwdResult1.classList.remove('invalid');
						pwdResult1.classList.add('valid');
						pwdResult1.innerHTML = '현재 비밀번호와 일치합니다.';
						pwdCheck1 = true;
					}
				},
				error : function(e){
					console.log(e);
				}
			})

            if(this.value == "") {
            	pwdResult1.innerHTML = '&nbsp;';
            	pwdCheck1 = false;
            }
        };
        pwd1.onfocus = function(){
            if(this.classList.contains('invalid')){
                this.classList.remove('invalid');
                pwdResult.innerHTML = '&nbsp;';
                pwdCheck1 = false;
            }
        }
        
		
        pwd2.onblur = function(){
            let regExp = /(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,}/;
            if(regExp.test(this.value)) {
            	if(this.value == pwd1.value){
            		pwdResult2.classList.remove('valid');
                	pwdResult2.classList.add('invalid');
                	pwdResult2.innerHTML = '이전 비밀번호와 일치합니다.';
                	pwdCheck2 = false;
            	}
            	else{
            		pwdResult2.classList.remove('invalid');
                	pwdResult2.classList.add('valid');
                	pwdResult2.innerHTML = '사용하실 수 있는 비밀번호입니다.';
                	pwdCheck2 = true;
            	}
            }
            else {
            	pwdResult2.classList.remove('valid');
            	pwdResult2.classList.add('invalid');
            	pwdResult2.innerHTML = '영문 대소문자 숫자 특수문자 포함 8~15 자리로 입력하세요.';
            	pwdCheck2 = false;
            }
            if(this.value == "") {
            	pwdResult2.innerHTML = '&nbsp;';
            	pwdCheck2 = false;
            }
        };
        pwd2.onfocus = function(){
            if(this.classList.contains('invalid')){
                this.classList.remove('invalid');
                pwdResult2.innerHTML = '&nbsp;';
                pwdCheck1 = false;
            }
        }

        
        pwd3.onblur = function(){
            if(this.value == pwd2.value) {
            	pwdResult3.classList.remove('invalid');
            	pwdResult3.classList.add('valid');
            	pwdResult3.innerHTML = '비밀번호가 일치합니다.';
            	pwdCheck3 = true;
            }
            else {
            	pwdResult3.classList.remove('valid');
            	pwdResult3.classList.add('invalid');
            	pwdResult3.innerHTML = '비밀번호가 일치하지 않습니다.';
            	pwdCheck3 = false;
            }
            if(this.value == "") {
            	pwdResult3.innerHTML = '&nbsp;';
            	pwdCheck3 = false;
            }
        };
        pwd3.onfocus = function(){
            if(this.classList.contains('invalid')){
                this.classList.remove('invalid');
                pwdResult3.innerHTML = '&nbsp;';
                pwdCheck3 = false;
            }
        }
        
        function validate(){
			if(pwdCheck1 == false){
				pwd1.focus();
				return;
			}
			if(pwdCheck2 == false){
				pwd2.focus();
				return;
			}
			if(pwdCheck3 == false){
				pwd3.focus();
				return;
			}
			else{
				document.forms.pwdModifyForm.submit();
			}
		}
        
	</script>
	
</body>
</html>