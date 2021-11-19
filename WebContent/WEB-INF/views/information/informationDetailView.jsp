<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자취는 템빨</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
   href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
   rel="stylesheet">
<style>

.second_menu {
color: rgb(135, 211, 124);
}
.outer {
   width: 1280px;
   margin: auto;
   margin-bottom: 2%;
   font-family: 'Nanum Gothic', sans-serif;
}

.wrap {
   width: 1240px;
   margin: auto;
}

.gray {
   color: gray;
}

.board_title {
   /*     border-bottom: 1px solid #282A35;   */
   
}

.board_content {
   padding: 0px 20px;
}

.board_content .subject {
   line-height: 35px;
   display: grid;
   grid-template-columns: 100px 100px 700px 280px;
   border-bottom: 1px solid #f3f5f7;
   text-align: right;
}

.board_content .subject span:nth-child(3) {
   grid-area: 1/4/1/5;
}

.board_content .subject span:nth-child(4) {
   grid-area: 2/4/2/5;
}

.board_content .subject span:nth-child(5) {
   grid-area: 3/4/3/5;
}

/* .board_content .subject span:nth-child(6) {
   grid-area: 4/4/4/5;
}
 */
.board_content .content {
   height: 500px;
   overflow: auto;
   margin-bottom: 30px;
}

.title_span {
   background-color: gray; /* #282A35; */
}

.board_area button {
   width: 100px;
   height: 35px;
   border: 0px;
   color: white;
   background: rgb(135, 211, 124);
   margin: 5px;
   cursor: pointer;
   border-radius: 10px;
}

.board_area button:hover {
   background: #0bd;
}

.photoList {
   text-align: center;
}

.photoList img {
   margin: 10px;
   width: 100%;
   pointer-events: none;
}

.btn_area {
   text-align: center;
   /* border-top: 1px solid #282A35; */
   border-top: 1px solid gray;
   padding: 30px;
}

.reply_write>.reBtn {
   background: silver;
   margin-left: 10px;
}

.wbtn {
   width: 80px;
   height: 40px;
   background: rgb(135, 211, 124);
   border-radius: 10px;
   border: none;
   color: white;
   cursor: pointer;
   display: inline-block;
   justify-content: flex-end;
}

.url {
   display: flex;
   justify-content: flex-end;
   color: gray;
   text-decoration: none;
}

.url:hover {
   text-decoration: underline;
}

.reply_write {
   display: flex;
   justify-content: center;
   align-items: center;
   padding: 20px;
   margin-bottom: 30px;
}

.reply_content {
   width: 100%;
   height: 50px;
   resize: none;
   border: 0.5px solid #gray;
}

.reply_content:focus {
   border: 0.5px solid #0bd;
   outline: none;
}

.reply_ul {
   display: flex;
   justify-content: center;
   list-style: none;
   padding: 5px;
}

.reply_ul .rwriter {
   margin-left: 60px;
   width: 150px;
}

.reply_ul .rcontent {
   width: 900px;
   height : 50px;
}

.reply_ul .rdate {
   width: 400px;
}

.replyProfileBox {
   display: flex;
   margin-right: 15px;
   margin-left: 15px;
}

.replyProfile {
   border-radius: 50%;
   width: 48px;
   height: 48px;
}

.reply_list {
   width: 100%;
}

.cname {
   margin-top: 15px;
}

.good {
   margin-top: 55px;
   margin-right: 15px;
}

/* .pp{
   display: flex;
   background-color:red;
   justify-content: space-between;
   margin-right: 20px;
   margin-left: 50px;
} */


.replyimg{
   display: flex;
   /* justify-content: flex-end; */
   justify-content: space-between;
   width: 30px; 
   height: 25px;
}

.commentReport>img{   
   margin-top: 0px;
   margin-right: 120px;
   margin-left: -100px;
}



.commentReportBox {
   display: flex;
   margin-bottom: 20px;
   justify-content: flex-end;
}



.commentDelete {
   display: flex;
   align-items: center;
   justify-content: space-around;
   background-color: white;
   cursor: pointer;
   margin-left: -140px;
   margin-right:140px;
}

</style>
</head>
<body>


   <!-- 메뉴바 -->
   <jsp:include page="/WEB-INF/views/common/menubar.jsp">
      <jsp:param name="pName" value="test" />
   </jsp:include>



   <div class="outer">
      <div class="wrap">
         <div class="board_area">
            <div class="board_title">
               <h4>
                  <span class="title_span"><p class="gray cname">${ board.cname }
                     </p></span>
               </h4>


               <h1>
                  <span class="title_span"><p>${ board.btitle }</p></span>
               </h1>
               <br>


            </div>
            <div class="board_content">
               <div class="subject">



                  <span class="gray"> 조회수 : ${ board.bcount } </span> <span
                     class="gray"> </span>
               <!-- 좋아요 : ${ board.likeCount } -->
                  <!-- <span><a >URL 복사</a></span> -->

                  <span class="gray"> 작성자 : ${ board.nickname } </span> <span
                     class="gray"> 작성일 : <fmt:formatDate
                        value="${ board.createDate }" type="both"
                        pattern="yyyy.MM.dd HH:mm:ss" />
                  </span> <span class="gray"> 수정일 : <fmt:formatDate
                        value="${ board.modifyDate }" type="both"
                        pattern="yyyy.MM.dd HH:mm:ss" />
                  </span> <br>
               </div>
               <br>
               <div>




                  <pre class="content"> ${ board.bcontent } </pre>

                  <!-- <a href="#"></a> -->
                 
                  <c:choose>
                  <c:when test="${  !empty loginUser and likeBid != board.bid }">
                  <a href="#"><img class="good" src="${ contextPath }/resources/images/infogood.png" onclick="location.href='${contextPath}/myscrap/insert?bid=${board.bid }'"></a>
                  </c:when>
                  <c:when test="${ !empty loginUser and likeBid == board.bid }">
                  <a href="#"><img class="good" src="${ contextPath }/resources/images/infogood2.png" onclick="location.href='${contextPath}/myscrap/delete?bid=${board.bid }'"></a>
                  </c:when>
                  <c:otherwise>
                   <a href="#"><img class="good" src="${ contextPath }/resources/images/infogood.png" onclick="loginplz();"></a>
                  </c:otherwise>
                  </c:choose>
                  
                  <%-- <a href="#" onclick=bad();><img src="${ contextPath }/resources/images/infobad.png"></a>  --%>
                 <c:choose>
                 <c:when test="${ !empty loginUser }">
                  <img class="report_img" src="/kh_semi/resources/images/notify.png" id="show">
                  <%@ include file="/WEB-INF/views/common/reportModal.jsp"%>
                 </c:when>
                 <c:otherwise>
                  <img class="report_img" src="/kh_semi/resources/images/notify.png" onclick="loginplz();">
                 </c:otherwise>
                 </c:choose>
                 
                  <span><a href="#" class="url"
                     onclick="clip(); return false;">URL 복사</a></span> <br>
               </div>


               <div class="reply_area">
                  <h4>
                     <span class="title_span">&nbsp;</span> 댓글
                  </h4>
                  

                  <div class="reply_write">
                     <textarea class="reply_content" maxlength="50"></textarea>
                     <button class="reBtn" onclick="addReply(${ board.bid });">댓글등록</button>
                  </div>


                  <div class="reply_list">
                     <c:forEach items="${ board.replyList }" var="reply">
                        <ul class="reply_ul">
                           <li class="rwriter">${ reply.nickName }</li>
                           <li class="rcontent">${ reply.acontent }</li>
                           <li class="rdate"><fmt:formatDate
                                 value="${ board.modifyDate }" type="both"
                                 pattern="yyyy.MM.dd HH:mm:ss" /></li>

                              
                           <!-- 댓글 신고 -->
                           <div class="commentReportBox">
                           <c:choose>
                          <c:when test="${ !empty loginUser }">
                           <li class="Rshow" onclick="Rshow('${ reply.nickName}','${reply.acontent }',${ reply.aid },${ reply.writer });">
                                 <img class="replyimg"  src="${contextPath }/resources/images/siren2.png"> 
                           </li> 
                          </c:when>
                          <c:otherwise>
                          <li class="Rshow" onclick="loginplz();">
                                 <img class="replyimg"  src="${contextPath }/resources/images/siren2.png"> 
                           </li> 
                          </c:otherwise>
                           </c:choose>
                           <!-- 댓글 삭제 -->                           
                           <c:if test="${ loginUser.userNo == reply.writer }">
                              <li class="commentDelete"
                                 onclick="deleteReply(${board.bid}, ${ reply.aid });">
                                 <img class="replyimg"
                                   src="${contextPath }/resources/images/infodeleteReply2.png"
                                   ></li> 
                                                                                                                                             
                           </c:if>   
                           </div>
                        </ul>
                     </c:forEach>
                     
                  </div>

 <!--  src="${contextPath }/resources/images/trash.png"></li> -->   



               </div>
               <%@ include file="/WEB-INF/views/common/replyModal.jsp"%>
               <script>
    let Rshows = document.querySelectorAll("#Rshow");
    
    
    function Rshow(nick,content,aid,writer) {
        document.querySelector(".Rbackground").className = "Rbackground Rshow";
        
         $("#RnickName").text(nick);
         $("#Rcontent").text(content);
        let html = '<input type="hidden" value="'+aid+'" name="aid"><input type="hidden" value="'+writer+'" name="replyWriter">';
         $(".hiddenAid").html(html);         
          }

      function Rclose() {
        document.querySelector(".Rbackground").className = "Rbackground";
      }

      document.querySelector(".Rcancel_btn").addEventListener("click", Rclose);
      document.querySelector(".Rreport_btn").addEventListener("click", Rclose);
      
    </script>
    
               <br>



               <div class="btn_area">
                  <button onclick="location.href='${ contextPath }/info/list'">목록으로</button>
                  <c:if test="${ loginUser.userNo == board.bwriter }">
                     <button onclick="updateBoardView();">수정하기</button>
                     <button onclick="deleteBoard();">삭제하기</button>
                  </c:if>
               </div>
            </div>
         </div>
      </div>
   </div>

   <c:if test="${loginUser.userNo == board.bwriter }">
      <form name="boardForm" method="post">
         <input type="hidden" name="bid" value="${ board.bid }">
      </form>
      <script>
    function updateBoardView() {
       document.forms.boardForm.action="${contextPath}/info/updateView";
       document.forms.boardForm.submit();
             
   }
    
    
    function deleteBoard() {
      if(confirm("이 게시글을 삭제하시겠습니까?")){
         document.forms.boardForm.action = "${contextPath}/info/delete";
         document.forms.boardForm.submit();
      }
   }
   </script>
   </c:if>


   <script type="text/javascript">

   function clip(){

   var url = '';
   var textarea = document.createElement("textarea");
   document.body.appendChild(textarea);
   url = window.document.location.href;
   textarea.value = url;
   textarea.select();
   document.execCommand("copy");
   document.body.removeChild(textarea);
   alert("URL이 복사되었습니다.")
   }

</script>


   <c:choose>
      <c:when test="${ !empty loginUser }">
         <script>
      /* 댓글 달기 버튼 클릭 시 Reply 테이블에 insert 기능 수행 후 
      비동기적으로 새로 갱신 된 댓글 목록을 Reply 테이블에서 select해서 화면에 적용시키는 기능 */
      function addReply(bid) {
         $.ajax({
            url : "${contextPath}/info/insertReply",
            type : "post",
            dataType: "json",
            data : { bid : bid, acontent : $(".reply_content").val() },
            success : function (data) {
               console.log(data);   
               
               var html = '';
               
               for(var i in data){
                  html += '<ul class="reply_ul"><li class="rwriter">'
                        + data[i].nickName + '</li><li class="rcontent">'
                        + data[i].acontent + '</li><li class="rdate">'
                        + data[i].createDate + '</li><div class="commentReportBox"><li class="Rshow" onclick=Rshow("'+data[i].nickName+'","'+data[i].acontent+'","'+data[i].aid+'","'+data[i].writer+'")>'
                        +'<img class="replyimg"src="${contextPath }/resources/images/siren2.png"></li>'
                        + '<li class="commentDelete" onclick="deleteReply(${board.bid}, ${ reply.aid });"><img class="replyimg" src="${contextPath}/resources/images/infodeleteReply2.png"></li></div></ul>';
                   /*  + '<li class="commentDelete"onclick="deleteReply(${board.bid}, ${ reply.aid });"><img class="replyimg"src="${contextPath }/resources/images/infodeleteReply2.png"></li></div></ul> '; 
                          */
                     
               }
               location.reload();
               
               /* 갱신 된 replyList를 테이블에 다시 적용 */
               $(".reply_list").html(html);
               /* 댓글 작성 부분 리셋 */
               $(".reply_content").val("");
               
               
            },
            error : function (e) {
               console.log(e);
            }
         });
         //buttonelement.submit();
      }
      
      
      
      // 댓글 삭제
      function deleteReply(bid, aid){
         
         let flag=confirm("댓글을 삭제하시겠습니까?");
         if(flag==true){
         
         $.ajax({
            url : "${contextPath}/info/deleteReply",
            type : "post",
            dataType : "json",
            data : { bid : bid, aid : aid},
            success : function(data) {
                console.log(data);   
               var html = '';

               for(var i in data){
                  html += '<ul class="reply_ul"><li class="rwriter">'
                        + data[i].nickName + '</li><li class="rcontent">'
                        + data[i].acontent + '</li><li class="rdate">'
                        + data[i].createDate + '</li><div class="commentReportBox"><li class="Rshow" onclick=Rshow("'+data[i].nickName+'","'+data[i].acontent+'","'+data[i].aid+'","'+data[i].writer+'")>'
                        +'<img class="replyimg" src="${contextPath }/resources/images/siren2.png"></li>'
                        + '<li class="commentDelete" onclick="deleteReply(${board.bid}, ${ reply.aid });"><img class="replyimg" src="${contextPath}/resources/images/infodeleteReply2.png"></li></div></ul>';
                       /* + '<li class="commentDelete"onclick="deleteReply(${board.bid}, ${ reply.aid });"><img class="replyimg"src="${contextPath }/resources/images/infodeleteReply2.png"></li></div></ul> ';
                         */
                         
                         
                         /* +'<script>$(".replyimg").hover(function(){$(this).attr("src", $(this).attr("src").replace("2.png", ".png"));}, function(){$(this).attr("src", $(this).attr("src").replace(".png", "2.png"));});</'
                        +'script>; */
               
               }
               location.reload();
               
               /* 갱신 된 replyList를 테이블에 다시 적용 */
               $(".reply_list").html(html);
               /* 댓글 작성 부분 리셋 */
               $(".reply_content").val("");
            },
            error : function(e) {
               console.log(e);
         }
         
      });}}
      

   </script>
   
<script>
      $(".replyimg").hover(function(){
           $(this).attr("src", $(this).attr("src").replace("2.png", ".png"));
       }, function(){
           $(this).attr("src", $(this).attr("src").replace(".png", "2.png"));
       });  
      
   </script> 
   

   
   
   
   
   
   
   
   
   
      </c:when>
      <c:otherwise>
         <script>
            function addReply(bid){
               alert('로그인 후 이용 가능합니다');
               location.href='${contextPath}/login';
            }
         </script>
      </c:otherwise>
   </c:choose>



<script>
   function loginplz(){
      if(confirm('로그인이 필요한 서비스입니다. 로그인 하시겠습니까?')){
         location.href='${contextPath}/login';
      }
   }
</script>











   <!-- 푸터 -->
   <jsp:include page="/WEB-INF/views/common/footer.jsp">
      <jsp:param name="pName" value="test" />
   </jsp:include>


</body>
</html>