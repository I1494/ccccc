package com.fh.service;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.common.Const;
import com.fh.common.ServerResponse;
import com.fh.mapper.MemberMapper;
import com.fh.message.SendMsg;
import com.fh.model.Member;
import com.fh.util.JwtUtil;
import com.fh.util.RedisUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Service
public class MemberServiceImpl implements MemberService {
    @Resource
    private MemberMapper mapper;


    @Override
    public ServerResponse checkMemberName(String name) {
        QueryWrapper<Member>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",name);
        Member  member=mapper.selectOne(queryWrapper);
        if(member!=null){
            return  ServerResponse.error("用户已存在");
        }
        return ServerResponse.success();
    }

    @Override
    public ServerResponse checkPhone(String phone) {
        QueryWrapper<Member>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("phone",phone);
        Member member=mapper.selectOne(queryWrapper);
        if(member!=null){
            return  ServerResponse.error("手机号已存在");
        }
        return ServerResponse.success();
    }

    @Override
    public ServerResponse sendCode(String phone) {
        String  code=SendMsg.getCode();
        try {
            SendSmsResponse  sendSmsResponse=SendMsg.sendSms(phone,code);
            if(sendSmsResponse!=null  && "OK".equals(sendSmsResponse.getCode())){
                RedisUtil.set(phone,code, Const.REDIS_EXPIRE_TIME);
            }
        } catch (ClientException e) {
            e.printStackTrace();
            return   ServerResponse.error(e.getErrMsg());
        }
        return ServerResponse.success(code);
    }

    @Override
    public ServerResponse register(Member member) {
        mapper.insert(member);
        return ServerResponse.success();
    }



    @Override
    public ServerResponse lo(Member member, HttpServletResponse response) {
        QueryWrapper<Member>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",member.getName());
        queryWrapper.or();
        queryWrapper.eq("phone",member.getName());
       Member memberDB= mapper.selectOne(queryWrapper);
       if(memberDB==null){
           return   ServerResponse.error("不存在");
       }

       if(!memberDB.getPassword().equals(member.getPassword())){
             return   ServerResponse.error("不正确");
       }
        String tokenMemberId = (String) JwtUtil.sign(memberDB.getName(),memberDB.getId().toString());
        RedisUtil.set(tokenMemberId,tokenMemberId, (long) Const.COOKIE_EXPIRE_TIME);
        return ServerResponse.success(tokenMemberId);
    }


}
