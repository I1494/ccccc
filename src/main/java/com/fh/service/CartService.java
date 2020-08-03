package com.fh.service;

import com.fh.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;

public interface CartService {
    ServerResponse addCart(Integer id, Integer count, HttpServletRequest request);
    ServerResponse queryCartCount(HttpServletRequest request);
}
