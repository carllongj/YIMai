/**
 * Created by carllongj on 2017/3/6.
 */

var current;

/** 当前页面首页的值 */
var first;

/** 当前页面页尾的值 */
var last;

/** 当前的总的页码数 */
var displayPages;

/**
 * 序列化所有需要的参数
 */
function serialize() {

    var link = "";

    //获取分类信息的值
    var cate = $("select.selectpicker").val();
    if (cate != undefined && cate != '') {
        link += "cid=" + cate + "&";
    }

    //获取关键字的信息
    var keyword = $(".input-lg").val();
    if (keyword != undefined && keyword != '') {
        link += "keyword=" + keyword + "&";
    }

    var range = $("#priceRange").val();
    if (range != undefined && range.trim() != '不限制') {
        var arr = range.split(" - ");
        if (arr.length == 2) {
            link += "lowPrice=" + arr[0] + "&highPrice=" + arr[1] + "&";
        } else if (arr[0].charAt(0) == '-') {
            link += "lowPrice=0&highPrice=" + 999 + "&";
        } else if (arr[0].charAt(arr[0].length - 1) == '+') {
            link += "lowPrice=10001" + "&";
        }
    }

    var sorted = $("#sortedBy").val();
    if (sorted != null) {
        link += "sortedBy=" + sorted + "&";
    }

    if (link != '') {
        link = "&" + link;
        link = link.substr(0, link.length - 1);
    }

    return link;
}

function pageLessThan() {
    last = displayPages;
    var elements = "";
    //判定当前是否为第一页进行展示
    if (current > first) {
        elements += "<li><a href='javascript:link(" + (current - 1) + ")'>上一页</a></li>";
    }

    for (var i = first; i <= displayPages; i++) {
        //判定中间展示的效果
        if (i == current) {
            elements += "<li><a style='background: grey;color: white;'>" + current + "</a></li>";
        } else {
            elements += "<li><a href='javascript:link(" + i + ")'>" + i + "</a></li>";
        }
    }
    //判定最后展示的效果
    if (current < last) {
        elements += "<li><a href='javascript:link(" + (current + 1) + ")'>下一页</a></li>";
    }
    $("ul.pagination").html(elements);
}

/**
 * 不改变页码显示的解析函数
 */
function parsePageWithoutModification() {
    last = displayPages;
    var elements = "";
    //判定当前是否为第一页进行展示
    if (current > first) {
        elements += "<li><a href='javascript:link(" + (current - 1) + ")'>上一页</a></li>";
    }

    for (var i = displayPages - 7; i <= displayPages; i++) {
        //判定中间展示的效果
        if (i == current) {
            elements += "<li><a style='background: grey;color: white;'>" + current + "</a></li>";
        } else {
            elements += "<li><a href='javascript:link(" + i + ")'>" + i + "</a></li>";
        }
    }
    //判定最后展示的效果
    if (current < last) {
        elements += "<li><a href='javascript:link(" + (current + 1) + ")'>下一页</a></li>";
    }
    $("ul.pagination").html(elements);
}

/**
 * 对于头部低于六的情况进行解析
 */
function parsePageWithoutModificationForHead() {
    first = 1;
    last = 8;
    var elements = "";
    //判定当前是否为第一页进行展示
    if (current > first) {
        elements += "<li><a href='javascript:link(" + (current - 1) + ")'>上一页</a></li>";
    }

    for (var i = first; i <= last; i++) {
        //判定中间展示的效果
        if (i == current) {
            elements += "<li><a style='background: grey;color: white;'>" + current + "</a></li>";
        } else {
            elements += "<li><a href='javascript:link(" + i + ")'>" + i + "</a></li>";
        }
    }
    //判定最后展示的效果
    if (current < last) {
        elements += "<li><a href='javascript:link(" + (current + 1) + ")'>下一页</a></li>";
    }
    $("ul.pagination").html(elements);
}

/**
 * 调整分页码的位置
 */
function resetPosition() {
    var count = current - 4;
    var elements = '';

    elements += "<li><a href='javascript:link(" + (current - 1) + ")'>上一页</a></li>";

    for (; count <= current + 3; count++) {
        if (count == current) {
            elements += "<li><a style='background: grey;color: white;'>" + current + "</a></li>";
        } else {
            elements += "<li><a href='javascript:link(" + count + ")'>" + count + "</a></li>";
        }
    }

    elements += "<li><a href='javascript:link(" + (current + 1) + ")'>下一页</a></li>";
    $("ul.pagination").html(elements);
}

function link(page) {
    current = page;
    var urlParameter = serialize();
    //ajax请求数据,并且重新调整分页展示
    $.ajax({
        url: "/query/async.action?page=" + page + urlParameter, success: function (data) {
            if (data) {
                parseItemList(data.list);
                parseListPage(data);
            }
        }
    });
}

function parseItemList(list) {

    var elements = "";

    for (var i = 0; i < list.length; i++) {
        var element = "<a href=\"/query/one/" + list[i].id + ".action\">" +
            "<li>" +
            "<img src=\"" + list[i].image + "\" alt=\"\" />" +
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

function parseListPage(data) {
    var pages = parseInt(data.totalRecords / data.rows);
    displayPages = pages + (((data.totalRecords % data.rows) == 0) ? 0 : 1);

    //页数小于10,直接展示所有的页面
    if (displayPages < 9) {
        pageLessThan();
    } else {
        //所有的页码超过8页
        var pageCount = displayPages;

        //如果超过最后三个
        if (current > pageCount - 3) {
            parsePageWithoutModification();
            return;
        }

        //如果低于前六个
        if (current < 6) {
            parsePageWithoutModificationForHead();
            return;
        }

        //都需要进行调整分页的页码显示
        resetPosition();
    }
}

function parseCategory(cateid) {
    var elements = '';
    $.ajax({
        url: "/category/list.action", async: false, success: function (data) {
            if (data && data.status) {
                if (!cateid) {
                    elements += "<option value=\"\" selected>所有分类</option>";
                } else {
                    elements += "<option value=\"\">所有分类</option>";
                }
                for (var i = 0; i < data.data.length; i++) {
                    if (cateid == data.data[i].id) {
                        elements += "<option selected='selected' value='" + data.data[i].id + "'>" + data.data[i].name + "</option>"
                    } else {
                        elements += "<option value='" + data.data[i].id + "'>" + data.data[i].name + "</option>"
                    }
                }
                $("#categeorySelector").html(elements);
            }
        }
    });
}

/**
 function parseCategory(dataObj){
    console.log(dataObj);
    var selectedStatus = true;
    var cid = '';
    $.ajax({url:"/category/list.action",async:false,success:function (data) {
        var elements = "<option value=\"\">所有分类</option>";
        for (var i = 0;i < data.data.length;i++){

            if (dataObj.totalRecords == 1){
                cid = dataObj.list[0].cateid;
            }

            elements += "<option value='" + data.data[i].id + "'>" + data.data[i].name + "</option>"
            if (selectedStatus && dataObj.totalRecords > 1){
                for (var j = 1;j < dataObj.list.length;j++){
                    cid = dataObj.list[j - 1].cateid;
                    if (dataObj.list[j - 1].cateid != dataObj.list[j].cateid){
                        selectedStatus = false;
                        break;
                    }
                }
            }
        }
        //先添加元素列表
        $("#categeorySelector").html(elements);

        if (selectedStatus) {
            for (var k = 0;k < data.data.length;k++) {
                if (cid == data.data[k].id){
                    $("#categeorySelector option:nth-child(" + (k + 2) + ")").attr("selected","selected");
                    break;
                }
            }
        }else{
            $("#categeorySelector:nth-child(1)").attr("selected","selected");
        }
    }});
}

 */

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

function parseCategoryIfNeeded() {
    var cid = '';
    if (location.href.indexOf("?") != -1){
        var params = location.href.split("?")[1];
        if (params.indexOf("agileinfo_search=") != -1) {
            cid = params.split("agileinfo_search=")[1];
        } else {
            cid = params.split("=")[1];
        }
        if (params.indexOf("keyword") != -1) {
            $(".input-lg").val(decodeURI(params.split("keyword=")[1].split("&")[0]));
        }
    }
    parseCategory(cid);
}

$(function () {
    parseUserInfo();

    if (data && data.trim() != '') {
        current = 1;
        first = 1;
        var dataObj = JSON.parse(data);
        parseItemList(dataObj.list);
        parseCategoryIfNeeded();
        parseListPage(dataObj);
        sidebarCate($("#open-button"), $(".icon-list"));
    }

    /**
     * 查询按钮注册事件
     */
    $("button.btn-info").bind('click', function () {
        link(1);
    });

    /**
     * 下拉框发生变化注册事件
     */
    $("#priceRange").bind('change', function () {
        link(1);
    });

    /**
     * 给排序注册事件
     */
    $("#sortedBy").bind('change', function () {
        link(1);
    });
});