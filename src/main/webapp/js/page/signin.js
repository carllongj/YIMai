/**
 * Created by carllongj on 2017/3/2.
 */

/** 标记用户是否已经点击登录了 */
var isClicked = false;

/**
 * 还原登录按钮的状态
 */
function resetLoginButton() {
    /** 如果用户已经点击过按钮了 */
    if (isClicked) {
        $("#submitBtn").css({cursor:"pointer",background:"#0099e5"});
        $("#submitBtn").removeAttr("disabled","disabled");
        $("button em").text("登录");
        $("#errorMessage").remove();
    }
}

$(function () {

    /** 获取到用户的用户名称 */
    var username = $.cookie('USERNAME');

    /** 保存用户的用户名称 */
    if (username != undefined && username != ''){
        $("#username").val(username);
    }

    /** 按钮绑定事件 */
    sidebarCate($("#open-button"),$(".icon-list"));

    /** 给用户名输入框设置监听 */
    $("#username").bind("focus",function () {
        resetLoginButton();
    });
    /** 给密码输入框设置监听 */
    $("#passwd").bind("focus",function () {
        resetLoginButton();
    });

    $("#submitBtn").bind('click',function () {
        $("#submitBtn").css({cursor:"not-allowed",background:"grey"});
        $("#submitBtn").attr("disabled","disabled");
        $("button em").text("正在处理,请稍候...");
        $.post('/user/login.action',$("#loginForm").serialize(),function (data) {
            isClicked = true;
            if (data.status) {
                if (redirect != undefined && redirect != ''){
                    location.href = redirect;
                }else{
                    location.href = 'http://localhost:8080';
                }
            }else{
                $("#passwd").after("<span id='errorMessage' style='position: relative;top: -12px;" +
                    "color:red;font-weight: 500;font-family: 'Ubuntu Condensed', sans-serif'>" + data.msg + "</span>");
            }
        },'json');
    })
});