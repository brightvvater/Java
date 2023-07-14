<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
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
	
	table, th, td {
		border: 1px solid black;
		border-collapse: collapse;
		margin: 0 auto;
		margin-top: 100px;
	}
	
	#page, #page th, #page td {
		border: none;
	}
	
	#page a {
		text-decoration: none;
	}
	
	td {
		width:300px;
		height:50px;
		font-size: 20px;
	}
	
	th {
		font-size: 30px;
	}
	
	button , input{
		font-size: 20px;
		margin:5px;
	}
</style>
<script>

</script>
</head>
<body>

<header>안녕하세요 BIT 도서관입니다.</header>
	

	<div class="body">
		<div class="menu">
			<p class="title">menu</p>
			<ul>
				<a href="/board/" class="activeMenu"><li>공지사항</li></a>
				<a href="/board/normal/list.do"><li>일반</li></a>
				<a href="/board/layer/list.do"><li>계층</li></a>
			</ul>
		</div>
		<div id="content">
			<div id="content-header">
				<h2>로그인</h2>
				<button>글 등록하기</button>
			</div>
			

			<form action="/board/user/login.do" method="post">
				<table>
					<tr>
						<td colspan="3">로그인</td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" id="userId" name="userId"></td>
						<td>
							<c:if test="${flag ==false}">
								<div>아이디나 비밀번호가 존재하지 않습니다.</div>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" id="password" name="password"></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="로그인" id="submit">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>