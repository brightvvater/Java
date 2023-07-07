<%@page import="bitedu.bipa.book.vo.BookCopy"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>디테일</title>
<style type="text/css">
table, td, th {
	border: 1px solid black;
	border-collapse: collapse;
	margin: 5px auto;
}

td {
	width:200px;
	height:50px;
	font-size: 20px;
}

input {
	font-size:20px;
}

#form {
	font-size: 30px;
}



</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
$(document).ready(function() {
	let form = $('#frm');
	$('#go_back_list').on("click", function() {
		form.attr("action","/Practice/bookController");
		form.submit();	
	});
	
	$('#update_btn').on("click", function() {
		form.attr("action","/Practice/bookController?cmd=update");
		form.submit();	
	})
})


</script>
</head>
<body>
<%
	BookCopy book = (BookCopy)request.getAttribute("book");
%>
<form action="" method="post" id="frm">
<table>
	<tr><th colspan="4" id="form">도서상세</th></tr>
	<tr>
		<td>구분</td>
		<td colspan="2">데이터입력</td>
		<td>비고</td>
	</tr>
	<tr>
		<td>도서번호</td>
		<td colspan="2"><input type="text" id="book_seq" name="book_seq" value="<%=book.getBookSeq()%>" disabled></td>
		<td></td>
	</tr>
	<tr>
		<td>ISBN</td>
		<td colspan="2"><input type="text" id="isbn" name="isbn" value="<%=book.getIsbn()%>"></td>
		<td></td>
	</tr>
	<tr>
		<td>도서명</td>
		<td colspan="2"><input type="text" id="title" name="title" value="<%=book.getTitle()%>"></td>
		<td></td>
	</tr>
	<tr>
		<td>저자/역자</td>
		<td colspan="2"><input type="text" id="author" name="author" value="<%=book.getAuthor()%>"></td>
		<td></td>
	</tr>
	<tr>
		<td>출판사</td>
		<td colspan="2"><input type="text" id="publisher" name="publisher" value="<%=book.getPublisher()%>"></td>
		<td></td>
	</tr>
	<tr>
		<td>출판일</td>
		<td colspan="2"><input type="text" id="publish_date" name="publish_date" value="<%=book.getPublishDate()%>"></td>
		<td></td>
	</tr>
	<tr>
		<td>도서위치</td>
		<td colspan="2"><input type="text" id="book_position" name="book_position" value="<%=book.getBookPosition()%>"></td>
		<td></td>
	</tr>
	<tr>
		<td>도서상태</td>
		<td colspan="2"><input type="text" id="book_status" name="book_status" value="<%=book.getBookStaus()%>"></td>
		<td></td>
	</tr>
	<tr>
		<td colspan="4">
			<input type="submit" id="update_btn" value="도서수정">
			<input type="submit" id="clear_btn" value="원래대로">
			<input type="submit" id="go_back_list" value="도서리스트">
		</td>
	</tr>
</table>
</form>
</body>
</html>