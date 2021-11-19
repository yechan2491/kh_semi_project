<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

        .admin_nav>li:nth-child(3) {
            border-bottom: 3px solid rgb(135, 211, 124);
            padding-bottom: 3px;
        }


        .wrap {
            width: 1000px;
            margin: 50px auto;
        }

        ul,
        li {
            margin: 0;
            padding: 0;
        }



        .report_list {

            margin: 20px 30px;
            margin-top: 50px;
            min-height: 150px;


        }

        .report_list>ul {
            border-radius: 5px;
            border-bottom: 1px solid #f3f5f7;
            height: 50px;
            line-height: 50px;
            display: flex;
            justify-content: space-around;
            list-style: none;
        }

        .board_header {
            background: #f3f5f7;
            color: #000;
            font-weight: bold;
        }

        .report_list>ul.last {
            border: 0;
        }

        .report_list>ul>li {
            text-align: center;
        }

        .report_list .id {
            width: 60px;
        }

        .report_list .category {
            width: 60px;
        }

        .report_list .title {
            width: 60px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }

        .report_list .writer {
            width: 80px;
        }

        .report_list .read {
            width: 300px;
        }

        .report_list .date {
            width: 100px;
        }


        .basic {
            border: 1px lightgray solid;
            border-radius: 5px;
            height: 30px;
            margin-bottom: 10px;
            width: 200px;

        }

        .search ,.suspension {
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
   

        .search_member {
            margin-bottom: 90px;
        }
        
        .select {
        border: 1px lightgray solid;
            border-radius: 5px;
            height: 30px;
            margin-bottom: 10px;


            width: 92px;
            }
    </style>

</head>


<%@ include file="/WEB-INF/views/common/menubar.jsp"%>


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
        <h1>회원 정보 조회 및 관리</h1>

        <div class="report_list">
            <ul class="board_header">
                <li class="id">회원ID</li>
                <li class="title">이름</li>
                <li class="writer">닉네임</li>
                <li class="read">이메일주소</li>
                <li class="date">가입일</li>
                <li class="date">관리</li>
            </ul>
            <ul class="board_ul">
                <li class="id"></li>
                <li class="title"></li>
                <li class="writer"></li>
                <li class="read">회원정보를 검색해주세요.</li>
                <li class="date"></li>
                <li class="date"></li>
            </ul>

        </div>
        <div class="search_member">
        	<select class="select" name="searchCondition" class="searchCondition">
        		<option value="userNo">회원번호</option>
        		<option value="nickName">닉네임</option>
        	</select>
            <input class="basic" id="searchValue" name="searchValue" type="text">
            <button class="search" onclick="addReply();">조회</button>
        </div>
    </div>

</div>
 <script>
      function addReply() {
         $.ajax({
            url : "${ contextPath }/userSearch",
            type : "post",
            dataType : "json",
            data : { searchCondition : $(".select").val(), searchValue : $("#searchValue").val() },
            success : function(data) {
               console.log(data);
               
               var html = '';
               
               if(data) {       
                  html = '<li class="id">' + data.userId +
						  '</li><li class="title">'+ data.userName +
					      '</li><li class="writer">'+ data.nickName +
					      '</li><li class="read">'+ data.email +
					      '</li><li class="date">'+ data.enrollDate +
					      '<li class="date"><button class="suspension" onclick="kickOut('+data.userNo+');">회원정지</button></li>'
               }else {
            	  alert('조회된 회원이 없습니다.');
               }
               
             
               $(".board_ul").html(html);    
            },
            error : function(e) {
            	alert('조회된 회원이 없습니다.');
            	
            	html = '<li class="id"></li>'+
                    '<li class="title"></li>'+
                    '<li class="writer"></li>'+
                    '<li class="read">회원정보를 검색해주세요.</li>'+
                    '<li class="date"></li>'+
                    '<li class="date"></li>';
                    
            	$(".board_ul").html(html); 
               
            }
         });
      };
   </script>
   <script>
   function kickOut(userNo){
	   location.href="${ contextPath }/kickOut?userNo="+userNo;
   }
   </script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>

</body>

</html>