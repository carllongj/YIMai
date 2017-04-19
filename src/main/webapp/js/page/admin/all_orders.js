/**
 * Created by carllongj on 2017/4/19.
 */
/**
 * 所有订单页面的js
 */

var type;

/** 当前的页码数 */
var current;

/** 当前页面首页的值 */
var first;

/** 当前页面页尾的值 */
var last;

/** 定义当前页面的总页数   */
var total;

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

function requestOrders() {
    $(".selectpicker").bind("change",function () {
        parseMyOrders(1,$(".selectpicker").val());
    });
}

function parseDate(date){
    return new Date(date).format('yyyy-MM-dd hh:mm');
}

function parseOrderStatus(status){
    if (0 == status){
        return "待付款";
    }else if(1 == status){
        return "已付款,待发货";
    }else if(2 == status){
        return "已发货";
    }else if(3 == status){
        return "已完成";
    }else {
        return "未知状态";
    }
}

function parseMyOrders (page,curType){
    current = page;
    type = curType;
    var str = '<div class="row">';
    $.ajax({url:"/admin/manage/order/all.action?type=" + type + "&page=" + page,success:function (data) {
        if (data && data.list.length > 0 && data.totalRecords > 0){
            for (var i = 0;i < data.list.length;i++){
                str += "<div style=\"margin-top: 5px;width: 100%\"><table class='table table-bordered'><tr><td>" + parseDate(data.list[i].created) + "</td>" +
                    "<td colspan='2'>订单号:<span class='title'>" + data.list[i].id + "</span></td><td>" +
                    "</td></tr>";
                str += "<tr><td><img width='80px' height='80px' src='" + data.list[i].image + "'/></td><td class='col-lg-5'><h5 class='title'>" + data.list[i].title + "</h5></td><td class='col-lg-3'><span class='adprice'>" +
                    formatMoney(data.list[i].price) + "</span></td><td class='col-lg-2 text-center' style='font-size: medium'>交易状态:<span>" + parseOrderStatus(data.list[i].status) + "</span>";
                if (0 == data.list[i].status){
                    str += "<a class='btn btn-success' href='javascript:payItem(\"" + data.list[i].id + "\")'><p class='text-center' style='color: #fff'>去付款</p></a>";
                }
                if (2 == data.list[i].status){
                    str += "<a class='btn btn-success' href='javascript:checkReceive(\"" + data.list[i].id + "\")'><p class='text-center' style='color: #fff'>确认收货</p></a>";
                }
                str += "</td></tr></table>";
            }
            total = parseInt(data.totalRecords / data.rows);
            if (data.totalRecords % data.rows != 0) {
                total += 1;
            }
            parseOrdersPage();
            $("#pageArea").show();
        }else{
            //没有相关的数据
            $("#pageArea").hide();
            str = "<div class='row' style='margin-top: 10%'><div class='col-lg-2'></div>" +
                "<div class='col-lg-8 text-center'><h3 style='color: red;font-family: 'Ubuntu Condensed''>当前还没有相关的信息</h3></div>" +
                "<div class='col-lg-2'></div></div>";
        }
        str += '<div class="clearfix"></div>';
        $("#showOrdersArea").html(str);
    }});
}

function parseOrdersPage() {
    if (total < 10) {
        parseItemLessThanTen();
        return;
    }
    parseItemMoreThanTen();
}

function tailOverflow() {
    last = total;
    first = total - 9;
    var str = '<li><a href="javascript:parseMyOrders(' + (current - 1) + "," + type + ')" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    for (var i = first; i <= last; i++) {
        if (current == i) {
            str += '<li class=\"active\"><a href=\"javascript:void(0)\">' + i + ' <span class=\"sr-only\">(current)</span></a></li>';
        } else {
            str += '<li><a href=\"javascript:parseMyOrders(' + i + "," + type + ')\">' + i + ' <span class=\"sr-only\"></span></a></li>';
        }
    }
    if (current == last) {
        str += '<li class=\"disabled\"><a href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    } else {
        str += '<li><a href=\"javascript:parseMyOrders(' + (current + 1) +  "," + type +  ')\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
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
        str = '<li><a href="javascript:parseMyOrders(' + (current - 1) +  "," + type + ')" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    }
    for (var i = first; i <= last; i++) {
        if (current == i) {
            str += '<li class=\"active\"><a href=\"javascript:void(0)\">' + i + ' <span class=\"sr-only\">(current)</span></a></li>';
        } else {
            str += '<li><a href=\"javascript:parseMyOrders(' + i + "," + type + ')\">' + i + ' <span class=\"sr-only\"></span></a></li>';
        }
    }
    if (current == last) {
        str += '<li class=\"disabled\"><a href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    } else {
        str += '<li><a href=\"javascript:parseMyOrders(' + (current + 1) +  "," + type + ')\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
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
    var str = '<li><a href="javascript:parseMyOrders(' + (current - 1) + ')" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    for (var i = first; i <= last; i++) {
        if (current == i) {
            str += '<li class=\"active\"><a href=\"javascript:void(0)\">' + i + ' <span class=\"sr-only\">(current)</span></a></li>';
        } else {
            str += '<li><a href=\"javascript:parseMyOrders(' + i +  "," + type + ')\">' + i + ' <span class=\"sr-only\"></span></a></li>';
        }
    }
    str += '<li><a href=\"javascript:parseMyOrders(' + (current + 1) +  "," + type + ')\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    $(".pagination").html(str);
}

function parseItemLessThanTen() {
    last = total;
    var str = '';
    if (current == 1) {
        str = '<li class=\"disabled\"><a href="javascript:void(0)" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    } else {
        str = '<li><a href="javascript:parseMyOrders(' + (current - 1) +  "," + type + ')" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    }
    for (var i = 1; i <= total; i++) {
        if (i == current) {
            str += '<li class=\"active\"><a href=\"javascript:javascript:void(0)\">' + i + ' <span class=\"sr-only\">(current)</span></a></li>';
        } else {
            str += '<li><a href=\"javascript:parseMyOrders(' + i +  "," + type + ')\">' + i + ' <span class=\"sr-only\"></span></a></li>';
        }
    }
    if (current == last) {
        str += '<li class=\"disabled\"><a href=\"javascript:void(0)\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    } else {
        str += '<li><a href=\"javascript:parseMyOrders(' + (current + 1) +  "," + type + ')\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    }
    $(".pagination").html(str);
}

$(function () {
    parseUserInfo();
    parseMyOrders(1,-1);
    requestOrders();
});