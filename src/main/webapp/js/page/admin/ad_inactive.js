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

function asyncLoading(page) {
    $.ajax({url:"/admin/manage/user/all.action?state=0&page=" + page,success:function (data) {
        var str = '';
        if (data && data.totalRecords > 0){
            str = "<table class=\"table table-striped\">" +
                "<thead>" +
                "<tr>" +
                "<td><span class='setFontSize'>用户名</span></td>" +
                "<td><span class='setFontSize'>昵称</span></td>" +
                "<td><span class='setFontSize'>生日</span></td>" +
                "<td><span class='setFontSize'>手机号码</span></td>" +
                "<td><span class='setFontSize'>邮箱</span></td>" +
                "<td><span class='setFontSize'>状态</span></td>" +
                "<td><span class='setFontSize'>注册日期</span></td>" +
                "<td><span class='setFontSize'>是否禁用</span></td>" +
                "<td><span class='setFontSize'>操作</span></td>" +
                "</tr>" +
                "</thead>" + "<tbody>" ;
            for (var i = 0; i < data.list.length;i++){
                str += "<tr>" +
                    "<td><span class='setFontSize'>" + data.list[i].username + "</span></td>" +
                    "<td><span class='setFontSize'>" + data.list[i].nickname + "</span></td>" +
                    "<td><span class='setFontSize'>" + parseDate(data.list[i].birthday) + "</span></td>" +
                    "<td><span class='setFontSize'>" + data.list[i].phone + "</span></td>" +
                    "<td><span class='setFontSize'>" + data.list[i].email + "</span></td>" +
                    "<td><span class='setFontSize'>" + data.list[i].state + "</span></td>" +
                    "<td><span class='setFontSize'>" + parseDate(data.list[i].created) + "</span></td>" +
                    "<td><span class='setFontSize'>" + data.list[i].forbidden + "</span></td>" +
                    "<td>" +
                    "<a href='javascript:forbiddenUser(\"" + data.list[i].id + "\"," + page + ")' class=\"btn btn-default btn-sm\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"禁用\">" +
                    "<span class=\"glyphicon glyphicon-trash\"></span>" +
                    "</a>" +
                    "</td>" +
                    "</tr>";
            }

            str +="</tbody>" + "</table>";
        }else{
            str = '当前没有用户信息';
        }
        parsePage(data,page);
        $("#showInfoArea").html(str);
    }});
}

$(function(){
    asyncLoading(1);
    parseUserInfo();
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