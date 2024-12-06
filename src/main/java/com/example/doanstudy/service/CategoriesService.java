package com.example.doanstudy.service;

import com.example.doanstudy.model.dto.CategoriesDTO;
import com.example.doanstudy.model.entity.CategoriesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CategoriesService {
    Page<CategoriesEntity> findAll(String search, int pageNumber);
    List<CategoriesEntity> findAll();
    List<CategoriesDTO> categoriesDTOList();
    List<CategoriesEntity> findByParentIdIsNotNull();
    List<CategoriesEntity> findAllByParentIdIsNull(); // Lấy danh mục cha
    List<CategoriesEntity> findAllByParentId(String parentId); // Lấy danh mục con dựa trên parentId
    CategoriesEntity SaveorUpdate(CategoriesEntity categoriesEntity);
    CategoriesEntity findById(String id);
    CategoriesEntity remove (CategoriesEntity categoriesEntity);
}
