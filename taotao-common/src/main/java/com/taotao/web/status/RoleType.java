package com.taotao.web.status;

/**
 * Created by apple on 18/3/3.
 */
public enum RoleType {

    BUYER(0, "买家"),
    SELLER(1, "卖家"),
    NOROLE(-1, "无角色");

    private final int roleType;
    private final String name;

    RoleType(int roleType, String name) {
        this.roleType = roleType;
        this.name = name;
    }

    public int getRoleType() {
        return roleType;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "RoleType{" +
                "roleType=" + roleType +
                ", name='" + name + '\'' +
                '}';
    }
}
