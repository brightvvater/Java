let valid = false;
$(document).ready(function() {
	$('#submit').on("click", function() {
		if(valid == false) {
			alert('아이디중복검사를 시행해 주세요.');
			return false;
		}
		
	})
	
});

function idChk() {
	let userId = $('#userId').val();
	if(userId =='') {
		alert('아이디를 입력하세요.');
		return false;
	}
	let data = {
			userId: userId
	}
	$.ajax({
		url: '/tiles/member/restful',
		type: 'post',
		data: data,
		success: function(data, xhr,status) {
			console.log(data);
			if(data == 'available'){
				alert('사용가능합니다.');
				valid =true;
			}else {
				alert('중복된 아이디');
				valid = false;
			}
		}, 
		error: function() {
			alert('에러발생');
		}
	});
}