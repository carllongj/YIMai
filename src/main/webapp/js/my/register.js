/**
 * Created by carllongj on 2016/12/27.
 */

var requestUsernameStatus = true;

$(function () {
    $("#username").bind("blur",function () {
        var inputText = $("#username");
        if (inputText.val() == '' || inputText.val().trim() == ''){
            inputText.val('请输入用户名');
        }else if (requestUsernameStatus){
            $.ajax({url :'/user/name/' + inputText.val() + ".action",dataTypes:'json',success:function(data){
                if (!data || !data.status) {
                    alert('当前用户名已经被注册');
                }else{
                    alert('当前用户名可用');
                    requestUsernameStatus = false;
                }
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
    })

    $("#email").bind("blur",function () {
        var emailObj = $("#email");
        if (emailObj.val() == '' || emailObj.val().trim() == ''){
            alert("邮箱不能为空");
        }else{
            $.ajax({url:"/user/email/" + emailObj.val() + ".action",dataTypes:'json',success:function (data) {
                if (!data || !data.status){
                    alert("该邮箱不可用");
                }else{
                    alert('该邮箱可用');
                }
            }})}
    })

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

