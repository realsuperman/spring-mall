package com.example.shopping.controller.item;


import com.example.shopping.domain.item.Item;
import com.example.shopping.dto.category.CategoryBestResponse;
import com.example.shopping.service.category.CategoryService;
import com.example.shopping.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {
    private final ItemService itemService;
    private static final Integer EXPOSE_CATEGORY_CNT = 3;
    private static final Long PREVIEW_ITEM_CNT = 4L;
    private static final Long NO_PAGINATION = 1L;

    @GetMapping("/")
    public String home(Model model){
        Map<String,Object> responseData = itemService.getHomePageData(PREVIEW_ITEM_CNT, NO_PAGINATION, EXPOSE_CATEGORY_CNT);
        model.addAllAttributes(responseData);

        return "mainPage";
    }

    @GetMapping("/item")
    public String item(@RequestParam Long categoryId, @RequestParam Long page, Model model){
        Map<String,Object> responseData = itemService.getItemPageData(categoryId,page);
        model.addAllAttributes(responseData);

        return "item";
    }

    @GetMapping("/itemDetail")
    public String itemDetail(@RequestParam Long itemId, Model model){
        Map<String,Object> responseData = itemService.getItemDetailPageData(itemId);
        model.addAllAttributes(responseData);

        return "itemDetail";
    }

    @ResponseBody
    @GetMapping("/itemJson")
    public List<CategoryBestResponse> itemDetail(@RequestParam Long categoryId){
        return itemService.getBestsellerItemsMatchingMasterCategoryId(categoryId,PREVIEW_ITEM_CNT);
    }

    @PostMapping
    public String insertItem(@Valid @ModelAttribute Item item, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            model.addAttribute("errorMessages", errorMessages);
            return "/admin";
        }

//        item.setItemImagePath(item.getImage1Name()+";"+item.getImage2Name()+";"+item.getImage3Name()+";" +item.getImage4Name()+";"+item.getImage5Name()+";"+item.getImage6Name());
//        itemService.insertItem(item, item.getItemQuantity());
        return "redirect:/admin";
    }
}
