package com.example.doanstudy.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "INSTRUCTOR", schema = "SYSTEM", catalog = "")
public class InstructorEntity {
    private String id;
    private String name;
    private String field;
    private Long courseNumber;
    private Long studentNumber;
    private Long reviewNumber;
    private Long ratingNumber;
    private String description;
    private String introduce;
    private Time createdAt;
    private Time updatedAt;
    private CourseEntity courseByCourseId;
    private RatingCommentsEntity ratingCommentsByRatingCommentId;

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
    @Column(name = "FIELD", nullable = true)
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Basic
    @Column(name = "COURSE_NUMBER", nullable = true, precision = 0)
    public Long getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Long courseNumber) {
        this.courseNumber = courseNumber;
    }

    @Basic
    @Column(name = "STUDENT_NUMBER", nullable = true, precision = 0)
    public Long getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Long studentNumber) {
        this.studentNumber = studentNumber;
    }

    @Basic
    @Column(name = "REVIEW_NUMBER", nullable = true, precision = 0)
    public Long getReviewNumber() {
        return reviewNumber;
    }

    public void setReviewNumber(Long reviewNumber) {
        this.reviewNumber = reviewNumber;
    }

    @Basic
    @Column(name = "RATING_NUMBER", nullable = true, precision = 0)
    public Long getRatingNumber() {
        return ratingNumber;
    }

    public void setRatingNumber(Long ratingNumber) {
        this.ratingNumber = ratingNumber;
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
    @Column(name = "INTRODUCE", nullable = true, length = 255)
    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
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
        InstructorEntity that = (InstructorEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(field, that.field) && Objects.equals(courseNumber, that.courseNumber) && Objects.equals(studentNumber, that.studentNumber) && Objects.equals(reviewNumber, that.reviewNumber) && Objects.equals(ratingNumber, that.ratingNumber) && Objects.equals(description, that.description) && Objects.equals(introduce, that.introduce) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, field, courseNumber, studentNumber, reviewNumber, ratingNumber, description, introduce, createdAt, updatedAt);
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
    @JoinColumn(name = "RATING_COMMENT_ID", referencedColumnName = "ID")
    public RatingCommentsEntity getRatingCommentsByRatingCommentId() {
        return ratingCommentsByRatingCommentId;
    }

    public void setRatingCommentsByRatingCommentId(RatingCommentsEntity ratingCommentsByRatingCommentId) {
        this.ratingCommentsByRatingCommentId = ratingCommentsByRatingCommentId;
    }
}
