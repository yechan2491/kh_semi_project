<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
        .main_outer{
            width: 1280px;
            margin: auto;
            margin-top: 20px;
            margin-bottom: 100px;
            font-family: 'Nanum Gothic', sans-serif;
        }

        .main_header{
            width: 1280px;
            height: 500px;
            display: flex;
        }

        .main_body{
            width: 1250px;
            margin-top: 50px;
        }

      .blank{
         height: 50px;
      }

        #best{
            width: 80%;
            height: 100%;
       
        }

      
        #best_header{
            display: flex;
         z-index:3;
         position: relative;         
        }

        #best_btn{
            width: 150px;
            height: 55px;
            font-size: 20px;
            font-family: 'Nanum Gothic', sans-serif;
            border-radius: 5px;
            border-color: white;
            background-color: rgb(135, 211, 124);
            cursor: pointer;
         margin-top: 415px;
         margin-left: 795px;
         z-index: 15;
         color: white;        
        }
        
       #best_btn:hover {
         background-color: #949494;
         border: none;
      }
      
      #best_btn:active {
         background-color: rgb(135, 211, 124);
         position: relative;
         top: 1px;
      }

        #best_img{
           position: absolute;
            width: 100%;
            height: 500px;
            justify-content: center;
            align-items: center;
            display: inline-block;
            z-index: -1;
        }
        
        #best_img img{
            width: 100%;
            display: inline-block;
            height: 100%;
            overflow: hidden;
          object-fit: cover;
          border-radius: 5px 5px;
        }


      
        #best_title{
             position: absolute;
            font-family: 'Nanum Gothic', sans-serif;
            font-size: 40px;
            font-weight: border;
            z-index:10;
            margin-left: 30px;
            margin-top: -100px;
            color: white;    
        }
        


        #ad{
            width: 20%;
            height: 100%;
            margin-left: 20px;  
        }
        #tip_title, #market_title{
            width: 99%;
            height: 60px;
            margin: 5px;
            display: flex;
            position: relative;         
        }

        #title_label{
            position: absolute;
            bottom: 5px;
            font-size: 20px;
            font-weight: 800;
        }

        #more{
            position: absolute;
            text-decoration: none;
            font-size: 18px;
            bottom: 5px;
            right: 1px;
            color: rgb(135, 211, 124);
            font-weight: 800;
        }

        #more:hover{
            font-weight: 800;
        }

        #tip, #market{
            width: 100%;
            height: 300px;
            display: grid;
         grid-template-columns: 24.1% 24.1% 24.1% 24.1%;
         column-gap: 2%;
         row-gap: 10%;
        }

        #board_layout{
            cursor: pointer;
        }

        #board_img{
           weight: 100%;
           height: 250px;
           overflow: hidden;
           border-radius: 5px 5px;
             

        }

        #board_img img{      
         weight: 100%;
         object-fit: cover;         
        }

        #board_title{
            font-family: 'Nanum Gothic', sans-serif;
            font-size: 18px;
            font-weight: 900;
            margin: 10px 10px;
            text-align: left;
        }
        
        #profile {
           position: absolute;
         z-index:10; 
         width:20px;   
         height:20px; 
         margin-top: -30px;    
         margin-left: 30px;
        }
        
        #profile img {
           width: 100%;
            height: 100%;
          object-fit: cover;
          border-radius: 50%;
        }
        #name {
             position: absolute;
           color: white;
           z-index:10; 
           font-size: 15px;
           margin-top: -32px;    
         margin-left: 60px;
        }
        
        #category {
           position: absolute;
           color: white;
           z-index:10; 
              font-size: 18px;
              margin-top:355px;
              margin-left: 35px;
        }
        
        #board_profile {
           position: absolute;
         z-index:10; 
         width:20px;   
         height:20px; 
         margin-top:-10px;    
         margin-left:20px;
        }
        
        #board_profile img {
           width: 100%;
            height: 100%;
          object-fit: cover;
          border-radius: 50%;
        }
        #board_name {
             position: absolute;
           z-index:10; 
           font-size: 15px;
           margin-top: -12px;    
         margin-left: 50px;
        }
        
        #no_best{
           display: flex;
          height: 100%;
          align-items: center;
          justify-content: center;
        }
    </style>

<title>자취는 꿀팁</title>
</head>
<body>

   <%@ include file="/WEB-INF/views/common/menubar.jsp"%>

   <div class="main_outer">
        <div class="main_header">
           <c:choose>
              <c:when test="${ empty today.btitle }">
                 <div id="best">
                    <div id="no_best">
                       <h2>오늘의 게시물이 없습니다.</h2>
                    </div>
                 </div>
              </c:when>
              <c:otherwise>
                 <div id="best">
                   <div id="best_header">
                      <div id="category">
                         <c:choose>
                            <c:when test="${ today.btype eq '1'}">자취는 꿀팁</c:when>
                            <c:when test="${ today.btype eq '2'}">자취는 꿀템</c:when>
                            <c:when test="${ today.btype eq '3'}">자취는 꿀친</c:when>
                         </c:choose>
                  </div>
                          <div id="best_img">
                             <img src="${ contextPath }${ today.filePath }${ today.changeName }">
                          </div>
                       <div id="best_right">
                         <button id="best_btn" onclick="todayView(${today.bid}, ${today.btype})">보러가기</button>
                       </div>
                   </div>
                   <div id="best_title">
                      ${ today.btitle }
                   </div> 
                   <div id="profile">
                     <img src="${ contextPath }${ today.profilePath }"> 
                  </div>
                  <div id="name">${ today.nickname }</div>
               </div>
              </c:otherwise>
            </c:choose>
            <div id="ad">
            <%@ include file="/WEB-INF/views/common/mainad.jsp"%>
            </div>
        </div>
        <div class="main_body">
            <div id="tip_title">
                <label id="title_label">최근 핫 한 자취 생활 정보글</label>
                <a href="${ contextPath }/info/list" id="more">더 보기</a>
            </div>
            <div id="tip">
               <c:forEach var="board" items="${ infoList }">
                   <div id="board_layout" onclick="infoView(${board.bid})">
                       <div id="board_img">
                           <img src="${ contextPath }${ board.filePath }${ board.changeName }">
                       </div>
                       <div id="board_title">
                           <c:choose>
                        <c:when test="${fn:length(board.btitle) gt 14}">
                           ${fn:substring(board.btitle, 0, 14)}...
                        </c:when>
                        <c:otherwise>
                           ${ board.btitle }
                        </c:otherwise>
                     </c:choose>
                       </div>
                       <div id="board_profile">
                           <img src="${ contextPath }${ board.profilePath }"> 
                       </div>
                       <div id="board_name">${ board.nickname }</div>

                   </div>
               </c:forEach>
            </div>
            <div class="blank"></div>
            <div id="market_title">
                <label id="title_label">주목받는 중고 자취 매물들</label>
                <a href="${ contextPath }/marketplace/list" id="more">더 보기</a>
            </div>
            <div id="market">
            <c:forEach var="board" items="${ marketList }">
                   <div id="board_layout" onclick="marketView(${board.bid})">
                       <div id="board_img">
                           <img src="${ contextPath }${ board.filePath }${ board.changeName }">
                       </div>
                       <div id="board_title">
                           <c:choose>
                        <c:when test="${fn:length(board.btitle) gt 14}">
                           ${fn:substring(board.btitle, 0, 14)}...
                        </c:when>
                        <c:otherwise>
                           ${ board.btitle }
                        </c:otherwise>
                     </c:choose>
                       </div>
                        <div id="board_profile">
                           <img src="${ contextPath }${ board.profilePath }"> 
                       </div>
                       <div id="board_name">${ board.nickname }</div>
                   </div>
               </c:forEach>
            </div>
        </div>
    </div>
    
    <%@ include file="/WEB-INF/views/common/footer.jsp"%>
    
    <script>
       function todayView(bid, btype){
          if(btype == 1)
             location.href='${contextPath}/info/detail?bid='+bid;
          else if(btype == 2)
             location.href='${contextPath}/marketplace/detail?bid='+bid;
          else if(btype == 3)
             location.href='${contextPath}/friend/detail?bid='+bid;
      }
       
      function infoView(bid){
         location.href='${contextPath}/info/detail?bid='+bid;
      }
      
      function marketView(bid){
         location.href='${contextPath}/marketplace/detail?bid='+bid;
      }
   </script>
    
</body>
</html>