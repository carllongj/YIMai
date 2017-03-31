package com.carl.yimai.web.utils;

/**
 * <p>Title: com.carl.yimai.web.utils Page</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2017/3/31 9:42
 * @Version 1.0
 */
public class Page {

    /** 页码开始的位置 */
    private int start;

    /** 查询的记录数 */
    private int count;

    /** 指定当前的页码 */
    private int currentPage;

    /** 查询未激活的用户 */
    private int state;

    private Page(int start, int count,boolean init) {
        this.start = start;
        this.count = count;
        if (init){
            if (start < 1)
                start = 1;
            this.currentPage = start;
            init();
        }
    }


    /**
     * 初始化设置参数
     */
    private void init(){
        this.start = (currentPage - 1) * 20;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public static Page getPageInstance(int page, int rows){
        return new Page(page,rows,true);
    }

    public static Page getStartPage(int start,int rows){
        return new Page(start,rows,false);
    }
}
