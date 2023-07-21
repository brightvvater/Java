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
<body>
<table id="join">
	<tr>
		<td colspan="3">회원상세</td>
	</tr>
	<tr>
		<td>아이디</td>
		<td>${user.userId }</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td>${user.password }</td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td>${user.phoneNumber }</td>
	</tr>
	
</table>
</body>
</html>