<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body style="display: flex;">
 	<a href="/web/board/list">
		<div style="width: 100px; height: 50px; border: 5px solid black">
			게시판
		</div>
	</a>
 	<a href="/web/board/write">
		<div style="width: 100px; height: 50px; border: 5px solid black">
			글작성
		</div>
	</a>
</body>
</html>
