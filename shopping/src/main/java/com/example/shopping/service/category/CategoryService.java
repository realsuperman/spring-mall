package com.example.shopping.service.category;

import com.example.shopping.dao.category.CategoryDao;
import lombok.RequiredArgsConstructor;
import com.example.shopping.domain.category.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryDao categoryDao;

    public List<Category> selectAll(){
        return categoryDao.selectAll();
    }

    public Category findCategoryById(long categoryId){
            return categoryDao.findCategoryById(categoryId);
    }

    public List<Category> findParentsById(long categoryId){
            return categoryDao.findParentsById(categoryId);
    }
}
