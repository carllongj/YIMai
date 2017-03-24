/**
 * Created by carllongj on 2017/3/24.
 */

var changeQueue = false;

function parseMonth () {
    var date = new Date().getMonth();
    var data = [];
    for (var i = 0;i < 12;i++){
        if (date < 0){
            date = 12;
            changeQueue = true;
        }
        if(changeQueue){
            data.unshift("去年" + date-- + "月");
        }else{
            if (i == 0){
                data.unshift("本月");
                date--;
            }
            else
                data.unshift((date--) + 1 + "月");
        }
    }
    return data;
}

$(document).ready(function() {
    parseUserInfo();
    $.ajax({url: "/admin/manage/highcharts.action", success:function (data) {
        if (data && data.status){
            var title = {
                text: '最近注册的人数'
            };
            var subtitle = {
                text: 'LastestRegister'
            };
            var xAxis = {
                categories: parseMonth()
            };
            var yAxis = {
                title: {
                    text: '人数 (单位/人)'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            };

            var tooltip = {
                valueSuffix: '人'
            };

            var legend = {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            };

            var series =  [
                {
                    name: '注册人数',
                    data: eval( "(" + data.data + ")")
                }
            ];

            var json = {};
            json.title = title;
            json.subtitle = subtitle;
            json.xAxis = xAxis;
            json.yAxis = yAxis;
            json.tooltip = tooltip;
            json.legend = legend;
            json.series = series;

            $('#showInfoArea').highcharts(json);
        }
    }})
});