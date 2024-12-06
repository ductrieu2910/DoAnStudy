package com.example.doanstudy.service.impl;

import com.example.doanstudy.model.entity.CategoriesEntity;
import com.example.doanstudy.model.entity.CourseEntity;
import com.example.doanstudy.model.entity.CoursePartEntity;
import com.example.doanstudy.model.entity.LessonEntity;
import com.example.doanstudy.repository.LessionRepository;
import com.example.doanstudy.service.LessionService;
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
public class LessionImpl implements LessionService {
    private final LessionRepository lessionRepository;


    @Override
    public Page<LessonEntity> findAll(String search, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber -1, 8);
        Specification<LessonEntity> specs = Specification.where(null);
        if(Objects.nonNull(search)){
            final String key = "%"+search+"%";
            specs = specs.or((r, q, b) -> b.like(r.get("name"),key))
                    .or((r,q,b) -> {
                        Join<LessonEntity, CoursePartEntity> entityJoin = r.join("coursePartByPartId");
                        return b.like(entityJoin.get("name"), key);
                    });
//                    .or(r, q, b) -> b.like(r.join("ratingCommentsByRatingCommentId")).get("name"),key);
        }
        Page<LessonEntity> page = lessionRepository.findAll(specs,pageable);
        return page;
    }

    @Override
    public List<LessonEntity> findAll() {

        return lessionRepository.findAll();
    }


    @Override
    public LessonEntity saveOrUpdate(LessonEntity lessonEntity) {

        try {
            return lessionRepository.save(lessonEntity);
        }catch (Exception ex){
            ex.printStackTrace();
        }return null;
    }

    @Override
    public LessonEntity findById(String id) {
        try {
            return lessionRepository.findById(id).orElse(null);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public LessonEntity remove(LessonEntity lessonEntity) {
        try {
            lessionRepository.delete(lessonEntity);
            return lessonEntity;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
