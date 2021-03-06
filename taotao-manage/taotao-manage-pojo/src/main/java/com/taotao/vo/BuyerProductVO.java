package com.taotao.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by apple on 18/3/17.
 */
public class BuyerProductVO extends ProductVO{

    // tb_seller_product主键id
    private long publicId;
    // 是否已购买
    private boolean sold;
    // 购买状态:根据sold的值设置,如果sold为false:"未购买";为true:"已购买"
    private String sellStatus;
    // 购买数量
    private int soldNum;
    // 最终成交价格
    private BigDecimal finalPrice;

    private long userId;

    private String username;

    private Date createTime;

    private Date modifyTime;

    private String createTimeStr;

    private String modifyTimeStr;

    public BuyerProductVO() {
    }

    public BuyerProductVO(long id, String title, String summary, String pic, String image, String file, String avatar, String detail, BigDecimal price, long publicId, boolean sold, String sellStatus, int soldNum, BigDecimal finalPrice, long userId, String username, Date createTime, Date modifyTime, String createTimeStr, String modifyTimeStr) {
        super(id, title, summary, pic, image, file, avatar, detail, price);
        this.publicId = publicId;
        this.sold = sold;
        this.sellStatus = sellStatus;
        this.soldNum = soldNum;
        this.finalPrice = finalPrice;
        this.userId = userId;
        this.username = username;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.createTimeStr = createTimeStr;
        this.modifyTimeStr = modifyTimeStr;
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

    public String getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(String sellStatus) {
        this.sellStatus = sellStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getModifyTimeStr() {
        return modifyTimeStr;
    }

    public void setModifyTimeStr(String modifyTimeStr) {
        this.modifyTimeStr = modifyTimeStr;
    }
}
