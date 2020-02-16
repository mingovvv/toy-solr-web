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
 <span>Seekers 사내 익명 게시판입니다.</span>
 <br/>  	 	
 <br/>
 <table border="5"> 
 	<tr>
 		<th style="text-align: center;">글 번호</th>
 		<th style="width: 600px;">제목</th>
 		<th style="text-align: center;">등록일자</th>
 	</tr>
 	<c:forEach var="search" items="${search}">
 	<tr>
 		<td style="text-align: center;">${search.rowNum}</td>
 		<td style="text-align: right;"><a href="/web/board/read?idx=${search.idx}">${search.subject}</a></td>
 		<td>${search.date}</td>
 	<tr>
 	</c:forEach>
 </table>
 <br />
	 <form method="post">
	 	<input type="checkbox" name="pick" value="subject" checked="checked">제목
	 	<input type="checkbox" name="pick" value="content">내용
	 	<input type="text" id="search" name="search" placeholder="검색어를 입력해주세요" style="width: 300px">
	 	<input type="submit" value="search">
	 </form>
 <br /> 
 <br /> 
 	<a href="/web/board/write">
		<div style="width: 100px; height: 50px; border: 5px solid black">
			글작성
		</div>
	</a>
</body>
</html>