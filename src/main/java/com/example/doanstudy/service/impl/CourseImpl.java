package com.example.doanstudy.service.impl;

import com.example.doanstudy.model.entity.CategoriesEntity;
import com.example.doanstudy.model.entity.CourseEntity;
import com.example.doanstudy.repository.CourseRepository;
import com.example.doanstudy.service.CourseService;
import jakarta.persistence.criteria.Join;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CourseImpl implements CourseService {

    private final CourseRepository courseRepository;



    @Override
    public Page<CourseEntity> findAll(String search, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber -1, 5);
        Specification<CourseEntity> specs = Specification.where(null);
        if(Objects.nonNull(search)){
            final String key = "%"+search+"%";
            specs = specs.or((r, q, b) -> b.like(r.get("name"),key))
                    .or((r,q,b) -> {
                        Join<CourseEntity, CategoriesEntity> entityJoin = r.join("categoriesByCategoriesId");
                        return b.like(entityJoin.get("name"), key);
                    });
//                    .or(r, q, b) -> b.like(r.join("ratingCommentsByRatingCommentId")).get("name"),key);
        }
        Page<CourseEntity> page = courseRepository.findAll(specs,pageable);
        return page;
    }

    @Override
    public List<CourseEntity> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public CourseEntity saveOrUpdate(CourseEntity courseEntity) {
        try {
            return courseRepository.save(courseEntity);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public CourseEntity findById(String id) {
        try {
            return courseRepository.findById(id).orElse(new CourseEntity());
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public CourseEntity remove(CourseEntity courseEntity) {
        try {
            courseRepository.delete(courseEntity);
            return courseEntity;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<CourseEntity> findBy(String search, String status, Pageable pageable) {
        Specification<CourseEntity> specs = Specification.where((r, q, b) -> b.equal(r.get("status"), status));
        if (Objects.nonNull(search)){
            final String key = "%" + search + "%";
            specs = specs.or((r, q,b) -> b.like(r.get("name"), key))
                    .or((r, q,b) -> {
                        Join<CourseEntity, CategoriesEntity> entityJoin = r.join("categoriesByCategoriesId");
                        return b.like(entityJoin.get("name"), key);
                    })
//                    .or((r, q,b) -> b.like(r.join("")))
            ;
        }
        Page<CourseEntity> page = courseRepository.findAll(specs, pageable);
        return page;
    }

    // Chuyển chuỗi thành danh sách ID
    public List<String> convertCategoryStringToList(String categoryIds) {
        // Kiểm tra nếu chuỗi chỉ chứa một giá trị (không có dấu "-")
        if (!categoryIds.contains("-")) {
            return List.of(categoryIds); // Trả về danh sách với một phần tử
        }
        // Nếu chuỗi chứa nhiều giá trị, tách bằng dấu "-"
        String[] idsArray = categoryIds.split("-");
        return List.of(idsArray);
    }

    // Lấy danh sách tên khóa học theo danh mục
    public List<CourseEntity> getCourseNamesByCategories(String categoryIds) {
        // Chuyển chuỗi thành danh sách ID
        List<String> categoriesIdList = convertCategoryStringToList(categoryIds);
        // Lấy danh sách tên khóa học từ repository
        return courseRepository.findByCategoriesId(categoriesIdList);
    }
}
