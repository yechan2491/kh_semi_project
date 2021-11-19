<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<style>
.header {
	width: 450px;
	height: 100px;
	border: 1px solid lightgray;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
	margin: 0px auto;
	margin-top: 70px;
	display: flex;
	justify-content: center;
	align-items: center;
	background-color: rgb(135, 211, 124);
}

.outer {
	width: 450px;
	height: 450px;
	border-left: 1px lightgray solid;
	border-right: 1px lightgray solid;
	border-bottom: 1px lightgray solid;
	border-bottom-left-radius: 10px;
	border-bottom-right-radius: 10px;
	margin: 0 auto;
	margin-bottom: 70px;
	display: flex;
	justify-content: center;
	align-items: center;
}

#id_btn, #pwd_btn {
	font-family: 'Nanum Gothic', sans-serif;
	width: 120px;
	height: 50px;
	background-color: white;
	font-size: 15px;
	font-weight: bold;
	border: none;
	cursor: pointer;
}

#id_btn {
	border-top-left-radius: 10px;
	border-bottom-left-radius: 10px;
}

#pwd_btn {
	border-top-right-radius: 10px;
	border-bottom-right-radius: 10px;
	background-color: rgb(231, 231, 231);
}

.input_area {
	display: block;
	position: relative;
	width: 300px;
	height: 30px;
	border: solid 1px #dadada;
	padding: 10px 5px 10px 5px;
	background: white;
}

.input_area input:focus {
	outline: none;
}

.input_area input {
	font-family: 'Nanum Gothic', sans-serif;
	width: 300px;
	height: 30px;
	border: 0px;
	font-size: 14px;
}

.area {
	margin: 15px;
	display: flex;
	justify-content: center;
	align-items: center;
}

#find_btn {
	font-family: 'Nanum Gothic', sans-serif;
	font-weight: bold;
	margin-top: 20px;
	width: 120px;
	height: 40px;
	border: 0px;
	border-radius: 10px;
	color: white;
	font-size: 20px;
	background: rgb(135, 211, 124);
	cursor: pointer;
}

#find_img {
	width: 50px;
	height: 50px;
}
</style>

<title>비밀번호 찾기</title>
</head>
<body>

	<%@ include file="/WEB-INF/views/common/menubar.jsp"%>

	<div class="header">
		<button id="id_btn" onclick="location.href='${ contextPath }/idfind'">아이디
			찾기</button>
		<button id="pwd_btn">비밀번호 찾기</button>
	</div>
	<div class="outer">
		<div>
			<div class="area">
				<img src="${ contextPath }/resources/images/id2.png" id="find_img">
			</div>
			<span class="input_area"> <input type="text" maxlength="15"
				name="userId" id="userId" placeholder="가입시 작성한 아이디를 입력해 주세요" required>
			</span>		
			<div class="area">
				<img src="${ contextPath }/resources/images/email.png" id="find_img">
			</div>
			<span class="input_area"> <input type="email" maxlength="30"
				name="email" id="email" placeholder="가입시 작성한 이메일을 입력해 주세요" required>
			</span>
			
			<div class="area">
				<button id="find_btn" onclick="pwdFind()">찾&nbsp;기</button>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/common/footer.jsp"%>

	<script>
        $('#id_btn').hover(function(){
            $('#pwd_btn').css('background','white');
            $(this).css('background','rgb(231,231,231)');
        });
        $('#id_btn').mouseout(function(){
            $('#pwd_btn').css('background','rgb(231,231,231)');
            $(this).css('background','white');
        })
        
        function pwdFind(){
        	var userId2 = $("[name=userId]");
        	var email2 = $("[name=email]");
        	$.ajax({
				url : "${contextPath}/pwdfind",
				type : "post",
				data : { userId : userId2.val(), email : email2.val() },
				success : function(result){
					if(result == "fail"){
						openWindow('<%= request.getContextPath() %>/findfail','resultfind',500,500);
					}
					else{
						openWindow('<%= request.getContextPath() %>/sendemail','resultfind',500,500);
					}
				},
				error : function(e){
					console.log(e);
				}
			})
        }
        
        function openWindow(url, title, width, height){
	         
	        var left =(document.body.clientWidth/2) - (width/2);
	        left+=window.screenLeft; // 듀얼모니터
	        var top=(screen.availHeight/2)-(height/2);
	        var options= "width="+width+",height="+height+",left="+left+",top="+top;
	        var openResult = window.open(url, title, options);
	    }
        
    </script>

</body>
</html>