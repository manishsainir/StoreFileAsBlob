<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th colspan="3">ImageName</th>

			<th colspan="3">TextFileName</th>

		</tr>

		<c:forEach var="i" items="${getName}">
			<tr>
				<td>${i[1]}</td>
				<td><a href="display?userId=${i[0]}&name=image">View</a></td>
				<td><a href="download?userId=${i[0]}&name=image">download</a></td>
				<td>${i[2]}</td>
				<td><a href="display?userId=${i[0]}&name=text">View</a></td>
				<td><a href="download?userId=${i[0]}&name=text">download</a></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>