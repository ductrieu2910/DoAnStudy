package com.example.doanstudy.service.impl;

import com.example.doanstudy.model.entity.CategoriesEntity;
import com.example.doanstudy.model.entity.CourseEntity;
import com.example.doanstudy.model.entity.CoursePartEntity;
import com.example.doanstudy.repository.CoursePartRepository;
import com.example.doanstudy.service.CoursePartService;
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
public class CoursePartImpl implements CoursePartService {
    private final CoursePartRepository coursePartRepository;
    @Override
    public Page<CoursePartEntity> findAll(String search, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber -1, 2);
        Specification<CoursePartEntity> specs = Specification.where(null);
        if(Objects.nonNull(search)){
            final String key = "%"+search+"%";
            specs = specs.or((r, q, b) -> b.like(r.get("name"),key))
                    .or((r,q,b) -> {
                        Join<CoursePartEntity, CourseEntity> entityJoin = r.join("courseByCourseId");
                        return b.like(entityJoin.get("name"), key);
                    });
//                    .or(r, q, b) -> b.like(r.join("ratingCommentsByRatingCommentId")).get("name"),key);
        }
        Page<CoursePartEntity> page = coursePartRepository.findAll(specs,pageable);
        return page;
    }

    @Override
    public List<CoursePartEntity> findAll() {

        return coursePartRepository.findAll();
    }

    @Override
    public List<CoursePartEntity> findByParentIdIsNotNull() {
        return null;
    }

    @Override
    public CoursePartEntity SaveorUpdate(CoursePartEntity coursepartentity) {
        try {
            return coursePartRepository.save(coursepartentity);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public CoursePartEntity findById(String id) {
        try {
            return coursePartRepository.findById(id).orElse(null);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public CoursePartEntity remove(CoursePartEntity coursepartentity) {
        try {
            coursePartRepository.delete(coursepartentity);
            return coursepartentity;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
