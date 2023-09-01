package com.example.shopping.service.item;

import com.example.shopping.dao.cargo.CargoDao;
import com.example.shopping.dao.item.ItemDao;
import com.example.shopping.domain.cargo.Cargo;
import com.example.shopping.domain.category.Category;
import com.example.shopping.domain.item.Item;
import com.example.shopping.dto.Category.CategoryBestResponse;
import com.example.shopping.dto.Category.CategoryRecentResponse;
import com.example.shopping.dto.Category.RecentCategoryDto;
import com.example.shopping.global.PageSize;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemDao itemDao;
    private final CargoDao cargoDao;

    public List<CategoryBestResponse> selectCategoryBest(long masterCategoryId) {
        return itemDao.selectCategoryBest(masterCategoryId);
    }

    public List<CategoryRecentResponse> selectCategoryRecent(Long page, Long categoryId) {
        RecentCategoryDto recentCategoryDto = RecentCategoryDto.builder()
                .limit(PageSize.SIZE.size())
                .categoryId(categoryId)
                .offset((page == null) ? null : (page - 1) * PageSize.SIZE.size())
                .build();
        return itemDao.selectCategoryRecent(recentCategoryDto);
    }

    public int itemCount(Long categoryId) {
        return itemDao.getItemCount(categoryId);
    }

    public Item selectItemById(long itemId) {
        return itemDao.selectItemById(itemId);
    }

    @Transactional
    public void insertItem(Item item, int count) {
        itemDao.insertItem(item);
        List<Cargo> cargoList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            cargoList.add(Cargo.builder().itemId(item.getItemId()).statusId(3L).build());
        }
        cargoDao.insertCargo(cargoList);

    }

    public List<Category> selectLeafCategories(){
        return itemDao.selectLeafCategories();
    }
}