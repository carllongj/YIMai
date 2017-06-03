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
        $("#cateName").attr("href","/query/list.action?cid=" + item.cateid);
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

    if (status == 1 || status == 2){
        return '已交易';
    }

    return "未知状态";
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

    sidebarCate($("#open-button"),$(".icon-list"));
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

    $("#buyItemFormInfo").bind("click",function () {
        setButtonStyle(selector);
        selector.text("请稍候,正在处理");

        $.ajax({url:"/user/isLogin.action",success:function (data) {
            if (data && data.status){
                $("#buyItemFormInfo > input").val(itemId);

                if (itemId == null){
                    setButtonStyle();
                    selector.text("当前的商品不存在....");
                    return;
                }
                //校验一些参数
                $.post("/cart/check.action",$("#buyItemFormInfo").serialize(),function (data) {
                    if (data && data.status){
                        //执行下单操作
                        $.post("/cart/buyItem.action",$("#buyItemFormInfo").serialize(),function (data) {
                            if (data && data.status){
                                swal("操作结果","下单成功,请尽快完成支付","success");
                                setTimeout("location.href = '/userinfo/order/myorders.action'",1000);
                            }else{
                                swal("操作结果",data.msg,"error");
                                setTimeout("location.reload()",1200);
                            }
                        });
                    }else{
                        swal("操作结果",data.msg,"error");
                        setTimeout("location.reload()",1200);
                    }
                    });
            }else{
                swal("操作结果",data.msg,"error");
                setTimeout("location.href = '/page/signin.action?redirect=' + location.href",700);
            }
        }});
    });
})