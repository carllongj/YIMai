/**
 * Created by carllongj on 2017/3/2.
 */
/**
 * 首页的js
 */

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
                "<img style='width: 300px;height: 300px;' src=\""+ advs[i].image +"\" class=\"img-responsive\" alt=" + advs[i].title + "/>" +
                "<div class=\"over-image\"></div>" +
                "</div>" +
                "<div class=\"portfolio-description\">" +
                "<h4><a href=\"/query/one/" + advs[i].id + ".action\">" + advs[i].title + "</a></h4>" +
                "<p>" + formatMoney(advs[i].price) + "</p>" +
                "<a href=\"/query/one/" + advs[i].id + ".action\">" +
                "<span>去看看</span>" +
                "</a>" +
                "</div>" +
                "<div class=\"clearfix\"> </div>" +
                "</div>");
        }
    }
}

/*function locateAtLogo () {
    $("#allCategory").scrollTop();
}*/

function parseCategory(data) {
    var elements = "";
    for (var i = 0; i < data.data.length;i++){
        elements += "<div class=\"col-md-3\">" +
        "<div class=\"focus-grid w3layouts-boder2\">" +
        "<a class=\"btn-8\" href=\"/query/list.action?cid=" + data.data[i].id + "\">" +
        "<div class=\"focus-border\">" +
        "<div class=\"focus-layout\">" +
        "<div class=\"focus-image\"><i class=\"fa fa-" + data.data[i].icon + "\"></i></div>" +
        "<h4 class=\"clrchg\"> " + data.data[i].name + "</h4>" +
        "</div>" +
        "</div>" +
        "</a>" +
        "</div>" +
        "</div>";
    }
    elements += "<div class=\"clearfix\"></div>";
    $("#showCategoryInfo").html(elements);
}

/**
 * 解析最近趋势的广告位
 * @param jsonStr
 */
function parseTrendingAd(jsonStr) {
    if (jsonStr && jsonStr.trim() !== '') {
        var advs = JSON.parse(jsonStr);
        var i = 0;
        for (;i < advs.length;i++){
            for (var j = advs[i].length - 1;j >= 0;j--){
                $("#flexiselDemo3 li:nth-child(" + (i + 1) + ")").append(
                "<div class=\"col-md-3 biseller-column\">" +
                "<a href=\"/query/one/" + advs[i][j].id + ".action\">" +
                "<img src= \"" + advs[i][j].image + "\" alt=\"" + advs[i][j].title + "\" />" +
                "<span class=\"price\"> &#165; " + formatMoney(advs[i][j].price) + "</span>" +
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

$(function () {
    /**
     * 控制首页的用户名的显示,如果用户设置了昵称,则显示昵称,否则显示用户名
     * 如果用户没有登录,即没有cookie,那么则显示去登陆的字样
     */
     parseUserInfo();

    /** 解析最新的广告位 */
    parseAdvertisement(lastest);

    /** 解决流行趋势广告位 */
    parseTrendingAd(trending);

    /** 异步请求分类信息 */
    setTimeout(requestForCategory($("#categoryListSelector"),parseCategory),1000);

    /** 绑定按钮的点击事件 */
    sidebarCate($("#open-button"),$(".icon-list"));

});