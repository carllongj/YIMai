/**
 * Created by carllongj on 2017/3/2.
 */
/**
 * 首页的js
 */
$(function () {
    /**
     * 控制首页的用户名的显示,如果用户设置了昵称,则显示昵称,否则显示用户名
     * 如果用户没有登录,即没有cookie,那么则显示去登陆的字样
     */
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
});