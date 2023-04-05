<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>이미지 등록 폼</h1>
<form action="write.do" method="post" enctype="multipart/form-data">
	제목 : <input name="title"><br/>
	첨부이미지 : <input name="files" type="file"><br/>
	첨부이미지 : <input name="files" type="file"><br/>
	첨부이미지 : <input name="files" type="file"><br/>
	첨부이미지 : <input name="files" type="file"><br/>
	첨부이미지 : <input name="files" type="file"><br/>
	<button>등록</button>
</form>
</body>
</html>