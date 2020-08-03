package com.fh.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberMapper  extends   BaseMapper<Member>{
    /*Member checkByName(String name);*/
}
