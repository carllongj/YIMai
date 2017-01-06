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

    /** 用户的唯一标识 */
    private String id;

    /** 用户的密码修改 */
    private String passwd;

    /** 用户的昵称 */
    private String nickname;

    /** 用户的手机号码 */
    private String phone;

    /** 用户的生日 */
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