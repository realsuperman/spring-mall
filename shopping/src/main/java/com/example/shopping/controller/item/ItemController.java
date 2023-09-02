package com.example.shopping.controller.item;


import com.example.shopping.domain.category.Category;
import com.example.shopping.domain.item.Item;
import com.example.shopping.dto.category.CategoryBestResponse;
import com.example.shopping.service.category.CategoryService;
import com.example.shopping.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
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
    private final CategoryService categoryService;

    private static final Integer EXPOSE_CATEGORY_CNT = 3;
    private static final Long PREVIEW_ITEM_CNT = 4L;
    private static final Long NO_PAGINATION = 1L;

    @GetMapping("/")
    public String home(Model model){
        Map<String,Object> responseData = itemService.getHomeData(PREVIEW_ITEM_CNT, NO_PAGINATION, EXPOSE_CATEGORY_CNT);
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
        Map<String,Object> responseData = itemService.getItemDetailData(itemId);
        model.addAllAttributes(responseData);

        return "itemDetail";
    }

    @ResponseBody
    @GetMapping("/itemJson")
    public List<CategoryBestResponse> itemDetail(@RequestParam Long categoryId){
        return itemService.selectCategoryBest(categoryId,PREVIEW_ITEM_CNT);
    }

    @GetMapping("/test")
    public String func(Model model){
        List<Category> categories = categoryService.selectAll();
        model.addAttribute("data",categories);

        return "test";
    }

    /**
     * 1. 폼이 이동했을 때 어떻게 할 것인가? < 해당 폼의 post에서 try-catch TODO
     * 2. json으로 요청 했는데 DB가 터졌어 < 2랑 3이랑은 content-type으로 구분
     * 3. 페이지 요청 했는데 DB가 터졌어
     */
    @PostMapping("/item")
    public String insertItem(@Valid @ModelAttribute Item item, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            model.addAttribute("errorMessages", errorMessages);
            return "/admin";
        }

        item.setItemImagePath(item.getImage1Name()+";"+item.getImage2Name()+";"+item.getImage3Name()+";" +item.getImage4Name()+";"+item.getImage5Name()+";"+item.getImage6Name());
        try {
            itemService.insertItem(item, item.getItemQuantity()); // form으로 하는 경우 이렇게 처리를 해야함
        }catch(DataAccessException e){
            model.addAttribute("dbError", "통신 중 문제가 발생 했습니다.");
            return "/admin";
        }
        return "redirect:/admin";
    }
}
