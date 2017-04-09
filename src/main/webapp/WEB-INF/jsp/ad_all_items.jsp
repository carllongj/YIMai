<%--
  Created by IntelliJ IDEA.
  User: carllongj
  Date: 2017/4/2
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE>
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
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //meta tags -->
    <!--fonts-->
    <link href='//fonts.googleapis.com/css?family=Ubuntu+Condensed' rel='stylesheet' type='text/css'>
    <link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
    <!--//fonts-->
</head>
<body>
<!-- header -->
<header>
    <div class="w3ls-header"><!--header-one-->
        <div class="w3ls-header-left">
        </div>
        <div class="w3ls-header-right">
            <ul>
                <li class="dropdown head-dpdn">
                    <a id="userinfomation" href="/page/signin.action" aria-expanded="false"></a>
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
<div class="w3layouts-breadcrumbs text-center">
    <div class="container">
        <span class="agile-breadcrumbs"><a href="/index.action"><i class="fa fa-home home_1"></i></a> / <span>后台管理</span></span>
    </div>
</div>
<section class="row section">
    <div class="list-group col-xs-2">
        <span class="list-group-item text-center" style="background-color:lightgray;
        font-size: 20px;font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif">人员管理</span>
        <a href="/admin/show/allusers.action" class="list-group-item text-center">所有用户</a>
        <a href="/admin/show/inactive.action" class="list-group-item text-center">未激活账户用户</a>
        <span class="list-group-item text-center" style="background-color:lightgray;
        font-size: 20px;font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif">商品管理</span>
        <a href="/admin/show/allitems.action" class="list-group-item text-center">所有商品</a>
        <a href="/admin/show/notpass.action" class="list-group-item text-center">待审核商品</a>
        <a href="/admin/show/allcate.action" class="list-group-item text-center">所有分类</a>
        <a href="/admin/show/addcate.action" class="list-group-item text-center">添加分类</a>
        <span class="list-group-item text-center" style="background-color:lightgray;
        font-size: 20px;font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif">订单管理</span>
        <a href="#" class="list-group-item text-center">所有订单</a>
    </div>
    <div class="col-xs-10" id="showInfoArea" style="margin-top: 2px;">
    </div>
    <div class="clearfix"></div>
    <div class="col-xs-10"></div>
    <div class="col-xs-1">
        <button type="button" class="btn btn-danger"><span>发送邮件</span></button>
    </div>
    <div class="col-xs-1">
        <button type="button" id="checkedPassButton" class="btn btn-success"><span>通过审核</span></button>
    </div>
    <div class="clearfix"></div>
    <div class="col-xs-7"></div>
    <div class="col-xs-5">
        <nav aria-label="...">
            <ul class="pagination">
            </ul>
        </nav>
    </div>
</section>

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
<!-- 引入操作cookie的js -->
<script type="text/javascript" src="/js/jquery.cookie-1.4.1.min.js"></script>
<script type="text/javascript" src="/js/page/common.js"></script>
<script type="text/javascript" src="/js/page/admin/common.js"></script>
<script type="text/javascript" src="/js/page/admin/all_items.js"></script>
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
<script src="/js/responsiveslides.min.js"></script>
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
</body>
</html>
