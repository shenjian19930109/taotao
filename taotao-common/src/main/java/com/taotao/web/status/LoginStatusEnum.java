package com.taotao.web.status;

/**
 * Created by apple on 18/3/2.
 */
public enum LoginStatusEnum {

    SUCCESS(1),
    FAIL(0);

    private String userName;
    private String passWord;
    private RoleType roleType;
    private int status;

    LoginStatusEnum(int status) {
        this.status = status;
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
        return "LoginStatusEnum{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", roleType=" + roleType +
                ", status=" + status +
                '}';
    }
}
