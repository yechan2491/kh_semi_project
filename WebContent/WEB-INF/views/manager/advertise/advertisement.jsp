<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>광고문의</title>

	<%@ include file="/WEB-INF/views/common/menubar.jsp"%>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100&display=swap"
	rel="stylesheet">
</head>
<style>
body {
	font-family: 'Nanum Gothic', sans-serif;
}

#containers {
	margin-top: 50px;
	border: 1px solid lightgray;
	margin-bottom: 50px;
	border-radius: 10px;
	min-width: 800px;
	width: 800px;
}

.notice_title {
	width: 200px;
	margin: auto;
	display: block;
}

.content {
	margin-top: -5%;
	text-align: center;
	font-size: 16px;
}

#substancetilte {
	font-size: 20px;
	font-weight: 800;
}

.substance {
	margin-left: 1%;
}

.sub {
	margin-left: 100px;
}

#content {
	padding: 7px 1px;
	font-size: 15px;
	width: 600px;
	font-family: 'Nanum Gothic', sans-serif;
}

.company {
	margin-left: 1%;
}

#title, #companyname, #writer, #phonenumber {
	font-size: 15px;
	width: 600px;
	height: 40px;
}

.submitbutton {
	width: 580px;
	height: 40px;
	border: none;
	background-color: rgb(135, 211, 124);
	margin: auto;
	display: block;
	margin-bottom: 100px;
	border-radius: 10px;
	color: white;
	font-size: 15px;
}

.submitbutton:active {
	position: relative;
	top: 1px;
}

.check {
	margin-top: 5px;
	margin-left: 98px;
	margin-bottom: 50px;
	width: 550px;
	font-size: 12px;
	border-radius: 3px 3px;
}

#checktitle {
	font-size: 15px;
	font-weight: 900;
	margin-top: -31px;
	margin-left: 28px;
}

#checkcontent {
	border: 1px solid lightgray;
	margin-left: 10px;
	width: 580px;
	padding: 10px;
}

.check_button {
	margin-left: 10px;
}
</style>
<body>
	<!-- header -->

	<div class="container" id="containers">
		<div class="notice_title">
			<img src="${ contextPath }/resources/images/ad.png">
		</div>

		<p class="content">
			자취는 템빨은 523만명의 가입자를 보유한 <span style=""211, 124);>국내 1위 자취생 전용
				커뮤니티</span> 입니다. <br> 자취생의 자취 생활에 가치를 더하고자 하는 다양한 광고 문의를 환영합니다.<br>
			보내주신 문의는 자취는 템빨의 관리자에게 전달 됩니다.
		</p>
		<br>
		<form method="post" action="${ contextPath }/ad/insert">
			<div class="sub">
				<p id="substancetilte">문의내용</p>
				<div class="substance">
					<label><p>
							제목 <span style="color: red">*</span>
						</p></label> <input type="text" name="title" id="title" size="50"
						placeholder="&nbsp;제목을 입력해주세요." required> <br> <label><p>
							내용 <span style="color: red">*</span>
						</p></label>
					<textarea cols="49" rows="10" style="resize: none" name="content"
						id="content" placeholder="&nbsp;내용을 입력해주세요." required></textarea>
				</div>
				<br>
				<p id="substancetilte">업체정보</p>
				<div class="company">
					<label><p>
							회사(기관)명 <span style="color: red">*</span>
						</p></label> <input type="text" name="companyname" id="companyname" size="50"
						placeholder="&nbsp;회사명을 입력해주세요." required> <label><p>
							성명 <span style="color: red">*</span>
						</p></label> <input type="text" name="writer" id="writer" size="50"
						placeholder="&nbsp;성명을 입력해주세요." required> <label><p>
							연락처 <span style="color: red">*</span>
						</p></label> <input type="text" name="phonenumber" id="phonenumber" size="50"
						maxlength="13" placeholder="&nbsp;연락처을 입력해주세요." required>
				</div>
			</div>
			<script>
				var autoHypenPhone = function(str){
				      str = str.replace(/[^0-9]/g, '');
				      var tmp = '';
				      if( str.length < 4){
				          return str;
				      }else if(str.length < 7){
				          tmp += str.substr(0, 3);
				          tmp += '-';
				          tmp += str.substr(3);
				          return tmp;
				      }else if(str.length < 11){
				          tmp += str.substr(0, 3);
				          tmp += '-';
				          tmp += str.substr(3, 3);
				          tmp += '-';
				          tmp += str.substr(6);
				          return tmp;
				      }else{              
				          tmp += str.substr(0, 3);
				          tmp += '-';
				          tmp += str.substr(3, 4);
				          tmp += '-';
				          tmp += str.substr(7);
				          return tmp;
				      }
				  
				      return str;
				}


				var phoneNum = document.getElementById('phonenumber');

				phoneNum.onkeyup = function(){
				  console.log(this.value);
				  this.value = autoHypenPhone( this.value ) ;  
				}
				</script>

			<br>

			<!-- 체크해야 확인 눌러지게 처리  -->
			<div class="check">
				<input type="checkbox" class="check_button" required>
				<p id="checktitle">
					사업 제휴 광고문의를 위한 정보수집 및 이용동의<span style="color: red">*</span>
				</p>
				<p id="checkcontent">
					주식회사 자취는 팀빨(이하 “회사”라 함)는 개인정보보호법, 정보통신망 이용촉진 및 정보보호 등에 관한 법률 등 관련
					법령상의 개인정보보호 규정을 준수하며, 파트너의 개인정보 보호에 최선을 다하고 있습니다.<br> 1. 개인정보
					수집 및 이용주체: 제휴문의 및 상담신청을 통해 제공하신 정보는 “회사”가 직접 접수하고 관리합니다.<br>
					2. 동의를 거부할 권리 및 동의 거부에 따른 불이익: 신청자는 개인정보제공 등에 관해 동의하지 않을 권리가
					있습니다.(이 경우 제휴문의 및 상담신청이 불가능합니다.)<br> 3. 수집하는 개인정보 항목(필수항목):
					담당자명, 담당자 이메일 주소, 담당자 전화번호<br> 4. 수집 및 이용목적: 제휴사 검토, 제휴사
					관리시스템의 운용, 공지사항의 전달 등<br> 5. 보유기간 및 이용기간: 수집된 정보는 제휴문의 및
					상담서비스가 종료되는 시점까지 보관됩니다.
				</p>
			</div>
			<button type="submit" class="submitbutton">제출하기</button>
		</form>
	</div>

	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
