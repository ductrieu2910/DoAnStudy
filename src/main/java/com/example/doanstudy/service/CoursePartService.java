package com.example.doanstudy.service;


import com.example.doanstudy.model.entity.CoursePartEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CoursePartService {
    Page<CoursePartEntity> findAll(String search, int pageNumber);
    List<CoursePartEntity> findAll();
    List<CoursePartEntity> findByParentIdIsNotNull();
    CoursePartEntity SaveorUpdate(CoursePartEntity coursepartentity);
    CoursePartEntity findById(String id);
    CoursePartEntity remove (CoursePartEntity coursepartentity);
}
