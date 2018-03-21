package com.taotao.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by apple on 18/3/20.
 */
public class ShoppingCarVO {

    private Long id;

    private Long userId;

    private String username;

    private Long productId;

    private String title;

    private Integer purchaseNum;

    private BigDecimal price;

    private Boolean bought;

    private Byte status;

    private Date createTime;

    private Date modifyTime;

    public ShoppingCarVO() {
    }

    public ShoppingCarVO(Long id, Long userId, String username, Long productId, String title, Integer purchaseNum, BigDecimal price, Boolean bought, Byte status, Date createTime, Date modifyTime) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.productId = productId;
        this.title = title;
        this.purchaseNum = purchaseNum;
        this.price = price;
        this.bought = bought;
        this.status = status;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(Integer purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getBought() {
        return bought;
    }

    public void setBought(Boolean bought) {
        this.bought = bought;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
}
