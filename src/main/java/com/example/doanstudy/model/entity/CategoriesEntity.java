package com.example.doanstudy.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CATEGORIES", schema = "SYSTEM", catalog = "")
public class CategoriesEntity {
    private String id;
    private String name;
    private String description;
    private String parentId;
    private Time createdAt;
    private Time updatedAt;
    private Collection<CourseEntity> coursesById;
    private Collection<EventEntity> eventsById;

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
    @Column(name = "DESCRIPTION", nullable = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "PARENT_ID", nullable = true, length = 255)
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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
        CategoriesEntity that = (CategoriesEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(parentId, that.parentId) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, parentId, createdAt, updatedAt);
    }

    @OneToMany(mappedBy = "categoriesByCategoriesId")
    public Collection<CourseEntity> getCoursesById() {
        return coursesById;
    }
    public void setCoursesById(Collection<CourseEntity> coursesById) {
        this.coursesById = coursesById;
    }


    @OneToMany(mappedBy = "categoriesByCategoriesId")
    public Collection<EventEntity> getEventsById() {
        return eventsById;
    }

    public void setEventsById(Collection<EventEntity> eventsById) {
        this.eventsById = eventsById;
    }



    @ManyToOne
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private CategoriesEntity parentCategory;

}
