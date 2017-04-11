<%--
  Created by IntelliJ IDEA.
  User: carllongj
  Date: 2017/3/2
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>注册</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css"><!-- bootstrap-CSS -->
    <link rel="stylesheet" href="/css/bootstrap-select.css"><!-- bootstrap-select-CSS -->
    <link href="/css/style.css" rel="stylesheet" type="text/css" media="all" /><!-- style.css -->
    <link rel="stylesheet" href="/css/font-awesome.min.css" /><!-- fontawesome-CSS -->
    <link rel="stylesheet" href="/css/menu_sideslide.css" type="text/css" media="all"><!-- Navigation-CSS -->
    <!-- meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //meta tags -->
    <!--fonts-->
    <link href='//fonts.googleapis.com/css?family=Ubuntu+Condensed' rel='stylesheet' type='text/css'>
    <link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
    <!--//fonts-->
    <!-- js -->
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <!-- js -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="/js/bootstrap.js"></script>
    <script src="/js/bootstrap-select.js"></script>
    <script>
        $(document).ready(function () {
            var mySelect = $('#first-disabled2');

            $('#special').on('click', function () {
                mySelect.find('option:selected').prop('disabled', true);
                mySelect.selectpicker('refresh');
            });

            $('#special2').on('click', function () {
                mySelect.find('option:disabled').prop('disabled', false);
                mySelect.selectpicker('refresh');
            });

            $('#basic2').selectpicker({
                liveSearch: true,
                maxOptions: 1
            });
        });
    </script>
    <!-- language-select -->
    <script type="text/javascript" src="/js/jquery.leanModal.min.js"></script>
    <link href="/css/jquery.uls.css" rel="stylesheet"/>
    <link href="/css/jquery.uls.grid.css" rel="stylesheet"/>
    <link href="/css/jquery.uls.lcd.css" rel="stylesheet"/>
    <!-- Source -->
    <script src="/js/jquery.uls.data.js"></script>
    <script src="/js/jquery.uls.data.utils.js"></script>
    <script src="/js/jquery.uls.lcd.js"></script>
    <script src="/js/jquery.uls.languagefilter.js"></script>
    <script src="/js/jquery.uls.regionfilter.js"></script>
    <script src="/js/jquery.uls.core.js"></script>
    <script>
        $( document ).ready( function() {
            $( '.uls-trigger' ).uls( {
                onSelect : function( language ) {
                    var languageName = $.uls.data.getAutonym( language );
                    $( '.uls-trigger' ).text( languageName );
                },
                quickList: ['en', 'hi', 'he', 'ml', 'ta', 'fr'] //FIXME
            } );
        } );
    </script>
    <!-- //language-select -->
</head>
<body>
<!-- Navigation -->
<div class="agiletopbar">
    <div class="wthreenavigation">
        <div class="menu-wrap">
            <nav class="menu">
                <div class="icon-list"></div>
            </nav>
            <button class="close-button" id="close-button">关闭</button>
        </div>
        <button class="menu-button" id="open-button"> </button>
    </div>
    <div class="clearfix"></div>
</div>
<!-- //Navigation -->
<!-- header -->
<header>
    <div class="w3ls-header"><!--header-one-->
        <div class="w3ls-header-left">
            <p><a href="mobileapp.html"><i class="fa fa-download" aria-hidden="true"></i>下载App</a></p>
        </div>
        <div class="w3ls-header-right">
            <ul>
                <li class="dropdown head-dpdn">
                    <a href="/page/signin.action" aria-expanded="false"><i class="fa fa-user" aria-hidden="true">登录</i></a>
                </li>
                <li class="dropdown head-dpdn">
                    <a href="help.html"><i class="fa fa-question-circle" aria-hidden="true"></i> 帮助</a>
                </li>
                <li class="dropdown head-dpdn">
                    <a href="#"><span class="active uls-trigger"><i class="fa fa-language" aria-hidden="true"></i>语言</span></a>
                </li>
            </ul>
        </div>
        <div class="clearfix"> </div>
    </div>
    <div class="container">
        <div class="agile-its-header">
            <div class="logo">
                <h1><a href="/index.action"><span>易卖</span>网</a></h1>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</header>
<!-- //header -->
<!-- sign up form -->
<!--注册验证的js-->
<script src="/js/page/signup.js"></script>
<script src="/js/page/common.js"></script>
<section>
    <div id="agileits-sign-in-page" class="sign-in-wrapper">
        <div class="agileinfo_signin">
            <h3 id="formContent">注册</h3>
            <form id="registerForm">
                <input type="text" id="username" name="username" placeholder="请输入您的用户名" required="">
                <input type="email" id="useremail" name="email" placeholder="请输入您的邮箱" required="">
                <input type="password" id="userpassword" name="passwd" placeholder="请输入您的密码" required="">
                <input type="password" id="retypepassword" placeholder="请再次确认您的密码" required="">
                <button type="button" id="submitBtn" style="display: inline-block;background: #0099e5;width: 100%;height: 48px;position: relative;">
                    <em style="position: relative;color:white;top: 20%;font-family: 'Open Sans', sans-serif;font-style: normal;font-size: 1.7rem;">注册</em>
                </button>
            </form>
        </div>
    </div>
</section>
<!-- //sign up form -->
<!--footer section start-->
<footer>
    <div class="agileits-footer-bottom text-center">
        <div class="container">
            <div class="w3-footer-logo">
                <h1><a href="/index.action"><span>易卖</span>网</a></h1>
            </div>
            <div class="w3-footer-social-icons">
                <ul>
                    <li><a class="facebook" href="#"><i class="fa fa-qq"
                                                        aria-hidden="true"></i><span>QQ</span></a>
                    </li>
                    <li><a class="twitter" href="#"><i class="fa fa-wechat" aria-hidden="true"></i><span>微信</span></a>
                    </li>
                    <li><a class="flickr" href="#"><i class="fa fa-weibo"
                                                      aria-hidden="true"></i><span>微博</span></a>
                    </li>
                    <li><a class="googleplus" href="#"><i class="fa fa-tencent-weibo"
                                                          aria-hidden="true"></i><span>腾讯微博</span></a>
                    </li>
                    <li><a class="dribbble" href="#"><i class="fa fa-github"
                                                        aria-hidden="true"></i><span>Github</span></a>
                    </li>
                </ul>
            </div>
            <div class="copyrights">
                <p> © 2016 易卖 All Rights Reserved </p>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</footer>
<!--footer section end-->
</body>
<!-- Navigation-JavaScript -->
<script src="/js/classie.js"></script>
<script src="/js/main.js"></script>
<!-- //Navigation-JavaScript -->
<!-- here stars scrolling icon -->
<script type="text/javascript">
    $(document).ready(function() {
        /*
         var defaults = {
         containerID: 'toTop', // fading element id
         containerHoverID: 'toTopHover', // fading element hover id
         scrollSpeed: 1200,
         easingType: 'linear'
         };
         */

        $().UItoTop({ easingType: 'easeOutQuart' });

    });
</script>
<!-- start-smoth-scrolling -->
<script type="text/javascript" src="/js/move-top.js"></script>
<script type="text/javascript" src="/js/easing.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function($) {
        $(".scroll").click(function(event){
            event.preventDefault();
            $('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
        });
    });
</script>
<!-- start-smoth-scrolling -->
<!-- //here ends scrolling icon -->
</html>