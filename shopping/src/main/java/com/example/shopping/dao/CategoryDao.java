package com.example.shopping.dao;


import com.example.shopping.domain.category.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryDao {
    public List<Category> selectAll();
    public Category findCategoryById(Long categoryId);

    public List<Category> findParentsById(long categoryId);
}
