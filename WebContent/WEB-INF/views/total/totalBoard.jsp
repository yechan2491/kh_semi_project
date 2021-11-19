<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<style>
		.outer {
			width: 1000px;
			margin: auto;
			
		}
		
		.wrap {
			width: 950px;
			margin: 20px auto;
		}

        .title_header{
            margin-top: 60px;
            margin-left: 55px;
        }

        #title{
        	font-family: 'Nanum Gothic', sans-serif;
            border: none;
            font-size: 30px;
            font-weight: bold;
        }

        #title:focus{
            outline: none;
        }
		
        ul, li {
        	font-family: 'Nanum Gothic', sans-serif;
			margin: 0;
			padding: 0;
		}
		
		.board_title {
			border-bottom: 1px solid #282A35;
		}
		
		.board_list {
			margin: 20px 30px;
			min-height: 565px;
		}
		
		.board_list>ul {
			border-bottom: 1px solid #f3f5f7;
			height: 50px;
			line-height: 50px;
			display: flex;
			justify-content: space-around;
			list-style: none;
		}
		
		.board_list>ul.last {
			border: 0;
		}
		
		.board_list>ul>li {
			text-align: center;
		}
		
		.board_header {
			background: rgb(135, 211, 124);
			color: white;
			font-weight: bold;
		}
		
		.board_list .id {
			width: 70px;
		}
		
		.board_list .category {
			width: 100px;
		}
		
		.board_list .title {
			width: 350px;
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap;
		}
		
		.board_list .writer {
			width: 140px;
		}
		
		.board_list .count {
			width: 70px;
		}
		
		.board_list .like {
			width: 70px;
		}
		
		.board_ul:hover {
			background: #f3f5f7;
			cursor: pointer;
		}
		
		.board_paging {
			height: 50px;
			line-height: 50px;
			display: flex;
			justify-content: center;
			list-style: none;
			width: 480px;
			margin: auto;
		}
		
		.board_paging a.current_page {
			border-bottom: 2px solid #282A35;
			font-weight: bold;
		}
		
		.board_title>ul, #title{
			-ms-user-select: none; 
            -moz-user-select: -moz-none;
            -khtml-user-select: none;
            -webkit-user-select: none;
            user-select: none;
        }
        
        .board_paging {
	height: 40px;
	line-height: 40px;
	display: flex;
	justify-content: center;
	list-style: none;
	width: 480px;
	margin: auto;
}

.board_paging a {
	text-decoration: none;
	line-height: 40px;
	display: block;
	text-align: center;
	font-weight: bold;
	margin: 0 3px;
	/* border: 1px solid #e6e6e6; */
	width: 40px;
	height: 40px;
	text-align: center;
	/* color: #999999; */
	text-decoration: none;
}

boadr_paging a:hover {
	cursor: pointer;
}

.board_paging a.current_page { /*현재 페이지 */
	/* background-color: #030066; */
	/* color: #fff; */
	border: 2px solid rgb(135, 211, 124);/* #030066; */
	border-radius: 50%;
	margin-top: -3%
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

.first_menu {
	color: rgb(135,211,124);
}

    </style>

<title>전체 게시글</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/menubar.jsp" %>

	<div class="outer">
        <div class="title_header">
        	<c:choose>
        		<c:when test="${ !empty param.search }">
        			<input id="title" value="'${ param.search }' 검색 결과" readonly>
        		</c:when>
        		<c:otherwise>
        			<input id="title" value="전체 게시글 조회" readonly>
        		</c:otherwise>
        	</c:choose>
        </div>
		<div class="wrap">
			<div class="board_list">
                <ul class="board_header">
                    <li class="id">글번호</li>
                    <li class="category">게시판</li>
                    <li class="title">제목</li>
                    <li class="writer">작성자</li>
                    <li class="count">조회수</li>
                </ul>
				<c:forEach var="board" items="${ boardList }">
                <ul class="board_ul" onclick="detailView(${board.bid}, '${board.btype}')">
					<li class="id">${ board.bid }</li>
					<li class="category">${ board.btype }</li>
					<li class="title">
						<c:choose>
							<c:when test="${fn:length(board.btitle) gt 16}">
								${fn:substring(board.btitle, 0, 15)}...
							</c:when>
							<c:otherwise>
								${ board.btitle }
							</c:otherwise>
						</c:choose>
					</li>
					<li class="writer">${ board.nickname }</li>
					<li class="count">${ board.bcount }</li>
                </ul>
                </c:forEach>
            </div>
            <c:if test="${ !empty param.search }">
					<c:set var="searchParam" value="&search=${ param.search }"/>
				</c:if>
            <ul class="board_paging">
					<!-- 맨 처음으로 이동하는 버튼(<<) -->
					<li><a
						href="${ contextPath }/total/list?page=1${ searchParam }"
						class="arrow pprev"></a></li>

					<!-- 이전 페이지로(<) -->
					<li><c:choose>
							<c:when test="${pi.page>1 }">
								<a class="arrow prev"
									href="${ contextPath }/total/list?page=${ pi.page-1 }${ searchParam }"></a>
							</c:when>
							<c:otherwise>
								<a class="arrow prev" href="#"></a>
							</c:otherwise>
						</c:choose></li>

					<!-- 최대 10개의 페이지 목록 -->
					<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
						<li><c:choose>
								<c:when test="${ p eq pi.page }">
									<a class="current_page" href="#">${ p }</a>
								</c:when>
								<c:otherwise>
									<a
										href="${ contextPath }/total/list?page=${ p }${ searchParam }">${ p }</a>
								</c:otherwise>
							</c:choose></li>
					</c:forEach>

					<!-- 다음 페이지로(>) -->
					<li><c:choose>
							<c:when test="${ pi.page < pi.maxPage }">
								<a class="arrow next"
									href="${ contextPath }/total/list?page=${ pi.page+1 }${ searchParam }"></a>
							</c:when>
							<c:otherwise>
								<a class="arrow next" href="#"></a>
							</c:otherwise>
						</c:choose></li>

					<!-- 맨 끝으로 이동하는 버튼(>>) -->
					<li><a class="arrow nnext"
						href="${ contextPath }/total/list?page=${ pi.maxPage }${ searchParam }"></a></li>
				</ul>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
	
	<c:choose>
		<c:when test="${ !empty loginUser }">
			<script>
				function detailView(bid, btype){
					if(btype == "자취는 꿀팁")
						location.href="${contextPath}/info/detail?bid="+bid;
					else if(btype == "자취는 꿀템")
						location.href="${contextPath}/marketplace/detail?bid="+bid;
					else if(btype == "자취는 꿀친")
						location.href="${contextPath}/friend/detail?bid="+bid;
				}
			</script>
		</c:when>
		<c:otherwise>
			<script>
				function detailView(bid,btype){
					alert("로그인 후 이용 가능합니다");
					location.href="${contextPath}/login";
				}
			</script>
		</c:otherwise>
	</c:choose>
	
</body>
</html>