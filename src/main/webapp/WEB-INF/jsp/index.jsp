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
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>"><!-- bootstrap-CSS -->
    <link rel="stylesheet" href="<c:url value="/css/bootstrap-select.css"/>"><!-- bootstrap-select-CSS -->
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet" type="text/css" media="all" /><!-- style.css -->
    <link rel="stylesheet" href="<c:url value="/css/flexslider.css"/>" type="text/css" media="screen" /><!-- flexslider-CSS -->
    <link rel="stylesheet" href="<c:url value="/css/font-awesome.min.css"/>" /><!-- fontawesome-CSS -->
    <link rel="stylesheet" href="<c:url value="/css/menu_sideslide.css"/>" type="text/css" media="all"><!-- Navigation-CSS -->
    <!-- meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <%-- <meta name="keywords" content="er Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, Sony Ericsson, Motorola web design" />--%>
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
                <div class="icon-list">
                    <a href="mobiles.html"><i class="fa fa-fw fa-mobile"></i><span>移动产品</span></a>
                    <a href="electronics-appliances.html"><i class="fa fa-fw fa-laptop"></i><span>电子商品</span></a>
                    <a href="cars.html"><i class="fa fa-fw fa-car"></i><span>汽车</span></a>
                    <a href="bikes.html"><i class="fa fa-fw fa-motorcycle"></i><span>自行车</span></a>
                    <a href="furnitures.html"><i class="fa fa-fw fa-wheelchair"></i><span>家具</span></a>
                    <a href="pets.html"><i class="fa fa-fw fa-paw"></i><span>宠物</span></a>
                    <a href="books-sports-hobbies.html"><i class="fa fa-fw fa-book"></i><span>图书,运动,爱好</span></a>
                    <a href="fashion.html"><i class="fa fa-fw fa-asterisk"></i><span>潮流</span></a>
                    <a href="kids.html"><i class="fa fa-fw fa-asterisk"></i><span>玩具</span></a>
                    <a href="services.html"><i class="fa fa-fw fa-shield"></i><span>服务</span></a>
                    <a href="jobs.html"><i class="fa fa-fw fa-at"></i><span>工作</span></a>
                    <a href="real-estate.html"><i class="fa fa-fw fa-home"></i><span>房产</span></a>
                </div>
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
                    <a href="/page/signin.action" aria-expanded="false"><i class="fa fa-user" aria-hidden="true"></i> 登录</a>
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
                <h1><a href="index.action"><span>易卖</span>网</a></h1>
            </div>
            <div class="agileits_search">
                <form action="#" method="post">
                    <input name="Search" type="text" placeholder="您需要些什么?" required="" />
                    <select id="agileinfo_search" name="agileinfo_search" required="">
                        <option value="">所有分类</option>
                    </select>
                    <button type="submit" class="btn btn-default" aria-label="Left Align">
                        <i class="fa fa-search" aria-hidden="true"> </i>
                    </button>
                </form>
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
                <a href="categories.html" class="w3layouts-explore-all">查询所有分类</a>
            </div>
        </li>
        <li>
            <div class="w3ls-slide-text">
                <h3>发现更好</h3>
                <a href="categories.html" class="w3layouts-explore">去探索</a>
            </div>
        </li>
        <li>
            <div class="w3ls-slide-text">
                <h3>购买你想要的</h3>
                <a href="real-estate.html" class="w3layouts-explore">去探索</a>
            </div>
        </li>
    </ul>
</div>
<!-- //Slider -->
<!-- content-starts-here -->
<div class="main-content">
    <div class="w3-categories">
        <h3>浏览分类</h3>
        <div class="container">
            <div class="col-md-3">
                <div class="focus-grid w3layouts-boder1">
                    <a class="btn-8" href="categories.html">
                        <div class="focus-border">
                            <div class="focus-layout">
                                <div class="focus-image"><i class="fa fa-mobile"></i></div>
                                <h4 class="clrchg">手机</h4>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-md-3">
                <div class="focus-grid w3layouts-boder2">
                    <a class="btn-8" href="categories.html#parentVerticalTab2">
                        <div class="focus-border">
                            <div class="focus-layout">
                                <div class="focus-image"><i class="fa fa-laptop"></i></div>
                                <h4 class="clrchg"> 电脑</h4>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-md-3">
                <div class="focus-grid w3layouts-boder3">
                    <a class="btn-8" href="categories.html#parentVerticalTab3">
                        <div class="focus-border">
                            <div class="focus-layout">
                                <div class="focus-image"><i class="fa fa-car"></i></div>
                                <h4 class="clrchg">汽车</h4>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-md-3">
                <div class="focus-grid w3layouts-boder4">
                    <a class="btn-8" href="categories.html#parentVerticalTab4">
                        <div class="focus-border">
                            <div class="focus-layout">
                                <div class="focus-image"><i class="fa fa-motorcycle"></i></div>
                                <h4 class="clrchg">摩托车</h4>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-md-3">
                <div class="focus-grid w3layouts-boder5">
                    <a class="btn-8" href="categories.html#parentVerticalTab5">
                        <div class="focus-border">
                            <div class="focus-layout">
                                <div class="focus-image"><i class="fa fa-wheelchair"></i></div>
                                <h4 class="clrchg">家具</h4>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-md-3">
                <div class="focus-grid w3layouts-boder6">
                    <a class="btn-8" href="categories.html#parentVerticalTab6">
                        <div class="focus-border">
                            <div class="focus-layout">
                                <div class="focus-image"><i class="fa fa-paw"></i></div>
                                <h4 class="clrchg">宠物</h4>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-md-3">
                <div class="focus-grid w3layouts-boder7">
                    <a class="btn-8" href="categories.html#parentVerticalTab7">
                        <div class="focus-border">
                            <div class="focus-layout">
                                <div class="focus-image"><i class="fa fa-book"></i></div>
                                <h4 class="clrchg">书籍</h4>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-md-3">
                <div class="focus-grid w3layouts-boder8">
                    <a class="btn-8" href="categories.html#parentVerticalTab8">
                        <div class="focus-border">
                            <div class="focus-layout">
                                <div class="focus-image"><i class="fa fa-asterisk"></i></div>
                                <h4 class="clrchg">潮流</h4>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-md-3">
                <div class="focus-grid w3layouts-boder9">
                    <a class="btn-8" href="categories.html#parentVerticalTab9">
                        <div class="focus-border">
                            <div class="focus-layout">
                                <div class="focus-image"><i class="fa fa-gamepad"></i></div>
                                <h4 class="clrchg">玩具</h4>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-md-3">
                <div class="focus-grid w3layouts-boder10">
                    <a class="btn-8" href="categories.html#parentVerticalTab10">
                        <div class="focus-border">
                            <div class="focus-layout">
                                <div class="focus-image"><i class="fa fa-shield"></i></div>
                                <h4 class="clrchg">服务</h4>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-md-3">
                <div class="focus-grid w3layouts-boder11">
                    <a class="btn-8" href="categories.html#parentVerticalTab11">
                        <div class="focus-border">
                            <div class="focus-layout">
                                <div class="focus-image"><i class="fa fa-at"></i></div>
                                <h4 class="clrchg">工作</h4>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-md-3">
                <div class="focus-grid w3layouts-boder12">
                    <a class="btn-8" href="categories.html#parentVerticalTab12">
                        <div class="focus-border">
                            <div class="focus-layout">
                                <div class="focus-image"><i class="fa fa-home"></i></div>
                                <h4 class="clrchg">房屋</h4>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
    <!-- most-popular-ads -->
    <div class="w3l-popular-ads">
        <h3>最近流行</h3>
        <div class="w3l-popular-ads-info">
            <div class="col-md-4 w3ls-portfolio-left">
                <div class="portfolio-img event-img">
                    <img src="images/ad4.jpg" class="img-responsive" alt=""/>
                    <div class="over-image"></div>
                </div>
                <div class="portfolio-description">
                    <h4><a href="electronics-appliances.html">耳机</a></h4>
                    <p>价格</p>
                    <a href="electronics-appliances.html">
                        <span>去看看</span>
                    </a>
                </div>
                <div class="clearfix"> </div>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
    <!-- most-popular-ads -->
    <div class="trending-ads">
        <div class="container">
            <!-- slider -->
            <div class="agile-trend-ads">
                <h2>Trending Ads</h2>
                <ul id="flexiselDemo3">
                    <li>
                        <div class="col-md-3 biseller-column">
                            <a href="single.html">
                                <img src="images/p1.jpg" alt="" />
                                <span class="price">&#36; 450</span>
                            </a>
                            <div class="w3-ad-info">
                                <h5>There are many variations of passages</h5>
                                <span>1 hour ago</span>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="col-md-3 biseller-column">
                            <a href="single.html">
                                <img src="images/p5.jpg" alt="" />
                                <span class="price">&#36; 1599</span>
                            </a>
                            <div class="w3-ad-info">
                                <h5>There are many variations of passages</h5>
                                <span>1 hour ago</span>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="col-md-3 biseller-column">
                            <a href="single.html">
                                <img src="images/p9.jpg" alt="" />
                                <span class="price">&#36; 2599</span>
                            </a>
                            <div class="w3-ad-info">
                                <h5>Lorem Ipsum is simply dummy</h5>
                                <span>3 hour ago</span>
                            </div>
                        </div>
                    </li>
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
                <h1><a href="/index.html"><span>易卖</span>网</a></h1>
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
<!-- Navigation-Js-->
<script type="text/javascript" src="<c:url value="/js/main.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/classie.js"/>"></script>
<!-- //Navigation-Js-->
<!-- js -->
<script type="text/javascript" src="<c:url value="/js/jquery.min.js"/>"></script>
<!-- js -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<c:url value="/js/bootstrap.js"/>"></script>
<script src="<c:url value="/js/bootstrap-select.js"/>"></script>
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
<script type="text/javascript" src="<c:url value="/js/jquery.leanModal.min.js"/>"></script>
<link href="<c:url value="/css/jquery.uls.css"/>" rel="stylesheet"/>
<link href="<c:url value="/css/jquery.uls.grid.css"/>" rel="stylesheet"/>
<link href="<c:url value="/css/jquery.uls.lcd.css"/>" rel="stylesheet"/>
<!-- Source -->
<script src="<c:url value="/js/jquery.uls.data.js"/>"></script>
<script src="<c:url value="/js/jquery.uls.data.utils.js"/>"></script>
<script src="<c:url value="/js/jquery.uls.lcd.js"/>"></script>
<script src="<c:url value="/js/jquery.uls.languagefilter.js"/>"></script>
<script src="<c:url value="/js/jquery.uls.regionfilter.js"/>"></script>
<script src="<c:url value="/js/jquery.uls.core.js"/>"></script>
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
<script type="text/javascript" src="<c:url value="/js/jquery.flexisel.js"/>"></script><!-- flexisel-js -->
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
