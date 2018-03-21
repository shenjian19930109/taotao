package com.taotao.vo;

import java.math.BigDecimal;

/**
 * Created by apple on 18/3/11.
 */
public class ProductVO {

    private long id;
    // 主题
    private String title;
    // 摘要
    private String summary;
    // 单选项:图片地址(url)  本地上传(file)
    private String pic;
    // 图片地址
    private String image;
    // 用于存放文件地址(image\avatar中不为空的那个)
    private String file;
    // 本地上传文件地址
    private String avatar;
    // 正文
    private String detail;
    // 价格
    private BigDecimal price;

    public ProductVO() {
    }

    public ProductVO(long id, String title, String summary, String pic, String image, String file, String avatar, String detail, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.pic = pic;
        this.image = image;
        this.file = file;
        this.avatar = avatar;
        this.detail = detail;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", pic='" + pic + '\'' +
                ", image='" + image + '\'' +
                ", file='" + file + '\'' +
                ", avatar='" + avatar + '\'' +
                ", detail='" + detail + '\'' +
                ", price=" + price +
                '}';
    }
}
