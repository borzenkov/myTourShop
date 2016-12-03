<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post"  modelAttribute="user" action="profile_editor">

    <form:label path="email">Email:</form:label>
    <br/>
    <form:input path="email" value="${user.email}"/>
    <br/>
    <form:label path="password">Password:</form:label>
    <br/>
    <form:input path="password"/>
    <br/>
    <br/>
    <form:button>Confirm</form:button>

</form:form>
</body>
</html>
