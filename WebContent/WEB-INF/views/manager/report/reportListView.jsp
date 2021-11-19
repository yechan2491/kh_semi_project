<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

        .admin_nav>li:nth-child(1) {
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
            min-height: 565px;


        }

        .report_list>ul {
            border-bottom: 1px solid #f3f5f7;
            height: 50px;
            line-height: 50px;
            display: flex;
            justify-content: space-around;
            list-style: none;
        }

        .report_list>ul.last {
            border: 0;
        }

        .report_list>ul>li {
            text-align: center;
        }

        .board_header {
            border-radius: 5px;
            background: rgb(135, 211, 124);
            color: white;
            font-weight: bold;
        }

        .report_list .id {
            width: 60px;
        }

        .report_list .category {
            width: 60px;
        }

        .report_list .title {
            width: 350px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }

        .report_list .writer {
            width: 100px;
        }

        .report_list .read {
            width: 60px;
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

        .board_ul {
	cursor:pointer;
}

.board_paging {
	height: 40px;
	line-height: 40px;
	display: flex;
	justify-content: center;
	list-style: none;
	width: 480px;
	margin: auto;
	margin-bottom: 50px;
}

.board_paging a {
	text-decoration: none;
	line-height: 40px;
	display: block;
	text-align: center;
	font-weight: bold;
 	margin: 0 3px; 
	width: 40px;
	height: 40px;
	text-decoration: none;
}

boadr_paging a:hover {
	cursor: pointer;
}

.board_paging a.current_page { /*현재 페이지 */
	border: 2px solid rgb(135, 211, 124);
	border-radius: 50%;
	margin-top:-3%;
}

.board_paging .pprev {
	background:
		url('<%=request.getContextPath()%>/resources/images/page_pprev.png')
		no-repeat center center;
}

.board_paging .prev {
	background: url('${contextPath}/resources/images/page_prev.png')
		no-repeat center center;
	margin-right: 7px;
}

.board_paging .next {
	background: url('${contextPath}/resources/images/page_next.png')
		no-repeat center center;
	margin-left: 7px;
}

.board_paging .nnext {
	background:	url('${contextPath}/resources/images/page_nnext.png') no-repeat center center;
	margin-right: 0;
}

.pagingcComment {
	text-align:center;
	font-weight: 900;
	margin-top: 3px;
	color: #707070;
	font-size: 15px;
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

        <div class="report_list">
            <ul class="board_header">
                <li class="id">글번호</li>
                <li class="title">신고사유</li>
                <li class="writer">작성자</li>
                <li class="read">처리</li>
                <li class="date">작성일</li>
            </ul>
           <c:forEach var="report" items="${reportList}">
            <ul class="board_ul" onclick="reportDetail(${ report.rid },'${report.rTitle }');">
                <li class="id">${ report.rid }</li>
                <li class="title">[ ${ report.rTitle } ]</li>
                <li class="writer">${ report.rnickName }</li>
                   <c:if test="${fn:contains(report.status,'N')}"><li class="read">미열람</li></c:if>
                   <c:if test="${fn:contains(report.status,'Y')}"><li class="read">열람</li></c:if>
                <li class="date">${ report.reportDate }</li>
            </ul>
          	</c:forEach>
          
        </div>
    </div>
     <ul class="board_paging">
	
	<!-- 1번 페이지 -->	
		<li><a href="${ contextPath }/friend/main?page=1${ searchParam }" class="arrow pprev"></a></li>
		<!-- 이전페이지 -->
		<li><c:choose>
				<c:when test="${ pi.page > 1 }">
					<a class="arrow prev" href="${ contextPath }/friend/main?page=${ pi.page - 1 }${ searchParam }"></a>
				</c:when>
				<c:otherwise>
					<a class="arrow prev" href="#"></a>
				</c:otherwise>
			</c:choose></li>

		<!-- 최대 5개의 페이지 목록 -->
		<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
			<li><c:choose>
					<c:when test="${ p eq pi.page }">
						<a class="current_page" href="#">${ p }</a>
					</c:when>
					<c:otherwise>
						<a href="${ contextPath }/friend/main?page=${ p }${ searchParam }">${ p }</a>
					</c:otherwise>
				</c:choose></li>
		</c:forEach>

		<!-- 다음페이지 -->
		<li><c:choose>
				<c:when test="${ pi.page < pi.maxPage }">
					<a class="arrow next" href="${ contextPath }/friend/main?page=${ pi.page + 1 }${ searchParam }"></a>
				</c:when>
				<c:otherwise>
					<a class="arrow next" href="#"></a>
				</c:otherwise>
			</c:choose></li>
			
		<!-- 마지막 페이지 -->
			<li><a class="arrow nnext" href="${ contextPath }/friend/main?page=${ pi.maxPage }${ searchParam }"></a></li>
	</ul>

</div>
 <%@ include file="/WEB-INF/views/common/footer.jsp" %>
	<script>
		function reportDetail(rid,title){
		
			if(title.substr(1,2)=='쪽지'){
				location.href='${contextPath}/report/note/detail?rid='+rid;
			} else {
				location.href='${contextPath}/report/detail?rid='+rid;
				
			}
		}
	</script>

</body>

</html>