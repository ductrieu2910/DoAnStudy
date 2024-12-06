package com.example.doanstudy.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "LESSON", schema = "SYSTEM", catalog = "")
public class LessonEntity {
    private String id;
    private String path;
    private String name;
    private String length;
    private String questionNumber;
    private String lectureNumber;
    private String status;
    private Time createdAt;
    private Time updatedAt;
    private CourseEntity courseByCourseId;
    private CoursePartEntity coursePartByPartId;

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
    @Column(name = "PATH", nullable = true, length = 255)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
    @Column(name = "LENGTH", nullable = true, length = 50)
    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    @Basic
    @Column(name = "QUESTION_NUMBER", nullable = true, length = 525)
    public String getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }

    @Basic
    @Column(name = "LECTURE_NUMBER", nullable = true, length = 525)
    public String getLectureNumber() {
        return lectureNumber;
    }

    public void setLectureNumber(String lectureNumber) {
        this.lectureNumber = lectureNumber;
    }

    @Basic
    @Column(name = "STATUS", nullable = true, length = 255)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        LessonEntity that = (LessonEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(path, that.path) && Objects.equals(name, that.name) && Objects.equals(length, that.length) && Objects.equals(questionNumber, that.questionNumber) && Objects.equals(lectureNumber, that.lectureNumber) && Objects.equals(status, that.status) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path, name, length, questionNumber, lectureNumber, status, createdAt, updatedAt);
    }

    @ManyToOne
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID")
    public CourseEntity getCourseByCourseId() {
        return courseByCourseId;
    }

    public void setCourseByCourseId(CourseEntity courseByCourseId) {
        this.courseByCourseId = courseByCourseId;
    }

    @ManyToOne
    @JoinColumn(name = "PART_ID", referencedColumnName = "ID")
    public CoursePartEntity getCoursePartByPartId() {
        return coursePartByPartId;
    }

    public void setCoursePartByPartId(CoursePartEntity coursePartByPartId) {
        this.coursePartByPartId = coursePartByPartId;
    }
}
