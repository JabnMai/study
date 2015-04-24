<%@ page language="java" pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form method="POST" modelAttribute="user">
	username: <form:input path="username"/><br/>
	nickname: <form:input path="nickname"/><br/>
	password: <form:password path="password"/><br/>
	yourmail: <form:input path="email"/><br/>
	<input type="submit" value="添加新用户"/>
</form:form>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>