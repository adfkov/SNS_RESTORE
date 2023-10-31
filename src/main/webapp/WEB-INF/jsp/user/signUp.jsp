<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="sign-up-box">
		<h1 class="m-4 font-weight-bold">회원가입</h1>
		<form id="signUpForm" method="post" action="/user/sign_up">
			<span class="sign-up-subject">ID</span>
			<%-- 인풋 옆에 중복확인 버튼을 옆에 붙이기 위해 div 만들고 d-flex --%>
			<div class="d-flex ml-3 mt-3">
				<input type="text" id="loginId" name="loginId" class="form-control col-6" placeholder="ID를 입력해주세요">
				<button type="button" id="loginIdCheckBtn" class="btn btn-success">중복확인</button>
			</div>

			<%-- 아이디 체크 결과 --%>
			<div class="ml-3 mb-3">
				<div id="idCheckLength" class="small text-danger d-none">ID를 4자 이상 입력해주세요.</div>
				<div id="idCheckDuplicated" class="small text-danger d-none">이미 사용중인 ID입니다.</div>
				<div id="idCheckOk" class="small text-success d-none">사용 가능한 ID 입니다.</div>
			</div>

			<span class="sign-up-subject">Password</span>
			<div class="m-3">
				<input type="password" name="password" class="form-control col-6" placeholder="비밀번호를 입력하세요">
			</div>

			<span class="sign-up-subject">Confirm password</span>
			<div class="m-3">
				<input type="password" name="confirmPassword" class="form-control col-6" placeholder="비밀번호를 입력하세요">
			</div>

			<span class="sign-up-subject">Name</span>
			<div class="m-3">
				<input type="text" name="name" class="form-control col-6" placeholder="이름을 입력하세요">
			</div>

			<span class="sign-up-subject">이메일</span>
			<div class="m-3">
				<input type="text" name="email" class="form-control col-6" placeholder="이메일을 입력하세요">
			</div>

			<br>
			<div class="d-flex justify-content-center m-3">
				<button type="submit" id="signUpBtn" class="btn btn-info">가입하기</button>
			</div>
		</form>
		
		<script>
			$(document).ready(function() {
				$('#loginIdCheckBtn').on('click', function() {
					$('#idCheckLength').addClass('d-none');
					$('#idCheckDuplicated').addClass('d-none');
					$('#idCheckOk').addClass('d-none');
					let loginId = $('#loginId').val().trim();
				
				$.ajax({
					url:"/user/is_duplicated_id"
					,data:{"loginId":loginId}
					
					, success: function(data) {
						if(data.isDuplicated) {
							$('#idCheckDuplicated').removeClass('d-none');

						} else {
							$('#idCheckOk').removeClass('d-none');
						}
					}
					, error: function(request, status, error) {
						alert("중복확인에 실패했습니다.");
					}
					});
				});
				
			$('#signUpForm').on('submit', function(e) {
				e.preventDefault();
				
				let loginId = $('#loginId').val();
			 	let password = $('input[name="password"]').val();
				let confirmPassword = $('input[name="confirmPassword"]').val();
				let name = $('input[name="name"]').val().trim();
				let email = $('input[name="email"]').val().trim(); 
				
				console.log(loginId);
				if(loginId == "") {
					alert("아이디를 입력하세요.");
					return;
				}
			 	if(password == "") {
					alert("비밀번호를 입력하세요.");
					return;
				} else {
					if(!(confirmPassword)) {
						alert("비밀번호 확인란을 입력하세요.");
						return;
					} else if(password != confirmPassword){
						alert("비밀번호와 비밀번호 확인란이 다릅니다.");
						return;
					}

				}
			 	if(name == "") {
					alert("이름을 입력하세요.");
					return;
				}
			 	if(email == "") {
					alert("이메일을 입력하세요.");
					return;
				}
			
			
				})
			});
		</script>
	</div>
</div>