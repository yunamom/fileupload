<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="C"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css?ver=1">
<meta charset="UTF-8">
<title>File Detail</title>
</head>
<body>
<%@ include file="topmenu.jsp" %>
<section>
<div class="title">${file.title }</div>
<div class="wrapper">

<div class="detail">
	<div class="wrapper"><img src="images/${file.fileName }"/></div>
	<p>${file.name }</p>
	<p>${file.title }</p>


</div>

</div>
</section>
</body>
</html>