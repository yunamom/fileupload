<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css?ver=1">
<script src="script/script.js"></script>
<title>파일 업로드</title>
</head>
<body>
<%@ include file="/include/topmenu.jsp" %>
<section>
<div class="title"> </div>
<div class="wrapper">
<fieldset >
	<legend class="legend">* 파일 업로드 *</legend>
	
	<form class="Boxfile" method="post" action="UploadService.do" enctype="multipart/form-data">
	<!-- 서버에 파일을 보낼것 이라고 알려주기 위해 enctype 옵션에 multipart/form-data 선언해주 -->
	<table>
		<tr>
		<td>Title : </td>
		<td><input class="input" type="text" name="title"/></td>
		</tr>
		<tr>
		<td>Name  : </td>
		<td><input class="input" type="text" name="name"/></td>
		</tr>
		<tr>
		<td>File  : </td>
		<td><input type="file" name="file"/></td>
		</tr>
		<tr>
		<td colspan="2" class="Boxbtn">
		<Button class="btn" type="submit" >Upload</Button>
		<Button class="btn" type="Button" onclick="location='Upload.do'">Reset</Button>
		</td>
		</tr>
	</table>
	</form>
	
</fieldset>
</div>
</section>
</body>
</html>