package com.fh.service;
import com.fh.common.ServerResponse;
import com.fh.mapper.OrderMapper;
import com.fh.model.*;
import com.fh.util.BigDecimalUtil;
import com.fh.util.IdUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper mapper;
    @Resource
    private  ProductService  service;

    @Override
    public ServerResponse bulidOrder(List<Cart> cartList, Integer payType, Integer addressId, Member member) {
          String  orderId= IdUtil.createId();
          List<OrderInfo>orderInfoList=new ArrayList<>();
          BigDecimal  totalPrice=new BigDecimal("0.00");
        for (Cart cart : cartList) {
            Product product=service.selectById(cart.getId());
            if(cart.getCount()>product.getCount()){

            }
            Long  res=service.updateProductCount(product.getId(),cart.getCount());
            if(res==1){
                Order  order=new Order();
                OrderInfo  orderInfo=new OrderInfo();
                orderInfo.setName(cart.getName());
                orderInfo.setCount((long) cart.getCount());
                orderInfo.setFilePath(cart.getFilePath());
                orderInfoList.add(orderInfo);
                BigDecimal  sumTotal= BigDecimalUtil.mul(cart.getPrice().toString(),cart.getCount()+"");
                BigDecimalUtil.add(sumTotal,order.getTotalPrice());
            }
        }
        if(orderInfoList!=null  &&orderInfoList.size()==cartList.size()){
            Order  order=new Order();
            order.setCreateDate(new Date());
        }
        return null;
    }
}
