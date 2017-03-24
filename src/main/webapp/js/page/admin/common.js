/**
 * Created by carllongj on 2017/3/24.
 */
/** 所有页面都通用的js */

function requestForUser (state) {
    $.ajax({url:"/admin/manage/user/all.action?state=" + state,success:function () {
        console.log(data);
    }});
}

$(function () {

});