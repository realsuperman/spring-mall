package com.example.shopping.dao;

import com.example.shopping.domain.cargo.Cargo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CargoDao {
    int insertCargo(List<Cargo> cargoList);
    int cargoCnt(long itemId);
}
