package com.carl.yimai.web.utils;

/**
 * 进行json通信时响应的结果对象
 * <p>Title: com.carl.yimai.web.utils Result</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/24 12:44
 * @Version 1.0
 */
public class Result {

    /** 结果是否可用 */
    private boolean status;

    /** 结果不可用时的信息 */
    private String msg;

    /** 结果可用时的数据 */
    private Object data;

    public Result(boolean status,String msg){
        this.status = status;
        this.msg = msg;
    }

    public Result(boolean status,Object data){
        this.status = status;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result ok(Object object){
        return new Result(true,object);
    }

    public static Result error(String msg){
        return new Result(false,msg);
    }
}
