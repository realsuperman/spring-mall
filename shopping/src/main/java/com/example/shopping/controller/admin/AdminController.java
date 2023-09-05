package com.example.shopping.controller.admin;

import com.example.shopping.dto.cargo.StockStatDto;
import com.example.shopping.service.cargo.CargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
    public String stockAndStat(@PathVariable String path, Model model) {
        validatePath(path);
        model.addAttribute("page",path);
        return "stockAndStat";
    }

    @GetMapping(value = "/{path}/rest")
    public ResponseEntity stockAndStatRest(@PathVariable String path, @RequestParam(required = false) String itemName,
                                           @RequestParam(required = false) Long page){
        validatePath(path);
        Object count;
        Object itemList;

        if("stat".equals(path)){
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

    @PatchMapping("/stock/stat")
    public ResponseEntity<String> updateStockStat(@RequestBody List<StockStatDto> cargoStatusArray){
        cargoService.updateStockStat(cargoStatusArray);
        return ResponseEntity.ok("SUCCESS");
    }

    private void validatePath(String path) {
        if(!Arrays.asList(validPath).contains(path)){
            throw new RuntimeException();
        }
    }

}