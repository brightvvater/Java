<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../resources/css/member.css"> 
<meta charset="UTF-8">
<title>member</title>
</head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
$(document).ready(function() {
	$('.button').on("click", function() {
		let userId = $(this).prev().val();
		console.log(userId);
		
		$.ajax({
			url:'./restful/'+userId,
			type:'delete',
			success: function() {
				alert('삭제성공');
			},
			error: function() {
				alert('에러')
			}
		});
	});
})

</script>
<body>
<table id="join">
	<tr>
		<td colspan="3">회원리스트</td>
	</tr>
	<tr>
		<td>아이디</td>
		<td>전화번호</td>
		<td>비고</td>
	</tr>
	<c:forEach items="${list }" var="user">
		<tr>
			<td><a href="./detail.do?userId=${user.userId }">${user.userId}</a></td>
			<td>${user.phoneNumber}</td>
			<td>
			<input type="hidden"  value="${user.userId}">
			<button type="button" class="button">삭제</button></td>
		</tr>
	</c:forEach>
</table>
</body>
</html>