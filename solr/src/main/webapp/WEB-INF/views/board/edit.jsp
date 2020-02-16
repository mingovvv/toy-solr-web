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
 <span>수정 게시판입니다.</span>
 <br/>  	 	
 <br/>
	 <form method="post">
		<table border="5"> 
		 	<tr>
		 		<th style="text-align: center;">글 번호</th>
		 		<th style="width: 600px;">제목</th>
		 		<th style="text-align: center;">등록일자</th>
		 	</tr>
		 	<tr>
		 		<td style="text-align: center;">${edit.rowNum}</td>
		 		<td style="text-align: right;"><input type="text" id="subject" name="subject" value="${edit.subject}" style="width: 100%"></td>
		 		<td>${edit.date}</td>
			</tr>
		 </table>
		 <div style="border: 5px solid black; width: 860px; height: 400px">
		 	<textarea id="content" name="content" style="width: 100%; height: 100%">${edit.content}</textarea>
		 </div>
		 <input type="submit" value="수정하기">
	</form>
</body>
</html>