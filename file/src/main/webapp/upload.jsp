<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css?ver=1">
<title>파일 업로드</title>
</head>
<body>
<%@ include file="topmenu.jsp" %>
<section>
<div class="title">파일 업로드</div>
<div class="wrapper">
<fieldset>
	<legend>파일 업로드</legend>
	<form method="post" action="UploadFile" enctype="multipart/form-data">
	<!-- 서버에 파일을 보낼것 이라고 알려주기 위해 enctype 옵션에 multipart/form-data 선언해주 -->
	<table>
		<tr>
		<td>작성자 : </td>
		<td><input class="input" type="text" name="name"/></td>
		</tr>
		<tr>
		<td>제 목 : </td>
		<td><input class="input" type="text" name="title"/></td>
		</tr>
		<tr>
		<td></td>
		<td><input type="file" name="file"/></td>
		</tr>
		<tr>
		<td colspan="2" class="Boxbtn">
		<Button class="btn" type="submit" >Upload</Button>
		<Button class="btn" type="Button" onclick="location='upload.jsp'">Reset</Button>
		</td>
		</tr>
	</table>
	</form>
</fieldset>
</div>
</section>
</body>
</html>