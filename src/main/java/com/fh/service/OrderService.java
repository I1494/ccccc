package com.fh.service;

import com.fh.common.ServerResponse;
import com.fh.model.Cart;
import com.fh.model.Member;

import java.util.List;

public interface OrderService {

    ServerResponse bulidOrder(List<Cart> cartList, Integer payType, Integer addressId, Member member);
}
