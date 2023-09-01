package com.example.shopping.dao;

import com.example.shopping.domain.category.Category;
import com.example.shopping.domain.item.Item;
import com.example.shopping.dto.category.CategoryBestResponse;
import com.example.shopping.dto.category.CategoryRecentRequest;
import com.example.shopping.dto.category.CategoryRecentResponse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ItemDao {
    int insertItem(Item item);
    Item selectByKey(long itemId);
    Item selectItemById(long itemId);
    int getItemCount(long categoryId);
    List<CategoryBestResponse> selectCategoryBest(long masterCategoryId, long limit);
    List<CategoryRecentResponse> selectCategoryRecent(CategoryRecentRequest categoryRecentRequest);
    List<Category> selectLeafCategories();

}
