<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            margin-top: 70px;
            text-align: center;

            font-family: 'Nanum Gothic', sans-serif;
        }

        .admin_nav>li {
            display: inline;
            font-weight: 700;
            font-size: 20px;
        }

        .admin_nav>li:not(:first-child) {
            margin-left: 100px;
        }

        .admin_nav>li:nth-child(2) {
            border-bottom: 3px solid rgb(135, 211, 124);
            padding-bottom: 3px;
        }


        
        ul,
        li {
            margin: 0;
            padding: 0;
        }
        .wrap {
            
            margin-top: 50px;
            margin-bottom: 150px;
         
        }
        .box {
            border-top: 3px solid lightgray;
            border-bottom: 3px solid lightgray;
            padding-top: 25px;
            padding-bottom: 14px;
            width: 700px;
            margin: auto;
        }

         .box_header {
            display: flex;
            margin-bottom: 10px;
            justify-content: center;
            
        }
        .box_header > h2 {
           position: absolute;
        }

        .back {
            margin-right: 650px;
        }
     

        
        
        .box p, pre, .title{
            text-align: left;
            margin-left: 20px;
            
        }

        .box p {
            font-size: 15px;
        }


        .content{
            margin-top: 20px;
            min-height: 200px;
            font-size: 14px;
            font-family: 'Nanum Gothic', sans-serif;
        }

        .writer {
            display: flex;
        }
        .back {
            left: 100px;
            border: none;
            font-size: 15px;
            border-radius: 5px;
            cursor: pointer;
            padding: 10px;
            color: #000;
            /* font-family: 'Gothic A1', sans-serif; */
            font-family: 'Nanum Gothic', sans-serif;
            /* font-family: 'Gowun Batang', serif; */
        }
      
    </style>
</head>

 <%@ include file="/WEB-INF/views/common/menubar.jsp" %>

<div class="home-content wrapper">
    <nav>
        <ul class="admin_nav">
             <li>
                     <a href="${ contextPath }/report/list">
                        신고내역
                    </a>
                </li>
                <li>
                    <a href="${ contextPath }/ad/list">
                        광고/제휴 문의
                    </a>
                </li>
                <li>
                    <a href="${ contextPath }/userSearch">
                        회원정보조회
                    </a>
                </li>
        </ul>
    </nav>
        <div class="wrap">
            <div class="box_header">
                <button class="back"  onclick="location.href='${ contextPath }/ad/list'">목록</button>
                <h2 class="detail_list">광고/배너 문의</h2>
            </div>
            <div class="box">
            <h2 class="title">${ ad.adTitle }</h2>
            <div class="writer">
                <p>${ ad.adWriter }</p>
                <p><fmt:formatDate value="${ ad.adDate }" type="both"
						pattern="yyyy년 MM월 dd일 HH:mm:ss"/></p>
                
            </div>
                <p class="content">${ad.adContent}</p>
                    <p>회사(기관)명 : ${ad.adCompany }</p>
                    <p>연락처 : ${ad.adPhone }</p>
        </div>
    </div>

</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>

</html>