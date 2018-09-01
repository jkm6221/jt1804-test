package com.jt.web.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

import com.jt.common.po.BasePojo;

public class ItemDesc extends BasePojo{
    
    private Long itemId;//与商品表的数据一致
    private String itemDesc;
    
    
    @Override
    public String toString() {
        return "ItemDesc [itemId=" + itemId + ", itemDesc=" + itemDesc + "]";
    }
    public Long getItemId() {
        return itemId;
    }
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
    public String getItemDesc() {
        return itemDesc;
    }
    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }  
    
}
