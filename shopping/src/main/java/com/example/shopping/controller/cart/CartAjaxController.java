package com.example.shopping.controller.cart;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/sm/c")
public class CartAjaxController {
    private final Logger cart_log = LoggerFactory.getLogger(CartAjaxController.class);

    @PostMapping("/api/v1")
    public String addUnchecked(@RequestBody Map<String, Object> requestData, HttpSession session) {
        int excludedItemId = (Integer)requestData.get("excludedItemId");
        cart_log.info("excludedItemId: " + Long.valueOf(excludedItemId));
//        Set<Long> excludedSet = (HashSet<Long>)session.getAttribute("excludedSet");
//        if(excludedSet == null) {
//            excludedSet = new HashSet<>();
//        }
//        excludedSet.add(Long.valueOf(excludedItemId));
//        cart_log.info("excludedSet: " + excludedItemId);
        return "cart_component";
    }
}
