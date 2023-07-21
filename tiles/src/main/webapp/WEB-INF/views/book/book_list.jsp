<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>book</title>
</head>
<body>
<table>
	<tr>
		<td colspan="4">도서목록</td>
	</tr>
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>비고</td>
	</tr>
	<c:forEach items="${list}" var="book">
		<tr>
			<td>${book.bookSeq}</td>
			<td>${book.title}</td>
			<td>${book.author}</td>
			<td></td>
		</tr>
	</c:forEach>
	
</table>
</body>
</html>