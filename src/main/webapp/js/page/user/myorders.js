/**
 * Created by carllongj on 2017/4/11.
 */

/**
 * 订单页面的js
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

function deleteOrder(id) {
    swal({
            title: "确定要取消订单吗?",
            text: "",
            type: "info",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true,
        },
        function(){
            $.post("/order/del.action",{id:id},function (data) {
                if (data && data.status){
                    swal("操作结果","操作成功","success");
                    parseMyOrders($(".selectpicker").val(),1);
                }else{
                    swal("操作结果",data.msg,"error");
                }
            });
        });
}

function formatMoney(money) {
    money = money + "";
    if (!money || money.trim() == ''){
        return;
    }

    if (money.length > 3){
        var end = money.substring(money.length - 2,money.length);
        var start = money.substr(0,money.length - 2);
        return start + "." + end;
    }
}

function payItem(id) {
    $.post("/order/one/" + id + ".action",function (data) {
        if (data && data.status){
            console.log(data);
            var str = "<form class='form-horizontal'>" +
                " <table class=\"table\"> " +
                " <tr> " +
                " <td>商品标题</td> " +
                " <td>商品价格(单位:元)</td> " +
                " </tr> " +
                " <tr> " +
                " <td class='title'> <span class='adprice' style='color: #000;'>" + data.data.title + "</span></td> " +
                " <td > <span id='priceInput' class='adprice'>" + formatMoney(data.data.price) + "</span></td> " +
                " </tr> " +
                " </table> " +
                    "<input type='hidden' name='orderId' value='"+ data.data.id + "'>" +
                " </form>";
            $(".modal-body").html(str);
            $(".modal").modal();
            $(".btn-success").bind("click",function () {
                $.ajax({
                    url: "/cart/checkRemain.action?money=" + $("#priceInput").text(),
                    success: function (data) {
                        if (data && data.status) {
                            $.post("/cart/payItem.action",$(".form-horizontal").serialize(),function(data){
                                if (data && data.status){
                                    swal("支付成功","成功","success");
                                    setTimeout("location.reload()",1000);
                                }else{
                                    swal("操作失败",data.msg,"error");
                                }
                            });
                        }else{
                            swal("操作失败",data.msg,"error");
                        }
                    }
                });
            });
        }else{
            swal("操作失败",data.msg,"error");
        }
    });
}

function checkReceive(orderId){
    swal({
            title: "确认收货",
            text: "是否确认收货",
            type: "info",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true,
        },
        function(){
            $.post("/order/check/receive.action","orderId=" + orderId,function(data){
                if (data && data.status){
                    swal("成功","确认收货成功","success");
                    setTimeout("parseMyOrders("+ current + "," + type + ")",700)
                }else{
                    swal("失败",data.msg,"error");
                }
            });
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
    $.ajax({url:"/order/myorders.action?type=" + type + "&page=" + page,success:function (data) {
        if (data && data.list.length > 0 && data.totalRecords > 0){
            for (var i = 0;i < data.list.length;i++){
            str += "<div style=\"margin-top: 5px;width: 100%\"><table class='table table-bordered'><tr><td>" + parseDate(data.list[i].created) + "</td>" +
                "<td colspan='2'>订单号:<span class='title'>" + data.list[i].id + "</span></td><td>" +
                "<a href='javascript:deleteOrder(" + data.list[i].id + ")' style='position: relative;left: 70%;'><span class='glyphicon glyphicon-trash' title='删除订单'></span></a></td></tr>";
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
                "<div class='col-lg-8 text-center'><h3 style='color: red;font-family: 'Ubuntu Condensed''>您还没有相关的信息</h3></div>" +
                "<div class='col-lg-2'></div></div>";
        }
        str += '<div class="clearfix"></div>';
        $("#showOrdersArea").html(str);
    }});
}

function requestOrders() {
    $(".selectpicker").bind("change",function () {
        parseMyOrders(1,$(".selectpicker").val());
    });
}


/**  =================  分页相关的函数 ============== */
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
    /** 绑定按钮的点击事件 */
    setTimeout("autoBind($('.icon-list'))",500);
});