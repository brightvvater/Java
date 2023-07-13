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
        margin: 0 auto;
      }
      
      h2 {
      	margin: 0 auto;
      }

      td,
      tr {
        border-collapse: collapse;
        border: 1px solid black;
        height: 60px;
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
    </style>
  </head>
  <body>
  <script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
  	<script>
  	$(document).ready(function() {
  		var today = new Date();
  		var year = today.getFullYear();
  		var month = ('0' + (today.getMonth() + 1)).slice(-2);
  		var day = ('0' + today.getDate()).slice(-2);
  		var hours = ('0' + today.getHours()).slice(-2); 
  		var minutes = ('0' + today.getMinutes()).slice(-2);
  		var seconds = ('0' + today.getSeconds()).slice(-2); 

  		var dateString = year + '-' + month  + '-' + day +' '+ hours + ':' + minutes  + ':00' ;
  		//console.log(dateString)
  		$('#regDate').val(dateString);
  	});
  	 function view_regist() {
  		 location.href="/board/layer/regist.do";
  	 }
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
          <h2>게시글 등록</h2>
        </div>
        <form action="regist.do" method="post">
        <table>
          <tr>
            <td>번호</td>
            <td><input type="text" disabled="disabled"></td>
          </tr>
          <tr>
            <td>제목</td>
            <td><input type="text" id="title" name="boardTitle"></td>
          </tr>
          <tr>
            <td>작성자</td>
            <td><input type="text" readonly="readonly" class="readonly" id="user_id" name="userId"></td>
          </tr>
          <tr>
            <td>내용</td>
            <td><textarea width="5" rows="15" id="content" name="content"></textarea></td>
     
          </tr>
          <tr>
            <td>작성일</td>
            <td><input type="datetime" id="regDate" name="regDate" value="" readonly="readonly"></td>
          </tr>
          <tr>
            <td colspan="2">
            	<button>등록하기</button>
            </td>
          </tr>
        </table>
        </form>
      </div>
    </div>
  </body>
</body>
</html>