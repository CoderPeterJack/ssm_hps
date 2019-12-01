<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE HTML>
<head>
    <title>高并发点赞</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<div id="moods">
    <b>说说列表：</b><br>
    <c:forEach items="${moods}" var="mood">
        <br>
        <b>用户：</b><span id="account">${mood.userName}</span> </br>
        <b>说说内容：</b><span id="content">${mood.content}</span><br>
        <b>发表时间：</b>
        <span id="publish_time">
            ${mood.publishTime}
        </span><br>
        <b>点赞数：</b><span id="praise_num">${mood.praiseNum}</span><br>

        <div style="margin-left: 350px">
<%--重点，每次点赞都会向后端发起请求，并把参数moodId和userId传递给后端--%>
            <a id="praise" href="/mood/${mood.id}/praise?userId=
            ${mood.userId}">赞</a>
        </div>
    </c:forEach>
</div>
</body>
<script></script>
</html>
