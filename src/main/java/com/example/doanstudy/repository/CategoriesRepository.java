package com.example.doanstudy.repository;

import com.example.doanstudy.model.entity.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<CategoriesEntity, String>, JpaSpecificationExecutor<CategoriesEntity> {

    List<CategoriesEntity> findByParentIdIsNotNull();

    List<CategoriesEntity> findAllByParentId(String parentId);

    List<CategoriesEntity> findAllByParentIdIsNull();
}
