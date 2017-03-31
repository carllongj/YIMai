/**
 * Created by carllongj on 2017/3/31.
 */

function parseDate (date){
    if ('未设置' != date) {
        return new Date(date).format('yyyy-MM-dd hh:mm');
    }
    return date;
}


/** 指定当前的页面 */
var current;
/** 指定总的页码数 */
var total;
/** 指定当前的最后一页 */
var last;


$(function(){
    $.ajax({url:"/admin/manage/user/all.action?state=0",success:function (data) {
        var str = '';
        if (data && data.totalRecords > 0){
            str = "<table class=\"table table-striped\">" +
                "<thead>" +
                "<tr>" +
                "<td>用户名</td>" +
                "<td>昵称</td>" +
                "<td>生日</td>" +
                "<td>手机号码</td>" +
                "<td>邮箱</td>" +
                "<td>状态</td>" +
                "<td>注册日期</td>" +
                "<td>操作</td>" +
                "</tr>" +
                "</thead>" + "<tbody>" ;
            for (var i = 0; i < data.list.length;i++){
                str += "<tr>" +
                    "<td>" + data.list[i].username + "</td>" +
                    "<td>" + data.list[i].nickname + "</td>" +
                    "<td>" + parseDate(data.list[i].birthday) + "</td>" +
                    "<td>" + data.list[i].phone + "</td>" +
                    "<td>" + data.list[i].email + "</td>" +
                    "<td>" + data.list[i].state + "</td>" +
                    "<td>" + parseDate(data.list[i].created) + "</td>" +
                    "<td>" +
                    "<a href='javascript:linkUser()' class=\"btn btn-default btn-sm\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"编辑\">" +
                    "<span class=\"glyphicon glyphicon-pencil\"></span>" +
                    "</a>" +
                    "</td>" +
                    "</tr>";
            }

            str +="</tbody>" + "</table>";
        }else{
            str = '当前没有用户信息';
        }
        parsePage(data,1);
        $("#showInfoArea").html(str);
    }});
});

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