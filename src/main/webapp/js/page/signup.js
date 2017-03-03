/**
 * Created by carllongj on 2017/3/2.
 */

/** 定义正则表达式 */
var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;

var flagUsername ;
var flagEmail ;
var flagPassword ;
var flagRetype ;

function forward(){
    location.href = "http://localhost:8080"
}

$(function () {

    /**
     * 表单的ajax请求
     */
    $("#username").bind("blur",function () {
        var nameText = $("#username").val();
        if (!nameText || nameText.trim() == '') {
            if ($("#username_span").val() !== undefined) {
                flagUsername = false;
                return;
            } else {
                flagUsername = false;
                $("#username").after("<span id='username_span' style='position: relative;top: -12px;" +
                    "color:red;font-weight: 500'>用户名不能为空</span>");
            }
        }else if (nameText.length < 6 || nameText.length > 30){
            flagUsername = false;
            $("#username").after("<span id='username_span' style='position: relative;top: -12px;" +
                "color:red;font-weight: 500'>用户名的长度应在6-30个字符之间</span>");
        }else {
            flagUsername = false;
            $.ajax({url :'/user/name/' + nameText + ".action",
                dataTypes:'json',
                success:function(data){
                    var result = JSON.parse(data);
                    if (result.valid ) {
                        flagUsername = true;
                        $("#username").after("<span id='username_span' style='position: relative;top: -12px;" +
                            "color:limegreen;font-weight: 500'>该用户名可用</span>");
                    }else{
                        $("#username").after("<span id='username_span' style='position: relative;top: -12px;" +
                            "color:red;font-weight: 500'>该用户名已被注册</span>");
                    }
                }
            });
        }
    });

    /**
     * 用户的邮箱验证
     */
    $("#useremail").bind("blur",function () {
        var emailText = $("#useremail").val();
        if (!emailText || emailText.trim() == '') {
            if ($("#useremail_span").val() !== undefined) {
                flagEmail = false;
                return;
            } else {
                flagEmail = false;
                $("#useremail").after("<span id='useremail_span' style='position: relative;top: -12px;" +
                    "color:red;font-weight: 500'>用户邮箱不能为空</span>");
            }
        }else if (!reg.test(emailText)){
            flagEmail = false;
            $("#useremail").after("<span id='useremail_span' style='position: relative;top: -12px;" +
                "color:red;font-weight: 500'>邮箱的格式不正确</span>");
        }else{
            flagEmail = false;
            $.ajax({url:"/user/email/" + emailText + ".action",dataTypes:'json',success:function (data) {
                    if (!data || !data.status) {
                        flagEmail = false;
                        $("#useremail").after("<span id='useremail_span' style='position: relative;top: -12px;" +
                            "color:red;font-weight: 500'>该邮箱已被注册</span>");
                    }else{
                        flagEmail = true;
                        $("#useremail").after("<span id='useremail_span' style='position: relative;top: -12px;" +
                            "color:limegreen;font-weight: 500'>该邮箱可用</span>");
                    }
            }})
        }
    });

    /**
     * 用户密码的输入验证
     */
    $("#userpassword").bind("blur",function () {
       var passwordText = $("#userpassword").val();
        if (!passwordText || passwordText.trim == ''){
            flagPassword = false;
            $("#userpassword").after("<span id='userpassword_span' style='position: relative;top: -12px;" +
                "color:red;font-weight: 500'>用户密码不能为空</span>");
        }else if (passwordText.length < 6 ){
            flagPassword = false;
            $("#userpassword").after("<span id='userpassword_span' style='position: relative;top: -12px;" +
                "color:red;font-weight: 500'>用户的密码长度至少为6</span>");
        }else {
            flagPassword = true;
        }
    });

    /**
     * 验证用户的输入的第二次的密码
     * */
    $("#retypepassword").bind("blur",function () {
        var retype = $("#retypepassword").val();
        var userpass = $("#userpassword").val();

        if (retype !== userpass) {
            flagRetype = false;
            $("#retypepassword").after("<span id='retypepassword_span' style='position: relative;top: -12px;" +
                "color:red;font-weight: 500'>输入的两次的密码不相同</span>");
        }else {
            flagRetype = true;
        }
    });


    /**
     * 注册按钮的事件绑定
     */
    $("#submitBtn").bind("click",function () {
        if (flagUsername && flagEmail && flagPassword && flagRetype) {
            $("#submitBtn").css({cursor:"not-allowed",background:"grey"});
            $("#submitBtn").attr("disabled","disabled");
            $("button em").text("正在处理,请稍候...");
            $.post('/user/register.action',$("#registerForm").serialize(),function (data) {
                if (data && data.status){
                    $("#formContent").after("<span style='color: #0099e5;font-size: large;font-family: 'Ubuntu Condensed', sans-serif'>注册成功,请查看您的邮箱进行激活帐号</span>");
                    $("#registerForm").hide();
                }else{
                    if(data.message.includes('用户名')){
                        $("#username").after("<span id='username_span' style='position: relative;top: -12px;" +
                            "color:red;font-weight: 500'>" + data.message + "</span>");
                    }else if(data.message.includes('邮箱')){
                        $("#useremail").after("<span id='useremail_span' style='position: relative;top: -12px;" +
                            "color:red;font-weight: 500'>" + data.message + "</span>");
                    }
                }
            },'json')
        }else{
            return false;
        }
    });

    /**
     * 用户名的取消显示提示信息
     */
    $("#username").bind("focus",function () {
        if ($("#username_span").val() == undefined) {
            return;
        } else {
            $("#username_span").remove();
        }
    });

    /**
     * 取消邮箱的显示提示
     */
    $("#useremail").bind("focus",function () {
        if ($("#useremail_span").val() == undefined) {
            return;
        } else {
            $("#useremail_span").remove();
        }
    });

    /**
     * 取消显示用户密码的提示信息
     */
    $("#userpassword").bind("focus",function () {
        if ($("#userpassword_span").val() == undefined) {
            return;
        } else {
            $("#userpassword_span").remove();
        }
    });

    /**
     * 取消显示第二次输入密码的提示信息
     */
    $("#retypepassword").bind("focus",function () {
        if ($("#retypepassword_span").val() == undefined) {
            return;
        } else {
            $("#retypepassword_span").remove();
        }
    });

});