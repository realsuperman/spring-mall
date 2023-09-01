package com.example.shopping.dao;

import com.example.shopping.domain.cargo.Cargo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CargoDao {
    int insertCargo(List<Cargo> cargoList);
    int cargoCnt(long itemId);
}
