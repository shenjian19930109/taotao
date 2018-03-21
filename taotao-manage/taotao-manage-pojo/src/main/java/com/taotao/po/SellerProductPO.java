package com.taotao.po;

/**
 * Created by apple on 18/3/15.
 */
public class SellerProductPO extends Product {

    // tb_seller_product主键id
    private long publicId;
    // 是否已售出
    private boolean sold;
    // 售出数量
    private int soldNum;

    private long userId;

    private String username;

    public SellerProductPO() {
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public int getSoldNum() {
        return soldNum;
    }

    public void setSoldNum(int soldNum) {
        this.soldNum = soldNum;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getPublicId() {
        return publicId;
    }

    public void setPublicId(long publicId) {
        this.publicId = publicId;
    }
}
