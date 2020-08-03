package com.fh.controller;
import com.fh.common.ServerResponse;
import com.fh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("ProductController")
public class ProductController {
    @Autowired
    private ProductService service;


 /*   @RequestMapping("queryProductList")
    @ResponseBody
    public ServerResponse  queryProductList(){
        return  service.queryProductList();
    }
*/

  @RequestMapping("queryShopList")
  @ResponseBody
 public  ServerResponse  queryShopList(){
      return  service.queryShopList();
  }

  @RequestMapping("queryProductPage")
  @ResponseBody
  public  ServerResponse  queryProductPage(Integer  start,Integer  pageSize ){
        return  service.queryProductPage(start,pageSize);
  }

  @RequestMapping("queryShopIsStatus")
  @ResponseBody
  public  ServerResponse  queryShopIsStatus(){
     return    service.queryShopIsStatus();
  }
}
