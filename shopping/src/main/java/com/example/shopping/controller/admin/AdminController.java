package com.example.shopping.controller.admin;

import com.example.shopping.service.cargo.CargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final CargoService cargoService;
    private final String[] validPath = {"stat", "stock"};
    @GetMapping
    public String home(){
        return "admin";
    }

    @GetMapping("/{path}")
    public ResponseEntity getStat(@PathVariable String path, @RequestParam(required = false) String itemName,
                                  @RequestParam(required = false) Long page) {
        /*if(!Arrays.asList(validPath).contains(path)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid path");
        }*/

        Object count;
        Object itemList;

        if("/stat".equals(path)){
            count = cargoService.getCountStockStat(itemName);
            itemList = cargoService.selectStockStat(page, itemName);
        }else{
            count = cargoService.getCountStock(itemName);
            itemList = cargoService.selectStock(page, itemName);
        }

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("count", count);
        responseMap.put("itemList", itemList);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseMap);
    }

}