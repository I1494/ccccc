package com.fh.service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fh.common.ServerResponse;
import com.fh.mapper.ProductMapper;
import com.fh.model.Product;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service


public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper mapper;
    @Override
    public Product queryProductById(Integer id) {
        return mapper.queryProductById(id);
    }

    @Override
    public ServerResponse queryShopList() {
        return mapper.queryShopList();
    }

    @Override
    public ServerResponse queryProductPage(Integer start, Integer pageSize) {
         QueryWrapper  queryWrapper=new QueryWrapper();
        IPage<Product>page=new Page<Product>() ;
         page.setCurrent(start);
         page.setSize(pageSize);
         IPage<Product>   productIPage=mapper.selectPage(page,null);
         List<Product> list=productIPage.getRecords();
          Long  total=productIPage.getTotal();
          Map<String,Object>map=new HashMap<>();
          map.put("list",list);
          map.put("total",total);
          return ServerResponse.success(map);
    }



    @Override
    public ServerResponse queryShopIsStatus() {
        QueryWrapper  queryWrapper=new QueryWrapper();
        queryWrapper.eq("isHot",1);
        List  list=mapper.selectList(queryWrapper);
        return ServerResponse.success(list);
    }

    @Override
    public Product selectProductById(Integer id) {
        Product  product=mapper.selectProductById(id);
        return product;
    }

    @Override
    public Long updateCount(Integer id, int count) {
        return mapper.updateCount(id,count);
    }

    @Override
    public Product selectById(Integer id) {
        Product  product=mapper.selectById(id);
        return product;
    }

    @Override
    public Long updateProductCount(Integer id, int count) {
        return mapper.updateProductCount(id,count);
    }

    @Override
    public Product selectShopById(Integer id) {

        return mapper.selectShopById(id);
    }

   /* @Override
    public ServerResponse queryProductList() {
        return mapper.queryProductList();
    }*/
}
