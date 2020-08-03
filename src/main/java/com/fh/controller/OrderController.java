package com.fh.controller;
import com.alibaba.fastjson.JSONObject;
import com.fh.common.MemberAnnotation;
import com.fh.common.ServerResponse;
import com.fh.model.Cart;
import com.fh.model.Member;
import com.fh.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("OrderController")
public class OrderController {
    @Autowired
    private OrderService  orderService;

    @RequestMapping("bulidOrder")
    public ServerResponse  bulidOrder(String  listString, Integer  payType, Integer addressId, @MemberAnnotation Member member){
        List<Cart>cartList=null;
        if(StringUtils.isNotBlank(listString)){
            cartList=JSONObject.parseArray(listString,Cart.class);
        }
        else {
            return   ServerResponse.error("选择商品");
        }
       return  orderService.bulidOrder(cartList,payType,addressId,member);
    }
}
