<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="C"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css?ver=1">
<meta charset="UTF-8">
<title>File Detail</title>
</head>
<body>
<%@ include file="/include/topmenu.jsp" %>
<section>
<div class="title"> </div>
<div class="wrapper">
<fieldset >
	<legend class="legend">제목 : ${file.title }</legend>
	<div class="Boxdetail">작성자 : ${file.name } ∙ ${file.uploadDate} ∙ 조회수 : ${file.hits }</div>
	<div class="Boxfile">
	<img src="images/${file.fileName }"/>	
	</div>
</fieldset>
</div>
	<div class="Boxbtn">
		<Button class="btn" type="Button" onclick="history.back()">목록으로</Button>
	</div>

</section>
</body>
</html>