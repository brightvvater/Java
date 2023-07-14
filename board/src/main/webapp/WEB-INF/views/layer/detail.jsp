<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bitedu.bipa.board.vo.ReplyVO"%>
<%@page import="bitedu.bipa.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>계층형</title>
    <style>
      header {
        font-size: 1.5rem;
        padding: 90px;
        text-align: center;
        display: flex;
        flex-direction: column;
        align-items: center;
        font-weight: 600;
        border-bottom: 1px solid rgb(190, 190, 190);
      }

      header > a {
        text-decoration: none;
        color: black;
      }
      .body {
        display: flex;
      }

      .menu {
        padding: 20px 60px 20px 20px;
        border-right: 1px solid rgb(190, 190, 190);
        height: 100vh;
        width: 15%;
      }

      .menu > .title {
        font-weight: bold;
        font-size: 1.5rem;
        padding: 10px 0px;
        border-bottom: 1px solid #aaaaaa;
      }

      .menu > ul {
        padding-left: 0;
      }

      .menu > ul > a > li {
        list-style: none;
        padding: 10px 0px;
        font-size: 1.2rem;
        width: 100%;
      }

      .menu > ul > a {
        text-decoration: none;
        color: #777777;
      }

      table {
        border-collapse: collapse;
        border: 1px solid black;
        text-align: center;
        width: 70%;
        margin: 50px auto;
       
      }
      
      h2 {
      	margin: 0 auto;
      }

      td,
      tr {
        border-collapse: collapse;
        border: 1px solid black;
        height: 60px;
        font-size: 20px;
      }

      #content {
      	margin-top:10px;
      	margin-bottom:10px;
        width: 80%;
        font-size: 20px;
        
      }
		
	  
      #content-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        width: 90%;
        margin: 10px 0px;
      }

      #content-header > button {
        height: 40px;
      }

      #col-title {
        font-weight: bolder;
      }

      .activeMenu {
        font-weight: bolder;
      }
      
      input {
      	width:60%;
      	height:25px;
      	font-size: 20px;
      }
      
      button {
      	font-size: 20px;
      }
      
      
      .readonly {
      	background: whitesmoke;
      
      }
      
      .comments {
      	margin-left: 250px;
      	margin-bottom: 50px;
      }
      .comment{
      	width:70%;
      	border: 1px solid black;
      	margin: 0 auto;
      }
      
      .repre_reply {
      	font-size: 20px;
      }
      
      .repre_reply p {
      	display: inline-block;
      }
      
      .repre_id {
     	margin-left: 10px;
     	font-size: 15px;
      }
      
      .repre_content {
     	margin-left: 30px;
      }
      
      .repre_regdate {
      	float: right;
    	margin-right: 15px;
    	font-size: 14px;
      }
     
      .reply {
      	margin-left: 30px;
      	font-size: 15px;
      	
      }
      
      #reply_box{
      	margin-left: 30px;
      	margin-bottom: 20px;
      }
      
    </style>
  </head>
  <body>
  <%
  	BoardVO vo = (BoardVO)request.getAttribute("board");
  	List<ReplyVO> list = (List)request.getAttribute("replyList");
  	
  	Set<Integer> idGroup = new HashSet<Integer>();
  	for(ReplyVO reply: list) {
  		idGroup.add(reply.getGroupId());
  	}
  	
  	//System.out.println(idGroup.toString());
  %>
  <script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
  	<script>
  	
  	$(document).ready(function() {
  		
 
  		$('#add_reply').on("click", function() {
  			//alert('add')
  			let rContent = $('#rContent').val();
  			let boardSeq = <%=vo.getBoardSeq()%>;
  			let userId = 'user1';
  			
  			
  			var today = new Date();
	  		var year = today.getFullYear();
	  		var month = ('0' + (today.getMonth() + 1)).slice(-2);
	  		var day = ('0' + today.getDate()).slice(-2);
	  		var hours = ('0' + today.getHours()).slice(-2); 
	  		var minutes = ('0' + today.getMinutes()).slice(-2);
	  		var seconds = ('0' + today.getSeconds()).slice(-2); 

	  		var dateString = year + '-' + month  + '-' + day +'T'+ hours + ':' + minutes  + ':'+ seconds ;
	  		
	  		 let data = {
					rContent : rContent,
					boardSeq: boardSeq,
					regDate: dateString,
				};
	  		
	  		
	  		//console.log(data);
	  		//console.log(JSON.stringify(data)); 
  			 $.ajax({
  				url:'/board/layer/reply',
  				type:'post',
  				data: JSON.stringify(data),
  				dataType:"json",
  				contentType:"application/json",
  				success: function() {
  					alert('댓글이 작성되었습니다.');
  					location.href="./detail.do?boardSeq="+boardSeq;
  				},
  				error: function() {
  					alert('로그인이 필요합니다.');
  					$('input').val('');
  				}
  			}); 
  		});
  		
  		$(document).on("click", "#reply-button", function () {
  			let rContent = $(this).parent().children('input').val();
  			let parentId = $(this).next().text();
  			let boardSeq = <%=vo.getBoardSeq()%>;
  			let userId = 'user1';
  			
  			
  			var today = new Date();
	  		var year = today.getFullYear();
	  		var month = ('0' + (today.getMonth() + 1)).slice(-2);
	  		var day = ('0' + today.getDate()).slice(-2);
	  		var hours = ('0' + today.getHours()).slice(-2); 
	  		var minutes = ('0' + today.getMinutes()).slice(-2);
	  		var seconds = ('0' + today.getSeconds()).slice(-2); 

	  		var dateString = year + '-' + month  + '-' + day +'T'+ hours + ':' + minutes  + ':'+ seconds ;
	  		
	  		 let data = {
					rContent : rContent,
					boardSeq: boardSeq,
					regDate: dateString,
					groupId: parentId
				};
	  		
  			 $.ajax({
  				url:'/board/layer/reply',
  				type:'post',
  				data: JSON.stringify(data),
  				contentType:"application/json",
  				success: function(data) {
  					alert('댓글이 작성되었습니다.');
  					location.href="./detail.do?boardSeq="+boardSeq;
				
  				},
  				error: function(error) {
  					alert('로그인이 필요합니다.');
  					$('input').val('');
  				}
  			}); 
  			
  		})
  		
  		
  		
  	})
  	</script>
    <header>안녕하세요 BIT 도서관입니다.</header>

    <div class="body">
      <div class="menu">
        <p class="title">menu</p>
        <ul>
          <a href="/board/"><li>공지사항</li></a>
          <a href="/board/normal/list.do" ><li>일반</li></a>
          <a href="/board/layer/list.do" class="activeMenu"><li>계층</li></a>
        </ul>
      </div>
      <div id="content">
        <div id="content-header">
          <h2>세부글</h2>
        </div>
        <form action="list.do" method="get">
        <table>
          <tr>
            <td>번호</td>
            <td>${board.boardSeq }</td>
          </tr>
          <tr>
            <td>제목</td>
            <td>${board.boardTitle }</td>
          </tr>
          <tr>
            <td>작성자</td>
            <td>${board.userId }</td>
          </tr>
          <tr>
            <td>내용</td>
            <td><textarea width="5" rows="15" id="content" name="content" readonly="readonly">${board.content}</textarea></td>
     
          </tr>
          <tr>
            <td>작성일</td>
            <td>
            	<fmt:formatDate value="${board.regDate }" pattern="yyyy-MM-dd"/>
            </td>
          </tr>
          <tr>
            <td colspan="2">
            	<button>목록으로</button>
            </td>
          </tr>
        </table>
        </form>
        
        <div class="comments">
        	<input type="text" name="rContent" id="rContent">
        	<button type="button" id="add_reply">댓글달기</button>
        </div>
        
         <div id="comments">
				<% for(Integer id: idGroup) {
         				System.out.println(id);
         				for(ReplyVO reply: list) {
         					if(reply.getGroupId() == id) {
         					%>
				<div class="comment">
					<%if(reply.getDepth()==0) { %>
						<div class="repre_reply">
						<p class="repre_id"><%=reply.getReplyId() %></p>
						<p class="repre_content"><%=reply.getrContent() %></p>
						<p class="repre_regdate">
							<fmt:formatDate value="<%=reply.getRegDate() %>"
							pattern="yyyy-MM-dd hh:mm" />
						</p>
						</div>
						<div id="reply_box">
							<input type="text" class="add_reply" value="">
							<button id="reply-button"  type="button">답글 달기</button>
							<div hidden="hidden"><%=reply.getReplyId() %></div>
						</div>
						
					<%} %>

					<% if(!idGroup.contains(reply.getReplyId())){%>
						<div class="reply">
						<p>ㄴ<%=reply.getReplyId() %></p>
						<p><%=reply.getrContent() %></p>
						<p>
							<fmt:formatDate value="<%=reply.getRegDate() %>"
							pattern="yyyy-MM-dd hh:mm" />
						</p>
						</div>
					<% }%>
						
				</div>
				<% }}} %>
				<div class="comment">
				 	<div id="ajax1"></div>
				</div>
				<div class="comment">
				 	<div class="reply" id="ajax"></div>
				</div>










			</div>
    </div>
  </body>
</body>
</html>