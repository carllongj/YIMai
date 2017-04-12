/**
 * Created by carllongj on 2017/4/12.
 */

/**
 * 解析我的地址页面的js
 */

function parseMyAddresses(){
    $.ajax({url:"/userinfo/show/myaddresses.action",success:function (data) {

    }});
    $("#showInfoArea").html();
}

$(function(){
    parseUserInfo();
    parseMyAddresses();
});