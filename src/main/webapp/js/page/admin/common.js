/**
 * Created by carllongj on 2017/3/24.
 */
/** 所有页面都通用的js */

function parseDate (date){
    if ('未设置' != date) {
        return new Date(date).format('yyyy-MM-dd hh:mm');
    }
    return date;
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
        str = '<li class=\"disabled\"><a href="void(0)" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    }else{
        str = '<li><a href="asyncLoading(' + current - 1 + ')" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>';
    }
    for (var i = 1;i <= total;i++){
        if (i == current){
            str += '<li class=\"active\"><a href=\"void(0)\">' + i + ' <span class=\"sr-only\">(current)</span></a></li>';
        }else{
            str += '<li><a href=\"asyncLoading(' + i + ')\">' + i + ' <span class=\"sr-only\"></span></a></li>';
        }
    }
    if (last == total){
        str += '<li class=\"disabled\"><a href=\"#\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    }else{
        str += '<li><a href=\"asyncLoading(' + (current + 1) + ')\" aria-label=\"Previous\"><span aria-hidden=\"true\">&raquo;</span></a></li>';
    }
    $(".pagination").html(str);
}

/**
 * 获取的结果超过十个
 */
function parsePageMoreThanTen(){

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
