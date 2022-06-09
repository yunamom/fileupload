<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="C"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css?ver=1">
<meta charset="UTF-8">
<title>File List</title>
</head>
<body>
<%@ include file="topmenu.jsp" %>
<section>
<div class="title">파일목록</div>
<div class="wrapper">
	<div class="Box">
	<table>
		<tr>
			<th>No</th>
			<th>Title</th>
			<th>Name</th>
			<th>FileName</th>
			<th>Date</th>
		</tr>
		<C:forEach var="i" items="${list }">
			<tr>
				<td>${i.unq }</td>
				<td><p class="target" style="width:200px">${i.title }</p></td>
				<td><p class="target" style="width:80px">${i.name }</p></td>
				<td><p class="target" style="width:50px">${i.fileName }</p></td>
				<td><p class="target" style="width:80px">${i.uploadDate }</p></td>
			</tr>
		</C:forEach>
	</table>
	</div>
</div>
<div class="wrapper">
<div class="Boxprev">
<C:if test="${paging.start != 1 }">
<a class="range" href="?view=${paging.start-1}">이전</a>
</C:if>
</div>

<div class="Boxrange">
<C:forEach var="status" begin="${paging.start }" end="${paging.end }">
<a class="range" href="?view=${status }">${status }</a>
</C:forEach>

</div>

<div class="Boxnext">
<C:if test="${paging.end < paging.endPage}">
<a class="range" href="?view=${paging.end+1}">다음</a>
</C:if>
</div>
</div>
</section>
</body>
</html>