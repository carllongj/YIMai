/**
 * Created by carllongj on 2017/3/3.
 */
/**
 * 发布我的商品信息页面的js
 */

/** 定义 金额的正则表达式 */
var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;

/** 声明分类列表保存的状态 */
var categoryStatus;

/** 声明标题的保存的状态 */
var titleStatus;

/** 声明详细描述的状态 */
var descStatus;

/** 声明用户的金额输入状态 */
var moneyStatus ;

/** 上传图片的按钮是否被点击 */
var uploadBtnClicked = false;

function setPageStyle() {

    $("#goodsInfoInput").next().css({position:"relative",left:"10px",top:"7px"});
    $("#goodsPriceInput").next().css({position:"relative",left:"10px",top:"7px"});

    if (!selected) {
        $("#uploadImageBtn").attr("disabled","disabled");
        $("#uploadImageBtn").css({cursor:"not-allowed",background:"grey"});
    }
}

function checkForCategoryList() {

    var selectorSpan = $("#categoryList").next();
    if ($("#categoryList").val() !== '选择分类'){
        $("#categoryList").css("border","1px solid green");
        $("#categoryListSp").css("color","green");

        selectorSpan.hide();

        categoryStatus = true;
    }else{
        $("#categoryList").css("border","1px solid red");
        $("#categoryListSp").css("color","red");

        selectorSpan.text("请选择一个分类");
        selectorSpan.css({display: "inline-block",color: "red",
            position: "relative",left: "10px"});
        selectorSpan.css("font-family","PingFangSC-Light");
        selectorSpan.css("font-size","12px");

        categoryStatus = false;
    }
}

function checkForGoodsInfo() {
    var inputTitleSpan = $("#goodsInfoInput").next();
    if (!$("#goodsInfoInput").val() || $("#goodsInfoInput").val().trim() == ''){
        $("#goodsInfo").css("color","red");
        $("#goodsInfoInput").css("border","1px solid red");
        inputTitleSpan.css("color","red");
        inputTitleSpan.text("内容的标题不能为空");
        inputTitleSpan.show();
        titleStatus = false;
    }else if ($("#goodsInfoInput").val().length > 30){
        $("#goodsInfo").css("color","red");
        $("#goodsInfoInput").css("border","1px solid red");
        inputTitleSpan.css("color","red");
        inputTitleSpan.text("输入的长度应小于30");
        inputTitleSpan.show();
        titleStatus = false;
    }else{
        $("#goodsInfo").css("color","green");
        $("#goodsInfoInput").css("border","1px solid green");
        titleStatus = true;
    }
}

function checkForGoodsPrice() {
    var goodsPriceInputSpan = $("#goodsPriceInput").next();
    if (reg.test($("#goodsPriceInput").val())){
        $("#goodsPrice").css("color","green");
        $("#goodsPriceInput").css("border","1px solid green");
        moneyStatus = true;
    }else{
        $("#goodsPrice").css("color","red");
        $("#goodsPriceInput").css("border","1px solid red");
        goodsPriceInputSpan.css("color","red");
        goodsPriceInputSpan.text("格式错误,如:1237.13");
        moneyStatus = false;
        goodsPriceInputSpan.show();
    }
}

function checkForGoodsDesc() {
    if ($("#goodsInfoDescInput").val() && $("#goodsInfoDescInput").val().trim() != '' ){
        $("#goodsInfoDesc").css("color","green");
        $("#goodsInfoDescInput").css("border","1px solid green");
        descStatus = true;
    }else{
        $("#goodsInfoDesc").css("color","red");
        $("#goodsInfoDescInput").css("border","1px solid red");
        descStatus = false;
    }
}

function asyncUploadPicture(data,status){
    $.ajax({url:'/picture/upload.action',type:'POST',
        data:data,processData:false,async:status,
        dataType:'json',contentType:false,success:function (data) {
            if (data && data.status){
                //保存后台传递的url的值
                $("#uploadImageUrl").val(data.data);
                $("#messages").css("border","1px solid green");
                $("#messages").text("文件上传成功");
                $("#messages").css("color","green");
                $("#uploadImageBtn").text("上传图片");
            }else {
                $("#messages").text("文件上传失败,请重试");
                $("#messages").css("color","red");
            }
            resetButtonStyle($("#uploadImageBtn"));
            resetButtonStyle($("#postMyAd"));
            $("#postMyAd").text("发布");
        }});
}

function uploadPicture () {
    setButtonStyle($("#uploadImageBtn"));
    $("#uploadImageBtn").text("正在上传..");
    setButtonStyle($("#postMyAd"));
    $("#postMyAd").text("图片上传ing...");
    var data = new FormData();
    $("#uploadImageBtn").attr("disabled");
    $.each($("#fileselect")[0].files, function(i, file) {
        data.append('fileselect', file);
    });
    asyncUploadPicture(data,uploadBtnClicked);
}

$(function () {

    /** 设置页面的样式 */
    setPageStyle();

    /** 解析用户登录后的信息 */
    parseUserInfo();

    /** 请求分类的列表 */
    setTimeout(requestForCategory($("#categoryListSelector")),1000);

    /** 绑定按钮的事件 */
    sidebarCate($("#open-button"),$(".icon-list"));

    /**
     * 校验分类是否合法
     */
    $("#categoryList").bind('change',function () {
        checkForCategoryList();
    });

    /**
     * 校验输入的标题是否合法
     */
    $("#goodsInfoInput").bind("blur",function () {
        checkForGoodsInfo();
    });

    /**
     * 获取到焦点后需要隐藏该字段的描述
     */
    $("#goodsInfoInput").bind("focus",function(){
            $("#goodsInfoInput").next().hide();
    });

    /**
     * 校验用户的输入的价格信息是否合法
     */
    $("#goodsPriceInput").bind("blur",function () {
        checkForGoodsPrice();
    });

    /** 隐藏显示的文本 */
    $("#goodsPriceInput").bind("focus",function () {
       $("#goodsPriceInput").next().hide();
    });

    /**
     * 校验用户的输入的详细信息是否合法
     */
    $("#goodsInfoDescInput").bind("blur",function () {
            checkForGoodsDesc();
    });

    /**
     * 上传图片的ajax实现
     */
    $("#uploadImageBtn").bind("click",function () {
        uploadBtnClicked = true;
        uploadPicture();
    });

    /**
     * 给发布信息的按钮添加提交事件
     */
    $("#postMyAd").bind("click",function () {

        if (!categoryStatus) {
            checkForCategoryList();
            return;
        }

        if (!titleStatus){
            checkForGoodsInfo();
            return;
        }

        if (!moneyStatus) {
            checkForGoodsPrice();
            return ;
        }

        if (!descStatus) {
            checkForGoodsDesc();
            return ;
        }

        /** 用户没有点击上传按钮,直接点击发布按钮 */
        if (!uploadBtnClicked){
            uploadPicture();
        }

        /** 移除上传图片的input */
        $("#fileselect").remove();

        /** 设置按钮的状态 */
        setButtonStyle($("#postMyAd"));

        setButtonStyle($("#uploadImageBtn"));

        $("#postMyAd").text("正在提交...");

        $.post('/item/addItem.action',$("#postMyAdForm").serialize(),function (data) {
                if (data && data.status){
                    swal("发布成功","请等待审核您的商品","success");
                    setTimeout("location.href = '/index.action'",1000);
                }else {
                    swal("发布失败",data.msg,"error");
                }
        },'json');
    })
});