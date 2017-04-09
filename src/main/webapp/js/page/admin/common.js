/**
 * Created by carllongj on 2017/3/24.
 */
/** 所有页面可能用的js */

/**
 * 解析状态
 * @param status
 * @returns {*}
 */
function parseStatus(status){
    if (status == 1) {
        return '可用';
    }else{
        return '不可用';
    }
}

/**
 * 禁用用户的信息
 * @param id
 * @param current
 */
function forbiddenUser (id,current) {
    swal({
            title: "确定要禁用吗?",
            text: "",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "禁用",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: false,
            showLoaderOnConfirm:true
        },
        function(isConfirm){
            if (isConfirm) {
                $.ajax({url:"/admin/manage/user/forbidden.action?id=" + id,success:function (data) {
                    if (data && data.status){
                        swal("操作结果", "操作成功.", "success");
                        setTimeout("asyncLoading(" + current + ")",1000);
                    }else{
                        swal("操作结果",data.msg,"error");
                    }
                }});
            } else {
                swal("已取消", "已取消操作", "error");
            }
        });
}


function parseDate (date){
    if ('未设置' != date) {
        return new Date(date).format('yyyy-MM-dd hh:mm');
    }
    return date;
}

function checkItem(selector){
    selector.bind("click",function () {
        $.ajax({url:"/",success:function () {
            
        }});
    });
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

/**
 * 解析总页数小于10的情况
 */
function parsePageLessThanTen(){
    last = total;
    var str = '';
    if (current == 1){
        str = '<li class=\"disabled\"><a href="javascript:void(0)" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    }else{
        str = '<li><a href="javascript:asyncLoading(' + (current - 1) + ')" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    }
    for (var i = 1;i <= total;i++){
        if (i == current){
            str += '<li class=\"active\"><a href=\"javascript:javascript:void(0)\">' + i + ' <span class=\"sr-only\">(current)</span></a></li>';
        }else{
            str += '<li><a href=\"javascript:asyncLoading(' + i + ')\">' + i + ' <span class=\"sr-only\"></span></a></li>';
        }
    }
    if (current == last){
        str += '<li class=\"disabled\"><a href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    }else{
        str += '<li><a href=\"javascript:asyncLoading(' + (current + 1) + ')\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    }
    $(".pagination").html(str);
}

function tailOverflow() {
    last = total;
    first = total - 9;
    var str = '<li><a href="javascript:asyncLoading(' + (current - 1) + ')" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    for (var i = first; i <= last;i++){
        if(current == i){
            str += '<li class=\"active\"><a href=\"javascript:void(0)\">' + i + ' <span class=\"sr-only\">(current)</span></a></li>';
        }else{
            str += '<li><a href=\"javascript:asyncLoading(' + i + ')\">' + i + ' <span class=\"sr-only\"></span></a></li>';
        }
    }
    if (current == last){
        str += '<li class=\"disabled\"><a href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    }else{
        str += '<li><a href=\"javascript:asyncLoading(' + (current + 1) + ')\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    }
    $(".pagination").html(str);
}

function headOverflow() {
    last = 10;
    first = 1;
    var str;
    if (first == current){
        str = '<li class=\"disabled\"><a href="javascript:void(0)" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    }else{
        str = '<li><a href="javascript:asyncLoading(' + (current - 1) + ')" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    }
    for (var i = first; i <= last;i++){
        if(current == i){
            str += '<li class=\"active\"><a href=\"javascript:void(0)\">' + i + ' <span class=\"sr-only\">(current)</span></a></li>';
        }else{
            str += '<li><a href=\"javascript:asyncLoading(' + i + ')\">' + i + ' <span class=\"sr-only\"></span></a></li>';
        }
    }
    if (current == last){
        str += '<li class=\"disabled\"><a href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    }else{
        str += '<li><a href=\"javascript:asyncLoading(' + (current + 1) + ')\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    }
    $(".pagination").html(str);
}

/**
 * 重新更新位置的处理函数
 */
function resetButtonPosition(){
    last = current + 4;
    first = current - 5;
    var str = '<li><a href="javascript:asyncLoading(' + (current - 1) + ')" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    for (var i = first; i <= last;i++){
        if(current == i){
            str += '<li class=\"active\"><a href=\"javascript:void(0)\">' + i + ' <span class=\"sr-only\">(current)</span></a></li>';
        }else{
            str += '<li><a href=\"javascript:asyncLoading(' + i + ')\">' + i + ' <span class=\"sr-only\"></span></a></li>';
        }
    }
    str += '<li><a href=\"javascript:asyncLoading(' + (current + 1) + ')\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    $(".pagination").html(str);
}

/**
 * 获取的结果超过十个
 */
function parsePageMoreThanTen(){
    //尾溢出解决
    if (current > total - 4){
        tailOverflow();
        return;
    }

    //头溢出解决
    if (current < 7){
        headOverflow();
        return;
    }

    //调整位置的处理
    resetButtonPosition();
}

function parsePage(data,cur){
    current = cur;
    if (data && data.list.length > 0){
        total = parseInt(data.totalRecords / data.rows);
        total = total + (data.totalRecords % data.rows == 0 ? 0 : 1);
        if (total < 10){
            parsePageLessThanTen();
        }else{
            parsePageMoreThanTen();
        }
    }
}
