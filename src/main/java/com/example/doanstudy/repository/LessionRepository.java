package com.example.doanstudy.repository;


import com.example.doanstudy.model.entity.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LessionRepository extends JpaRepository<LessonEntity, String>, JpaSpecificationExecutor<LessonEntity> {
}
