package com.jt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jt.web.pojo.Item;
import com.jt.web.pojo.ItemDesc;
import com.jt.web.service.ItemService;

@Controller
@RequestMapping("/items")
public class ItemController {
    
    @Autowired
    private ItemService itemService;
    /**
     * 商品展现界面
     * @param itemId
     * @return                               标识只能用get请求拦截
     */
    @RequestMapping(value="/{itemId}",method=RequestMethod.GET)
    public String findItemById(@PathVariable String itemId,Model model) {
        //根据商品id查询商品信息
        Item item = itemService.findItemById(itemId);
        ItemDesc itemDesc = itemService.findItemDescById(itemId);
        //将数据保存到Request域中
        model.addAttribute("item",item);
        model.addAttribute("itemDesc",itemDesc);        
        return "item";
    }
}
