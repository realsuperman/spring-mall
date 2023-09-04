package com.example.shopping.service.item;

import com.example.shopping.dao.cargo.CargoDao;
import com.example.shopping.dao.category.CategoryDao;
import com.example.shopping.dao.item.ItemDao;
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

    public Map<String,Object> getHomePageData(Long limit, Long page, Integer EXPOSE_CATEGORY_CNT){
        List<Category> leafCategories = getLeafCategories();
        Collections.shuffle(leafCategories);

        List<List<CategoryRecentResponse>> items = new ArrayList<>();
        List<Long> categoryIds = new ArrayList<>();
        List<String> categoryNames = new ArrayList<>();

        for (int i = 0; i < leafCategories.size(); i++){
            long categoryId = leafCategories.get(i).getCategoryId();
            List<CategoryRecentResponse> recentItems = getRecentItemsMatchingCategoryId(limit, page, categoryId);
            if(recentItems.size() >= MIN_COUNT_FOR_PREVIEW_RECENT_ITEM){
                items.add(recentItems);
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
        responseData.put("items", getRecentItemsMatchingCategoryId(PageSize.SIZE.size(),page,categoryId));
        responseData.put("categoryId",categoryId);
        responseData.put("totalPage", getItemCount(categoryId));
        responseData.put("categoryName",category.getCategoryName());
        responseData.put("upperCategoryNames",upperCategoryNames);
        return responseData;
    }

    public Map<String,Object> getItemDetailPageData(Long itemId){
        Item item = getItemById(itemId);
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

    public List<CategoryBestResponse> getBestsellerItemsMatchingMasterCategoryId(long masterCategoryId, long limit) {
        return itemDao.selectBestsellersMatchingMasterCategoryId(masterCategoryId, limit);
    }

    private List<CategoryRecentResponse> getRecentItemsMatchingCategoryId(Long limit, Long page, Long categoryId) {
        CategoryRecentRequest categoryRecentRequest = CategoryRecentRequest.builder()
                .limit(limit)
                .categoryId(categoryId)
                .offset((page - 1) * PageSize.SIZE.size())
                .build();
        return itemDao.selectRecentsMatchingCategoryId(categoryRecentRequest);
    }

    private List<Category> getLeafCategories(){
        return itemDao.selectLeafCategories();
    }

    private int getItemCount(Long categoryId) {
        return itemDao.getItemCount(categoryId);
    }

    private Item getItemById(long itemId) {
        return itemDao.selectItemById(itemId);
    }


}
