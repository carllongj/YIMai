<%--
  Created by IntelliJ IDEA.
  User: carllongj
  Date: 2016/12/27
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script src="/js/jquery-2.2.4.js"></script>
    <script src="/js/my/register.js"></script>
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- css files -->
    <link href="/css/my/style.css" rel='stylesheet' type='text/css' media="all" />
    <script src="/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/css/validator/bootstrapValidator.css">
    <link rel="stylesheet" href="/css/validator/bootstrapValidator.min.css">
    <script type="text/javascript" src="/js/validator/bootstrapValidator.js" ></script>
    <script type="text/javascript" src="/js/validator/bootstrapValidator.min.js" ></script>

    <!-- /css files -->
</head>
<body>
<h1>注册易卖网</h1>
<div class="log">
    <div class="content2">
        <h2>注册</h2>
        <form id="registerForm">
            <input type="text" id="username" name="username" placeholder="请输入用户名">
            <input type="password" id="passwd1" placeholder="请输入密码" >
            <input type="password" id="passwd2" name="passwd" placeholder="请再次输入密码">
            <input type="email" id="email" name="email" placeholder="请输入邮箱">
            <button type="button" id="registerBtn" class="register">注册</button>
        </form>
    </div>
    <div class="clear"></div>
</div>
<div class="footer">
    <p>Copyright &copy; 2016.易卖网 All rights reserved.<a target="_blank" href="http://localhost:8080">首页</a></p>
</div>
</body>
</html>