/**
 * Created by carllongj on 2017/4/7.
 */

/** 当前的分类名称是否符合 */
var cateNameStatus = false;

/** 当前的分类图标是否复合 */
var cateIconStatus = false;

/**
 * 添加分类信息页面的js
 */
$(function () {
    $('[data-toggle="popover"]').popover();
    /**
     * 失去焦点时
     */
    $("#categoryName").bind("blur", function () {
        var info = $("#categoryName").val();
        if(info && info.trim().length > 0){
            cateNameStatus = true;
        }else{
            cateNameStatus = false;
        }
        $.ajax({
            url: "/admin/manage/item/check/cate.action?name=" + $("#categoryName").val(),
            success: function (data) {
                 if (data && data.status){
                     $("#categoryName + div").css({display: 'inline-block',position: 'relative'}).
                     css("font-size","0.7em").html(
                     " <div class=\"arrow\"></div> " +
                     " <div class=\"popover-content\"> " +
                         " <p>该分类名称可用</p> " +
                     " </div> "
                     );
                     $("#categoryName").css("border","1px solid green");
                 }else{
                     $("#categoryName + div").css({display: 'inline-block',position: 'relative'}).
                     css("font-size","0.7em").html(
                         " <div class=\"arrow\"></div> " +
                         " <div class=\"popover-content\"> " +
                         " <p style='color: red'>该分类名称不可用</p> " +
                         " </div>"
                     );
                     $("#categoryName").css("border","1px solid red");
                 }
                $("#categoryName + div").show();
            }
        });

    });

    /**
     * 获取焦点时的操作
     */
    $("#categoryName").bind("focus", function () {
        cateNameStatus = false;
        $("#categoryName + div").hide();
        $("#categoryName").css("border","1px solid red");
    });

    $("#categoryIcon").bind("blur",function () {
        var info = $("#categoryIcon").val();
        if(info && info.trim().length > 0){
            cateIconStatus = true;
            $("#categoryIcon + div").hide();
            $("#categoryIcon").css("border","1px solid green");
        }else{
            cateIconStatus = false;
            $("#categoryIcon + div").css({display: 'inline-block',position: 'relative'}).
            css("font-size","0.7em").html(" <div class=\"arrow\"></div> " +
                " <div class=\"popover-content\"> " +
                " <p style='color: red'>该分类名称图标不能为空</p> " +
                " </div>");
            $("#categoryIcon").css("border","1px solid red");
            $("#categoryIcon + div").show();
        }
    });

    $("#categoryIcon").bind("focus",function () {
        cateIconStatus = false;
        $("#categoryIcon + div").hide();
        $("#categoryIcon").css("border","1px solid red");
    });

    /**
     * 提交表单
     */
    $("#addCategory").bind("click",function () {
        if (!cateNameStatus){
            $("#categoryName").blur();
            return;
        }

        if (!cateIconStatus){
            $("#categoryIcon").blur();
            return;
        }

        $.post("/admin/manage/item/add/category.action",$("#addCategoryInfo").serialize(),function (data) {
            $("#categoryName + div").hide();
            if (data && data.status){
                swal("操作结果!","操作成功", "success");
                setTimeout("location.href = '/admin/show/allcate.action'",1500);
            }else{
                swal("操作结果!",data.msg, "error");
            }
        },'json');
    });
});