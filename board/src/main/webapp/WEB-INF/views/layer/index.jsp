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
        width: 85%;
      }

      td,
      tr {
        border-collapse: collapse;
        border: 1px solid black;
        height: 40px;
      }

      #content {
        width: 100%;
        margin: 30px;
      }

      #content-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        width: 85%;
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
    </style>
  </head>
  <body>
  	<script>
  	 function view_regist() {
  		 location.href="./regist.do";
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
          <h2>독후감 작성 계층 게시판</h2>
          <button onclick="view_regist()">글 등록하기</button>
        </div>
        
        <table>
          <tr id="col-title">
            <td>번호</td>
            <td>제목</td>
            <td>작성자</td>
            <td>조회수</td>
            <td>작성일</td>
          </tr>
          <c:forEach items="${list}" var="board">
			<tr>
				<td>${board.boardSeq}</td>
				<td><a href="./detail.do?boardSeq=${board.boardSeq}">${board.boardTitle}</a></td>
				<td>${board.userId}</td>
				<td>${board.view}</td>
				<td><fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd"/></td>
			</tr>
		 </c:forEach>
          
        </table>
      </div>
    </div>
  </body>
</body>
</html>