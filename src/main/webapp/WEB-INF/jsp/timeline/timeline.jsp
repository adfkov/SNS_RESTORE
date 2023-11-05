<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center">
	<div class="contents-box w-50">

		<%-- 글쓰기 영역(로그인 된 사람만 보이게) --%>
		<c:if test="${not empty userId}">
		
		<div class="write-box border rounded m-3">
				<input type="text" class="form-control" id="subject" value="" placeholder="제목을 입력하세요">
		
			<textarea id="writeTextArea" placeholder="내용을 입력해주세요" class="w-100 border-0"></textarea>
			
			<%-- 이미지 업로드를 위한 아이콘과 업로드 버튼을 한 행에 멀리 떨어뜨리기 위한 div --%>
			<div class="d-flex justify-content-between">
				<div class="file-upload d-flex">
				<%-- file 태그를 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 효과 --%>
				<input type="file" id="file" accept=".jpg, .png, .gif, .jpeg" class="d-none">
					<a href="#" id="fileUploadBtn"><img width="35" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"></a>
				
				<%-- 업로드된 임시 파일명 노출 --%>
				<div id="fileName" class="ml-2"></div>
				</div>
				<button id="writeBtn" class="btn btn-info">게시</button>
			</div>
		</div> <%--// 글쓰기 영역 끝 --%>
		</c:if>
		
		<%-- 타임라인 영역 --%>
		<div class="timeline-box my-5">
			<c:forEach items="${postList}" var="post">
			<%-- 카드1 --%>
			<div class="card border rounded mt-3">
				<%-- 글쓴이, 더보기(삭제) --%>
				<div class="p-2 d-flex justify-content-between">
					<span class="font-weight-bold" id="userIdSpan">${post.userId}</span>
					<span class="postId d-none">${post.id}</span>
					
					<a href="#" class="more-btn">
						<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
					</a>
				</div>	
				
				<%-- 카드 이미지 --%>
				<c:if test="${not empty post.imagePath}">
				<div class="card-img">
					<img src="${post.imagePath}" class="img" alt="본문 이미지" width="100" height="100">
				</div>
				</c:if>
				
				<%-- 좋아요 --%>
				<div class="card-like m-3">
					<a href="#" class="like-btn">
						<img src="https://www.iconninja.com/files/214/518/441/heart-icon.png" width="18" height="18" alt="filled heart">
					</a>
					좋아요 11개
				</div>
				
				<%-- 글 --%>
				<div class="card-post m-3">
					<span class="font-weight-bold">${post.userId}</span>
					<span class="postContent">${post.content}</span>
				</div>
				
				<%-- 댓글 제목 --%>
				<div class="card-comment-desc border-bottom">
					<div class="ml-3 mb-1 font-weight-bold">댓글</div>
				</div>
				
				<%-- 댓글 목록 --%>
				<div class="card-comment-list m-2">
					<%-- 댓글 내용들 --%>
					<div class="card-comment m-1">
						<span class="font-weight-bold">댓글쓴이</span>
						<span>댓글 내용</span>
						
						<%-- 댓글 삭제 버튼 --%>
						<a href="#" class="comment-del-btn">
							<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10" height="10">
						</a>
					</div>
					
					<%-- 댓글 쓰기 --%>
					<div class="comment-write d-flex border-top mt-2">
						<input type="text" class="form-control border-0 mr-1 comment-input" placeholder="댓글 달기"/> 
						<button type="button" class="comment-btn btn btn-light" data-post-id="${card.post.id}">게시</button>
					</div>
				</div> <%--// 댓글 목록 끝 --%>
			</div> <%--// 카드1 끝 --%>
			</c:forEach>
		</div> <%--// 타임라인 영역 끝  --%>
	</div> <%--// contents-box 끝  --%>
</div>


<script>
	$(document).ready(function() {
		// 파일 이미지 클릭 => 숨겨져 있던 type="file"을 동작시킨다.
		$('#fileUploadBtn').on('click', function(e) {
			e.preventDefault(); // a태그의 올라가는 현상 방지
			//alert("클릭");
			$('#file').click();
		});
		
		// 이미지를 선택하는 순간 -> 유효성 확인 및 업로드 된 파일명 노출
		$("#file").on('change', function(e) {
			let fileName = e.target.files[0].name; // target -> this
			console.log(fileName); //스크린샷(1).png
			
			// 확장자 유효성 확인
			let ext = fileName.split(".").pop().toLowerCase();
			
			if (ext != 'jpg' && ext != 'gif' && ext != 'png' && ext != 'jpeg') {
				alert("이미지 파일만 업로드 할 수 있습니다.");
				$('#file').val(""); // 파일 태그에 파일 제거(보이지 않지만 업로드 될 수 있으므로 주의)
				$('#fileName').text("");
				return;
				// 태그 사이는 text 함수
				
			}
			// 유효성 통과한 이미지는 업로드 된 파일명 노출
			$('#fileName').text(fileName);
		
		});
		
		// 내용 입력 후 글쓰기 버튼 눌렀을 때
		$("#writeBtn").on('click', function() {
			let subject = $('#subject').val().trim();
			let content = $('#writeTextArea').val().trim();
			if(subject == "") {
				alert("제목을 입력하세요.");
				return;
			}
			if(content == "") {
				alert("내용을 입력하세요.");
				return;
			}
			let fileName = $('#file').val();
			//alert(fileName); // C:\fakepath\스크린샷(1).png
			let formData = new FormData();
			formData.append("subject", subject);
			formData.append("content", content);
			formData.append("file",$('#file')[0].files[0]);
			
			$.ajax({
				type:"POST"
				, url:"/post/create-post"
				, data: formData
				, enctype : "multipart/form-data" // 파일 업로드를 위한 필수 설정
				, processData:false  // 파일 업로드를 위한 필수 설정
				, contentType: false  // 파일 업로드를 위한 필수 설정
				
				, success : function(data) {
					if(data.code == 200) {
						alert("메모가 저장되었습니다.");
						
						location.href = "/timeline/timeline-view";
					} else {
						alert(data.errorMessage);
						
					}
				}
				, error : function(request, status, error) {
					alert("글을 저장하는 데 실패");
					
				}
			})
		})
		
		
		// 댓글 게시 버튼 눌렀을 때 
		$(".comment-btn").on('click', function() {
			let userId = $('#userIdSpan').text();
			let postId  = $('.postId').text();
			let commentContent = $('.comment-input').val();
			
			$.ajax({
				// request
				type:"POST"
				,url:"/comment/add-comment"
				,data:{"userId":userId, "postId":postId, "commentContent":commentContent}
				,success: function(data) {
					if(data.code == 200) {
						
						alert("댓글이 달렸습니다.");
						location.href="/timeline/timeline-view";
					} else {
						alert(data.errorMessage);
					}
				}	
			//
			})
		})
	});
</script>