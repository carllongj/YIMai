/**
 * Created by carllongj on 2017/4/16.
 */

/**
 * 查询所有已买商品的页面
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

function parseAllBuyItems(page) {
    current = page;
    var str = '';
    $.ajax({url:"/item/allsell.action?page=" + page,success:function (data) {
        if (data && data.list.length > 0 && data.totalRecords > 0){
            console.log(data);
            for (var i = 0;i < data.list.length;i++) {
                str += '<li><img width="202px" height="202px" src="' + data.list[i].image + '" alt="">' +
                    '<section class="list-left"><h5 class="title">' + data.list[i].title + '</h5><span class="adprice">' + formatMoney(data.list[i].price) +
                    '</span><p class="catpath title">状态:' + parseStatus(data.list[i].status) + '</p>' +
                    '</section>' +
                    '<section class="list-right"><span class="date">' + parseDate(data.list[i].created) + '</span></section>' +
                    '<div class="clearfix"></div></li>';
            }
            $(".list").html(str);
            total = parseInt(data.totalRecords / data.rows);
            if (data.totalRecords % data.rows != 0){
                total += 1;
            }
            parseItemsPage(data,page);
        }else{
            str = "<div class='row' style='margin-top: 10%'><div class='col-lg-2'></div>" +
                "<div class='col-lg-8 text-center'><h3 style='color: red;font-family: 'Ubuntu Condensed''>您还没有相关的信息</h3></div>" +
                "<div class='col-lg-2'></div></div>";
            $(".wrapper").html(str);
        }
    }});
}

function parseItemsPage() {
    if (total < 10){
        parseItemLessThanTen();
        return;
    }
    parseItemMoreThanTen();
}

function tailOverflow(){
    last = total;
    first = total - 9;
    var str = '<li><a href="javascript:parseAllBuyItems(' + (current - 1) + ')" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    for (var i = first; i <= last; i++) {
        if (current == i) {
            str += '<li class=\"active\"><a href=\"javascript:void(0)\">' + i + ' <span class=\"sr-only\">(current)</span></a></li>';
        } else {
            str += '<li><a href=\"javascript:parseAllBuyItems(' + i + ')\">' + i + ' <span class=\"sr-only\"></span></a></li>';
        }
    }
    if (current == last) {
        str += '<li class=\"disabled\"><a href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    } else {
        str += '<li><a href=\"javascript:parseAllBuyItems(' + (current + 1) + ')\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    }
    $(".pagination").html(str);
}

function headOverflow() {
    last = 10;
    first = 1;
    var str;
    if (first == current) {
        str = '<li class=\"disabled\"><a href="javascript:void(0)" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    } else {
        str = '<li><a href="javascript:parseAllBuyItems(' + (current - 1) + ')" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    }
    for (var i = first; i <= last; i++) {
        if (current == i) {
            str += '<li class=\"active\"><a href=\"javascript:void(0)\">' + i + ' <span class=\"sr-only\">(current)</span></a></li>';
        } else {
            str += '<li><a href=\"javascript:parseAllBuyItems(' + i + ')\">' + i + ' <span class=\"sr-only\"></span></a></li>';
        }
    }
    if (current == last) {
        str += '<li class=\"disabled\"><a href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    } else {
        str += '<li><a href=\"javascript:parseAllBuyItems(' + (current + 1) + ')\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    }
    $(".pagination").html(str);
}

function parseItemMoreThanTen() {
    //尾溢出解决
    if (current > total - 4) {
        tailOverflow();
        return;
    }

    //头溢出解决
    if (current < 7) {
        headOverflow();
        return;
    }

    //调整位置的处理
    resetButtonPosition();
}

function resetButtonPosition() {
    last = current + 4;
    first = current - 5;
    var str = '<li><a href="javascript:asyncLoading(' + (current - 1) + ')" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    for (var i = first; i <= last; i++) {
        if (current == i) {
            str += '<li class=\"active\"><a href=\"javascript:void(0)\">' + i + ' <span class=\"sr-only\">(current)</span></a></li>';
        } else {
            str += '<li><a href=\"javascript:asyncLoading(' + i + ')\">' + i + ' <span class=\"sr-only\"></span></a></li>';
        }
    }
    str += '<li><a href=\"javascript:asyncLoading(' + (current + 1) + ')\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    $(".pagination").html(str);
}

function parseItemLessThanTen() {
    last = total;
    var str = '';
    if (current == 1) {
        str = '<li class=\"disabled\"><a href="javascript:void(0)" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    } else {
        str = '<li><a href="javascript:parseAllBuyItems(' + (current - 1) + ')" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    }
    for (var i = 1; i <= total; i++) {
        if (i == current) {
            str += '<li class=\"active\"><a href=\"javascript:javascript:void(0)\">' + i + ' <span class=\"sr-only\">(current)</span></a></li>';
        } else {
            str += '<li><a href=\"javascript:parseAllBuyItems(' + i + ')\">' + i + ' <span class=\"sr-only\"></span></a></li>';
        }
    }
    if (current == last) {
        str += '<li class=\"disabled\"><a href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    } else {
        str += '<li><a href=\"javascript:parseAllBuyItems(' + (current + 1) + ')\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    }
    $(".pagination").html(str);
}

function parseStatus(status) {
    if (0 == status ){
        return '未付款';
    }else if(1 == status){
        return '已付款';
    }else if (2 == status){
        return '已完成'
    }
}

$(function(){
    parseUserInfo();
    parseAllBuyItems(1);
});