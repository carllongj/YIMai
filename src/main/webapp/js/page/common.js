/**
 * Created by carllongj on 2017/3/3.
 */
/** 许多页面都要使用的方法 */
/**
 * 从cookie中获取用户的信息
 */
function parseUserInfo() {
    var jsonObj = $.cookie("USER_INFO");
    if (jsonObj != undefined) {
        var userObj = JSON.parse(jsonObj);
        document.getElementById("userinfomation").href = '/userinfo/user.action';
        if ( !userObj.nickname ||userObj.nickname == undefined || userObj.nickname == '') {
            $("#userinfomation").html("<i class=\"fa fa-user\" aria-hidden=\"true\"></i>" + userObj.username);
        } else {
            $("#userinfomation").html("<i class=\"fa fa-user\" aria-hidden=\"true\"></i>" + userObj.nickname);
        }
    }
}

/**
 * 设置按钮的样式
 */
function setButtonStyle(selector) {
    selector.css({cursor: "not-allowed", background: "grey"});
    selector.attr("disabled", "disabled");
}

/**
 * 重置按钮的样式
 * @param selector
 */
function resetButtonStyle(selector) {
    selector.css({cursor: "pointer", background: "#0099e5"});
    selector.removeAttr("disabled");
}

function postMyAd() {
    $.ajax({
        url: "/userinfo/wallet/check.action", success: function (data) {
            if (data && data.status) {
                location.href = '/post/post_ad.action';
            } else {
                swal("操作结果", data.msg, "error");
            }
        }, dataType: 'json'
    });
}

function download() {
    swal("此功能还在开发中.....", "", "info");
}

/**
 * 格式化金额显示
 */
function formatMoney(money) {
    money = money + "";
    if (!money || money.trim() == '') {
        return;
    }

    if (money.length > 3) {
        var end = money.substring(money.length - 2, money.length);
        var start = money.substr(0, money.length - 2);
        return start + "." + end + "元";
    }
}

/**
 * 请求和解析分类列表的js
 */
function requestForCategory(selector, func) {
    //执行ajax请求,获取服务器的分类信息
    $.ajax({
        url: '/category/list.action', success: function (data) {
            if (data) {
                var category = data.data;
                if (category !== null) {
                    var i = category.length - 1;
                    for (; i >= 0; i--) {
                        $(selector).after("<option value=" + category[i].id + ">" + category[i].name + "</option>");
                    }
                }
                if (func != undefined) {
                    func(data);
                }
            }
        }
    });
}

/**
 * 如果需要的进行按钮的加载
 * @param selector
 */
function sidebarCate(selectorBind, selector) {
    selectorBind.bind('click', function () {
        $.ajax({
            url: '/category/list.action', success: function (data) {
                if (data && data.status) {
                    var len = 0;
                    var elements = "";
                    for (; len < data.data.length; len++) {
                        elements += "<a href=\"/query/list.action?cid=" + data.data[len].id + "\"><i class=\"fa fa-fw fa-" + data.data[len].icon + "\"></i><span>" + data.data[len].name + "</span></a>";
                    }
                    selector.html(elements);
                }
            }
        });
    });
}

function autoBind(selector) {
    $.ajax({
        url: '/category/list.action', success: function (data) {
            if (data && data.status) {
                var len = 0;
                var elements = "";
                for (; len < data.data.length; len++) {
                    elements += "<a href=\"/query/list.action?cid=" + data.data[len].id + "\"><i class=\"fa fa-fw fa-" + data.data[len].icon + "\"></i><span>" + data.data[len].name + "</span></a>";
                }
                selector.html(elements);
            }
        }
    });
}