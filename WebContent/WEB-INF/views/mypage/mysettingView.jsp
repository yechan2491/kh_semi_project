<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>mypage</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
   
    <style>
        .home-content {
            display: inline-block;
            margin-top: 50px;
            text-align: center;

            font-family: 'Nanum Gothic', sans-serif;
        }

        .mypage_nav>li {
            display: inline;
            font-weight: 700;
            font-size: 20px;
        }

        .mypage_nav>li:not(:first-child) {
            margin-left: 40px;
        }

        .mypage_nav>li:nth-child(4)>a {
            border-radius: 10px;
            background-color: rgb(135, 211, 124);
            padding: 5px 5px;
        }

		.mypage_nav >li > a {
		    font-family: 'Gothic A1', sans-serif;
		}
		
        .profile {
            display: inline-block;
            margin-top: 40px;
            border-radius: 20px;
            width: 550px;
            height: 700px;
            background-color: rgb(245, 245, 245);
            margin-bottom: 100px;



        }

        .profile>h2 {
            margin-top: 20px;
            margin-bottom: 10px;
        }

        .image_area {
            display: inline-block;
            width: 250px;
            height: 250px;
            background-color: white;
            border-radius: 70%;
            margin-bottom: 35px;
            cursor: pointer;
        	overflow: hidden; 

        }
        
        .image_area img {
        	width: 100%;
        	height : 100%;
        	object-fit: cover;
        }

        .image_area>h2 {
            position: relative;
            bottom: 35px;
        }

        .table {
            margin-left: 100px;
        }

        .basic {
            border: 1px lightgray solid;
            border-radius: 5px;
            height: 30px;
            margin-bottom: 10px;
            width: 200px;
        }

        input:not(.search__input):first-child {
            margin-left: 30px;
        }

        .phone {
            border: 1px lightgray solid;
            border-radius: 5px;
            height: 30px;
            margin-bottom: 10px;

            margin-left: 3px;
            width: 60px;
        }

        .email {
            border: 1px lightgray solid;
            border-radius: 5px;
            height: 30px;
            margin-bottom: 10px;


            width: 92px;
        }

        #user_id {
            background-color: rgb(245, 245, 245);
            cursor: auto;
        }


        #user_id:focus {
            outline: none;

        }

        .submit {
        	
            margin-top: 10px;
            position: relative;
            left:10px;
            background-color: rgb(135, 211, 124);
            border: none;
            font-size: 15px;
            border-radius: 5px;
            cursor: pointer;
            padding: 10px 45px; 
            color: #fff;
            margin-left: 10px;
            font-family: 'Gowun Batang', serif;
        }

        .profile_header{
            display: flex;
        }
        
        .profile_header h2{
            margin: 20px auto;
            margin-left: 200px;
        }

        .quit {
            /* position: relative; */
            position: absolute;
            margin-top: 3px;
            margin-left: 475px;
            color: gray;
        }
    	
    	.pwd_btn {
    	position: relative;
    	padding: 3px 3px;
    	bottom:235px;
    	left:20px;
    	border: 1px solid lightgray;
    	border-radius:5px;
    	color: rgb(100,100,100);
    	cursor: pointer
    	}
        
    </style>

</head>

<body>
	<script>
   $(function () {
	   
    $(".image_area").click(() => {
    	$(".file").click();
    });
   });
   
   
   </script>


	<%@ include file="/WEB-INF/views/common/menubar.jsp"%>

	<div class="home-content wrapper">
		<nav>
			<ul class="mypage_nav">
				<li><a href="${contextPath }/myprofile"> 프로필 </a></li>
				<li><a href="${contextPath }/myproduct"> 찜한상품 </a></li>
				<li><a href="${contextPath }/myscrap"> 스크랩 </a></li>
				<li><a href="${contextPath }/mysetting"> 설정 </a></li>
			</ul>
		</nav>
		<div class="profile">
			<div class="profile_header">
				<h2>회원 설정 변경</h2>
				<a href="${contextPath }/quit" class="quit">탈퇴하기</a>
			</div>

			<form method="post" name="modifyForm"
				action="${ contextPath }/mypage/modify"
				enctype="multipart/form-data">
				<input type="file" class="file" name="profile"
					style="display: none;" accept="image/gif,image/jpeg,image/png">
				<div class="image_area" title="프로필 이미지 변경">
					<c:if
						test="${ loginUser.getProfilePath() != '/resources/uploadFiles/null' }">
						<img src="${contextPath }${ loginUser.getProfilePath()}">
					</c:if>
					<c:if
						test="${ loginUser.getProfilePath() == '/resources/uploadFiles/null' }">
						<img src="${contextPath }/resources/images/mypage_profile.png">
					</c:if>
				</div>
				<div class="table">
					<table>
						<tr>
							<th align="right">아이디</th>
							<td><input class="basic" maxlength="13" id="user_id"
								name="user_id" type="text" value="<%= loginUser.getUserId()%>"
								readonly></td>
						</tr>
						<tr>
							<th align="right">닉네임</th>
							<td><input class="basic" name="user_nickname"
								onfocus="this.value=''" type="text" maxlength="6"
								value="<%= loginUser.getNickName() != null ? loginUser.getNickName() : "" %>"></td>
						</tr>
						<tr>
							<th align="right">이름</th>
							<td><input class="basic" maxlength="5" name="user_name"
								id="user_name" onfocus="this.value=''" type="text"
								value="<%= loginUser.getUserName() != null ? loginUser.getUserName() : "" %>"></td>
						</tr>
						<tr>
							<th align="right">휴대전화</th>
							<c:set var="phoneValue" value="<%= loginUser.getPhone()%>" />
							<td><input class="phone" name="user_phone" id="tel1"
								onfocus="this.value=''" type="text" maxlength="3"
								value="${fn:substring(phoneValue,0,3) }"> <input
								class="phone" name="user_phone" id="tel2"
								onfocus="this.value=''" type="text" maxlength="4"
								value="${fn:substring(phoneValue,3,7) }"> <input
								class="phone" name="user_phone" id="tel3"
								onfocus="this.value=''" type="text" maxlength="4"
								value="${fn:substring(phoneValue,7,11) }"></td>

						</tr>
						<tr>
							<th align="right">이메일</th>
							<c:set var="emailValue" value="<%= loginUser.getEmail()%>" />
							<td><input class="email" name="user_email" type="text"
								value="${ fn:substringBefore(emailValue, '@') }"
								onfocus="this.value=''">@<select class="email"
								id="selbox" name="user_email">
									<c:if
										test="${not fn:contains(emailValue, 'daum.net') && not fn:contains(emailValue, 'naver.com') &&
                                not fn:contains(emailValue, 'gmail.com') && not fn:contains(emailValue, 'hanmail.net') && not fn:contains(emailValue, 'nate.com')}">
										<option value="${ fn:substringAfter(emailValue, '@') }">
											${ fn:substringAfter(emailValue, '@') }</option>
									</c:if>
									<option value="daum.net"
										<c:if test="${fn:contains(emailValue, 'daum.net') }">selected</c:if>>
										daum.net</option>
									<option value="naver.com"
										<c:if test="${fn:contains(emailValue, 'naver.com') }">selected</c:if>>
										naver.com</option>
									<option value="gmail.com"
										<c:if test="${fn:contains(emailValue, 'gmail.com') }">selected</c:if>>
										gmail.com</option>
									<option value="hanmail.net"
										<c:if test="${fn:contains(emailValue, 'hanmail.net') }">selected</c:if>>
										hanmail.net</option>
									<option value="nate.com"
										<c:if test="${fn:contains(emailValue, 'nate.com') }">selected</c:if>>
										nate.com</option>
									<option value="direct">직접입력</option>
							</select><input type="text" class="email" id="selboxDirect"
								name="user_email_direct"
								value="${ fn:substringAfter(emailValue, '@') }" /></td>
						</tr>
					</table>
					<input class="submit" type="submit" value="회원정보 수정하기">
					<button class="pwd_btn" type="button" onclick="openPopup('<%= request.getContextPath() %>/pwdModify','pwdModify',600,650);">비밀번호 변경</button>
				</div>

			</form>
		</div>
	</div>

	<script>
   	 document.forms.modifyForm.onsubmit = function(){
    	 if(!check(/^[가-힣]{2,}$/,document.getElementById('user_name'),"이름은 한글로 2글자 이상 입력하세요"))
 			return false;
    	 
    	 if(!check(/^01[01679]$/,document.getElementById('tel1'),"잘못 된 전화번호 입력입니다."))
             return false;
             if(!check(/^\d{4}$/,document.getElementById('tel2'),"잘못 된 전화번호 입력입니다."))
             return false;
             if(!check(/^\d{4}$/, document.getElementById('tel3'), "잘못 된 전화번호 입력입니다."))
             return false;
    	
        	
  		if(!check(/(com|net|kr)/, document.getElementById('selboxDirect'), "잘못 된 이메일 형식입니다."))
            return false; 
    	

 };
     function check(regExp, input, msg){
         if(regExp.test(input.value)) return true;
         alert(msg);
         input.value ='';
         input.focus();
         return false;
     };
     
     //비밀 번호 변경 팝업 창
     function openPopup(url, title, width, height){
         
	        var left =(document.body.clientWidth/2) - (width/2);
	        left+=window.screenLeft; // 듀얼모니터
	        var top=(screen.availHeight/2)-(height/2);
	        var options= "width="+width+",height="+height+",left="+left+",top="+top;
	         window.open(url, title, options);
	    }
    
    </script>
	<script>
    

    $(function () {
            $("#selboxDirect").hide();

            $("#selbox").change(function () {

                if ($("#selbox").val() == "direct") {

                    $("#selboxDirect").show();
                    $("#selbox").hide();

                } else {

                    $("#selboxDirect").hide();

                }
            })
        });
    </script>


	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	<script src="${ contextPath }/resources/js/imagePreview.js"></script>
</body>

</html>