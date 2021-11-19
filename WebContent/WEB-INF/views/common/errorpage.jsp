<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%

	String msg = (String)request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>errorpage</title>
<style>
#imageArea {
	width: 500px;
	margin: 100px auto;
	display: flex;
	justify-content: center;
	align-item: center
}

#imageArea img {
	width: 100%;
}

#msgArea {
	text-align: center;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/menubar.jsp"%>
	<div id="imageArea">
		<img id="errorImage"
			src="<%= request.getContextPath() %>/resources/images/error.png">
	</div>
	<h1 id="msgArea"><%= msg %></h1>

</body>
</html>