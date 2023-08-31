package com.example.shopping.service;

import com.example.shopping.dao.CargoDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CargoService {
    private final CargoDao cargoDao;

    public int getCargoCnt(long itemId){
        return cargoDao.cargoCnt(itemId);
    }

}
