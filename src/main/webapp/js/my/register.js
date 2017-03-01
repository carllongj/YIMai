/**
 * Created by carllongj on 2016/12/27.
 */

var requestUsernameStatus = true;

$(function () {

        $('#registerForm').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {/*input状态样式图片*/
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {/*验证：规则*/
                username: {//验证input项：验证规则
                    message: 'The username is not valid',

                    validators: {
                        notEmpty: {//非空验证：提示消息
                            message: '用户名不能为空'
                        },
                        stringLength: {
                            min: 6,
                            max: 30,
                            message: '用户名长度必须在6到30之间'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_\.]+$/,
                            message: '用户名由数字字母下划线和.组成'
                        },
                        remote: {
                            url: '/user/name/' + $("#username").val(),
                            message: '用户名已经被注册'
                        }
                    }
                },
                password: {
                    message:'密码无效',
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        stringLength: {
                            min: 6,
                            max: 30,
                            message: '用户名长度必须在6到30之间'
                        },
                        identical: {//相同
                            field: 'password', //需要进行比较的input name值
                            message: '两次密码不一致'
                        },
                        different: {//不能和用户名相同
                            field: 'username',//需要进行比较的input name值
                            message: '不能和用户名相同'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_\.]+$/,
                            message: 'The username can only consist of alphabetical, number, dot and underscore'
                        }
                    }
                },
                repassword: {
                    message: '密码无效',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        },
                        stringLength: {
                            min: 6,
                            max: 30,
                            message: '用户名长度必须在6到30之间'
                        },
                        identical: {//相同
                            field: 'password',
                            message: '两次密码不一致'
                        },
                        different: {//不能和用户名相同
                            field: 'username',
                            message: '不能和用户名相同'
                        },
                        regexp: {//匹配规则
                            regexp: /^[a-zA-Z0-9_\.]+$/,
                            message: 'The username can only consist of alphabetical, number, dot and underscore'
                        }
                    }
                }
            }
        });

    $("#username").bind("blur",function () {
        var inputText = $("#username");
        if (!inputText.val() || inputText.val().trim() == ''){
            inputText.val('请输入用户名');
        }else if (requestUsernameStatus){
            $.ajax({url :'/user/name/' + inputText.val() + ".action",dataTypes:'json',success:function(data){
                if (!data || !data.status) {
                    alert('当前用户名已经被注册');
                }else{
                    alert('当前用户名可用');
                }
                requestUsernameStatus = false;
            }});
        }else {}
    });

    $("#passwd2").bind("blur",function () {
        var passwdTextObj1 = $("#passwd1");
        var passwdTextObj2 = $("#passwd2");

        if (passwdTextObj1.val() != passwdTextObj2.val()){
            alert('输入的密码不一致');
            $("#registerBtn").attr("disabled","disabled");
        }
    });

    $("#email").bind("blur",function () {
        var emailObj = $("#email");
        if (emailObj.val() == '' || emailObj.val().trim() == ''){
            alert("邮箱不能为空");
        }/*else if(){

        }*/ else{
            $.ajax({url:"/user/email/" + emailObj.val() + ".action",dataTypes:'json',success:function (data) {
                if (!data || !data.status){
                    alert("该邮箱不可用");
                }else{
                    alert('该邮箱可用');
                }
            }})}
    });

    $("#registerBtn").bind("click",function () {
        $.post('/user/register.action',$("#registerForm").serialize(),function (data) {
            if (!data.status){
                console.log(data.msg);
            }else{
                setTimeout(function () {
                    location.href = 'http://localhost:8080/page/login.action'
                },1000)
            }
        },'json')
    })
});

