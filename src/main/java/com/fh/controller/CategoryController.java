package com.fh.controller;
import com.fh.common.ServerResponse;
import com.fh.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("CategoryController")
public class CategoryController {
    @Autowired
    private CategoryService  service;


    @RequestMapping("queryList")
    @ResponseBody
    public ServerResponse  queryList(){
        List<Map<String,Object>> list=service.queryList();
        return  ServerResponse.success(list);
    }

}
