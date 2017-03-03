/**
 * Created by carllongj on 2017/3/3.
 */
/**
 * 发布我的商品信息页面的js
 */

/** 声明分类列表保存的状态 */
var categoryStatus;

/** 声明标题的保存的状态 */
var titleStatus;

/** 声明详细描述的状态 */
var descStatus;

/** 声明用户的金额输入不正确 */
var moneyStatus ;

$(function () {

    /** 解析用户登录后的信息 */
    parseUserInfo();

    setTimeout(requestForCategory($("#categoryListSelector")),1000);

    /**
     * 校验分类是否合法
     */
    $("#categoryList").bind('change',function () {
        if ($("#categoryList").val() !== '选择分类'){
            $("#categoryList").css("border","1px solid green");
            $("#categoryListSp").css("color","green");
            categoryStatus = true;
        }else{
            $("#categoryList").css("border","1px solid red");
            $("#categoryListSp").css("color","red");
            categoryStatus = false;
        }
    });

    /**
     * 校验输入的标题是否合法
     */
    $("#goodsInfoInput").bind("blur",function () {
       if ($("#goodsInfoInput").val() && $("#goodsInfoInput").val().trim() != ''){
           $("#goodsInfo").css("color","green");
           $("#goodsInfoInput").css("border","1px solid green");
           titleStatus = true;
       }else{
           $("#goodsInfo").css("color","red");
           $("#goodsInfoInput").css("border","1px solid red");
           titleStatus = false;
       }
    });


    /**
     * 校验用户的输入的价格信息是否合法
     */
    $("#goodsPriceInput").bind("blur",function () {
        var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
        if (reg.test($("#goodsPriceInput").val())){
            $("#goodsPrice").css("color","green");
            $("#goodsPriceInput").css("border","1px solid green");
            moneyStatus = true;
        }else{
            $("#goodsPrice").css("color","red");
            $("#goodsPriceInput").css("border","1px solid red");
            moneyStatus = false;
        }
    });

    /**
     * 校验用户的输入的详细信息是否合法
     */
    $("#goodsInfoDescInput").bind("blur",function () {
        if ($("#goodsInfoDescInput").val() && $("#goodsInfoDescInput").val().trim() != '' ){
            $("#goodsInfoDesc").css("color","green");
            $("#goodsInfoDescInput").css("border","1px solid green");
            descStatus = true;
        }else{
            $("#goodsInfoDesc").css("color","red");
            $("#goodsInfoDescInput").css("border","1px solid red");
            descStatus = false;
        }
    });

    /**
     * 上传图片的ajax实现
     */
    $("#uploadImageBtn").bind("click",function () {
        var data = new FormData();
        $("#uploadImageBtn").attr("disabled");
        $.each($('#fileselect')[0].files, function(i, file) {
            data.append('picture', file);
        });
        $.ajax({url:'/picture/upload.action',type:'POST',
            data:data,processData:false,
            dataType:'json',contentType:false,success:function (data) {
                if (data){
                    $("#messages").css("border","green");
                    $("#messages").val("文件上传成功");
                }
            }});
    });
});