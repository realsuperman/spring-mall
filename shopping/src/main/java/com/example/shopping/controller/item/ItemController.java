package com.example.shopping.controller.item;

import com.example.shopping.domain.item.Item;
import com.example.shopping.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {
    //private final ItemService itemService;
    private final CategoryService categoryService;
    private final String fileName = "item";
    private final int imageSize = 6; // 섬네일 + 일반 이미지들 5개

    @PostMapping
    public String insertItem(@Valid @ModelAttribute Item item,
                             @RequestParam(name="item_quantity") int itemQuantity){

        return "redirect:/";
    }
}
