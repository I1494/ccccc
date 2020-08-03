package com.fh.controller;
import com.fh.common.ServerResponse;
import com.fh.model.Member;
import com.fh.service.MemberService;
import com.fh.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("MemberController")
public class MemberController {
    @Autowired
    private MemberService service;

    /*@RequestMapping("login")
    @ResponseBody
    public ServerResponse   login(Member member, HttpServletResponse  response, HttpServletRequest  request){
        return  service.login(member,response,request);
    }*/
    @RequestMapping("checkMemberName")
    @ResponseBody
    public ServerResponse  checkMemberName(String  name){
       return  service.checkMemberName(name);
    }


    @RequestMapping("checkPhone")
    @ResponseBody
    public  ServerResponse  checkPhone(String  phone){
        return  service.checkPhone(phone);
    }


    @RequestMapping("sendCode")
    @ResponseBody
    public  ServerResponse  sendCode(String  phone){
        return   service.sendCode(phone);
    }

    @RequestMapping("register")
    @ResponseBody
    public  ServerResponse register(Member  member){
        String redisCode = RedisUtil.get(member.getPhone(), member.getId());
        if(redisCode==null){
            return  ServerResponse.error("以失效");
        }
        if(!redisCode.equals(member.getCode())){
            return  ServerResponse.error("不正确");
        }
        return  service.register(member);
    }


    @RequestMapping("lo")
    @ResponseBody
    public  ServerResponse  lo(Member  member, HttpServletResponse  response){
        return  service.lo(member,response);
    }

}
