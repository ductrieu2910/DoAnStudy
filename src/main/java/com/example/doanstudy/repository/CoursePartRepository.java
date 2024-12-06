package com.example.doanstudy.repository;


;
import com.example.doanstudy.model.entity.CoursePartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CoursePartRepository extends JpaRepository<CoursePartEntity, String>, JpaSpecificationExecutor<CoursePartEntity> {
}
