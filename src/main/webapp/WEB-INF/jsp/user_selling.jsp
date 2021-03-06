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
    <link href="/css/style.css" rel="stylesheet" type="text/css" media="all"/><!-- style.css -->
    <link rel="stylesheet" href="/css/font-awesome.min.css"/><!-- fontawesome-CSS -->
    <link rel="stylesheet" href="/css/menu_sideslide.css" type="text/css" media="all">
    <link rel="stylesheet" href="/css/sweetalert.css" type="text/css">
    <!-- Navigation-CSS -->
    <!-- meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!-- //meta tags -->
    <!--fonts-->
    <link href='//fonts.googleapis.com/css?family=Ubuntu+Condensed' rel='stylesheet'
          type='text/css'>
    <link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
          rel='stylesheet' type='text/css'>
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
    <!-- 引入操作cookie的jquery -->
    <script src="/js/jquery.cookie-1.4.1.min.js"></script>
    <script src="/js/page/common.js"></script>
    <script src="/js/page/user/selling.js"></script>
    <script>
        $(document).ready(function () {
            $('.uls-trigger').uls({
                onSelect: function (language) {
                    var languageName = $.uls.data.getAutonym(language);
                    $('.uls-trigger').text(languageName);
                },
                quickList: ['en', 'hi', 'he', 'ml', 'ta', 'fr'] //FIXME
            });
        });
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
        <button class="menu-button" id="open-button"></button>
    </div>
    <div class="clearfix"></div>
</div>
<!-- //Navigation -->
<!-- header -->
<header>
    <div class="w3ls-header"><!--header-one-->
        <div class="w3ls-header-left">
            <p><a href="javascript:download()"><i class="fa fa-download" aria-hidden="true"></i>下载App</a>
            </p>
        </div>
        <div class="w3ls-header-right">
            <ul>
                <li class="dropdown head-dpdn">
                    <a id="userinfomation" href="/page/signin.action" aria-expanded="false"><i
                            class="fa fa-user" aria-hidden="true">登录</i></a>
                </li>
            </ul>
        </div>
        <div class="clearfix"></div>
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
        <span class="agile-breadcrumbs"><a href="/index.action"><i
                class="fa fa-home home_1"></i></a> / <span>用户信息</span></span>
    </div>
</div>
<section class="row section">
    <div class="list-group col-md-2">
                <span class="list-group-item text-center" style="background-color:lightgray;
        font-size: 20px;font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif">个人中心</span>
        <a href="/userinfo/user.action" class="list-group-item text-center">个人信息</a>
        <a href="/userinfo/myaddr.action" class="list-group-item text-center">收货地址</a>
        <span class="list-group-item text-center" style="background-color:lightgray;
        font-size: 20px;font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif">我的交易</span>
        <a href="/userinfo/order/myorders.action" class="list-group-item text-center">我的订单</a>
        <a href="/userinfo/selling.action" class="list-group-item text-center">待卖的商品</a>
        <a href="/userinfo/allsell.action" class="list-group-item text-center">卖出的商品</a>
        <a href="/userinfo/allbuy.action" class="list-group-item text-center">已买的商品</a>
        <span class="list-group-item text-center" style="background-color:lightgray;
        font-size: 20px;font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif">我的钱包</span>
        <a href="/userinfo/account.action" class="list-group-item text-center">我的账单</a>
    </div>
    <div class="agileinfo-ads-display col-md-10">
        <div class="wrapper">
            <div class="bs-example bs-example-tabs" role="tabpanel"
                 data-example-id="togglable-tabs">
                <div id="myTabContent" class="tab-content">
                    <div role="tabpanel" class="tab-pane fade in active" id="home"
                         aria-labelledby="home-tab">
                        <div>
                            <div id="container">
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
</section>
<div class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">编辑商品信息</h4>
            </div>
            <div class="modal-body">
                <div>
                    <form class="form-horizontal">
                        <div class="form-group form-group-sm">
                            <label class="col-sm-2 control-label"
                                   for="categorySelector">选择分类</label>
                            <div class="col-sm-10">
                                <select id="categorySelector">
                                </select>
                            </div>
                        </div>
                        <div class="form-group form-group-sm">
                            <label class="col-sm-2 control-label" for="title">商品标题</label>
                            <div class="col-sm-10">
                                <input class="form-control" type="text" name="title" id="title"
                                       placeholder="商品标题">
                            </div>
                        </div>
                        <div class="form-group form-group-sm">
                            <label class="col-sm-2 control-label" for="price">商品价格</label>
                            <div class="col-sm-10">
                                <input class="form-control" type="text" name="inputPrice" id="price"
                                       placeholder="商品价格">
                            </div>
                        </div>
                        <div class="form-group form-group-sm">
                            <label class="col-sm-2 control-label" for="price">详细描述</label>
                            <div class="col-sm-10">
                                <input class="form-control" type="text" name="desc" id="desc"
                                       placeholder="商品详细描述">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
                <button type="button" id="saveItemInfoButton" class="btn btn-success">保存更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
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
                    <li><a class="facebook" href="#"><i class="fa fa-qq"
                                                        aria-hidden="true"></i><span>QQ</span></a>
                    </li>
                    <li><a class="twitter" href="#"><i class="fa fa-wechat"
                                                       aria-hidden="true"></i><span>微信</span></a>
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
    $(document).ready(function () {
        /*
         var defaults = {
         containerID: 'toTop', // fading element id
         containerHoverID: 'toTopHover', // fading element hover id
         scrollSpeed: 1200,
         easingType: 'linear'
         };
         */

        $().UItoTop({easingType: 'easeOutQuart'});

    });
</script>
<!-- start-smoth-scrolling -->
<script type="text/javascript" src="/js/move-top.js"></script>
<script type="text/javascript" src="/js/easing.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function ($) {
        $(".scroll").click(function (event) {
            event.preventDefault();
            $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1000);
        });
    });
</script>
<!-- start-smoth-scrolling -->
<!-- //here ends scrolling icon -->
</html>
