package com.fh.service;
import com.alibaba.fastjson.JSONObject;
import com.fh.common.Const;
import com.fh.common.ServerEnum;
import com.fh.common.ServerResponse;
import com.fh.model.Cart;
import com.fh.model.Member;
import com.fh.model.Product;
import com.fh.util.RedisUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
@Service

public class CartServiceImpl implements CartService {
    @Resource
    private  ProductService  service;

    @Override
    public ServerResponse addCart(Integer id, Integer count, HttpServletRequest request) {
      Product product=  service.selectShopById(id);
      if(product==null){
          return  ServerResponse.error(ServerEnum.NOTEXISTERROR);
      }
      if(product.getStatus()==2){
          return  ServerResponse.error(ServerEnum.NOTONERROR);
      }

     Member member= (Member) request.getSession().getAttribute(Const.SESSION_KEY);
     boolean  exist=RedisUtil.exist(Const.SHOPPING_CAR_KET+member.getId(),id.toString());
     if(!exist){
         Cart cart=new  Cart();
         cart.setId(id);
         cart.setCount(count);
         cart.setFilePath(product.getFilePath());
         cart.setName(product.getName());
         String jsonString=JSONObject.toJSONString(cart);
         RedisUtil.set(Const.SHOPPING_CAR_KET+member.getId(),id.toString(),jsonString);
     }else{
        String  productJson= (String) RedisUtil.get(Const.SHOPPING_CAR_KET+member.getId(),id.toString());
        Cart  cart=JSONObject.parseObject(productJson,Cart.class);
        cart.setCount(cart.getCount()+count);
        String  jsonString=JSONObject.toJSONString(cart);
        RedisUtil.set(Const.SHOPPING_CAR_KET+member.getId(),id.toString(),jsonString);
     }
        return ServerResponse.success();
    }

    @Override
    public ServerResponse queryCartCount(HttpServletRequest request) {
        return null;
    }
}
