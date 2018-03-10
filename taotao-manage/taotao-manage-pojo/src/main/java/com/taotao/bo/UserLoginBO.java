package com.taotao.bo;

import com.taotao.web.status.RoleType;

/**
 * Created by apple on 18/3/3.
 */
public class UserLoginBO {

    private String userName;
    private String passWord;
    private RoleType roleType;

    public UserLoginBO() {
    }

    public UserLoginBO(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
        roleType = RoleType.NOROLE;
    }

    public UserLoginBO(String userName, String passWord, RoleType roleType) {
        this.userName = userName;
        this.passWord = passWord;
        this.roleType = roleType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    @Override
    public String toString() {
        return "UserLoginBO{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", roleType=" + roleType +
                '}';
    }
}
