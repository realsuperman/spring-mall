package com.example.shopping.service.cargo;

import com.example.shopping.dao.cargo.CargoDao;
import com.example.shopping.dto.cargo.CargoDto;
import com.example.shopping.dto.cargo.StockDto;
import com.example.shopping.dto.cargo.StockSearchDto;
import com.example.shopping.dto.cargo.StockStatDto;
import com.example.shopping.global.PageSize;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CargoService {
    private final CargoDao cargoDao;

    public List<StockDto> selectStock(Long page, String itemName) {
        return cargoDao.selectStock(setStockSearchDto(page,itemName));
    }

    public int getCountStock(String itemName) {
        return cargoDao.countStock(setStockSearchDto(null,itemName));
    }

    public List<CargoDto> selectStockStat(Long page, String itemName) {
        return cargoDao.selectStockStat(setStockSearchDto(page,itemName));
    }

    public int getCountStockStat(String itemName) {
        return cargoDao.countStockStat(setStockSearchDto(null,itemName));
    }

    @Transactional
    public void updateStockStat(List<StockStatDto> stockStatDtoList) {
        for(StockStatDto stockStatDto : stockStatDtoList){
            cargoDao.updateCargoStat(stockStatDto);
        }
    }

    private StockSearchDto setStockSearchDto(Long page, String itemName){
        StockSearchDto stockSearchDto = new StockSearchDto();
        stockSearchDto.setItemName('%'+itemName+'%');
        stockSearchDto.setPageSize(PageSize.SIZE.size());
        long offset = page==null?0:page* PageSize.SIZE.size();
        stockSearchDto.setOffset(offset);
        return stockSearchDto;
    }
}