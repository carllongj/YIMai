/**
 * Created by carllongj on 2017/3/12.
 */

function parseOrders (list){

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

$(function () {
    var dataObj = JSON.parse(orders);
    /*console.log(dataObj);*/
    if (dataObj.status){
        /*parseOrders(dataObj.data);*/
    }
});