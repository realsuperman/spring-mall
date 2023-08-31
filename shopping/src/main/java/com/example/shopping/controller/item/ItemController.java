package com.example.shopping.controller.item;

import com.example.shopping.domain.category.Category;
import com.example.shopping.dto.category.CategoryBestResponse;
import com.example.shopping.service.CategoryService;
import com.example.shopping.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final CategoryService categoryService;
    private final ItemService itemService;

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
    // spring boot는 기본적으로 jsp를 처리할 수 있는 viewResolver를 가지고 있지 않다. 그래서 gradle에서 jsp를 처리할 수 있는 viewResolver를 추가했다.
    // controller에서 return했을 때 application.properties의 prefix, suffix 특히 suffix를 확인한다.
    // suffix가 .jsp이기 때문에 여기에 맞는 viewResolver를 선택한다. 우리는 gradle에서 jsp용 viewResolver를 추가했기 때문에 해당 viewResolver를 찾아올 수 있다.
    // adapter는 String을 받으면 무조건 viewResolver를 타도록 설정되어 있다.
    // adapter가 responseEntity를 받으면 viewResolver를 타지 않도록 설정되어 있다.
    // adapter가 modelAndView로 결과를 DispatcherServlet에게 전달한다
    // DispatcherServlet은 adapter가 알려준 결과를 보고 viewResolver를 태울지말지 결정한다.
}
