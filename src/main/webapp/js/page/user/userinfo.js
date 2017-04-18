/**
 * Created by carllongj on 2017/4/11.
 */
/**
 * 用户个人中心的页面的js
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

function parseForbidden(state) {
    if (0 == state) {
        return "已禁用";
    } else if (1 == state) {
        return "未禁用";
    }
}

function parseBirthday(birthday) {
    if (undefined == birthday) {
        return '未设置'
    } else {
        return parseDateWithTemplate(birthday);
    }
}

function parseFormDate() {
    if ($(".setFontSize tr:eq(1) td:eq(1)").text().length > 0) {
        $("#updateInfoForm input:eq(0)").val($(".setFontSize tr:eq(1) td:eq(1)").text());
    }

    if ($(".setFontSize tr:eq(4) td:eq(1)").text().length > 0) {
        $("#updateInfoForm input:eq(1)").val($(".setFontSize tr:eq(4) td:eq(1)").text());
    }

    if ($(".setFontSize tr:eq(2) td:eq(1)").text().length > 0) {
        $("#updateInfoForm input:eq(2)").val($(".setFontSize tr:eq(2) td:eq(1)").text());
    }

}

function parseNickname(nick) {
    if (undefined == nick) {
        return ''
    } else {
        return nick;
    }
}

function parsePhone(phone) {
    if (undefined == phone) {
        return '';
    } else {
        return phone;
    }
}

function parseDateWithTemplate(date) {
    if ('未设置' != date) {
        return new Date(date).format('yyyy-MM-dd');
    }
    return date;
}

function parseDocument() {
    var user = JSON.parse(info);
    var str = "<table class='table table-bordered table-hover setFontSize'>" +
        "<tr><td>名称</td><td>" + user.username + "</td>" +
        "<tr><td>昵称</td><td>" + parseNickname(user.nickname) + "</td>" +
        "<tr><td>生日</td><td>" + parseBirthday(user.birthday) + "</td>" +
        "<tr><td>邮箱</td><td>" + user.email + "</td>" +
        "<tr><td>手机</td><td>" + parsePhone(user.phone) + "</td>" +
        "<tr><td>禁用状态</td><td>" + parseForbidden(user.forbidden) + "</td>" +
        "<tr><td>注册日期</td><td>" + parseDateWithTemplate(user.created) + "</td>" +
        "</tr></table>";
    str += "<div class='clearfix'></div><div class='col-xs-10'></div><div class='col-xs-2'>" +
        "<button type='button' id='editMyInfo' class='btn btn-success'>编辑个人信息</button> " + "</div>";
    $("#showInfoArea").html(str);
    $("#editMyInfo").bind("click", function () {
        var form = "<form id='updateInfoForm'> " +
            "<div class=\"form-group\"> " +
            "<label for=\"userNickname\">昵称</label> " +
            "<input type=\"text\" class=\"form-control\" name='nickname' placeholder=\"昵称\"> " +
            "</div> " +
            "<div class=\"form-group\"> " +
            "<label for=\"phone\">手机</label> " +
            "<input type=\"text\" class=\"form-control\" name='phone' placeholder=\"手机\"> " +
            "</div> " +
            "<div class=\"form-group\"> " +
            "<label for=\"birthday\">生日</label> " +
            "<input type=\"text\" class='form-control' id='birthday' name='birthday' placeholder=\"生日\"> " +
            "</div> " +
            "</div> " +
            "</form> ";
        $(".modal-body").html(form);
        parseFormDate();
        $(".modal").modal();
    });

    $("#updateInfoBtn").bind("click", function () {
        swal({
                title: "保存更改",
                text: "",
                type: "info",
                closeOnConfirm: false,
                showCancelButton: true,
                showLoaderOnConfirm: true,
            },
            function(){
                $.post("/userinfo/update.action",$("#updateInfoForm").serialize(),function (data) {
                    if (data && data.status){
                        swal("操作结果","操作成功","success");
                        setTimeout("location.reload()",1000);
                    }else {
                        swal("操作结果",data.msg,"error");
                    }
                },'json');
            });
    });
}

$(function () {
    parseUserInfo();
    parseDocument();
    /** 绑定按钮的点击事件 */
    setTimeout("autoBind($('.icon-list'))",500);
});