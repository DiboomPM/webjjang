<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>공지 등록 폼</h1>
<form action="write.do" method="post">
	제목 : <input name="title"><br/>
	내용 : <textarea rows="5" name="content" ></textarea> <br>
	공지 시작일(yyyy-mm-dd) : <input name="startDate"><br/>
	공지 종료일(yyyy-mm-dd) : <input name="endDate"><br/>
	<button>등록</button>
</form>
</body>
</html>