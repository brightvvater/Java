<%@page import="bitedu.bipa.member.vo.BookCopy"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 도서 상세</title>
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

        input , select, button {
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
        
        #message {
        	color: red;
        }
        
        #go_reserve, #go_rent{
        	font-size: 20px;
        }
        
        #info {
        	background-color: red;
        	color: white;
        }
    </style>
    <script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript">
	<%
	BookCopy detail = (BookCopy)request.getAttribute("detail");
	System.out.println(detail.getBookStaus());
%>
	$(document).ready(function(){
	 
		$('#go_reserve').on("click", function() {
			let bookSeq = $('#book_seq').val();
			let userId = 'user1'; //하드코딩
			
			$.ajax({
				url: "/MemberSampleJSP/reserve",
				data: {"bookSeq":bookSeq,"userId": userId},
				type: "post",
				success: function(data) {
					let result = JSON.parse(data).result;
					if(result == false) {
						alert('예약 불가합니다.')
					}else {
						alert('예약 완료!');
					}
					
				},
				error: function() {
					
				}
			})
		});
		
		$("#go_book_list").on("click", function(){
			let form = $("#frm");
			form.attr("action", "/MemberSampleJSP/BlmController?cmd=list");
			form.submit();
		});
	})
		
	</script>
</head>
<body>

<form action="" method="post" id="frm">
    <table>
        <tr><th colspan="4" id="form">도서 상세</th></tr>
        <tr><th>구분</th><th class="data_ui" colspan="2">데이터입력</th><th>비고</th></tr>
        <tr>
            <td>도서번호</td>
            <td colspan="2">
            	<input type="text" id="book_seq" name="book_seq" value="<%=detail.getBookSeq() %>" disabled>
            </td>
            <td id="message"></td></tr>
        <tr>
        	<td>ISBN</td>
        	<td colspan="2">
        		<input type="text" id="isbn" name="isbn" value="<%=detail.getIsbn() %>" disabled>
        	</td>
        	<td>
        		<input type="hidden" id="flag" value="false">
        	</td>
        </tr>
        <tr>
        	<td>도서명</td>
        	<td colspan="2">
        		<input type="text" id="book_title" name="book_title" value="<%=detail.getTitle() %>" disabled>
        	</td><td></td>
        </tr>
        <tr>
        	<td>저자/역자</td>
        	<td colspan="2">
        		<input type="text" id="author" name="author" value="<%=detail.getAuthor()%>" disabled>
        	</td><td></td>
        </tr>
        <tr>
        	<td>출판사</td>
        	<td colspan="2">
        		<input type="text" id="publisher" size="35" name="publisher" disabled>
        	</td><td></td>
        </tr>
        <tr>
        	<td>출판일</td>
        	<td colspan="2">
        		<input type="text" id="publish_date" size="35" name="publish_date" value="<%=detail.getPublishDate() %>" disabled>
        	</td>
        	<td></td>
        </tr>
        
        <tr>
        	<td colspan="4" id="sending">
        		<c:if test="${detail.getBookStaus() eq 'BM-0001'}">
        			<button type="button" id="go_rent">대출</button> 
        		</c:if>
        		<c:if test="${detail.getBookStaus() ne 'BM-0001'}">
        			<button type="button" id="info" disabled>대출중</button>
        			<button type="button" id="go_reserve">도서예약</button>
        		</c:if>
        		<input type="submit" id="go_book_list" value="도서리스트">
        	</td>
        </tr>
    </table>
</form>
</body>
</html>