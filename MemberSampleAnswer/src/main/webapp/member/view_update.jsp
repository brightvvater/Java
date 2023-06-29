<%@page import="bitedu.bipa.member.controller.TestVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8" isELIgnored="true" %>
<!DOCTYPE html>
<html>
<head>

<title>Insert title here</title>
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
        
        #message {
        	color: red;
        }
    </style>
</head>
<body>
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>

<%
	TestVO vo = (TestVO)request.getAttribute("test");
	String userId = vo.getUserId();
	String pwd = vo.getPwd();
	String name = vo.getName();
	String zipcode = vo.getZipcode();
	String address1 = vo.getAddress1();
	String address2 = vo.getAddress2();
	String year = vo.getYear();
	String month = vo.getMonth();
	String day = vo.getDay();
	String gender =vo.getGender();
	String[] interests = vo.getInterests();
	String introduction = vo.getIntroduction();
	
	String interest ="";
	for(String val:interests) {
		interest+=(val+",");
	}
%>
<form action="#" method="">
    <table>
        <tr><th colspan="5" id="form">회원수정하기</th></tr>
        <tr><th>구분</th><th class="data_ui" colspan="2">데이터입력</th><th>유효성검사</th><th>비고</th></tr>
        <tr>
            <td>아이디</td>
            <td colspan="2"><input type="text" id="userId" name="userId" value = <%=userId%>><button id="btn_check_id">아이디확인</button></td>
            <td>8자리 , 첫글자 영문소문자</td><td id="message"></td></tr>
        <tr><td>비밀번호</td><td colspan="2"><input type="text" id="pwd1" value = <%=pwd%>></td><td rowspan="2">비밀번호 일치</td><td><input type="hidden" id="flag" value="false"></td></tr>
        <tr><td>비번확인</td><td colspan="2"><input type="text" id="password" value = <%=pwd%>></td><td></td></tr>
        <tr><td>이름</td><td colspan="2"><input type="text" id="name" value = <%=name%>></td><td>필수입력</td><td></td></tr>
        <tr><td>우편번호</td><td colspan="2"><input type="text" id="postal" value = <%=zipcode%> disabled><button id="find_zipcode">우편번호찾기</button></td><td rowspan="3">필수입력</td><td></td></tr>
        <tr><td>주소1</td><td colspan="2"><input type="text" id="addr1" size="35" value ="" disabled></td><td></td></tr>
        <tr><td>주소2</td><td colspan="2"><input type="text" id="addr2" size="35" value ="" disabled></td><td></td><tr>
        <tr>
            <td>생년월일</td>
            <td colspan="2">
                <input type="text" placeholder="년도" size="5" id="year"  value = <%=year%>>
                -
                <select id="month" name="month">
                    <option>-- 월 --</option>
                    <option value='1'>1</option>
                    <option value='2'>2</option>
                    <option value='3'>3</option>
                    <option value='4'>4</option>
                    <option value='5'>5</option>
                </select>
                -
                <select id="day">
                	<option>-- 일 --</option>
                	<option value='1'>1</option><option value='2'>2</option><option value='3'>3</option>
                	<option value='4'>4</option><option value='5'>5</option><option value='6'>6</option>
                	<option value='7'>7</option><option value='8'>8</option><option value='9'>9</option>
                	<option value='10'>10</option><option value='11'>11</option><option value='12'>12</option>
                	<option value='13'>13</option><option value='14'>14</option><option value='15'>15</option>
                 </select>
                <br><br>
                <input type="text" id="" placeholder="년도" size="5"  value = <%=year%>>-
                <input type="text" id="" placeholder="월" size="5" value=<%=month %>>-
                <input type="text" id="" placeholder="일" size="5" value=<%=day %>>
            </td><td>년도는 1900~2050, 월은 1~12, 일은 1~31</td><td>2개중 하나 선택</td></tr>
        <tr>
            <td>성별</td>
            <td colspan="2">
                <input type="radio" name="gender" value="man">남
                <input type="radio" name="gender" value = "woman">여
            </td><td>필수 선택</td><td></td></tr>
        <tr>
            <td>관심분야</td>
            <td colspan="2">
                <input type="checkbox" name="interests" value='1'>문학
                <input type="checkbox" name="interests" value='2'>어학
                <input type="checkbox" name="interests" value='3'>정보IT<br>
                <input type="checkbox" name="interests" value='4'>과학
                <input type="checkbox" name="interests" value='5'>수학
                <input type="checkbox" name="interests" value='6'>예술
            </td><td>2~3개 사이 선택</td><td></td>
        </tr>
        <tr><td>자기소개</td><td colspan="2"><textarea cols="50" rows="5" ><%=introduction%></textarea></td><td></td><td></td></tr>    
        <tr><td colspan="5" id="sending"><input type="submit" value="가입"> <input type="reset"></td></tr>
    </table>
</form>
<script>
	$(document).ready(function() {
		$('#addr1').val('<%=address1%>');
		$('#addr2').val('<%=address2%>');
		
		
		selectedControl();
		function selectedControl(){
			  const el = document.getElementById('month');  //select box
			  const el2 = document.getElementById('day');  //select box
			  const len = el.options.length; //select box의 option 갯수
			  const len2 = el2.options.length; //select box의 option 갯수
			  
			  const str = '<%=month%>';
			  //select box의 option 갯수만큼 for문 돌림
			  for (let i=0; i<len; i++){  
			  	//select box의 option value가 입력 받은 value의 값과 일치할 경우 selected
			    if(el.options[i].value == str){
			    	el.options[i].selected = true;
			    }
			  }
			  
			  const str2= '<%=day%>';
			  for (let i=0; i<len2; i++){  
				  	//select box의 option value가 입력 받은 value의 값과 일치할 경우 selected
				    if(el2.options[i].value == str2){
				    	el2.options[i].selected = true;
				    }
				  }
			}
		
		
		
		if($("input:radio[name='gender']:radio[value='man']").val() =='<%=gender%>'){
			$("input:radio[name='gender']:radio[value='man']").prop('checked', true); // 선택하기
		}else if($("input:radio[name='gender']:radio[value='woman']").val() =='<%=gender%>'){
			$("input:radio[name='gender']:radio[value='woman']").prop('checked', true);
		}
		
		
		
		console.log('<%=interest%>');
		let list = '<%=interest%>'.split(",");
		console.log(list);
		for(let i=0;i<list.length-1;i++) {
			if(list[i] =='1'){
				$("input:checkbox[name='interests']:checkbox[value='1']").prop('checked', true);
			}else if(list[i] =='2'){
				$("input:checkbox[name='interests']:checkbox[value='2']").prop('checked', true);
			}else if(list[i] =='3'){
				$("input:checkbox[name='interests']:checkbox[value='3']").prop('checked', true);
			}else if(list[i] =='4'){
				$("input:checkbox[name='interests']:checkbox[value='4']").prop('checked', true);
			}else if(list[i] =='5'){
				$("input:checkbox[name='interests']:checkbox[value='5']").prop('checked', true);
			}else if(list[i] =='6'){
				$("input:checkbox[name='interests']:checkbox[value='6']").prop('checked', true);
			}
		}
		
		
		
		
	});
	
</script>
</body>
</html>