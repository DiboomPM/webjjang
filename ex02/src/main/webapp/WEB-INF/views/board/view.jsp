<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!-- <link rel="stylesheet"href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> -->
<!-- <script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script> -->
<!-- <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->

<style type="text/css">
#deleteDiv {
	display: none;
}
ul.chat > li:hover{
	background: #eee;
	cursor: pointer;
}
</style>

<!-- reply.js에 객체 가져오기   -->
<script type="text/javascript" src="/js/reply.js"></script>

<script type="text/javascript">
	$(function() {

		console.log(replyService)

		var no = ${vo.no};
		var replyUL = $(".chat");
		var replyPageFooter = $("#replyPageDiv");

		var page = 1;

		//댓글을 가져와서 출력해 주는 함수 호출 
		showList(page)

		function showList(page) {
			// replyService.list() 테스트 
			replyService.list(
			// 넘어가는 데이터
			{page : page, bno : no},
			// 성공했을 때의 함수
			function(data) {
				//alert(data);
				var list = data.list;
				var pageObject = data.pageObject;

				// 추가 해야할 li 태그들을 저장하는 변수 선언
				var str = "";

				//데이터가 없는 경우 
				if (list == null || list.length == 0) {
					replyUL.html("<li>댓글이 존재하지 않습니다. </li>");
					return;
				}

				// 댓글이 있는 경우 처리 -> UL태그 안에 들어갈 li 태그 작성
				for (var i = 0, len = list.length; i < len; i++) {
					//console.log(list[i]);
					str += "<li class='left clearfix' data-rno='"+list[i].rno + "'>";
					str += "<div>";
					str += "<div class='header'>";
					str += "<strong class='primary-font'>"
							+ list[i].replyer + "</strong>";
					str += "<small class='pull-rigth text-muted'>"
							+ list[i].replyDate + "</small>";
					str += "</div>";
					str += "<p>" + list[i].reply + "</p>";
					str += "</div>";
					str += "</li>";
				}

				replyUL.html(str);

				//페이지네이션 코드 작성하기 - pageObject를 사용하여  
				str = "";
				str += "<ul class='pagination'>";
				str += "<li class='page-item ";
				if(pageObject.startPage == 1) str += "disabled";
				str += "'><a class='page-link' href='#'>Previous</a></li>";
				for(var i = pageObject.startPage; i <= pageObject.endPage; i++){
				  str += "<li class='page-item ";
				  if(page == i) str += "active";
				  str += "'><a class='page-link' href='#'>"+i+"</a></li>";
				}
				str += "<li class='page-item ";
				if(pageObject.endPage == pageObject.totalPage) str += "disabled";
				str += "'><a class='page-link' href='#'>Next</a></li>";
				str += "</ul>";

				// 댓글 페이지 네이션 출력
				replyPageFooter.html(str);
				
				
			});
		};

		// 게시판 글보기 이벤트 처리
		$("#deleteBtn").click(function() {
// 			alert("삭제 버튼 클릭");
			$("#deleteDiv").slideDown();
		});
		$("#cancelBtn").click(function() {
			$("#deleteDiv").slideUp();
		});

		//모달 창을 보이게 - 댓글 등록 버튼 : 댓글 제목 오른쪽 버튼
		$("#replyWriteBtn").click(function(){
			// 댓글 모달창 제목 바꾸기
			$("#replyModalTitle").text("댓글 등록 모달 창");

			// 댓글 번호 숨김
			$("#rnoDiv").hide();

			// 필요없는 버튼 숨김 - 수정, 삭제
			$("#modalUpdateBtn, #modalDeleteBtn").hide();

			// 필요한 버튼 - 등록 표시 
			$("#modalWriteBtn").show();

			// 입력되어 있는 데이터 지우기 
			$("#reply").val("");
			
			// 모달창 보이게 설정
			$("#replyModal").modal("show");
		});

		// 댓글 등록에 처리 버튼 - 모달 창에 있는 버튼
		$("#modalWriteBtn").click(function(){
			//alert("댓글 등록 처리")
			
			// 데이터 수집해서 replyService.write()에 보낸다.
			var reply = {bno: no, reply : $("#reply").val()};

			// replyService.write()로 보낸다.
			replyService.write(
				reply,
				function(result){
					page = 1;
					showList(page);
					if(result) alert(decodeURI(result.replaceAll("+", " ")));
// 					if(result) alert(result);
					else alert("댓글 등록이 되었습니다.");
					$("#replyModal").modal("hide");
				}
			);
						
			// 모달 창을 안보이게 한다 
			$("#replyModal").modal("hide");
		}); // 댓글 등록 처리 끝 

		// 댓글을 클릭하면 동작되는 이벤트 생성
		// 댓글 한개는 나중에 처리되서 나타난 내용에 해당된다. 이벤트 처리할 당시에는 소스가 없었다.
		// 댓글 전체는 처음부터 있었음. 있는 전체에 이벤트 처리 -> on(이벤트, 함수)
		$("ul.chat").on("click","li", function(){
// 			alert("댓글 한개 클릭");
			// 클릭한 태그 안에 data-rno=34 
			var rno = $(this).data("rno");
			var reply = $(this).find("p").text();
// 			alert("rno = " + rno + ", reply =" + reply);

			// 모달에 데이터 셋팅
			$("#rno").val(rno);
			$("#reply").val(reply);

			// 모달창 제목 바꾸기 
			$("#replyModalTitle").text("댓글 수정/삭제 모달 창");

			// 모달 처리 버튼
			$("#modalWriteBtn").hide();
			$("#modalUpdateBtn, #modalDeleteBtn").show();

			// 댓글 번호 보여지게 
			$("#rnoDiv").show();
			
			// 모달 보여주기
			$("#replyModal").modal("show");
		});

		//모달 창안에 수정 버튼 이벤트 
		$("#modalUpdateBtn").click(function(){
			alert("댓글 수정 처리 진행");
			// 데이터 수집 - rno, reply
			var reply = {rno:$("#rno").val(), reply:$("#reply").val()};

			replyService.update(reply, function(result){
				// 수정이 성공되면 처리 내용
				// 1. 리스트 데이터를 다시 가져와서 표시한다.
				showList(page);
				// 2. 모달창은 닫는다.
				$("#replyModal").modal("hide");
				// 3. 메세지 모달창에 데이터 세팅하고 보여준다.
				if(result) alert(decodeURI(result.replaceAll("+", " ")));
				else alert("댓글 수정이 되었습니다.");
			});
		});

		//모달 창안에 삭제 버튼 이벤트 
		$("#modalDeleteBtn").click(function(){
// 			alert("댓글 삭제 처리 진행");
			
			//데이터 수집
			var rno = $("#rno").val();
			
			replyService.delete(rno, function(result){

				// 삭제 성공되면 처리 내용
				// 1. 리스트 데이터를 다시 가져와서 표시한다.
				page = 1;
				showList(page);
				// 2. 모달창은 닫는다.
				$("#replyModal").modal("hide");
				// 3. 메세지 모달창에 데이터 세팅하고 보여준다.
				if(result) alert(decodeURI(result.replaceAll("+", " ")));
				else alert("댓글 삭제가 완료 되었습니다.");

			});
		});

		// 댓글 페이지네이션 이벤트
		$("#replyPageDiv").on("click", "ul>li", function(){
			//alert("댓글 페이지 클릭");
			page = $(this).text();
			//alert(page);
// 			if($(this).hasClass("active")) 
// 				alert("현재 페이지 입니다.");
// 			else alert("현재 페이지가 아닙니다.")
			//현재 페이지가 아닌 경우 페이지를 이동 시킨다.
			if(!$(this).hasClass("active")) 
				showList(page);
			return false; // 페이지 이동 취소
		});
	});
</script>

</head>
<body>
	<div class="card shadow md-4">
		<div class="card-header py-3">게시판 글 보기</div>
		<div class="card-body">
			<table class="table">
				<tbody>
					<tr>
						<th>글번호</th>
						<td>${vo.no }</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>${vo.title }</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>${vo.content }</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>${vo.writer }</td>
					</tr>
					<tr>
						<th>작성일</th>
						<td><fmt:formatDate pattern="yyyy-MM-dd"
								value="${vo.writeDate }" /></td>
					</tr>
					<tr>
						<th>조회수</th>
						<td><fmt:formatNumber pattern="#,###" value="${vo.hit }" /></td>
					</tr>
				</tbody>
			</table>
			<a href="update.do?no=${vo.no }" class="btn btn-default">수정</a> <a
				href="#" class="btn btn-default" onclick="return false"
				id="deleteBtn">삭제</a> <a href="list.do" class="btn btn-default">리스트</a>
			<div id="deleteDiv">
				<form action="delete.do" method="post">
					<input name="no" value="${vo.no }" type="hidden">
					<div class="form-group">
						<label>본인 확인용 비밀번호 입력 :</label> <input name="pw"
							class="form-control" type="password">
					</div>
					<button class="btn btn-danger btn-sm">삭제</button>
					<button class="btn btn-warning btn-sm" type="button" id="cancelBtn">취소</button>
				</form>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-12">
			<div class="card">
				<div class="card-header">
					<i class="fa fa-comments fa-fw"></i> Reply
					<button id="replyWriteBtn" class="btn btn-primary btn-sm float-right">New Reply</button>
				</div>
				<div class="card-body">
					<!-- 댓글을 출력하는 UL - 데이터 한개당 li 태그 한개씩   -->
					<ul class="chat">
						<li class="left clearfix" data-rno="12">
							<div>
								<div class="header">
									<strong class="primary-font">작성자</strong> <small
										class="pull-rigth text-muted">2023-04-03</small>
								</div>
								<p>댓글 내용</p>
							</div>
						</li>
					</ul>
				</div>
				<div class="card-footer" id="replyPageDiv" ></div>
			</div>
		</div>
	</div>

	<!-- The Modal -->
	<div class="modal" id="replyModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title" id="replyModalTitle">REPLY Modal</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body form tag - 등록 : 댓글 내용, 수정 : 댓글 번호 -->
				<div class="modal-body">
					<div class="form-group" id="rnoDiv">
						<label>댓글 번호</label>
						<input name="rno" id="rno" class="form-control" readonly="readonly">
					</div>
					<div class="form-group">
						<label>댓글 내용</label>
						<textarea rows="5" class="form-control" name="reply" id="reply"></textarea>
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="modalWriteBtn">등록</button>
					<button type="button" class="btn btn-primary" id="modalUpdateBtn">수정</button>
					<button type="button" class="btn btn-primary" id="modalDeleteBtn">삭제</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
				</div>

			</div>
		</div>
	</div> 
</body>
</html>