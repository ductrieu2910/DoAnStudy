package com.example.doanstudy.service;


import com.example.doanstudy.model.entity.LessonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LessionService {
    Page<LessonEntity> findAll(String search, int pageNumber);
    List<LessonEntity> findAll();
    LessonEntity saveOrUpdate(LessonEntity lessonEntity);
    LessonEntity findById(String id);
    LessonEntity remove (LessonEntity lessonEntity);
}
