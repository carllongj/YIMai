/**
 * Created by carllongj on 2017/3/2.
 */
/**
 * 首页的js
 */

/**
 * 请求和解析分类列表的js
 */
function requestForCategory(){
    //执行ajax请求,获取服务器的分类信息
    $.ajax({url:'/category/list.action',success:function (data) {
        if (data){
            var category = data.data;
            if (category !== null){
                var i = category.length - 1;
                for (;i >= 0;i--){
                    $("#categoryListSelector").after("<option value=" + category[i].id + ">" + category[i].name + "</option>");
                }
            }
        }
    }});
}

/**
 * 解析首页的广告
 * @param obj
 */
function parseAdvertisement (obj){
    if (obj && obj != '') {
        var advs = JSON.parse(obj);
        var i = advs.length - 1;
        for (; i >= 0; i--){
            $("#newestAdvertisement").append("<div class=\"col-md-4 w3ls-portfolio-left\">" +
                "<div class=\"portfolio-img event-img\">" +
                "<img src= "+ advs[i].image +" class=\"img-responsive\" alt=" + advs[i].title + "/>" +
                "<div class=\"over-image\"></div>" +
                "</div>" +
                "<div class=\"portfolio-description\">" +
                "<h4><a href=\"/item/info/" + advs[i].id + ".action\">" + advs[i].title + "</a></h4>" +
                "<p>" + formatMoney(advs[i].price) + "</p>" +
                "<a href=\"/item/info/" + advs[i].id + ".action\">" +
                "<span>去看看</span>" +
                "</a>" +
                "</div>" +
                "<div class=\"clearfix\"> </div>" +
                "</div>");
        }
    }
}

/**
 * 解析最近趋势的广告位
 * @param jsonStr
 */
function parseTrendingAd(jsonStr) {
    if (jsonStr && jsonStr.trim() !== '') {
        var advs = JSON.parse(jsonStr);
        var i = 0;
        for (;i < 3;i++){
            console.log(advs[i]);
            for (var j = advs[i].length - 1;j >= 0;j--){
                $("#flexiselDemo3 li:nth-child(" + (i + 1) + ")").append(
                "<div class=\"col-md-3 biseller-column\">" +
                "<a href=\"single.html\">" +
                "<img src= \"" + advs[i][j].image + "\" alt=\"" + advs[i][j].title + "\" />" +
                "<span class=\"price\">&#36; " + formatMoney(advs[i][j].price) + "</span>" +
                "</a>" +
                "<div class=\"w3-ad-info\">" +
                "<h5>" + advs[i][j].title + "</h5>" +
                "<span>" + advs[i][j].title + "</span>" +
                "</div>" +
                "</div>");
            }
        }
    }
}

/**
 * 格式化金额显示
 */
function formatMoney(money) {
    money = money + "";
    if (!money || money.trim() == ''){
        return;
    }

    if (money.length > 3){
        var end = money.substring(money.length - 2,money.length);
        var start = money.substr(0,money.length - 2);
        return start + "." + end + "元";
    }
}

$(function () {
    /**
     * 控制首页的用户名的显示,如果用户设置了昵称,则显示昵称,否则显示用户名
     * 如果用户没有登录,即没有cookie,那么则显示去登陆的字样
     */
    var jsonObj = $.cookie("USER_INFO");
    if (jsonObj != undefined){
        var userObj = JSON.parse(jsonObj);
        document.getElementById("userinfomation").href = '/person/info';
        var content = $("#userinfomation").html();
        var remain = content.substring(0,content.length - 2);
        if (userObj.nickname != undefined){
            $("#userinfomation").html(remain + userObj.nickname);
        }else{
            $("#userinfomation").html(remain + userObj.username);
        }
    }

    /** 解析最新的广告位 */
    parseAdvertisement(lastest);

    /** 解决流行趋势广告位 */
    parseTrendingAd(trending);

    /** 异步请求分类信息 */
    setTimeout(requestForCategory(),1000);

});