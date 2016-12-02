<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: imac
  Date: 01.12.16
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post"  modelAttribute="user" action="signup">

    <form:label path="email">Email:</form:label>
    <br/>
    <form:input path="email"/>
    <br/>
    <form:label path="password">Password:</form:label>
    <br/>
    <form:input path="password"/>
    <br/>
    <br/>
    <form:button>Sign Up</form:button>

</form:form>
</body>
</html>
