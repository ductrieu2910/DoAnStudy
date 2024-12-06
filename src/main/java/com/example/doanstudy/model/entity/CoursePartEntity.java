package com.example.doanstudy.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Time;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "COURSE_PART", schema = "SYSTEM", catalog = "")
public class CoursePartEntity {
    private String id;
    private String name;
    private Time createdAt;
    private Time updatedAt;
    private CourseEntity courseByCourseId;
    private Collection<LessonEntity> lessonsById;

    @Id
    @UuidGenerator
    @Column(name = "ID", nullable = false, length = 255)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "CREATED_AT", nullable = true)
    public Time getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Time createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "UPDATED_AT", nullable = true)
    public Time getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Time updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoursePartEntity that = (CoursePartEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createdAt, updatedAt);
    }

    @ManyToOne
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID")
    public CourseEntity getCourseByCourseId() {
        return courseByCourseId;
    }

    public void setCourseByCourseId(CourseEntity courseByCourseId) {
        this.courseByCourseId = courseByCourseId;
    }

    @OneToMany(mappedBy = "coursePartByPartId")
    public Collection<LessonEntity> getLessonsById() {
        return lessonsById;
    }

    public void setLessonsById(Collection<LessonEntity> lessonsById) {
        this.lessonsById = lessonsById;
    }
}
