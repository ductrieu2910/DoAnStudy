package com.example.doanstudy.repository;

import com.example.doanstudy.model.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, String>, JpaSpecificationExecutor<CourseEntity> {

        @Query("select c from CourseEntity c WHERE c.categoriesByCategoriesId.id in ?1")
        List<CourseEntity>  findByCategoriesId(List<String> categoriesId);

}
