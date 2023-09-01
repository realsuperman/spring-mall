package com.example.shopping.controller.category;

import com.example.shopping.domain.category.Category;
import com.example.shopping.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private Map<String, Object> responseBody = null;
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllCategories() {
        if(responseBody != null){
            return ResponseEntity.ok(responseBody);
        }

        List<Category> categories = categoryService.selectAll();
        Map<String, List<String>> largeCategory = new LinkedHashMap<>();
        Map<String, List<String>> middleCategory = new LinkedHashMap<>();
        initCategory(categories, largeCategory, middleCategory);

        responseBody = new LinkedHashMap<>();
        responseBody.put("largeCategory", largeCategory);
        responseBody.put("middleCategory", middleCategory);

        return ResponseEntity.ok(responseBody);
    }

    private void initCategory(List<Category> categories, Map<String, List<String>> largeCategory,
                              Map<String, List<String>> middleCategory){
        for(Category category : categories){
            if(category.getMasterCategoryId()==null){
                largeCategory.put(category.getCategoryId()+";"+category.getCategoryName(),new ArrayList<>());
            }
        }

        setCategory(categories, largeCategory, middleCategory);
        setCategory(categories, middleCategory, null);

    }

    private void setCategory(List<Category> categories, Map<String, List<String>> categoryMap,
                             Map<String, List<String>> nextCategoryMap) {
        for (String categoryKey : categoryMap.keySet()) {
            String[] key = categoryKey.split(";");
            for (Category category : categories) {
                if(category.getMasterCategoryId()==null) continue;
                if (Long.parseLong(key[0]) == category.getMasterCategoryId()) { // 타입 변환 후 비교
                    List<String> list = categoryMap.get(categoryKey);
                    list.add(category.getCategoryId() + ";" + category.getCategoryName());
                    if(nextCategoryMap!=null){
                        nextCategoryMap.put(category.getCategoryId() + ";" + category.getCategoryName(), new ArrayList<>());
                    }
                }
            }
        }
    }
}