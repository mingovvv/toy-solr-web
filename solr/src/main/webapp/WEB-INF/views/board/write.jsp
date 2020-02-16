<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <span>글쓰기 게시판입니다.</span>
 <br/>  	 	
 <br/>
	<form method="post">
		<input type="text" id="subject" name="subject" placeholder="제목을 입력해주세요" required="required" style="display: block; width: 400px; height: 30px">
		<br />  	
		<br />  	
		<textarea id="content" name="content" placeholder="내용을 입력해주세요" style="width: 400px; height: 300px"></textarea>
		<br />  	
		<br />  	
		<input type="submit" value="작성">
	</form>
	
	
	<br /> 
 	<br /> 
 	<a href="/web/board/list">
		<div style="width: 100px; height: 50px; border: 5px solid black">
			게시판 가기
		</div>
	</a>
</body>
</html>