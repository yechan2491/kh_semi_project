<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Page Title</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/header_style.css" >
<style>
.Rbackground {
font-family: 'Nanum Gothic', sans-serif;
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100vh;
	background-color: rgba(0, 0, 0, 0.3);
	z-index: 1000000;
	/* 숨기기 */
	z-index: -1;
	opacity: 0;
}

.Rshow {
	opacity: 1;
	z-index: 1000;
	transition: all 0.5s;
}

.window {
	position: relative;
	width: 100%;
	height: 100%;
}

.popup {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background-color: #ffffff;
	box-shadow: 0 2px 7px rgba(0, 0, 0, 0.3);
	/* 임시 지정 */
	width: 450px;
	height: 620px;
	/* 초기에 약간 아래에 배치 */
	transform: translate(-50%, -40%);
}

.Rshow .popup {
	transform: translate(-50%, -50%);
	transition: all 0.5s;
}


      .Rshow .popup {
        transform: translate(-50%, -50%);
        transition: all 0.5s;
      }
        
        .text_area {
            margin-left: 70px;
            resize: none;
            width: 300px;
            height: 230px;
            border-radius: 3px;
            padding: 10px 10px;
            border: 1px solid lightgray;
            font-family: 'Noto Sans KR', sans-serif;
            margin-top: 15px;
        }
        .select_reason {
            /* padding: 5px 5px; */
            border: 1px solid lightgray;
            width: 170px;
            border-radius: 3px;
            margin-left: 40px;
            height: 30px;
        }
        .btns > * {
            width: 100px;
            border: none;
            font-size: 15px;
            border-radius: 5px;
            cursor: pointer;
            padding: 10px 10px;
        }
        .Rreport_btn{
            
            background: rgb(135, 211, 124);
            color:#FFF;
            
            
        }
        .Rcancel_btn{
            
            border: 1px solid rgb(135, 211, 124);
            color:rgb(135, 211, 124);
            background-color: white;
            
        }
        .write_report>div {
            display: flex;
            margin-bottom: 15px;
            
        }
        
        
        .write_report >div> p{
            position: absolute;
            margin-left: 180px;
        }
        
        
        
        .report_h4 {
            margin-left: 75px;
              overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
        }
        
        .btns {
            justify-content: center;
        }
        
        .btns > *{
            margin: 0 15px;
        }

        .report_title {
            text-align: center;
            margin: 20px auto;
        }
             .pTag {
                 overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  width:200px;
        }
    </style>
  </head>
  <body>
  </head>
  <body>
   
   <div class="Rbackground">
      <div class="window">
                <form id="replyReportForm" name="replyReportForm" method="post" action="${contextPath }/report/reply">
                <input type="hidden" name="bid" value="${ board.bid }">
                <input type="hidden" name="bwriter" value="${ board.bwriter }">
        <div class="popup">
            <div class="write_report">

                <h2 class="report_title">신고하기</h2>
                <div>
                    <h4 class="report_h4">댓글 작성자</h4>
                    <p class="pTag" id="RnickName"></p>
    
                </div>
                <div>
    
                    <h4 class="report_h4">댓글 내용</h4>
                    <p class="pTag" id="Rcontent"></p>
                </div>
                <div>
                    <h4 class="report_h4">신고 사유</h4>
                   
                    <select class="select_reason" name="select_reason">
                        <option>
                            신고사유를 선택하세요.
                        </option>
                        <option>
                            불법 게시물 (도박/사행성 등)
                        </option>
                        <option>
                            욕설 및 비하발언
                        </option>
                        <option>
                            부적절한 홍보성 게시물
                        </option>
                        <option>
                            명예훼손/사생활 침해 및 저작관 침해 등
                        </option>
                        <option>
                            기타
                        </option>
                    </select>
                </div>
                <div class="hiddenAid"></div>
                    
                <textarea class="text_area" name="detail_reason" placeholder="내용을 입력해주세요."></textarea>
                
                <br><br>
                <div class="btns">
                    <button type="reset" class="Rcancel_btn">취소하기</button><button type="submit" class="Rreport_btn">신고하기</button>
                </div>
            </div>
        </div>
                </form>
      </div>
    </div>


</body>
</html>