/**
 * Created by carllongj on 2017/4/19.
 */

/**
 * 用户账单页面的js
 */

/** 当前的页码数 */
var current;

/** 当前页面首页的值 */
var first;

/** 当前页面页尾的值 */
var last;

/** 定义当前页面的总页数   */
var total;

/**
 *
 * @param money
 * @returns {string}
 */
function formatMoney(money) {
    money = money + "";
    if (!money || money.trim() == '') {
        return money;
    }

    if (money.length > 3) {
        var end = money.substring(money.length - 2, money.length);
        var start = money.substr(0, money.length - 2);
        return start + "." + end;
    }
}

function parseStatus(status) {
    if (1 == status) {
        return '交易成功';
    } else {
        return '交易失败';
    }
}

function parseActions(page) {
    $.ajax({
        url: "/wallet/show/actions.action",
        success: function (data) {
            var str = '<tbody>';
            if (data && data.totalRecords > 0) {
                total = parseInt(data.totalRecords / data.rows);
                if (data.totalRecords % data.rows != 0) {
                    total += 1;
                }
                str += "<tr class=\"setFontSize\">" +
                    "<td>名称</td>" +
                    "<td>主题</td>" +
                    "<td>金额</td>" +
                    "<td>消费状态</td> </tr>";
                for (var i = 0; i < data.list.length; i++) {
                    str += '<tr class="setFontSizeCon">' +
                        "<td>" + data.list[i].title + "</td>" +
                        "<td>" + data.list[i].subject + " </td>";
                    if (data.list[i].state == 1) {
                        str += "<td style='color: green;'><span>+</span>" + formatMoney(data.list[i].fee) + "</td>";
                    } else {
                        str += "<td style='color: red;'><span>-</span>" + data.list[i].fee + "</td>";
                    }
                    str += "<td>" + parseStatus(data.list[i].status) + "</td></tr>";
                }
                str += '</tbody>';
                $("#ActionsList").html(str);
                parseActionsPage();
            }
        }
    });
}

function parseAccount(page) {
    current = page;
    $.post("/wallet/remain.action", function (data) {
        if (data && data.status) {
            var str = "<div><h4 class=\"title\">账户余额</h4></div> " +
                " <div> " +
                " <span class=\"adprice\" style=\"display: inline-block; margin-top: 10px;\">" + formatMoney(data.data) + "元</span> " +
                " <button class=\"btn btn-default\" style=\"margin-left: 2px\"><span class=\"setFontSizeCon\">充值</span></button> " +
                " <button class=\"btn btn-default\" style=\"margin-left: 2px\"><span class=\"setFontSizeCon\">转账</span></button> " +
                " </div> ";
            $(".col-xs-6").html(str);
            parseActions(page);
        } else {
            swal("加载失败", data.msg, "error");
        }
    }, 'json');
}

function parseActionsPage() {
    if (total < 10) {
        parseItemLessThanTen();
        return;
    }
    parseItemMoreThanTen();
}

function tailOverflow() {
    last = total;
    first = total - 9;
    var str = '<li><a href="javascript:parseAccount(' + (current - 1) + ')" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    for (var i = first; i <= last; i++) {
        if (current == i) {
            str += '<li class=\"active\"><a href=\"javascript:void(0)\">' + i + ' <span class=\"sr-only\">(current)</span></a></li>';
        } else {
            str += '<li><a href=\"javascript:parseAccount(' + i + ')\">' + i + ' <span class=\"sr-only\"></span></a></li>';
        }
    }
    if (current == last) {
        str += '<li class=\"disabled\"><a href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    } else {
        str += '<li><a href=\"javascript:parseAccount(' + (current + 1) + ')\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
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
        str = '<li><a href="javascript:parseAccount(' + (current - 1) + ')" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    }
    for (var i = first; i <= last; i++) {
        if (current == i) {
            str += '<li class=\"active\"><a href=\"javascript:void(0)\">' + i + ' <span class=\"sr-only\">(current)</span></a></li>';
        } else {
            str += '<li><a href=\"javascript:parseAccount(' + i + ')\">' + i + ' <span class=\"sr-only\"></span></a></li>';
        }
    }
    if (current == last) {
        str += '<li class=\"disabled\"><a href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    } else {
        str += '<li><a href=\"javascript:parseAccount(' + (current + 1) + ')\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
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
    var str = '<li><a href="javascript:parseAccount(' + (current - 1) + ')" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    for (var i = first; i <= last; i++) {
        if (current == i) {
            str += '<li class=\"active\"><a href=\"javascript:void(0)\">' + i + ' <span class=\"sr-only\">(current)</span></a></li>';
        } else {
            str += '<li><a href=\"javascript:parseAccount(' + i + ')\">' + i + ' <span class=\"sr-only\"></span></a></li>';
        }
    }
    str += '<li><a href=\"javascript:parseAccount(' + (current + 1) + ')\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    $(".pagination").html(str);
}

function parseItemLessThanTen() {
    last = total;
    var str = '';
    if (current == 1) {
        str = '<li class=\"disabled\"><a href="javascript:void(0)" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    } else {
        str = '<li><a href="javascript:parseAccount(' + (current - 1) + ')" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    }
    for (var i = 1; i <= total; i++) {
        if (i == current) {
            str += '<li class=\"active\"><a href=\"javascript:javascript:void(0)\">' + i + ' <span class=\"sr-only\">(current)</span></a></li>';
        } else {
            str += '<li><a href=\"javascript:parseAccount(' + i + ')\">' + i + ' <span class=\"sr-only\"></span></a></li>';
        }
    }
    if (current == last) {
        str += '<li class=\"disabled\"><a href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    } else {
        str += '<li><a href=\"javascript:parseAccount(' + (current + 1) + ')\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    }
    $(".pagination").html(str);
}


$(function () {
    console.log("\u6e29\u99a8\u63d0\u793a\uff1a\u8bf7\u4e0d\u8981\u8c03\u76ae\u5730\u5728\u6b64\u7c98\u8d34\u6267\u884c\u4efb\u4f55\u5185\u5bb9\uff0c\u8fd9\u53ef\u80fd\u4f1a\u5bfc\u81f4\u60a8\u7684\u8d26\u6237\u53d7\u5230\u653b\u51fb\uff0c\u7ed9\u60a8\u5e26\u6765\u635f\u5931 \uff01^_^");
    parseUserInfo();
    parseAccount(1);
    setTimeout("autoBind($('.icon-list'))", 500);
});