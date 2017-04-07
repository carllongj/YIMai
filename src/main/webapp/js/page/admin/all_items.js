/**
 * Created by carllongj on 2017/4/2.
 */

function asyncLoading(page){
    $.ajax({url:"/admin/manage/item/show/" + page + ".action",success:function (data) {
        var str = '';
        if (data && data.totalRecords > 0){
            console.log(data);
            str = "<table class=\"table table-striped\">" +
                "<thead>" +
                "<tr>" +
                "<td>标题</td>" +
                "<td>价格</td>" +
                "<td>发布者</td>" +
                "<td>分类</td>" +
                "<td>详细</td>" +
                "<td>状态</td>" +
                "<td>图片</td>" +
                "<td>是否审核</td>" +
                "<td>创建日期</td>" +
                "</tr>" +
                "</thead>" + "<tbody>" ;
            for (var i = 0; i < data.list.length;i++){
                str += "<tr>" +
                    "<td>" + data.list[i].title + "</td>" +
                    "<td>" + formatMoney(data.list[i].price) + "</td>" +
                    "<td>" + data.list[i].username + "</td>" +
                    "<td>" + data.list[i].category + "</td>" +
                    "<td>" + data.list[i].content + "</td>" +
                    "<td>" + data.list[i].status + "</td>" +
                    "<td>" + data.list[i].image + "</td>" +
                    "<td>" + data.list[i].pass_status + "</td>" +
                    "<td>" + parseDate(data.list[i].created) + "</td>" +
                    "<td>" +
                    "<a href='javascript:linkItem()' class=\"btn btn-default btn-sm\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"编辑\">" +
                    "<span class=\"glyphicon glyphicon-pencil\"></span>" +
                    "</a>" +
                    "</td>" +
                    "</tr>";
            }

            str +="</tbody>" + "</table>";
        }else{
            str = '当前没有用户信息';
        }
        $("#showInfoArea").html(str);
        parsePage(data,page);
    }});
}

$(function(){
    asyncLoading(1);
    parseUserInfo();
});