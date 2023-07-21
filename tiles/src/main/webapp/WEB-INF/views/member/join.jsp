<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>join</title>
<link rel="stylesheet" type="text/css" href="../resources/css/member.css" >
</head>
<body>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="../resources/js/member.js"></script>
<form action="join.do" method="post">
	<table id="join">
		<tr>
			<td colspan="2">회원가입</td>
		</tr>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="userId" id="userId"><button type="button" onclick="idChk()">아이디 중복검사</button></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="password"></td>
		</tr>
		<tr>
			<td>비밀번호확인</td>
			<td><input type="password" name="password2"></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="tel" name="phoneNumber"></td>
		</tr>
		<tr>
			<td colspan="2"><button type="submit" id="submit">회원가입</button>
		</tr>
	</table>
</form>
</body>
</html>