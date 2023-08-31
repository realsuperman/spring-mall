package com.example.shopping.dao;


import com.example.shopping.domain.category.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryDao {
    List<Category> selectAll();
    Category findCategoryById(Long categoryId);
    List<Category> findParentsById(long categoryId);
}
