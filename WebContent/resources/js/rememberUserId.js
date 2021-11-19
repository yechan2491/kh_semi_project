// userId input 태그
const userId = document.querySelector("#userId");

const remember = document.querySelector("#remember");

let rememberId = getCookie("rememberId");

userId.value = rememberId;

if(userId.value != "")
	remember.checked = true;

remember.addEventListener('change',function(){
	if(this.checked){
		setCookie("rememberId", userId.value, 7)
	}
	else{
		deleteCookie("rememberId");
	}
});

userId.addEventListener('keyup',function(){
	if(remember.checked){
		setCookie("rememberId", userId.value, 7);
	}
})

/* 쿠키 저장 (쿠키 이름, 값, 유효 일자) */
function setCookie(cookieName, value, exdays){	
	
	// 현재 시간 + 유효 일자 더해서 일자 설정
    let exdate = new Date();
    exdate.setDate(exdate.getDate() + exdays);

    // escape() 함수 : 알파벳과 숫자 및 * , @, - , _ , + , . , / 를 제외한 문자를 모두 16진수 문자로 변경
    // 쉼표와 세미콜론 같은 문자가 쿠키문자열과의 충돌을 피하기 위해 사용
    // 전달 받은 값 escape + 유효일자 있을 경우 더하기
    let cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
    
    // name=value 형식으로 쿠키 저장
    document.cookie = cookieName + "=" + cookieValue;
}

/* 쿠키 삭제 (쿠키 이름) */
function deleteCookie(cookieName){
	// 쿠키 만료 기간을 하루 전으로 설정하여 저장함으로써 자동 삭제 되도록 함
	let expireDate = new Date();
    expireDate.setDate(expireDate.getDate() - 1);
    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
}

/* 쿠키 가져오기 (쿠키 이름) */
function getCookie(cookieName) {
    cookieName = cookieName + '=';
    let cookieData = document.cookie;
    // 전달받은 쿠키 이름으로 전체 쿠키 값에서 시작 위치 구하기
    let start = cookieData.indexOf(cookieName);
    let cookieValue = '';
    // 해당 이름을 가진 쿠키가 존재한다면
    if(start != -1){
    	// 쿠키 이름 이후가 value이므로 start 위치 변경
        start += cookieName.length;
        // start 위치부터 ;의 위치 구하기
        let end = cookieData.indexOf(';', start);
        // ;의 위치를 구하지 못했다면 맨 끝에 있으므로 현재 cookieData의 길이가 end 
        if(end == -1)end = cookieData.length;
        // start에서 end까지를 잘라내면 cookieValue
        cookieValue = cookieData.substring(start, end);
    }
    // unescape() 함수 : escape 함수로 이스케이핑 했던 값을 다시 원래 값으로 변경
    // 저장 시 escape 했으므로 unescape 하여 반환
    return unescape(cookieValue);
}