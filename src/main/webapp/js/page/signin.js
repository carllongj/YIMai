/**
 * Created by carllongj on 2017/3/2.
 */
$(function () {
    /** 获取到用户的用户名称 */
    var username = $.cookie('USERNAME');

    /** 保存用户的用户名称 */
    if (username != undefined && username != ''){
        $("#username").val(username);
    }
});