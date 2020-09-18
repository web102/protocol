package com.bobandata.iot.entity.dms;

import javax.persistence.*;

/**
 * @Author: zhanglingzhi
 * @Description:    用户表
 * @Company: 上海博般数据技术有限公司
 * @Date: Created in 11:35 2018/7/17.
 */
@Entity
public class User {
//    USER_ID	用户ID	Int	10	是
//    USER_NAME	用户名	varchar	128	否
//    NAME	用户姓名	varchar	128	否
//    ROLE	角色	varchar	20	否
//    PHONE	联系方式	varchar	20	否
//    PASSWORD	登陆密码	varchar	20	否

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ROLE")
    private String role;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "PASSWORD")
    private String password;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
