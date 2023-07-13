<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>메인</title>
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
    <header>안녕하세요 BIT 도서관입니다.</header>

    <div class="body">
      <div class="menu">
        <p class="title">menu</p>
        <ul>
          <a href=""><li>공지사항</li></a>
          <a href="/index.html" class="activeMenu"><li>일반</li></a>
          <a href=""><li>계층</li></a>
        </ul>
      </div>
      <div id="content">
        <div id="content-header">
          <h2>독후감 작성 게시판</h2>
          <button>글 등록하기</button>
        </div>

        <table>
          <tr id="col-title">
            <td>번호</td>
            <td>제목</td>
            <td>작성자</td>
            <td>조회수</td>
            <td>작성일</td>
          </tr>
          <tr>
            <td>1</td>
            <td>아 집에가고싶다</td>
            <td>중영</td>
            <td>3</td>
            <td>2023-07-10</td>
          </tr>
        </table>
      </div>
    </div>
  </body>
</body>
</html>