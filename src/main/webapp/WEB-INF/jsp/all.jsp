<%--
  Created by IntelliJ IDEA.
  User: carllongj
  Date: 2017/3/6
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>所有商品</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css"><!-- bootstrap-CSS -->
    <link rel="stylesheet" href="/css/bootstrap-select.css"><!-- bootstrap-select-CSS -->
    <link href="/css/style.css" rel="stylesheet" type="text/css" media="all" /><!-- style.css -->
    <link rel="stylesheet" type="text/css" href="/css/jquery-ui1.css">
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
    <!-- switcher-grid and list alignment -->
    <script src="/js/tabs.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var elem=$('#container ul');
            $('#viewcontrols a').on('click',function(e) {
                if ($(this).hasClass('gridview')) {
                    elem.fadeOut(1000, function () {
                        $('#container ul').removeClass('list').addClass('grid');
                        $('#viewcontrols').removeClass('view-controls-list').addClass('view-controls-grid');
                        $('#viewcontrols .gridview').addClass('active');
                        $('#viewcontrols .listview').removeClass('active');
                        elem.fadeIn(1000);
                    });
                }
                else if($(this).hasClass('listview')) {
                    elem.fadeOut(1000, function () {
                        $('#container ul').removeClass('grid').addClass('list');
                        $('#viewcontrols').removeClass('view-controls-grid').addClass('view-controls-list');
                        $('#viewcontrols .gridview').removeClass('active');
                        $('#viewcontrols .listview').addClass('active');
                        elem.fadeIn(1000);
                    });
                }
            });
        });
    </script>
    <!-- //switcher-grid and list alignment -->
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
            <div class="agileits_search">
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
        <span class="agile-breadcrumbs"><a href="/index.action"><i class="fa fa-home home_1"></i></a> / <span>所有商品</span></span>
    </div>
</div>
<!-- //breadcrumbs -->
<!-- Products -->
<div class="total-ads main-grid-border">
    <div class="container">
        <div class="select-box">
            <div class="browse-category ads-list">
                <label>选择您想要的分类</label>
                <select class="selectpicker show-tick" data-live-search="true">
                    <option>所有分类</option>
                </select>
            </div>
            <div class="search-product ads-list">
                <label>关键字查询</label>
                <div class="search">
                    <div id="custom-search-input">
                        <div class="input-group">
                            <input type="text" class="form-control input-lg" placeholder="关键字" />
                            <span class="input-group-btn">
								<button class="btn btn-info btn-lg" type="button">
									<i class="glyphicon glyphicon-search"></i>
								</button>
							</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="ads-grid">
            <div class="side-bar col-md-3">
                <div class="range">
                    <h3 class="agileits-sear-head">价格区间</h3>
                    <ul class="dropdown-menu6">
                        <li>
                            <div id="slider-range"></div>
                            <input type="text" id="amount" style="border: 0; color: #ffffff; font-weight: normal;" />
                        </li>
                    </ul>
                    <!---->
                    <script type="text/javascript" src="/js/jquery-ui.js"></script>
                    <script type='text/javascript'>//<![CDATA[
                    $(window).load(function(){
                        $( "#slider-range" ).slider({
                            range: true,
                            min: 0,
                            max: 9000,
                            values: [ 50, 6000 ],
                            slide: function( event, ui ) {  $( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
                            }
                        });
                        $( "#amount" ).val( "$" + $( "#slider-range" ).slider( "values", 0 ) + " - $" + $( "#slider-range" ).slider( "values", 1 ) );

                    });//]]>
                    </script>

                </div>
            </div>
            <div class="agileinfo-ads-display col-md-9">
                <div class="wrapper">
                    <div class="bs-example bs-example-tabs" role="tabpanel" data-example-id="togglable-tabs">
                        <div id="myTabContent" class="tab-content">
                            <div role="tabpanel" class="tab-pane fade in active" id="home" aria-labelledby="home-tab">
                                <div>
                                    <div id="container">
                                        <div class="sort">
                                            <div class="sort-by">
                                                <label>排序 : </label>
                                                <select>
                                                    <option value="">最近的</option>
                                                    <option value="">价格从低到高</option>
                                                    <option value="">价格从高到低</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="clearfix"></div>
                                        <ul class="list">
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <ul class="pagination pagination-sm">
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!-- // Products -->
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

<!-- start -->
<script>
    var data = '${pageResult}';
</script>
<script src="/js/jquery.cookie-1.4.1.min.js"></script>
<script src="/js/page/common.js"></script>
<script src="/js/page/all.js"></script>
<!-- end -->

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