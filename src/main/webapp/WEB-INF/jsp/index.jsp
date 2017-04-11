<%--
  Created by IntelliJ IDEA.
  User: carllongj
  Date: 2017/3/1
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>易卖</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css"/><!-- bootstrap-CSS -->
    <link rel="stylesheet" href="/css/bootstrap-select.css"/><!-- bootstrap-select-CSS -->
    <link href="/css/style.css" rel="stylesheet" type="text/css" media="all" /><!-- style.css -->
    <link rel="stylesheet" href="/css/flexslider.css" type="text/css" media="screen" /><!-- flexslider-CSS -->
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
        <button class="menu-button" id="open-button"></button>
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
                    <a id="userinfomation" href="/page/signin.action" aria-expanded="false"><i class="fa fa-user" aria-hidden="true">登录</i></a>
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
            <div class="agileits_search">
                <form action="/query/list.action" method="post">
                    <input name="keyword" type="text" placeholder="您需要些什么?" required="" />
                    <select id="agileinfo_search" name="agileinfo_search" required="">
                        <option name="cid" id="categoryListSelector" value="">所有分类</option>
                    </select>
                    <button type="submit" class="btn btn-default" aria-label="Left Align">
                        <i class="fa fa-search" aria-hidden="true"> </i>
                    </button>
                </form>
                <a class="post-w3layouts-ad" href="/post/post_ad.action">发布我的商品</a>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</header>
<!-- //header -->
<!-- Slider -->
<div class="slider">
    <ul class="rslides" id="slider">
        <li>
            <div class="w3ls-slide-text">
                <h3>出售或者查询更多</h3>
                <a href="javascript:locateAtLogo()" class="w3layouts-explore-all">查询所有分类</a>
            </div>
        </li>
        <li>
            <div class="w3ls-slide-text">
                <h3>发现更好</h3>
                <a href="/query/list.action" class="w3layouts-explore">去探索</a>
            </div>
        </li>
    </ul>
</div>
<!-- //Slider -->
<!-- content-starts-here -->
<div class="main-content">
    <div id="allCategory" class="w3-categories">
        <h3>浏览分类</h3>
        <div id="showCategoryInfo" class="container">
        </div>
    </div>
    <!-- most-popular-ads -->
    <div class="w3l-popular-ads">
        <h3>最新</h3>
        <div id="newestAdvertisement" class="w3l-popular-ads-info">
        </div>
        <div class="clearfix"></div>
    </div>
    <!-- most-popular-ads -->
    <div class="trending-ads">
        <div class="container">
            <!-- slider -->
            <div class="agile-trend-ads">
                <h2>最近潮流</h2>
                <ul id="flexiselDemo3">
                    <li></li>
                    <li></li>
                    <li></li>
                </ul>
            </div>
        </div>
        <!-- //slider -->
    </div>
</div>
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
<!-- Navigation-Js-->
<script type="text/javascript" src="/js/main.js"></script>
<script type="text/javascript" src="/js/classie.js"></script>
<!-- //Navigation-Js-->
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
<!-- 广告位处理的js -->
<script type="text/javascript">
    var lastest = '${lastest}';
    var trending = '${trending}';
</script>
<!-- 引入操作cookie的js -->
<script type="text/javascript" src="/js/jquery.cookie-1.4.1.min.js"></script>
<script type="text/javascript" src="/js/page/common.js"></script>
<script type="text/javascript" src="/js/page/index.js"></script>
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
<script type="text/javascript" src="/js/jquery.flexisel.js"></script><!-- flexisel-js -->
<script type="text/javascript">
    $(window).load(function() {
        $("#flexiselDemo3").flexisel({
            visibleItems:1,
            animationSpeed: 1000,
            autoPlay: true,
            autoPlaySpeed: 5000,
            pauseOnHover: true,
            enableResponsiveBreakpoints: true,
            responsiveBreakpoints: {
                portrait: {
                    changePoint:480,
                    visibleItems:1
                },
                landscape: {
                    changePoint:640,
                    visibleItems:1
                },
                tablet: {
                    changePoint:768,
                    visibleItems:1
                }
            }
        });

    });
</script>
<!-- Slider-JavaScript -->
<script src="<c:url value="/js/responsiveslides.min.js"/>"></script>
<script>
    $(function () {
        $("#slider").responsiveSlides({
            auto: true,
            pager: false,
            nav: true,
            speed: 500,
            maxwidth: 800,
            namespace: "large-btns"
        });

    });
</script>
<!-- //Slider-JavaScript -->
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
<script type="text/javascript" src="<c:url value="/js/move-top.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/easing.js"/>"></script>
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
</body>
</html>
