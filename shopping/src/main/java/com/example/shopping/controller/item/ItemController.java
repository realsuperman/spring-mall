package com.example.shopping.controller.item;

import com.example.shopping.domain.category.Category;
import com.example.shopping.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final CategoryService categoryService;

//    @GetMapping
//    public String func(@RequestParam Long categoryId, @RequestParam Long page){
//        Category category = categoryService.findCategoryById(categoryId);
//
//        List<Category> parentsById = categoryService.findParentsById(categoryId);
//        List<String> upperCategoryNames = parentsById.stream()
//                .map(Category::getCategoryName)
//                .collect(Collectors.toList());
//        Collections.reverse(upperCategoryNames);
//
//        request.setAttribute("items", itemService.selectCategoryRecent(page, categoryId));
//        request.setAttribute("categoryId", categoryId);
//        request.setAttribute("totalPage", itemService.itemCount(categoryId));
//        request.setAttribute("categoryName", category.getCategoryName());
//        request.setAttribute("upperCategoryNames", upperCategoryNames);
//        RequestDispatcher rd = request.getRequestDispatcher(LabelFormat.PREFIX.label() + fileName + LabelFormat.SUFFIX.label());
//        rd.forward(request, response);
//    }


    @GetMapping("/test")
    public String func(Model model){
        List<Category> categories = categoryService.selectAll();
        model.addAttribute("data",categories);
        return "test";
    }
    // adapter는 String을 받으면 무조건 viewResolver를 타도록 설정되어 있다.
    // adapter가 responseEntity를 받으면 viewResolver를 타지 않도록 설정되어 있다.
    // adapter가 modelAndView로 결과를 DispatcherServlet에게 전달한다
    // DispatcherServlet은 adapter가 알려준 결과를 보고 viewResolver를 태울지말지 결정한다.
}
