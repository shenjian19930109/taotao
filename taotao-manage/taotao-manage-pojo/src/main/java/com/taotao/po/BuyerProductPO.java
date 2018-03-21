package com.taotao.po;

import java.math.BigDecimal;

/**
 * Created by apple on 18/3/17.
 */
public class BuyerProductPO extends Product {

    // tb_seller_product主键id
    private long publicId;
    // 是否已购买
    private boolean sold;
    // 购买数量
    private int soldNum;
    // 最后成交价格
    private BigDecimal finalPrice;

    private long userId;

    private String username;

    public BuyerProductPO() {
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

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
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
