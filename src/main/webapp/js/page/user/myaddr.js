/**
 * Created by carllongj on 2017/4/12.
 */

/**
 * 解析我的地址页面的js
 */

function parseMyAddresses() {
    var str = "<tr class='setFontSize'> " +
        "<td>收货人</td> " +
        "<td>详细地址</td> " +
        "<td>手机号码</td> " +
        "<td>操作</td> " +
        "<td>默认</td> " +
        "</tr>";
    $.ajax({
        url: "/userinfo/show/myaddresses.action", success: function (data) {
            if (data && data.status) {
                if (data.data.length > 0) {
                    for (var i = 0; i < data.data.length; i++) {
                        str += "<tr class='setFontSizeCon'><td>" + data.data[i].username + "</td>" +
                            "<td>" + data.data[i].address + "</td>" +
                            "<td>" + data.data[i].phone + "</td>";

                        if (data.data[i].defAddr) {
                            str +="<td><a href='javascript:deleteAddr(\"" + data.data[i].id + "\")' title='删除'><span class='glyphicon glyphicon-trash'></span></a></td><td>默认地址</td>";
                        } else {
                            str += "<td><a href='javascript:setDefault(\"" + data.data[i].id + "\")' title='设为默认'><span class='glyphicon glyphicon-asterisk'></span></a> | " +
                                "<a href='javascript:deleteAddr(\"" + data.data[i].id + "\")' title='删除'><span class='glyphicon glyphicon-trash'></span></a></td><td>非默认地址</td>";
                        }
                    }
                    $("#addressList").html(str);
                }
            }
        }
    });
}

function setDefault(id) {
    $.ajax({url:"/userinfo/def/addr.action?id=" + id,success:function (data) {
        if (data && data.status) {
            swal("操作结果", "设置成功", "success");
            parseMyAddresses();
        } else {
            swal("操作结果", data.msg, "error");
        }
    }})
}

function deleteAddr(id) {
    swal({
            title: "删除地址",
            text: "删除地址信息",
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true,
        },
        function () {
            $.ajax({
                url: "/userinfo/del/addr.action?id=" + id, success: function (data) {
                    if (data && data.status) {
                        swal("操作结果", "删除成功", "success");
                        parseMyAddresses();
                    } else {
                        swal("操作结果", data.msg, "error");
                    }
                }
            });
        });
}

function check() {
    if ($("input[name=address]").val().trim().length == 0) {
        return false;
    }

    if ($("input[name=phone]").val().trim().length == 0) {
        return false;
    }

    if ($("input[name=phone]").val().trim().length == 0) {
        return false;
    }

    return true;
}

function bindFunction() {

    $("#saveMyAddress").bind("click", function () {

        if (!check()) {
            swal("错误信息", "您所填写的数据不全", "error");
            return;
        }

        swal({
                title: "保存信息",
                text: "保存地址信息",
                type: "info",
                showCancelButton: true,
                closeOnConfirm: false,
                showLoaderOnConfirm: true,
            },
            function () {
                $.post("/userinfo/add/addr.action", $("#inputDataForm").serialize(), function (data) {
                    if (data && data.status) {
                        swal("操作结果", "操作成功", "success");
                        setTimeout("location.reload()", 1000);
                    } else {
                        swal("操作结果", data.msg, "error");
                    }
                });
            });
    });
}

$(function () {
    parseUserInfo();
    bindFunction();
    parseMyAddresses();
    /** 绑定按钮的点击事件 */
    setTimeout("autoBind($('.icon-list'))",500);
});