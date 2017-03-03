/**
 * Created by carllongj on 2017/3/3.
 */
/**
 * 发布我的商品信息页面的js
 */
$(function () {

    /** 解析用户登录后的信息 */
    parseUserInfo();

    setTimeout(requestForCategory($("#categoryListSelector")),1000);

});