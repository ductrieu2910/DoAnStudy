package com.example.doanstudy.service;

import com.example.doanstudy.model.entity.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {
    //ADMIN
    Page<CourseEntity> findAll(String search,int pageNumber);
    List<CourseEntity> findAll();
    CourseEntity saveOrUpdate(CourseEntity courseEntity);
    CourseEntity findById(String id);
    CourseEntity remove(CourseEntity courseEntity);
    //CUSTOMER
    Page<CourseEntity> findBy(String search, String status, Pageable pageable);
    // Thêm phương thức chuyển chuỗi thành danh sách
    List<String> convertCategoryStringToList(String categoryIds);
    // Thêm phương thức để lấy danh sách tên khóa học từ chuỗi danh mục
    List<CourseEntity> getCourseNamesByCategories(String categoryIds);
}
