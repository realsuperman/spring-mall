package com.example.shopping.service.category;

import com.example.shopping.dao.CategoryDao;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import com.example.shopping.domain.category.Category;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
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
