package com.example.shopping.service;

import com.example.shopping.dao.CargoDao;
import com.example.shopping.dao.CategoryDao;
import com.example.shopping.dao.ItemDao;
import com.example.shopping.domain.cargo.Cargo;
import com.example.shopping.domain.category.Category;
import com.example.shopping.domain.item.Item;
import com.example.shopping.dto.category.CategoryBestResponse;
import com.example.shopping.dto.category.CategoryRecentRequest;
import com.example.shopping.dto.category.CategoryRecentResponse;
import com.example.shopping.util.PageSize;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemDao itemDao;
    private final CargoDao cargoDao;
    private final CategoryDao categoryDao;

    private static final Long MIN_COUNT_FOR_PREVIEW_RECENT_ITEM = 4L;

    @Transactional
    public void insertItem(Item item, int count) {
        itemDao.insertItem(item);
        List<Cargo> cargoList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            cargoList.add(Cargo.builder().itemId(item.getItemId()).statusId(3L).build());
        }
        cargoDao.insertCargo(cargoList);
    }

    public Map<String,Object> getHomeData(Long limit, Long page, Integer EXPOSE_CATEGORY_CNT){
        List<Category> leafCategories = selectLeafCategories();
        Collections.shuffle(leafCategories);

        List<List<CategoryRecentResponse>> items = new ArrayList<>();
        List<Long> categoryIds = new ArrayList<>();
        List<String> categoryNames = new ArrayList<>();

        for (int i = 0; i < leafCategories.size(); i++){
            long categoryId = leafCategories.get(i).getCategoryId();
            List<CategoryRecentResponse> categoryRecentResponse = selectCategoryRecent(limit, page, categoryId);
            if(categoryRecentResponse.size() >= MIN_COUNT_FOR_PREVIEW_RECENT_ITEM){
                items.add(categoryRecentResponse);
                categoryIds.add(categoryId);
                categoryNames.add(leafCategories.get(i).getCategoryName());
                if(items.size() >= EXPOSE_CATEGORY_CNT) break;
            }
        }
        Map<String,Object> responseData = new HashMap<>();
        responseData.put("itemList",items);
        responseData.put("categoryIds",categoryIds);
        responseData.put("categoryNames",categoryNames);

        return responseData;
    }

    public Map<String,Object> getItemPageData(Long categoryId, Long page){
        Category category = categoryDao.findCategoryById(categoryId);

        List<String> upperCategoryNames = categoryDao.findParentsById(categoryId)
                .stream()
                .map(Category::getCategoryName)
                .collect(Collectors.toList());
        Collections.reverse(upperCategoryNames);

        Map<String,Object> responseData = new HashMap<>();
        responseData.put("items",selectCategoryRecent(PageSize.SIZE.size(),page,categoryId));
        responseData.put("categoryId",categoryId);
        responseData.put("totalPage",itemCount(categoryId));
        responseData.put("categoryName",category.getCategoryName());
        responseData.put("upperCategoryNames",upperCategoryNames);
        return responseData;
    }

    public Map<String,Object> getItemDetailData(Long itemId){
        // TODO: JOIN해서 한번에 가져올 수 있는가?
        Item item = selectItemById(itemId);
        long categoryId = item.getCategoryId();
        List<String> upperCategoryNames = categoryDao.findParentsById(categoryId)
                .stream()
                .map(Category::getCategoryName)
                .collect(Collectors.toList());
        int cargoCnt = cargoDao.cargoCnt(itemId);
        Map<String,Object> responseData = new HashMap<>();
        responseData.put("item",item);
        responseData.put("upperCategoryNames",upperCategoryNames);
        responseData.put("cargoCnt",cargoCnt);

        return responseData;
    }

    public List<CategoryBestResponse> selectCategoryBest(long masterCategoryId, long limit) {
        return itemDao.selectCategoryBest(masterCategoryId, limit);
    }

    private List<CategoryRecentResponse> selectCategoryRecent(Long limit, Long page, Long categoryId) {
        CategoryRecentRequest categoryRecentRequest = CategoryRecentRequest.builder()
                .limit(limit)
                .categoryId(categoryId)
                .offset((page - 1) * PageSize.SIZE.size())
                .build();
        return itemDao.selectCategoryRecent(categoryRecentRequest);
    }

    private List<Category> selectLeafCategories(){
        return itemDao.selectLeafCategories();
    }

    private int itemCount(Long categoryId) {
        return itemDao.getItemCount(categoryId);
    }

    private Item selectItemById(long itemId) {
        return itemDao.selectItemById(itemId);
    }


}
