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

input, button {
	font-size:20px;
	margin-left: 20px;
	margin-right: 20px;
	
}

#form {
	font-size: 30px;
}

table {
	margin-bottom: 50px;
}


</style>
<%
	BookCopy book = (BookCopy)request.getAttribute("book");
%>
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
$(document).ready(function() {
	let form = $('#frm');
	$('#go_back_list').on("click", function(e) {
		e.preventDefault();
		location.href="list.do?page=1";
		
	});
	
	$('#update_btn').on("click", function() {
		form.attr("action","update.do");
		form.attr("method", "get");
		form.submit();	
	})
})


</script>
</head>
<body>

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
		<td colspan="2"><input type="text" id="book_seq" name="bookSeq" value="<%=book.getBookSeq()%>" disabled></td>
		<td></td>
	</tr>
	<tr>
		<td>ISBN</td>
		<td colspan="2"><input type="text" id="isbn" name="isbn" value="<%=book.getIsbn()%>" readonly="readonly"></td>
		<td></td>
	</tr>
	<tr>
		<td>도서명</td>
		<td colspan="2"><input type="text" id="title" name="title" value="<%=book.getTitle()%>" readonly="readonly"></td>
		<td></td>
	</tr>
	<tr>
		<td>저자/역자</td>
		<td colspan="2"><input type="text" id="author" name="author" value="<%=book.getAuthor()%>" readonly="readonly"></td>
		<td></td>
	</tr>
	<tr>
		<td>출판사</td>
		<td colspan="2"><input type="text" id="publisher" name="publisher" value="<%=book.getPublisher()%>" readonly="readonly"></td>
		<td></td>
	</tr>
	<tr>
		<td>출판일</td>
		<td colspan="2"><input type="text" id="publish_date" name="publishDate" value="<%=book.getPublishDate()%>" readonly="readonly"></td>
		<td></td>
	</tr>
	<tr>
		<td>도서위치</td>
		<td colspan="2"><input type="text" id="book_position" name="bookPosition" value="<%=book.getBookPosition()%>" readonly="readonly"></td>
		<td></td>
	</tr>
	<tr>
		<td>도서상태</td>
		<td colspan="2"><input type="text" id="book_status" name="bookStatus" value="<%=book.getBookStatus()%>" readonly="readonly"></td>
		<td></td>
	</tr>
	<tr>
		<td colspan="4"><input type="hidden" id="book_seq" name="bookSeq" value="<%=book.getBookSeq()%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td colspan="4">
			<input type="submit" id="update_btn" value="도서수정">
			<input type="submit" id="go_back_list" value="도서리스트">
		</td>
	</tr>
</table>

<table>
	<tr>
		<td>댓글</td>
		<td><input type="text"></td>
		<td><button>작성하기</button></td>
	</tr>
	

</table>
</form>
</body>
</html>