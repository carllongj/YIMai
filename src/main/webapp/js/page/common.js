/**
 * Created by carllongj on 2017/3/3.
 */
/** 许多页面都要使用的方法 */
/**
 * 从cookie中获取用户的信息
 */
function parseUserInfo () {
    var jsonObj = $.cookie("USER_INFO");
    if (jsonObj != undefined){
        var userObj = JSON.parse(jsonObj);
        document.getElementById("userinfomation").href = '/person/info';
        var content = $("#userinfomation").html();
        var remain = content.substring(0,content.length - 2);
        if (userObj.nickname != undefined){
            $("#userinfomation").html(remain + userObj.nickname);
        }else{
            $("#userinfomation").html(remain + userObj.username);
        }
    }
}

/**
 * 请求和解析分类列表的js
 */
function requestForCategory(selector){
    //执行ajax请求,获取服务器的分类信息
    $.ajax({url:'/category/list.action',success:function (data) {
        if (data){
            var category = data.data;
            if (category !== null){
                var i = category.length - 1;
                for (;i >= 0;i--){
                    $(selector).after("<option value=" + category[i].id + ">" + category[i].name + "</option>");
                }
            }
        }
    }});
}