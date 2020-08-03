package com.fh.model;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_category")
public class Category {


    private  Integer  categoryId;
    private  String  name;
    private  Integer  pid;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
