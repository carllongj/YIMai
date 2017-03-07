/**
 * Created by carllongj on 2017/3/6.
 */

var nowPage;

/** 当前页面首页的值 */
var first;

/** 当前页面页尾的值 */
var last;

/** 当点击的阀值超过了当前值,需要调整分页的位置 */
var threshold = 5;

function resetPosition(){

}

function spellInfo () {

};

function link(page) {
    nowPage = page;
    //ajax请求数据,并且重新调整分页展示
    $.ajax({url:"/query/async.action",success:function (data) {
        if (data && data.trim() != ''){
            parseItemList(data.list);
            if(nowPage - first > threshold){
                resetPosition();
            }
        }
    }});
}

function parseItemList (list){

    var elements = "";

    for(var i = 0;i < list.length;i++){
        var element = "<a href=\"/query/one/" + list[i].id + ".action\">" +
        "<li>" +
        "<img src=\"" + list[i].image +"\" alt=\"\" />" +
        "<section class=\"list-left\">" +
        "<h5 class=\"title\">" + list[i].title + "</h5>" +
        "<span class=\"adprice\">" + formatMoney(list[i].price) + "</span>" +
        "<p class=\"catpath\"></p>" +
        "</section>" +
        "<section class=\"list-right\">" +
        "<span class=\"date\">" + new Date(list[i].created).format("yyyy-MM-dd hh:mm") + "</span>" +
        "</section>" +
        "<div class=\"clearfix\"></div>" +
        "</li>" +
        "<div class=\"clearfix\"></div>" +
        "</a>";
        elements += element;
    }

    $("ul.list").html(elements);
}

function parseListPage (data){
    var pages = parseInt(data.totalRecords / data.rows);
    var displayPages = pages + (((data.totalRecords % data.rows) == 0) ? 0 : 1);

    //页数小于10,直接展示所有的页面
    if(displayPages < 10 ){
        last = displayPages;
        var elements = "";
        //判定当前是否为第一页进行展示
        if (nowPage != first){
            elements += "<li><a href='javascript:link(" + (nowPage + 1) + ")'>上一页</a></li>";
        }

        for(var i = first;i <= displayPages;i++){
            //判定中间展示的效果
            if(i == nowPage){
                elements += "<li><a>" + nowPage + "</a></li>";
            }else{
                elements += "<li><a href='javascript:link(" + i + ")'>" + i + "</a></li>";
            }
        }
        //判定最后展示的效果
        if(nowPage == first){
            elements += "<li><a href='javascript:link(" + (nowPage + 1) + ")'>下一页</a></li>";
        }
        $("ul.pagination").html(elements);
    }
}

function parseCategory(){
    $.ajax({url:"/category/list.action",success:function (data) {
        for (var i = 0;i < data.data.length;i++){
            $("select.selectpicker:last-child").append("<option value=\"" + data.data[i].id +"\">" + data.data[i].name + "</option>");
        }
    }});
}

Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};

$(function () {
    if (data && data.trim() != ''){
        nowPage = 1;
        first = 1;
        var dataObj = JSON.parse(data);
        parseItemList(dataObj.list);
        parseCategory();
        parseListPage(dataObj);
    }
});