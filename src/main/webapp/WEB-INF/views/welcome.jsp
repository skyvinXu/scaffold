<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>欢迎页</title>
</head>
<body>
    <div style="width:98%;height:150px;padding:10px;background:#fafafa;">
        <p><b><shiro:principal></shiro:principal></b>,欢迎您登录<b>风落春泥-后台管理系统</b>!</p>
    </div>
</body>
</html>