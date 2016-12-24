


function showIndex () {
    location.href = "http://localhost:8080/index.action"
}

function goRegister() {
    location.href = "http://localhost:8080/user/register.action"
}

function lostPwd() {
    location.href = "http://localhost:8080/user/lostPwd.action"
}

var redirect = '${redirect}';

 $(function() {
        /**
         * 下面是进行插件初始化
         * 你只需传入相应的键值对
         * */
        $('#userdataform').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {/*输入框不同状态，显示图片的样式*/
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {/*验证*/
                username: {/*键名username和input name值对应*/
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {/*非空提示*/
                            message: '用户名不能为空'
                        },
                        stringLength: {/*长度提示*/
                            min: 6,
                            max: 30,
                            message: '用户名长度必须在6到30之间'
                        }/*最后一个没有逗号*/
                    }
                },
                passwd: {
                    message:'密码无效',
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        stringLength: {
                            min: 6,
                            max: 30,
                            message: '用户名长度必须在6到30之间'
                        }
                    }
                }
            }
        });

    var valid = {
        checkUserName:function () {
            var usernameLength = $("#inputUsername").val();
            return usernameLength.length != 0;
        },
        checkPassword:function () {
            var usernameLength = $("#inputPassword").val();
            return usernameLength.length != 0;
        }
    }

    $("#loginButton").bind("click",function () {
        if(valid.checkUserName() && valid.checkPassword()){
            $.post('user/login.action',$("#userdataform").serialize(),function (data) {
                console.log(data);
                if(data.status){
                    if(redirect){
                        location.href = redirect;
                    }else{
                         location.href = 'http://localhot:8080'
                    }
                }else{

                }
            },'json');
        }else{
            if(!valid.checkUserName()){
                alert('用户名不能为空');
            }

            return ;

            if(!valid.checkPassword()){
                alert('密码不能为空');
            }
        }
    });
});