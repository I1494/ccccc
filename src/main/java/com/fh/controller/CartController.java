package com.fh.controller;
import com.alibaba.fastjson.JSONObject;
import com.fh.common.Const;
import com.fh.common.MemberAnnotation;
import com.fh.common.ServerResponse;
import com.fh.model.Cart;
import com.fh.model.Member;
import com.fh.service.CartService;
import com.fh.util.RedisUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("CartController")
public class CartController {
    @Resource
    private CartService  service;


    @RequestMapping("addCart")
    public ServerResponse  addCart(Integer  id, Integer  count, HttpServletRequest  request){
        return   service.addCart(id,count,request);
    }

//--------------------------------------------------------------------
   @RequestMapping("queryCartCount")
    public  ServerResponse  queryCartCount(HttpServletRequest  request){
        return  service.queryCartCount(request);
   }




   @RequestMapping("queryList")
    public  ServerResponse  queryList(@MemberAnnotation Member member){
      List<String>stringList= Collections.singletonList(RedisUtil.get(Const.SHOPPING_CAR_KET, member.getId()));
      List<Cart>cartList=new ArrayList<>();
      for(stringList!=null  && stringList.size()>0){
          for (String s : stringList) {
                Cart  cart= JSONObject.parseObject(s,Cart.class);
              cartList.add(cart);
          }
      }


       return  ServerResponse.success(cartList);
   }


   @RequestMapping("del/{productId}")
    public  ServerResponse  del(@MemberAnnotation  Member member, @PathVariable("productId")  Integer  productId){
        RedisUtil.del(Const.SHOPPING_CAR_KET+member.getId(),productId);
        return  ServerResponse.success();
   }



   @RequestMapping("queryCount")
    public ServerResponse  queryCount(@MemberAnnotation  Member member){
    List<String>stringList= Collections.singletonList(RedisUtil.get(Const.SHOPPING_CAR_KET, member.getId()));
    int count=0;
    if(stringList!=null  && stringList.size()>0){
        for (String s : stringList) {
        Cart  cart=JSONObject.parseObject(s,Cart.class);
        count+=cart.getCount();
        }
    }else{
        return  ServerResponse.success(0);
    }

    return  ServerResponse.success(count);
   }
}
