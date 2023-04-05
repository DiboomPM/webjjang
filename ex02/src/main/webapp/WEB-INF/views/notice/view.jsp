<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>게시판 글 보기</h1>
<a href="update.do">수정</a>
<a href="list.do">리스트</a>
<div>
	<form action="delete.do" method="post">
		<input name="no" value="10">
		<input name="no" value="12">
		<input name="no" value="20">
		<button>삭제</button>
	</form>
</div>
</body>
</html>