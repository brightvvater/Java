<%@page import="bitedu.bipa.book.utils.PageDTO"%>
<%@page import="bitedu.bipa.book.vo.BookCopy"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
<style type="text/css">
	table, th, td {
		border: 1px solid black;
		border-collapse: collapse;
		margin:20px auto;
	}
	
	td {
		width:200px;
		height:50px;
		font-size: 20px;
	}
	
	th {
		font-size: 30px;
	}
	
	button {
		font-size: 15px;
		margin:5px;
	}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
	
</script>
</head>
<body>
<%
PageDTO<BookCopy> dto = (PageDTO<BookCopy>)request.getAttribute("dto");

%>
<table>
	<tr>
		<th colspan="5">도서리스트</th>
	</tr>
	<tr>
		<td>순번</td>
		<td>타이틀</td>
		<td>저자</td>
		<td>출판일</td>
		<td></td>
	</tr>
	<% for(int i=0;i<dto.getContent().size();i++){ 
		List<BookCopy> list = dto.getContent(); %>
	<tr>
		<td><%=list.get(i).getBookSeq() %></td>
		<td><a href="/Practice/bookController?cmd=detail&bookSeq=<%=list.get(i).getBookSeq() %>"><%=list.get(i).getTitle() %></a></td>
		<td><%=list.get(i).getAuthor() %></td>
		<td><%=list.get(i).getPublishDate() %></td>
		<td><a href="/Practice/bookController?cmd=remove&bookSeq=<%=list.get(i).getBookSeq() %>">삭제</a></td>
	</tr>
	<%} %>
	<tr>
		<td colspan="5"><a href="view_regist.do"><button type="button" id="reg_btn">도서등록</button></a></td>
	</tr>
	
</table>
	<div>
		<span>
			<c:if test="${dto.getFirstPageInThisPage() !=1}">
				<a href="/Practice/bookController?page=<%=dto.getCurrentPage()-1%>">prev</a>
			</c:if>
		</span>
			<c:forEach var="i" begin="${dto.firstPageInThisPage}" end="${dto.lastPageInThisPage}">
				<span><a href="/Practice/bookController?page=${i}">${i}</a></span>
			</c:forEach>
		<span>
			<c:if test="${dto.getLastPageInThisPage() !=dto.getLastPage()}">
				<a href="/Practice/bookController?page=<%=dto.getCurrentPage()+1%>">next</a>
			</c:if>
		</span>
	</div>
</body>
</html>