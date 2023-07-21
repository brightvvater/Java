<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../resources/css/member.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="./regist.do" method="post">
	<table id="join">
		<tr>
			<td colspan="3">등록페이지</td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="title"></td>
			<td></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="writer"></td>
			<td></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><input type="text" name="content"></td>
			<td></td>
		</tr>
		<tr>
			<td>이미지</td>
			<td><img src="http://placehold.it/300X200" width="300" height="200" id="preview">
            	<input type="file" name="boardImage" id="up_image"></td>
			<td></td>
		</tr>
		<tr>
			<td colspan="3"><button type="submit">등록</button></td>
		</tr>
	</table>
</form>

</body>
</html>