package com.taotao.vo;

import java.math.BigDecimal;

/**
 * Created by apple on 18/3/15.
 */
public class SellerProductVO extends ProductVO {

    // tb_seller_product主键id
    private long publicId;
    // 是否已售出
    private boolean sold;
    // 销售状态:根据sold的值设置,如果sold为false:"未售出";为true:"已售出"
    private String sellStatus;
    // 售出数量
    private int soldNum;

    private long userId;

    private String username;

    public SellerProductVO() {
    }

    public SellerProductVO(long id, String title, String summary, String pic, String image, String file, String avatar, String detail, BigDecimal price, long publicId, boolean sold, int soldNum, long userId, String username) {
        super(id, title, summary, pic, image, file, avatar, detail, price);
        this.publicId = publicId;
        this.sold = sold;
        this.soldNum = soldNum;
        this.userId = userId;
        this.username = username;
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

    public String getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(String sellStatus) {
        this.sellStatus = sellStatus;
    }
}
