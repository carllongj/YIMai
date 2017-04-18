<%--
  Created by IntelliJ IDEA.
  User: carllongj
  Date: 2017/3/5
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>商品详情</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css"><!-- bootstrap-CSS -->
    <link rel="stylesheet" href="/css/bootstrap-select.css"><!-- bootstrap-select-CSS -->
    <link href="/css/style.css" rel="stylesheet" type="text/css" media="all" /><!-- style.css -->
    <link rel="stylesheet" href="/css/font-awesome.min.css" /><!-- fontawesome-CSS -->
    <link rel="stylesheet" href="/css/menu_sideslide.css" type="text/css" media="all"><!-- Navigation-CSS -->
    <link rel="stylesheet" href="/css/sweetalert.css" type="text/css">
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
    <script type="text/javascript" src="/js/sweetalert.min.js"></script>
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
    <link rel="stylesheet" href="/css/flexslider.css" media="screen" /><!-- flexslider css -->
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
            <p><a href="javascript:download()"><i class="fa fa-download" aria-hidden="true"></i>下载App</a></p>
        </div>
        <div class="w3ls-header-right">
            <ul>
                <li class="dropdown head-dpdn">
                    <a id="userinfomation" href="/page/signin.action" aria-expanded="false"><i class="fa fa-user" aria-hidden="true">登录</i></a>
                </li>
                <li class="dropdown head-dpdn">
                    <a href="help.html"><i class="fa fa-question-circle" aria-hidden="true"></i> 帮助</a>
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
            <div class="agileits_search">
                <form action="#" method="post">
                    <input name="Search" type="text" placeholder="您需要些什么?" required="" />
                    <select id="agileinfo_search" name="agileinfo_search" required="">
                        <option id="categoryListSelector" value="">所有分类</option>
                    </select>
                    <button type="submit" class="btn btn-default" aria-label="Left Align">
                        <i class="fa fa-search" aria-hidden="true"> </i>
                    </button>
                </form>
                <a class="post-w3layouts-ad" href="javascript:postMyAd()">发布我的商品</a>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</header>
<!-- //header -->
<!-- 数据处理 -->
<script type="text/javascript">
    var info = '${item}';
</script>
<script src="/js/jquery.cookie-1.4.1.min.js"></script>
<script src="/js/page/common.js"></script>
<script src="/js/page/single.js"></script>
<!-- breadcrumbs -->
<div class="w3layouts-breadcrumbs text-center">
    <div class="container">
			<span class="agile-breadcrumbs">
			<a href="index.html"><i class="fa fa-home home_1"></i></a> /
			<a href="all-classifieds.html">所有商品</a> /
			<a id="cateName"></a> /
			<span></span>
            </span>
    </div>
</div>
<!-- //breadcrumbs -->
<!--single-page-->
<div class="single-page main-grid-border">
    <div class="container">
        <div class="product-desc">
            <div class="col-md-7 product-view">
                <h2 id="itemTitle"></h2>
                <p></p>
                <div class="flexslider">
                    <ul class="slides">
                        <li data-thumb="images/ss1.jpg">
                            <img/>
                        </li>
                    </ul>
                </div>
                <!-- FlexSlider -->
                <script defer src="/js/jquery.flexslider.js"></script>

                <script>
                    // Can also be used with $(document).ready()
                    $(window).load(function() {
                        $('.flexslider').flexslider({
                            animation: "slide",
                            controlNav: "thumbnails"
                        });
                    });
                </script>
                <!-- //FlexSlider -->
                <div class="product-details">
                    <h4><span class="w3layouts-agileinfo">详细描述</span> :<p id="summaryDesc"></p><div class="clearfix"></div></h4>
                </div>
            </div>
            <div class="col-md-5 product-details-grid">
                <div class="item-price">
                    <div class="product-price">
                        <p class="p-price">价格</p>
                        <h3 class="rate"></h3>
                        <div class="clearfix"></div>
                    </div>
                    <div class="condition">
                        <p class="p-price">状态</p>
                        <h4></h4>
                        <div class="clearfix"></div>
                    </div>
                    <div class="itemtype">
                        <p class="p-price">商品类型</p>
                        <h4></h4>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="interested text-center">
                    <h4>有兴趣?<small> 联系卖家</small></h4>
                </div>
                <div class="clearfix"></div>
                <form id="buyItemFormInfo">
                    <input type="hidden" name="itemId" value="${id}">
                <div>
                   <button class="common-button-style" type="button">拍下</button>
                </div>
                </form>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!--//single-page-->
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