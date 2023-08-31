package com.example.shopping.dao.cargo;

import com.example.shopping.domain.cargo.Cargo;
import com.example.shopping.dto.cargo.CargoDto;
import com.example.shopping.dto.cargo.StockDto;
import com.example.shopping.dto.cargo.StockSearchDto;
import com.example.shopping.dto.cargo.StockStatDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface CargoDao {
    List<Cargo> selectAll();

    int insertCargo(List<Cargo> cargoList);

    int updateCargoStatusByCargoId(Map<String, Long> map);

    long selectCountByItemId(Long itemId);

    List<Cargo> selectCargoToDeliver(Map<String, Long> map);

    List<Cargo> selectCargoByItemIdAndNotStatusId(Map<String, Long> map);
    int cargoCnt(SqlSession session, long itemId);

    List<StockDto> selectStock(StockSearchDto stockSearchDto);

    int countStock(StockSearchDto stockSearchDto);

    List<CargoDto> selectStockStat(StockSearchDto stockSearchDto);

    int countStockStat(StockSearchDto stockSearchDto);

    int updateCargoStat(StockStatDto stockStatDto);
}