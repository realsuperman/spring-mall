package com.example.shopping.dao.status;

import com.example.shopping.domain.status.Status;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface StatusDao {
    List<Status> selectAll();
}
