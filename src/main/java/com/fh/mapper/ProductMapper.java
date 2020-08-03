package com.fh.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.common.ServerResponse;
import com.fh.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ProductMapper  extends BaseMapper<Product> {
    Product queryProductById(Integer id);
    ServerResponse queryShopList();
    Product selectProductById(Integer id);

    Long updateCount(@Param("id")Integer id,   @Param("count") int count);

    Long updateProductCount(@Param("id") Integer id, @Param("count") int count);

    Product selectShopById(Integer id);
    /*ServerResponse queryProductList();*/



}
