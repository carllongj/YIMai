/**
 * Created by carllongj on 2017/4/7.
 */

/**
 * 未通过审核商品页面的js
 */

function parseItemStatus(status){
    if (0 == status){
        return "待售";
    }else if(1 == status){
        return "已被拍下,未付款";
    }else if (2 == status){
        return "已交易";
    }

    return "未知状态";
}

/** 指定当前的页面 */
var current;
/** 指定总的页码数 */
var total;
/** 指定当前的最后一页 */
var last;


function asyncLoading(page,passStatus) {
    $.ajax({
        url: "/admin/manage/item/show/" + page + ".action?passStatus=" + passStatus,
        success: function (data) {
            var str = '';
            if (data && data.totalRecords > 0) {
                str = "<table class=\"table table-striped table-bordered\">" +
                    "<thead><tr><td style='width:10%'>商品属性名</td><td>商品属性值</td></tr></thead>" +
                    "<div class='col-xs-6'><tbody>" +
                    "<tr><td>标题</td><td>" + data.list[0].title + "</td></tr>" +
                    "<tr><td>价格</td><td>" + parseDate(data.list[0].price) + "</td></tr>" +
                    "<tr><td>发布者</td><td>" + data.list[0].username + "</td></tr>" +
                    "<tr><td>分类</td><td>" + data.list[0].category + "</td></tr>" +
                    "<tr><td>详细</td><td>" + data.list[0].content + "</td></tr>" +
                    "<tr><td>状态</td><td>" + parseItemStatus(data.list[0].status) + "</td></tr>" +
                    "<tr><td>图片</td><td>" + data.list[0].image + "</td></tr>" +
                    "<tr><td>是否审核</td><td>" + data.list[0].pass_status + "</td></tr>" +
                    "<tr><td>创建日期</td><td>" + parseDate(data.list[0].created) + "</td></tr>" + "</tbody></div></table>";
             } else {
                swal("操作结果!", "当前没有相关的商品的信息", "error");
                setTimeout("location.href = '/admin/show/allitems.action'", 1000);
            }
            $("#showInfoArea").html(str);
            parsePage(data, page);
        }
    });
}

$(function () {
    asyncLoading(1,0);
    parseUserInfo();
});