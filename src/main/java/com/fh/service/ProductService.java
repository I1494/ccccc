package com.fh.service;
import com.fh.common.ServerResponse;
import com.fh.model.Product;
public interface ProductService {
    Product queryProductById(Integer id);
    ServerResponse queryShopList();
    ServerResponse queryProductPage(Integer start, Integer pageSize);
    ServerResponse queryShopIsStatus();

    Product selectProductById(Integer id);

    Long updateCount(Integer id, int count);

    Product selectById(Integer id);

    Long updateProductCount(Integer id, int count);

    Product selectShopById(Integer id);


    /*ServerResponse queryProductList();*/
}
