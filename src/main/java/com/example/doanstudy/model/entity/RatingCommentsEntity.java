package com.example.doanstudy.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Time;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "RATING_COMMENTS", schema = "SYSTEM", catalog = "")
public class RatingCommentsEntity {
    private String id;
    private Long type;
    private String content;
    private Long numberStars;
    private Long numberReview;
    private String status;
    private Time createdAt;
    private Time updatedAt;
    private Collection<CourseEntity> coursesById;
    private Collection<EventEntity> eventsById;
    private Collection<InstructorEntity> instructorsById;
    private CourseEntity courseByCourseId;
    private UsersEntity usersByUserId;

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
    @Column(name = "TYPE", nullable = true, precision = 0)
    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    @Basic
    @Column(name = "CONTENT", nullable = true)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "NUMBER_STARS", nullable = true, precision = 0)
    public Long getNumberStars() {
        return numberStars;
    }

    public void setNumberStars(Long numberStars) {
        this.numberStars = numberStars;
    }

    @Basic
    @Column(name = "NUMBER_REVIEW", nullable = true, precision = 0)
    public Long getNumberReview() {
        return numberReview;
    }

    public void setNumberReview(Long numberReview) {
        this.numberReview = numberReview;
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
        RatingCommentsEntity that = (RatingCommentsEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(type, that.type) && Objects.equals(content, that.content) && Objects.equals(numberStars, that.numberStars) && Objects.equals(numberReview, that.numberReview) && Objects.equals(status, that.status) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, content, numberStars, numberReview, status, createdAt, updatedAt);
    }

    @OneToMany(mappedBy = "ratingCommentsByRatingCommentId")
    public Collection<CourseEntity> getCoursesById() {
        return coursesById;
    }

    public void setCoursesById(Collection<CourseEntity> coursesById) {
        this.coursesById = coursesById;
    }

    @OneToMany(mappedBy = "ratingCommentsByRatingCommentId")
    public Collection<EventEntity> getEventsById() {
        return eventsById;
    }

    public void setEventsById(Collection<EventEntity> eventsById) {
        this.eventsById = eventsById;
    }

    @OneToMany(mappedBy = "ratingCommentsByRatingCommentId")
    public Collection<InstructorEntity> getInstructorsById() {
        return instructorsById;
    }

    public void setInstructorsById(Collection<InstructorEntity> instructorsById) {
        this.instructorsById = instructorsById;
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
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }
}
