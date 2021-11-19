<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">



<title>자취는 꿀템</title>



<style>


.third_menu {
color: rgb(135, 211, 124);
}
.outer {
	width: 1000px;
	margin: auto;
	margin-bottom: 3%;
	font-family: 'Nanum Gothic', sans-serif;
}

.wrap h2 {
	height: 100px;
	font-size: 26px;
	display: flex;
	align-items: center;
	border-bottom: 2px solid rgb(30, 29, 41);
}

.wrap h2>span {
	color: rgb(255, 80, 88);
	font-size: 1rem;
	margin-left: 2rem;
}

ol, ul {
	list-style: none;
}

.inputBox {
	display: flex;
	padding: 2rem 0px;
	border-bottom: 1px solid rgb(220, 219, 228);
}

.inputBox1_img {
	flex-grow: 1;
}

.inputBox_title {
	width: 200px;
}

.inputBox_title>span {
	color: rgb(255, 80, 88);
}

.imgFlex {
	display: flex;
	justify-content: space-between;
}

.imgFlex>div {
	width: 240px;
	height: 240px;
	/* border: 1px solid rgb(220, 219, 228); */
	border: 1px solid rgb(195, 194, 204);
}

.preview {
	position: relative;
	cursor: pointer;
	text-align: center;
	line-height: 20px;
	/* margin-right: 20px; */
}

.preview>img {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
}

.files {
	display: hidden;
}

.files>input {
	display: hidden;
	width: 0px;
	height: 0px;
}

#previewImg1, #previewImg2, #previewImg3 {
	position: absolute;
	transform: translate(-50%, -50%);
	left: 50%;
	top: 38%;
	width: 50px;
	height: 50px;
}

.preview>div {
	position: absolute;
	transform: translate(-50%, -50%);
	width: 80px;
	left: 50%;
	top: 57%;
}

.warningMsg {
	margin-top: 1.5rem;
	color: rgb(74, 164, 255);
	line-height: 1.5;
	font-size: 14px;
}

input:not ([type="file"] ):not ([type="radio"] ) {
	height: 3rem;
	padding: 0px 1rem;
}

.titleInputBox {
	display: flex;
	align-items: center;
	flex-grow: 1;
	justify-content: space-between;
}

#titleInput {
	width: 85%;
}

.categoryInputBox {
	flex-grow: 1;
}

#category {
	height: 3rem;
	padding: 0px 1rem;
	width: 30%;
}

#category option {
	padding: 3px 0;
}

.postcodifyBox {
	display: flex;
	align-items: center;
	padding-bottom: 10px;
	width: 50%;
}

#postcodify_search_button {
	height: 3.3rem;
	width: 6.5rem;
	text-align: center;
	border: 1px solid rgb(195, 194, 204);
	margin-left: 1rem;
	border-radius: 2px;
	background-color: transparent;
	cursor: pointer;
	outline: none;
	flex-grow: 1;
}

.postcodify_postcode5 {
	flex-grow: 1;
}

.postcodify_address, .postcodify_details {
	width: 100%;
}

.postcodify_details {
	margin-bottom: -10px;
}

.conditionInputBox {
	display: flex;
	align-items: center;
	width: 50%;
	justify-content: space-between;
}

.conditionLabel {
	font-size: 20px;
}

.priceInputBox {
	display: flex;
	align-items: center;
	width: 40%;
}

.priceInputBox>input {
	flex-grow: 1;
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

.descriptionInputBox>div {
	display: flex;
	justify-content: end;
}

.waringAndCounterBox {
	display: flex;
}

/* 페이지 이동버튼 수정하기 목록이동 */
.pageButton {
	width: 100%;
	display: flex;
	justify-content: flex-end;
	padding-top: 50px;
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
	color:white;
}

#cancel {
	width: 30px;
	height: 30px;
	background-position: center center;
	background-repeat: no-repeat;
	background-size: 12px 12px;
	background-color: rgba(30, 29, 41, 0.32);
	background-image: url(<%= request.getContextPath ()%>/resources/images/cancel2.png);
	border: 0px;
	border-radius: 50%;
	position: absolute;
	top: 0.5rem;
	right: 0.5rem;
	z-index: 10;
	cursor: pointer;
}

#cancel:focus, #cancel:active {
	border: 0px;
}

.inputBox input:not([type=file]){
	padding: 1rem 1rem;
}

 
</style>
</head>
<body>

	<!-- 메뉴바 -->
	<jsp:include page="/WEB-INF/views/common/menubar.jsp" />

    <div class="outer">
        <div class="wrap">
            <form method="post" method="post" action="${ contextPath }/marketplace/insert"
					enctype="multipart/form-data">
            <h2>상품정보
            <span>*필수항목</span>
            </h2>
            <div class="inputList">
                <div class="inputBox1 inputBox">
                    <div class="inputBox_title">
                        상품이미지
                        <span>*</span>
                    </div>
                    
                    <div class="inputBox1_img">
                        <div class="files" style="width: 0px; height: 0px; display: hidden;">
                            <input required oninvalid="test();" type="file" id="thumbnail" name="thumbnail" class="imgUpload" accept="image/gif,image/jpeg,image/png">
                            <input type="file" id="subimg1" name="contentImg1" class="imgUpload" accept="image/gif,image/jpeg,image/png">
                            <input type="file" id="subimg2" name="contentImg2" class="imgUpload" accept="image/gif,image/jpeg,image/png">
                        </div>
                        <div class="imgFlex">
                            <script>

                                function test(){
                                    alert("메인 이미지는 반드시 입력하셔야 합니다.");
                                    $("#preview1").focus();
                                    document.getElementById("preview1").scrollIntoView({behavior: "smooth"});
                                    document.getElementById("preview1").style.border='1px solid red';
                                }
                            </script>
								<div id="preview1" class="preview">
									<img id="previewImg1"
										src="${contextPath }/resources/images/camera.png">
									<div>메인이미지(필수)</div>
								</div>
								<div id="preview2" class="preview">
									<img id="previewImg2"
										src="${contextPath }/resources/images/camera.png">
									<div>추가이미지(옵션)</div>
								</div>
								<div id="preview3" class="preview">
									<img id="previewImg3"
										src="${contextPath }/resources/images/camera.png">
									<div>추가이미지(옵션)</div>
								</div>

							</div>

							<div class="warningMsg">
								<b>상품이미지는 430x450에 최적화되어있습니다.</b><br> - 이미지는 등록시 정사각형으로 짤려서
								등록됩니다.<br> - 큰 이미지일경우 이미지가 깨지는 경우가 발생할 수 있습니다.<br> -
								최대 지원 사이즈는 640 X 640으로 리사이즈 해서 올려주세요.(개당 이미지 최대 10M)
							</div>
						</div>
					</div>

					<div class="inputBox2 inputBox">
						<div style="padding-top: 10px;" class="inputBox_title">
							제목 <span>*</span>
						</div>
						<div class="titleInputBox">
							<input required id="titleInput" type="text"
								placeholder="상품 제목을 입력해주세요." value="" maxlength="20"
								name="title">
							<div>
								<span id="counter">0</span>/<span>20</span>
							</div>
						</div>
					</div>

					<div class="inputBox3 inputBox">
						<div style="padding-top: 10px;" class="inputBox_title">
							카테고리 <span>*</span>
						</div>
						<div class="categoryInputBox">
							<select id="category" name="category" required>

								<option value="21">의류</option>
								<option value="22">신발</option>
								<option value="23">가방</option>
								<option value="24">뷰티/미용</option>
								<option value="25">주방용품</option>
								<option value="26">디지털/가전</option>
								<option value="27">차량/오토바이</option>
								<option value="28">기타용품</option>
							</select>
						</div>
					</div>

					<div class="inputBox4 inputBox">
						<div style="padding-top: 10px;" class="inputBox_title">
							거래지역 <span>*</span>
						</div>
						<div class="categoryInputBox">
							<div class="postcodifyBox">
								<input required placeholder="우편번호" type="text" name="address"
									class="postcodify_postcode5" onkeydown="return false;" />
								<button type="button" id="postcodify_search_button">검색</button>
							</div>
							<div class="postcodifyBox">
								<input required placeholder="도로명주소" type="text" name="address"
									class="postcodify_address" onkeydown="return false;" />
							</div>
							<div class="postcodifyBox">
								<input placeholder="상세주소 (20자이내 직접입력)" type="text"
									name="address" class="postcodify_details" maxlength=30 required />
							</div>
						</div>
					</div>

					<div class="inputBox5 inputBox">
						<div style="padding-top: 5px;" class="inputBox_title">
							상품상태 <span>*</span>
						</div>
						<div class="conditionInputBox">
							<label for="condition1" class="conditionLabel"> <input
								id="condition1" type="radio" value="중고상품" name="condition"
								checked> 중고상품
							</label> <label for="condition2" class="conditionLabel"> <input
								id="condition2" type="radio" value="최상품" name="condition">
								최상품
							</label> <label for="condition3" class="conditionLabel"> <input
								id="condition3" type="radio" value="미개봉" name="condition">
								미개봉(새상품)
							</label>
						</div>
					</div>

					<div class="inputBox6 inputBox">
						<div style="padding-top: 10px;" class="inputBox_title">
							가격 <span>*</span>
						</div>
						<div class="priceInputBox">
							<input maxlength=10 required placeholder="숫자만 입력해주세요." id="price"
								type="text" name="price"><span
								style="font-size: 20px; margin-left: 10px;">원</span>
						</div>
					</div>

					<div class="inputBox7 inputBox">
						<div style="padding-top: 10	px;" class="inputBox_title">
							상품설명 <span>*</span>
						</div>
						<div class="descriptionInputBox">
							<textarea placeholder="상품 설명을 입력해주세요." id="description"
								name="content" required></textarea>
						

							<div id="waringAndCounterBox">
								<div>
									<span id="warningText"></span>
								</div>
								<div>
									<span id="counter2" style="margin-left: 20px;">0</span>/<span>4000</span>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- 페이지 이동버튼 수정하기 목록이동-->
				<div class="pageButton">
					<button class="listBtn" type="button"
						onclick="location.href='${contextPath}/marketplace/list'">목록으로</button>
					<button class="updateBtn">등록하기</button>
				</div>
			</form>
		</div>

	</div>

	<!-- 푸터 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp">
		<jsp:param name="pName" value="test" />
	</jsp:include>

	<script>
        document.getElementById("price").addEventListener('keyup',function(event){
            let regExp =/^[0-9]+$/;
            let numberCheck=document.getElementById("price").value;

            if(!(regExp.test(numberCheck)||event.key=='Backspace')){
                alert('숫자만 입력하세요');
                document.getElementById("price").value="";
            } 
         });
    </script>

	<script>
        let preview = document.querySelectorAll(".preview");
        let imgUpload = document.querySelectorAll(".imgUpload");


        for (let i = 0; i < 3; i++) {
            preview[i].addEventListener('click', function (event) {
                imgUpload[i].click();
            });

            imgUpload[i].addEventListener('change', function () {
                document.getElementById("preview1").style.border='1px solid rgb(195, 194, 204)'
                if (this.files && this.files[0]) {
                    let reader = new FileReader();

                    reader.onload = function () {
                        // result : 파일의 컨텐츠

                        // div에 이미지 넣기
                        let k=i+1;
                        preview[i].innerHTML = '<img id="img'+k+'" src="' + reader.result + '">'+'<button type="button" id="cancel" onclick="deleteImage'+k+'(event);"></button>';
                    }
                    reader.readAsDataURL(this.files[0]);
                }
            });
        }

    </script>

	<script>
		function deleteImage1(event){
			
			document.getElementById("thumbnail").value = "";
			document.getElementById("img1").remove();

			document.getElementById("preview1").innerHTML='<img id="previewImg1" src="${contextPath }/resources/images/camera.png">'
            +'<div>메인이미지(필수)</div>';
			
            $("#thumbnail").prop("required" , true);
            event.stopPropagation();
			
			
		}
		function deleteImage2(event){
			
			document.getElementById("subimg1").value = "";
			document.getElementById("img2").remove();

			document.getElementById("preview2").innerHTML='<img id="previewImg2" src="${contextPath }/resources/images/camera.png">'
            +'<div>추가이미지(옵션)</div>';
			event.stopPropagation();
		}
		function deleteImage3(event){
			
			document.getElementById("subimg2").value = "";
			document.getElementById("img3").remove();

			document.getElementById("preview3").innerHTML='<img id="previewImg3" src="${contextPath }/resources/images/camera.png">'
            +'<div>추가이미지(옵션)</div>';
			event.stopPropagation();
		}
		
	
	</script>


	<script>
        $(document).ready(function () {
            $("#titleInput").keyup(function () {
                // 현재 요소의 값의 길이 알기
                let inputLenth = $(this).val().length;
                $("#counter").text(inputLenth);

                if($(this).val().length > 20) {
                    $(this).val($(this).val().substring(0, 20));
                    $('#counter').text("20");
                }
            });

            $("#description").keyup(function () {
                // 현재 요소의 값의 길이 알기
                let inputLenth = $(this).val().length;
                $("#counter2").text(inputLenth);

                if($(this).val().length > 4000) {
                    $(this).val($(this).val().substring(0, 4000));
                    $("#warningText").text("4000자를 초과하여 입력 하실 수 없습니다.");
                    $("#warningText").css('color','red');
                    $('#counter2').text("4000");
                } else {
                    $("#warningText").text("");
                }
            });
        });
    </script>

	<script>
        $(function() {
            $("#postcodify_search_button").postcodifyPopUp();
        });
    </script>

	
</body>
</html>