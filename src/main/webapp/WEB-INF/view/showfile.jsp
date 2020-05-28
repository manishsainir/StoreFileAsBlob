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

	<c:if test="${name == 'image'}">

		<img alt="img" src="data:image/png;base64,${file}" height="200"
			width="230" />
	</c:if>
	<c:if test="${name eq 'text'}">

		<pre>${file}</pre>
	</c:if>

</body>
</html>