/**
 * Created by carllongj on 2017/3/5.
 */

var itemId ;
/**
 * 单件商品页面的js
 */
function parseItem(){
    if (info && info.trim() != ''){
        var item = JSON.parse(info);
        requestCategory(item.cateid);
        itemId = item.id;
        $("#cateName").next().text(item.title);
        $("#itemTitle").next().text("发布时间: " + new Date(item.created).format("yyyy-MM-dd hh:mm") );
        requestDesc(item.descid);
        parseItemOthers(item);
        requestForCategory($("#categoryListSelector"));
    }
}

function formatCondition(status){
    if (status == 0){
        return '待售';
    }

    if (status == 1){
        return '待付款';
    }

    if (status == 2) {
        return '已交易';
    }
}

Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};

function requestForSeller(sellerId){
    $.ajax({url:"/item/seller/" + sellerId + ".action",success:function (data) {
        if(data && data.status){
            if (data.data.phone != undefined && data.data.phone != null){
                $(".interested h4").after("<p><i class=\"glyphicon glyphicon-envelope\"></i>" + data.data.email + "</p>");
            }else{
                $(".interested h4").after("<p><i class=\"glyphicon glyphicon-envelope\"></i>" + data.data.email + "</p>");
            }
        }
    }});
}

function parseItemOthers(item){
    $("#itemTitle").text(item.title);

    $("ul li img").attr("src",item.image).css({width:"300px",height:"400px"});

    $(".rate").text(formatMoney(item.price));

    $(".condition h4").text(formatCondition(item.status));

    requestForSeller(item.uid);
}




/**
 * 获取分类的信息
 * @param itemId
 */
function requestCategory(itemId){
    $.ajax({url:'/category/cate/' + itemId + ".action",success:function (data) {
        if (data && data.status){
            $("#cateName").text(data.data.name);
            $(".itemtype h4").text(data.data.name);
        }
    }});
}

function requestDesc (descId) {
    $.ajax({url:'/desc/query/' + descId + ".action",success:function (data) {
        if (data && data.status){
            $("#summaryDesc").text(data.data.content);
        }
    }});
}

$(function () {

    parseUserInfo();

    parseItem();

    var selector = $(".common-button-style");

    $(".common-button-style").bind("click",function () {
        if (itemId == null){
            setButtonStyle();
            selector.text("当前的商品不存在....");
            return;
        }

        setButtonStyle(selector);
        selector.text("请稍候,正在处理");

        $.post("/cart/buyItem.action",{itemId:itemId},function (data) {
            if (data && data.status){
                selector.text("拍下成功,请尽快完成支付..");
                setTimeout("location.href = \"/index.action\"",3000);
             }else{
                selector.text(data.msg);
             }
        },'json');
    });
})