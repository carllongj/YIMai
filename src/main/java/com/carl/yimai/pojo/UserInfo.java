package com.carl.yimai.pojo;

import java.util.Date;

/**
 * 允许用户修改账户的个别属性的包装类
 * <p>Title: com.carl.yimai.po.pojo UserInfo</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author carl
 * @date 2016/12/25 22:56
 * @Version 1.0
 */
public class UserInfo {

    private String id;

    private String passwd;

    private String nickname;

    private String phone;

    private Date birthday;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}