/**
 * Created by carllongj on 2017/3/24.
 */
/** 所有用户页面的js */

$(function () {
    $.ajax({url:"/admin/manage/user/all.action",success:function (data) {
        console.log(data);
    }});
    parseUserInfo();
});
