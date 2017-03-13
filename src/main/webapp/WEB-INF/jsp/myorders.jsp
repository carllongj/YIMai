<%--
  Created by IntelliJ IDEA.
  User: carllongj
  Date: 2017/3/11
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>个人中心</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css"><!-- bootstrap-CSS -->
    <link rel="stylesheet" href="/css/bootstrap-select.css"><!-- bootstrap-select-CSS -->
    <link href="/css/style.css" rel="stylesheet" type="text/css" media="all" /><!-- style.css -->
    <link rel="stylesheet" href="/css/font-awesome.min.css" /><!-- fontawesome-CSS -->
    <link rel="stylesheet" href="/css/menu_sideslide.css" type="text/css" media="all"><!-- Navigation-CSS -->
    <style type="text/css">
        body {
            width:100%;
            min-width:1000px;
            width:expression_r(document.body.clientWidth < 1000 ? "1000px": "auto" );
        }
        #div1{height: 300px;border: 2px solid #dddddd;}
        #hr1{background: #dddddd;margin-top: 30px;width: 100%;height: 1px;}
        #div_top1{width: 4px;height: 26px;background: red;position: relative;left: 20px;top: 25px;}
        #span1{position: relative;top: 2px;left: 40px;}
        .top_right{float: right;position: relative;top: -30px;right: 30px;list-style: none;margin-left: 30px;}
        .div_top2{width: 2px;height: 20px;background:#dddddd;position: absolute;right: 60px;top: 2px;}
        #img1{width: 200px;height: 222px;float: left;}
        #div_above1{width: 25%;height:222px;/*border: 1px solid red; */margin-bottom: -200px;position: relative;left: 30%;top: ;clear: both;}
        .div_above11{position: relative;left: 10%;top: -80%}
        #div_above2{width: 16%;height:222px;/*border: 1px solid red; */margin-bottom: -200px;position: relative;left: 56%;bottom: 16%}
        .div_above21{position: relative;left: 10%;top: -70%}
        #div_above3{width: 16%;height:222px;/*border: 1px solid red;*/ margin-bottom: -200px;position: relative;left: 73%;bottom: 24%}
        .div_above31{position: relative;left: 4%;top: -72%}
    </style>
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
    <!-- 引入操作cookie的jquery -->
    <script src="/js/jquery.cookie-1.4.1.min.js"></script>
    <script src="/js/page/common.js"></script>
    <script src="/js/page/orders.js"></script>
    <script>
        var orders = '${orders}';
    </script>
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
                    <a id="userinfomation" href="/page/signin.action" aria-expanded="false"><i class="fa fa-user" aria-hidden="true"></i> 登录</a>
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
<div class="w3layouts-breadcrumbs text-center">
    <div class="container">
        <span class="agile-breadcrumbs"><a href="/index.action"><i class="fa fa-home home_1"></i></a> / <span>用户信息</span></span>
    </div>
</div>
<section class="row">
    <div class="list-group col-xs-2">
        <span class="list-group-item text-center" style="background-color:lightgray;
        font-size: 20px;font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif">我的交易</span>
        <a href="javascript:myorders()" class="list-group-item text-center">我的订单</a>
        <a href="#" class="list-group-item text-center">我的账单</a>
        <a href="#" class="list-group-item text-center">卖出的商品</a>
        <a href="#" class="list-group-item text-center">已买的商品</a>
        <span class="list-group-item text-center" style="background-color:lightgray;
        font-size: 20px;font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif">个人中心</span>
        <a href="#" class="list-group-item text-center">个人信息</a>
        <a href="#" class="list-group-item text-center">收货地址</a>
        <span class="list-group-item text-center" style="background-color:lightgray;
        font-size: 20px;font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif">我的钱包</span>
        <a href="#" class="list-group-item text-center">我的余额</a>
    </div>
    <div class="total-ads main-grid-border">
        <div class="container">
            <div class="ads-grid">
                <div class="agileinfo-ads-display col-md-10">
                    <div id="div1" class="col-xs-12">
                        <div id="div_top1"></div>
                        <span id="span1">我的订单</span>
                        <ul >
                            <li class="top_right">全部订单<div class="div_top2" style="position: absolute;left: -16px;"></div></li>
                            <li class="top_right">待评价<div class="div_top2"></div></li>
                            <li class="top_right">待收货<div class="div_top2"></div></li>
                            <li class="top_right">待付款</li>
                        </ul>
                        <div id="hr1"> </div>
                        <image id="img1" src="http://img3x9.ddimg.cn/50/30/22788959-1_b_3.jpg"></image>
                        <div id="div_above1">
                            <span class="div_above11" style="color: black;">共一件商品</span>
                            <br> <span class="div_above11" style="color: #726f68">2017-3-12 10：40：18</span></div>
                        <div id="div_above2"><span class="div_above21" style="color: black;">60.00</span> <br><span class="div_above21"  style="color: #726f68">网上支付</span></div>
                        <div id="div_above3">
                            <span class="div_above31" style="color: red;">等待收货</span>
                            <br>
                            <span class="div_above31">派送中派送中派送中派送中派送中</span></div>
                        <span style="position: relative;left:94%;top: -84%;clear: both;float: left;">查看</span>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</section>
<!-- //header -->

<!--footer section start-->
<footer>
    <div class="agileits-footer-bottom text-center">
        <div class="container">
            <div class="w3-footer-logo">
                <h1><a href="/index.action"><span>易卖</span>网</a></h1>
            </div>
            <div class="w3-footer-social-icons">
                <ul>
                    <li><a class="facebook" href="#"><i class="fa fa-facebook" aria-hidden="true"></i><span>Facebook</span></a></li>
                    <li><a class="twitter" href="#"><i class="fa fa-twitter" aria-hidden="true"></i><span>Twitter</span></a></li>
                    <li><a class="flickr" href="#"><i class="fa fa-flickr" aria-hidden="true"></i><span>Flickr</span></a></li>
                    <li><a class="googleplus" href="#"><i class="fa fa-google-plus" aria-hidden="true"></i><span>Google+</span></a></li>
                    <li><a class="dribbble" href="#"><i class="fa fa-dribbble" aria-hidden="true"></i><span>Dribbble</span></a></li>
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
