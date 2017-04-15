/**
 * Created by carllongj on 2017/4/15.
 */

/**
 * 所有卖出商品的js
 */

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

function parseDate(date){
    return new Date(date).format('yyyy-MM-dd hh:mm');
}

function parseAllSellItems(page){
    var str = '';
    $.ajax({url:"/item/allsell.action?page=" + page,success:function (data) {
        if (data && data.list.length > 0 && data.totalRecords > 0){
            for (var i = 0;i < data.list.length;i++) {
                str += '<li><img width="20%" src="' + data.list[i].image + '" alt="">' +
                    '<section class="list-left"><h5 class="title">' + data.list[i].title + '</h5><span class="adprice">' + formatMoney(data.list[i].price) + '</span><p class="catpath"></p></section>' +
                    '<section class="list-right"><span class="date">' + parseDate(data.list[i].created) + '</span>' +
                    '</section><div class="clearfix"></div></li>';
            }
            $(".list").html(str);
        }else{
            str = "<div class='row' style='margin-top: 10%'><div class='col-lg-2'></div>" +
            "<div class='col-lg-8 text-center'><h3 style='color: red;font-family: 'Ubuntu Condensed''>您还没有相关的信息</h3></div>" +
            "<div class='col-lg-2'></div></div>";
            $(".wrapper").html(str);
        }
    }});
}


$(function () {
    parseUserInfo();

    parseAllSellItems(1);
});
