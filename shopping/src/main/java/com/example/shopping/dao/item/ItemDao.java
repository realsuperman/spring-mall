package com.example.shopping.dao.item;

import com.example.shopping.domain.category.Category;
import com.example.shopping.domain.item.Item;
import com.example.shopping.dto.Category.CategoryBestResponse;
import com.example.shopping.dto.Category.CategoryRecentResponse;
import com.example.shopping.dto.Category.RecentCategoryDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ItemDao {
    List<CategoryBestResponse> selectCategoryBest(long masterCategoryId);

    List<CategoryRecentResponse> selectCategoryRecent(RecentCategoryDto recentCategoryDto);

    Item selectItemById(long itemId);

    int insertItem(Item item);

    int getItemCount(long categoryId);

    Item selectByKey(long itemId);
    List<Category> selectLeafCategories();
}
