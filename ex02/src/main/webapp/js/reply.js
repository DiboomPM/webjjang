/**
 *  댓글 객체 선언 사용
 */
 
 
 console.log("Reply Module.........");
 
 var replyService = (function(){
 	
 	//list(전달 데이터, 성공하면 실행할 함수, 실피하면 실행할 함수)
 	function list(param, callback, error){
 		var bno = param.bno;
 		var page = param.page;
 		// ajax 함수 한줄
 		$.getJSON(
 			"/boardreply/list.do?page=" + page + "&bno=" + bno, 
 			function(data){
 				// 성공하면 실행할 함수가 있다면 실행
 				if(callback){
 					console.log("data = " + JSON.stringify(data));
 					callback(data);
 				} // end if(callback)
 				else {
 					alert(data);
 				}
 			}// end function(data) - 성공했을 떄 실행 함수 끝
 		).fail(function(xhr, status, err ){ // 실패했을떄의 처리 시작
 			if(error){
 				error();
 			} else {
 				console.log("xhr = " + xhr);
 				console.log("status = " + status);
 				console.log("err = " + err);
 				alert("댓글 리스트를 가져오는데 문제가 발생했습니다.");
 			}
 		}) // end fail(function) 실패 했을떄의 처리 끝 
 		;
 	}
 	
 	// write()
 	function write(reply, callback, error){
 		console.log("write Reply......................................");
 		console.log("Reply = " + JSON.stringify(reply));
 		
 		// 서버로 데이터를 보내서 댓글 등록을 시킨다.
 		$.ajax({
 			url : "/boardreply/write.do",
 			type : "post",
 			data : JSON.stringify(reply),
 			contentType : "application/json; charset=UTF-8",
 			success : function(result, status, xhr){
 				if(callback){
 					callback(result);
 				} else {
 					alert("성공적으로 댓글 등록이 되었습니다")
 					console.log(result);
 				}
 			},
 			error : function(xhr, status, er){
 				console.log(xhr);
 				console.log(status);
 				console.log(er);
 				if(error){
 					error();
 				} else{
 					alert("댓글 등록에 실패하였습니다.")
 				}
 			} // end error function
 		}); // end $ajax
 	} // end function write()  
 	
 		// update()
 	function update(reply, callback, error){
 		console.log("update Reply......................................");
 		console.log("Reply = " + JSON.stringify(reply));
 		
 		// 서버로 데이터를 보내서 댓글 등록을 시킨다.
 		$.ajax({
 			url : "/boardreply/update.do",
 			type : "post",
 			data : JSON.stringify(reply),
 			contentType : "application/json; charset=UTF-8",
 			success : function(result, status, xhr){
 				if(callback){
 					callback(result);
 				} else {
 					alert("성공적으로 댓글 수정이 되었습니다")
 					console.log(result);
 				}
 			},
 			error : function(xhr, status, er){
 				console.log(xhr);
 				console.log(status);
 				console.log(er);
 				if(error){
 					error();
 				} else{
 					alert("댓글 등록 수정이 실패하였습니다.")
 				}
 			} // end error function
 		}); // end $ajax
 	} // end function update()  
 	
 	
 	
 	// delete()
 	function deleteFunc(rno, callback, error){
 		console.log("delete Reply......................................");
 		console.log("rno = " + rno);
 		
 		// 서버로 데이터를 보내서 댓글 등록을 시킨다.
 		$.ajax({
 			url : "/boardreply/delete.do?rno=" + rno,
 			//url : "/boardreply/delete.do",
 			type : "delete",
 			success : function(result, status, xhr){
 				if(callback){
 					callback(result);
 				} else {
 					alert("성공적으로 댓글 삭제가 되었습니다")
 					console.log(result);
 				}
 			},
 			error : function(xhr, status, er){
 				console.log(xhr);
 				console.log(status);
 				console.log(er);
 				if(error){
 					error();
 				} else{
 					alert("댓글 삭제에 실패하였습니다.")
 				}
 			} // end error function
 		}); // end $ajax
 	} // end function delete() 
 	
 
 	
 	return {
		//함수 리턴
		list:list, //replyService.list() 를 사용하면 쓸수있음 
		write:write, //replyService.write() 를 사용하면 쓸수있음 
		update:update, //replyService.update() 를 사용하면 쓸수있음 
		delete:deleteFunc, //replyService.delete() 를 사용하면 쓸수있음 
		
	};
 
 })();  //JSON 객체 선언 방법
 