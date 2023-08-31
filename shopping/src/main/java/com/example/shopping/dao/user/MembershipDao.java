package com.example.shopping.dao.user;

import com.example.shopping.domain.user.Membership;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;

@Mapper
public interface MembershipDao {

    public Membership selectMembershipByPrice(long price);
}
