/**
 * Created by carllongj on 2017/4/11.
 */

/**
 * 订单页面的js
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

function parseDate(date){
    return new Date(date).format('yyyy-MM-dd hh:mm');
}

function parseOrderStatus(status){
    if (0 == status){
        return "待付款";
    }else if(1 == status){
        return "待发货";
    }else if(2 == status){
        return "已完成";
    }else{
        return "未知状态";
    }
}

function parseMyOrders (type,page){
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
                    str += "<a href=''><p class='text-center' style='color: #999'>去付款</p></a>";
                }
                str += "</td></tr></table>";
            }
        }else{
            //没有相关的数据
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
        parseMyOrders($(".selectpicker").val(),1);
    });
}

$(function () {
    parseUserInfo();
    parseMyOrders(-1,1);
    requestOrders();
});