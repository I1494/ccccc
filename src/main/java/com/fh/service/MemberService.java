package com.fh.service;
import com.fh.common.ServerResponse;
import com.fh.model.Member;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MemberService {
    ServerResponse checkMemberName(String name);

    ServerResponse checkPhone(String phone);

    ServerResponse sendCode(String phone);

    ServerResponse register(Member member);

    ServerResponse lo(Member member, HttpServletResponse response);
    /*ServerResponse login(Member member, HttpServletResponse response, HttpServletRequest request);*/
}
