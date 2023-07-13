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
<link href="../resources/css/basic_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>

</head>
<body>
<%
 List<BookCopy> list = (List)request.getAttribute("list");
%>
<%-- <%
PageDTO<BookCopy> dto = (PageDTO<BookCopy>)request.getAttribute("dto");
%> --%>
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
	<% //for(int i=0;i<dto.getContent().size();i++){ 
		//List<BookCopy> list = dto.getContent();
		for(int i=0;i<list.size();i++) {
	%>
	<tr>
		<td><%=list.get(i).getBookSeq() %></td>
		<td><a href="detail.do?bookSeq=<%=list.get(i).getBookSeq()%>"><%=list.get(i).getTitle() %></a></td>
		<td><%=list.get(i).getAuthor() %></td>
		<td><%=list.get(i).getPublishDate() %></td>
		<td><a href="remove.do?bookSeq=<%=list.get(i).getBookSeq()%>">삭제</a></td>
	</tr>
	<%} %>
	<tr>
		<td colspan="5"><a href="view_regist.do"><button type="button" id="reg_btn">도서등록</button></a></td>
	</tr>
	
</table>
	<!--<table id="page">
		<tr>
			<td>
			<c:if test="${dto.getFirstPageInThisPage() !=1}">
				<a href="list.do?page=<%-- <%=dto.getCurrentPage()-1%> --%>">prev</a>
			</c:if>	
			<c:forEach var="i" begin="${dto.firstPageInThisPage}" end="${dto.lastPageInThisPage}">
				<span><a href="list.do?page=${i}">${i}</a></span>
			</c:forEach>		
			<c:if test="${dto.getLastPageInThisPage() !=dto.getLastPage()}">
				<a href="list.do?page=<%-- <%=//dto.getCurrentPage()+1%> --%>">next</a>
			</c:if>
			</td>
		</tr>
	</table> -->
</body>
</html>