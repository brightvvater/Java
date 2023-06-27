<%@page import="bitedu.bipa.member.controller.TestVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1>
<%
	//String id = request.getParameter("userId");
	TestVO testvo = (TestVO)request.getAttribute("test");
	String id = testvo.getUserId();
	String pwd = testvo.getPwd();
	
	out.print(id);
%>
</h1>
<br>
<h1><%=id%></h1>
<h1><%=pwd%></h1>
</body>
</html>