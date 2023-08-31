package com.example.shopping.controller.admin;

import com.example.shopping.service.cargo.CargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

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
    public ResponseEntity getStat(@PathVariable String path) {
        if(!Arrays.asList(validPath).contains(path)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid path"); // 404 응답 코드 설정
        }

        /*Object count;
        Object itemList;

        if("/stat".equals(path)){
            count = cargoService.getCountStockStat(request.getParameter("itemName"));
            itemList = cargoService.selectStockStat(Long.valueOf(request.getParameter("page")), request.getParameter("itemName"));
        }else if("/stock".equals(path)){
            count = cargoService.getCountStock(request.getParameter("itemName"));
            itemList = cargoService.selectStock(Long.valueOf(request.getParameter("page")), request.getParameter("itemName"));
        }else{

        }

        Map<String, Object> response = new HashMap<>();
        response.put("count", count);
        response.put("itemList", itemList);

        return ResponseEntity.ok(response);*/
        return null;
    }

}