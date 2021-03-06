/**
 * Created by carllongj on 2017/4/15.
 */

/**
 * 所有卖出商品的js
 */

/** 金额校验正则表达式 */
var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;

/** 定义当前分页的页面 */
var current;

/** 定义当前页面的第一页 */
var first;

/** 定义当前页面的最后一页 */
var last;

/** 定义总的记录数 */
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

function parseDate(date) {
    return new Date(date).format('yyyy-MM-dd hh:mm');
}

function formatMoney(money) {
    money = money + "";
    if (!money || money.trim() == '') {
        return;
    }

    if (money.length > 3) {
        var end = money.substring(money.length - 2, money.length);
        var start = money.substr(0, money.length - 2);
        return start + "." + end;
    }
}

function checkPrice() {
    if (!reg.test($("#price").val())) {
        swal("输入不符合标准", "格式错误,如:1237.13", "error");
        return;
    }
}

function getItem(id) {
    $.ajax({
        url: "/item/info/" + id + ".action", success: function (data) {
            if (data && data.status) {
                getDesc(data.data.descid);
                $("#title").val(data.data.title);
                $("#price").val(formatMoney(data.data.price));
                getCategory(data.data);
            } else {
                swal("操作结果", data.msg, "error");
            }
        }
    });
}

function getDesc(id) {
    $.ajax({
        url: "/desc/query/" + id + ".action", success: function (data) {
            if (data && data.status) {
                $("#desc").val(data.data.content);
            } else {
                swal("操作结果", data.msg, "error");
            }
        }
    });
}

function getCategory(item) {
    $.ajax({
        url: '/category/list.action', success: function (data) {
            if (data) {
                var category = data.data;
                if (category !== null) {
                    var str = '';
                    var i = 0;
                    for (; i < category.length; i++) {
                        if (item.cateid == category.id) {
                            str += "<option selected value=" + category[i].id + ">" + category[i].name + "</option>";
                        } else {
                            str += "<option value=" + category[i].id + ">" + category[i].name + "</option>";
                        }
                    }
                    $("#categorySelector").html(str);
                }
            }
        }
    });
}

/**
 * 确认发货后
 */
function shipped(orderId) {
    swal({
            title: "确认发货",
            text: "输入快递单号并确认发货",
            type: "input",
            showCancelButton: true,
            closeOnConfirm: false,
            animation: "slide-from-top",
            inputPlaceholder: "快递单号"
        },
        function (inputValue) {
            if (inputValue === false) return false;

            if (inputValue === "") {
                swal.showInputError("请输入您发货的快递单号!");
                return false;
            }

            $.ajax({
                url: "/order/shipped.action?orderId=" + orderId + "&expressId=" + inputValue,
                success: function (data) {
                    if (data && data.status){
                        swal("成功","确认发货成功","success");
                        setTimeout("location.reload()",1000);
                    }else{
                        swal("失败",data.msg,"error");
                    }
                },
                dataType: 'json'
            });
        })
}

function showAddress(orderId){
    $.ajax({
        url:"/order/showAddress.action?orderId=" + orderId,
        success:function(data){
            if (data && data.status){
                swal("获取地址成功",data.data,"success");
            }else{
                swal("获取地址失败",data.msg,"error");
            }
        }
    });
}

function parseStatus(status) {
    if (0 == status) {
        return "待付款";
    } else if (1 == status) {
        return "已付款,待发货";
    } else if (2 == status) {
        return "已发货";
    } else if (3 == status) {
        return "已完成";
    } else {
        return "未知状态"
    }
}

function parseAllSellItems(page) {
    current = page;
    var str = '';
    $.ajax({
        url: "/item/allsell.action?page=" + page, success: function (data) {
            if (data && data.list.length > 0 && data.totalRecords > 0) {
                for (var i = 0; i < data.list.length; i++) {
                    str += '<li><img width="202px" height="202px" src="' + data.list[i].image + '" alt="">' +
                        '<section class="list-left"><h5 class="title">' + data.list[i].title + '</h5><span class="adprice">' + formatMoney(data.list[i].price) +
                        '</span><p class="catpath title">状态:' + parseStatus(data.list[i].status) + '</p>' +
                        '<button onclick="javascript:showAddress(' + data.list[i].id + ')" style="margin-top: 10px;" class="btn btn-default"><span>查看客户地址</span></button></section>' +
                        '<section class="list-right"><span class="date">' + parseDate(data.list[i].created) + '</span>';
                    if (data.list[i].status == 1) {
                        str += "<br><button style='margin-top: 7px' onclick=\"javascript:shipped('" + data.list[i].id + "')\" class=\"btn btn-success\">已发货</button>";
                    }
                    str += "</section><div class=\"clearfix\"></div></li>";
                }
                $(".list").html(str);
                total = parseInt(data.totalRecords / data.rows);
                if (data.totalRecords % data.rows != 0) {
                    total += 1;
                }
                parseItemsPage(data, page);
            } else {
                str = "<div class='row' style='margin-top: 10%'><div class='col-lg-2'></div>" +
                    "<div class='col-lg-8 text-center'><h3 style='color: red;font-family: 'Ubuntu Condensed''>您还没有相关的信息</h3></div>" +
                    "<div class='col-lg-2'></div></div>";
                $(".wrapper").html(str);
            }
        }
    });
}

function parseItemsPage() {
    if (total < 10) {
        parseItemLessThanTen();
        return;
    }
    parseItemMoreThanTen();
}

function tailOverflow() {
    last = total;
    first = total - 9;
    var str = '<li><a href="javascript:parseAllSellItems(' + (current - 1) + ')" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    for (var i = first; i <= last; i++) {
        if (current == i) {
            str += '<li class=\"active\"><a href=\"javascript:void(0)\">' + i + ' <span class=\"sr-only\">(current)</span></a></li>';
        } else {
            str += '<li><a href=\"javascript:parseAllSellItems(' + i + ')\">' + i + ' <span class=\"sr-only\"></span></a></li>';
        }
    }
    if (current == last) {
        str += '<li class=\"disabled\"><a href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    } else {
        str += '<li><a href=\"javascript:parseAllSellItems(' + (current + 1) + ')\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
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
        str = '<li><a href="javascript:parseAllSellItems(' + (current - 1) + ')" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    }
    for (var i = first; i <= last; i++) {
        if (current == i) {
            str += '<li class=\"active\"><a href=\"javascript:void(0)\">' + i + ' <span class=\"sr-only\">(current)</span></a></li>';
        } else {
            str += '<li><a href=\"javascript:parseAllSellItems(' + i + ')\">' + i + ' <span class=\"sr-only\"></span></a></li>';
        }
    }
    if (current == last) {
        str += '<li class=\"disabled\"><a href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    } else {
        str += '<li><a href=\"javascript:parseAllSellItems(' + (current + 1) + ')\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
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
    var str = '<li><a href="javascript:parseAllSellItems(' + (current - 1) + ')" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    for (var i = first; i <= last; i++) {
        if (current == i) {
            str += '<li class=\"active\"><a href=\"javascript:void(0)\">' + i + ' <span class=\"sr-only\">(current)</span></a></li>';
        } else {
            str += '<li><a href=\"javascript:parseAllSellItems(' + i + ')\">' + i + ' <span class=\"sr-only\"></span></a></li>';
        }
    }
    str += '<li><a href=\"javascript:parseAllSellItems(' + (current + 1) + ')\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    $(".pagination").html(str);
}

function parseItemLessThanTen() {
    last = total;
    var str = '';
    if (current == 1) {
        str = '<li class=\"disabled\"><a href="javascript:void(0)" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    } else {
        str = '<li><a href="javascript:parseAllSellItems(' + (current - 1) + ')" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    }
    for (var i = 1; i <= total; i++) {
        if (i == current) {
            str += '<li class=\"active\"><a href=\"javascript:javascript:void(0)\">' + i + ' <span class=\"sr-only\">(current)</span></a></li>';
        } else {
            str += '<li><a href=\"javascript:parseAllSellItems(' + i + ')\">' + i + ' <span class=\"sr-only\"></span></a></li>';
        }
    }
    if (current == last) {
        str += '<li class=\"disabled\"><a href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    } else {
        str += '<li><a href=\"javascript:parseAllSellItems(' + (current + 1) + ')\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    }
    $(".pagination").html(str);
}

$(function () {
    parseUserInfo();
    parseAllSellItems(1);
    /** 绑定按钮的点击事件 */
    setTimeout("autoBind($('.icon-list'))", 500);
});
