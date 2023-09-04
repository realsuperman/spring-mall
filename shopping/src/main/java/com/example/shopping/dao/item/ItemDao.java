package com.example.shopping.dao.item;
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
    List<CategoryBestResponse> selectBestsellersMatchingMasterCategoryId(long masterCategoryId, long limit);

    List<CategoryRecentResponse> selectRecentsMatchingCategoryId(CategoryRecentRequest categoryRecentRequest);

    Item selectItemById(long itemId);

    int insertItem(Item item);

    int getItemCount(long categoryId);

    List<Category> selectLeafCategories();
}
