<%@page import="bitedu.bipa.member.vo.BookCopy"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book List</title>
<style>
        table, td, th {
            border : 1px solid black;
            border-collapse: collapse;
            margin: 20px auto;
        }
        td {
            width: 150px;
            height: 50px;
            padding: 5px;
            font-size: 20px;
            /* text-align: center; */
        }

        input , select {
            font-size: 20px;
        }
        .data_ui {
            /* width: 250px; */
            height: 50px;
        }

        button {
            font-size: 15px;
            margin: 5px;
        }
        
        
        #sending {
        	text-align: center;
        }
        
        input.poster :disabled {
        	background: gray;
        }
        
        #form {
        	font-size: 30px;
        }
        
        #title {
        	height: 80px;
        	font-size: 50px;
        }
    </style>
</head>
<body>
<%-- <% 
	String cmd = request.getParameter("flag");
	cmd = cmd==null?"":cmd;
%>
<%=cmd.equals("true")?"<script>alert('삭제성공');</script>":""%> --%>
${param.flag=='true'?"<script>alert('삭제성공');</script>":""}
<%

	ArrayList<BookCopy> list = (ArrayList<BookCopy>)request.getAttribute("list");
%>
<table>
	<tr><td>순번</td><td>타이틀</td><td>저자</td><td>출판일</td><td></td></tr>
<c:forEach var ="copy" items="${list}">
	<tr>
		<td>${copy.bookSeq}</td>
		<td><a href='/MemberSampleJSP/BlmController?cmd=userDetail&bookSeq=${copy.bookSeq}'>${copy.title}</a></td>
		<td>${copy.author}"</td><td>${copy.publishDate}</td>
		<td><a href="./BlmController?cmd=remove&bookSeq=${copy.bookSeq}">삭제</a></td>
	</tr>
</c:forEach>
	
	<tr><td colspan="5"><a href="./BlmController?cmd=view_regist"><button>도서등록</button></a></td></tr>
</table>
</body>
</html>