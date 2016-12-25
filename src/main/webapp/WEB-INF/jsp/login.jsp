<%--
  Created by IntelliJ IDEA.
  User: carllongj
  Date: 2016/12/25
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="/css/my/login.css">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/js/jquery-2.2.4.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/my/login.js"></script>
    <link rel="stylesheet" href="/css/validator/bootstrapValidator.css">
    <link rel="stylesheet" href="/css/validator/bootstrapValidator.min.css">
    <script type="text/javascript" src="/js/validator/bootstrapValidator.js" ></script>
    <script type="text/javascript" src="/js/validator/bootstrapValidator.min.js" ></script>
    <script>
        var redirect = "${redirect}";
    </script>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="javascript:showIndex()">易卖网首页</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="javascript:goRegister()">没有帐号,去注册</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<form class="form-horizontal" role="form" id="userdataform">
    <div class="form-group">
        <label for="inputUsername" class="col-sm-8 control-label">用户名</label>
        <div class="col-sm-3">
            <input type="text" name="username" class="form-control" id="inputUsername" placeholder="请输入用户名">
        </div>
        <div class="col-sm-1"></div>
    </div>
    <div class="form-group">
        <label for="inputPassword" class="col-sm-8 control-label">密码</label>
        <div class="col-sm-3">
            <input type="password" name="passwd" class="form-control" id="inputPassword" placeholder="请输入密码">
        </div>
        <div class="col-sm-1"></div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-8 col-sm-2">
            <div class="checkbox">
                <label>
                    <input type="checkbox"> 记住我
                </label>
            </div>
        </div>
        <div class="col-sm-2" id="passwddiv">
            <a href="javascript:lostPwd()">忘记密码</a>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-8 col-sm-4">
            <button type="button" id="loginButton" class="btn btn-default">登录</button>
        </div>
    </div>
</form>
</body>
</html>