package com.jt.web.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jt.common.po.BasePojo;

@Table(name="tb_item")//对象和表进行绑定 通用mapper
public class Item extends BasePojo{//父类序列化了子类就不用了
    @Id//id是主键
    @GeneratedValue(strategy=GenerationType.IDENTITY)//注明是主键自增的 否则id必须不为空
    private long id;
    private String title;   
    @Column(name="sell_point")
    private String sellPoint;
    private long price;//计算速度快，精确  int>long>double 计算速度
    private Integer num;//商品数量
    private String barcode;//二维码
    private String image;//图片内容 
    private Long cid;//商品分类id
    private Integer status;
    
    public String[] getImages(){
        return image.split(",");
    }
    
    @Override
    public String toString() {
        return "Item [id=" + id + ", title=" + title + ", sellPoint=" + sellPoint + ", price=" + price + ", numl="
                + num + ", barcode=" + barcode + ", image=" + image + ", cid=" + cid + ", status=" + status + "]";
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
    public String getSellPoint() {
        return sellPoint;
    }
    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }
    public long getPrice() {
        return price;
    }
    public void setPrice(long price) {
        this.price = price;
    }
    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
    public String getBarcode() {
        return barcode;
    }
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public Long getCid() {
        return cid;
    }
    public void setCid(Long cid) {
        this.cid = cid;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    
}
