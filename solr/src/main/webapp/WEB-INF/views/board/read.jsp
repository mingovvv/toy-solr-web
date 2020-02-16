<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <span>상세내용 게시판입니다.</span>
 <br/>  	 	
 <br/>
	<table border="5"> 
	 	<tr>
	 		<th style="text-align: center;">글 번호</th>
	 		<th style="width: 600px;">제목</th>
	 		<th style="text-align: center;">등록일자</th>
	 	</tr>
	 	<tr>
	 		<td style="text-align: center;">${read.rowNum}</td>
	 		<td style="text-align: right;">${read.subject}</td>
	 		<td>${read.date}</td>
	 	</tr>
	 </table>
	 <div style="border: 5px solid black; width: 860px; height: 400px">
	 	${read.content}
	 </div>
	 <button><a href="/web/board/edit?idx=${read.idx}">수정</a></button>		   
	 <button><a href="/web/board/delete?idx=${read.idx}">삭제</a></button>  		   
</body>
</html> 