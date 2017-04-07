/**
 * Created by carllongj on 2017/4/7.
 */
/**
 * 所有分类页面的js
 */

/** 指定当前的页面 */
var current;
/** 指定总的页码数 */
var total;
/** 指定当前的最后一页 */
var last;


/**
 * 解析带有模板的日期
 * @param date
 * @returns {*}
 */
function parseDateWithTemplate (date){
    if ('未设置' != date) {
        return new Date(date).format('yyyy-MM-dd');
    }
    return date;
}

/**
 * 解析状态
 */
function parseStatus(status){
    if (status == 1) {
        return '可用';
    }else{
        return '不可用';
    }
}

/**
 * ajax加载数据
 * @param page
 */
function asyncLoading (page){
    $.ajax({url:"/admin/manage/item/all/category.action?page=" + page,success:function (data) {
        var str = '';
        if (data && data.totalRecords > 0){
            str = "<table class=\"table table-striped\">" +
                "<thead>" +
                "<tr>" +
                "<td><span class='setFontSize'>分类名称</span></td>" +
                "<td><span class='setFontSize'>状态</span></td>" +
                "<td><span class='setFontSize'>图标名称</span></td>" +
                "<td><span class='setFontSize'>创建日期</span></td>" +
                "</tr>" +
                "</thead>" + "<tbody>" ;
            for (var i = 0; i < data.list.length;i++){
                str += "<tr>" +
                    "<td><span class='setFontSizeContent'>" + data.list[i].name + "</span></td>" +
                    "<td><span class='setFontSizeContent'>" + parseStatus(data.list[i].status) + "</span></td>" +
                    "<td><span class='setFontSizeContent'>" + data.list[i].icon + "</span></td>" +
                    "<td><span class='setFontSizeContent'>" + parseDateWithTemplate(data.list[i].created) + "</span></td>" +
                    "<td>" +
                    "<a href='javascript:linkCategory(" + data.list[i].id + ")' class=\"btn btn-default btn-sm\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"编辑\">" +
                    "<span class=\"glyphicon glyphicon-pencil\"></span>" +
                    "</a>" +
                    "<a href='javascript:deleteCategory(" + data.list[i].id + ")' class=\"btn btn-default btn-sm\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"删除\">" +
                    "<span class=\"glyphicon glyphicon-trash\"></span>" +
                    "</a>" +
                    "</td>" +
                    "</tr>";
            }
    str +="</tbody>" + "</table>";
    parsePage(data,page);
}else{
    str = '当前没有用户信息';
}
$("#showInfoArea").html(str);
}});
}

$(function () {
    $('[data-toggle="popover"]').popover();
    asyncLoading(1);
    parseUserInfo();
});