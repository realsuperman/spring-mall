package com.example.shopping.dao.user;

import com.example.shopping.domain.user.Consumer;
import com.example.shopping.dto.user.UpdatePasswordRequest;
import com.example.shopping.dto.user.UpdateUserRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConsumerDao {

    public Consumer selectOne(String consumerId);

    public int insert(Consumer consumer);

    public int updatePassword(Consumer consumer);

    public int updateUserInfo(Consumer consumer);

}
