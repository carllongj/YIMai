<%--
  Created by IntelliJ IDEA.
  User: carllongj
  Date: 2017/3/3
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>发布商品</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css"><!-- bootstrap-CSS -->
    <link rel="stylesheet" href="/css/bootstrap-select.css"><!-- bootstrap-select-CSS -->
    <link href="/css/style.css" rel="stylesheet" type="text/css" media="all" /><!-- style.css -->
    <link rel="stylesheet" href="/css/font-awesome.min.css" /><!-- fontawesome-CSS -->
    <link rel="stylesheet" href="/css/menu_sideslide.css" type="text/css" media="all"><!-- Navigation-CSS -->
    <!-- meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Resale Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, Sony Ericsson, Motorola web design" />
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
                    <a id="userinfomation" href="/page/signin.action" aria-expanded="false"><i class="fa fa-user" aria-hidden="true"></i>登录</a>
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
                <a class="post-w3layouts-ad" href="/post/post_ad.action">发布我的商品</a>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</header>
<!-- //header -->
<!-- breadcrumbs -->
<div class="w3layouts-breadcrumbs text-center">
    <div class="container">
        <span class="agile-breadcrumbs"><a href="/index.action"><i class="fa fa-home home_1"></i></a> / <span>发布商品</span></span>
    </div>
</div>
<!-- //breadcrumbs -->
<!-- Submit Ad -->
<div class="submit-ad main-grid-border">
    <div class="container">
        <h2 class="w3-head">发布商品</h2>
        <div class="post-ad-form">
            <form id="postMyAdForm">
                <label>选择分类 <span id="categoryListSp">*</span></label>
                <select id="categoryList" name="cateid" class="">
                    <option id="categoryListSelector">选择分类</option>
                </select><span></span>
                <div class="clearfix"></div>
                <label>商品信息 <span id="goodsInfo">*</span></label>
                <input type="text" name="title" id="goodsInfoInput" class="phone" placeholder="请简述您的商品,字符数应在30个字符之间">
                <span></span>
                <div class="clearfix"></div>
                <label>商品价格 <span id="goodsPrice">*</span></label>
                <input type="text" name="unformedPrice" id="goodsPriceInput" class="phone" placeholder="请输入您期望的价格">
                <span></span>
                <div class="clearfix"></div>
                <label>商品的详细描述<span id="goodsInfoDesc">*</span></label>
                <textarea class="mess" name="content" id="goodsInfoDescInput" placeholder="请向其他人推荐您的商品和详细介绍您的商品"></textarea>
                <span></span>
                <div class="clearfix"></div>
                <div class="upload-ad-photos">
                    <label>上传图片 :</label>
                    <div class="photos-upload-view">
                            <div id="pictureInput">
                                <input type="file" id="fileselect" name="fileselect"/>
                                <input type="hidden" id="uploadImageUrl" name="image"/>
                            </div>
                            <div id="submitbutton">
                                <button type="button" id="uploadImageBtn">上传图片</button>
                            </div>
                        <div id="messages" style="width: 86%;">
                            <p></p>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <script src="/js/filedrag.js"></script>
                </div>
                <div class="personal-details">
                    <button type="button" id="postMyAd">发布</button>
                    <div class="clearfix"></div>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- // Submit Ad -->
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
</body>
<!-- Navigation-JavaScript -->
<script src="/js/classie.js"></script>
<script src="/js/main.js"></script>

<!-- 引入操作cookie的js -->
<script src="/js/jquery.cookie-1.4.1.min.js"></script>
<script src="/js/page/common.js"></script>
<script src="/js/page/post_ad.js"></script>
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