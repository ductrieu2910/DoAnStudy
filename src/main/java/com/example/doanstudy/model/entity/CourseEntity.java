package com.example.doanstudy.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import java.text.Normalizer;
import java.util.*;
import java.util.regex.Pattern;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "COURSE", schema = "SYSTEM", catalog = "")
public class CourseEntity {
    private String id;
    private String name;
    private String art;
    private String slug;
    private String description;
    private String content;
    private String timeSaleoff;
    private String duration;
    private Long lectures;
    private String language;
    private String skillLevel;
    private Time deadline;
    private String cartificate;
    private String readmore;
    private String require;
    private Long price;
    private Long percentSale;
    private String status;
    private Long totalRating;
    private Long totalStart;
    private Long totalReview;
    private Date createdAt;
    private Date updatedAt;
    private Collection<CartEntity> cartsById;
    private Collection<CartDetailEntity> cartDetailsById;
    private CategoriesEntity categoriesByCategoriesId;
    private RatingCommentsEntity ratingCommentsByRatingCommentId;
    private Collection<CoursePartEntity> coursePartsById;
    private Collection<InstructorEntity> instructorsById;
    private Collection<LessonEntity> lessonsById;
    private Collection<RatingCommentsEntity> ratingCommentsById;

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
        this.slug = generateSlug(name);
    }

    @Basic
    @Column(name = "ART", nullable = true, length = 255)
    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    @Basic
    @Column(name = "SLUG", nullable = true, length = 255)
    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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
    @Column(name = "CONTENT", nullable = true)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "TIME_SALEOFF", nullable = true, length = 255)
    public String getTimeSaleoff() {
        return timeSaleoff;
    }

    public void setTimeSaleoff(String timeSaleoff) {
        this.timeSaleoff = timeSaleoff;
    }

    @Basic
    @Column(name = "DURATION", nullable = true, length = 50)
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "LECTURES", nullable = true, precision = 0)
    public Long getLectures() {
        return lectures;
    }

    public void setLectures(Long lectures) {
        this.lectures = lectures;
    }

    @Basic
    @Column(name = "LANGUAGE", nullable = true, length = 50)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Basic
    @Column(name = "SKILL_LEVEL", nullable = true, length = 50)
    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    @Basic
    @Column(name = "DEADLINE", nullable = true)
    public Time getDeadline() {
        return deadline;
    }

    public void setDeadline(Time deadline) {
        this.deadline = deadline;
    }

    @Basic
    @Column(name = "CARTIFICATE", nullable = true, precision = 0)
    public String getCartificate() {
        return cartificate;
    }

    public void setCartificate(String cartificate) {
        this.cartificate = cartificate;
    }

    @Basic
    @Column(name = "READMORE", nullable = true, length = 255)
    public String getReadmore() {
        return readmore;
    }

    public void setReadmore(String readmore) {
        this.readmore = readmore;
    }

    @Basic
    @Column(name = "REQUIRE", nullable = true)
    public String getRequire() {
        return require;
    }

    public void setRequire(String require) {
        this.require = require;
    }

    @Basic
    @Column(name = "PRICE", nullable = true, precision = 0)
    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Basic
    @Column(name = "PERCENT_SALE", nullable = true, precision = 0)
    public Long getPercentSale() {
        return percentSale;
    }

    public void setPercentSale(Long percentSale) {
        this.percentSale = percentSale;
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
    @Column(name = "TOTAL_RATING", nullable = true, precision = 0)
    public Long getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(Long totalRating) {
        this.totalRating = totalRating;
    }

    @Basic
    @Column(name = "TOTAL_START", nullable = true, precision = 0)
    public Long getTotalStart() {
        return totalStart;
    }

    public void setTotalStart(Long totalStart) {
        this.totalStart = totalStart;
    }

    @Basic
    @Column(name = "TOTAL_REVIEW", nullable = true, precision = 0)
    public Long getTotalReview() {
        return totalReview;
    }

    public void setTotalReview(Long totalReview) {
        this.totalReview = totalReview;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT", nullable = true)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {

        this.createdAt = createdAt;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_AT", nullable = true)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {

        this.updatedAt = updatedAt;
    }
    // Phương thức để tạo slug tự động
    private String generateSlug(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        // Loại bỏ dấu tiếng Việt
        String slug = Normalizer.normalize(input, Normalizer.Form.NFD);
        slug = slug.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        // Loại bỏ các ký tự đặc biệt và thay khoảng trắng bằng dấu '-'
        slug = slug.replaceAll("[^a-zA-Z0-9\\s]", "").trim().replaceAll("\\s+", "-");
        return slug.toLowerCase();
    }

    // Tự động tạo và cập nhật slug trước khi lưu vào database
    @PrePersist
    public void prePersist() {
        Date now = new Date();
        this.createdAt = now;
        this.updatedAt = now;

        // Tạo slug nếu chưa có
        if (this.slug == null || this.slug.isEmpty()) {
            this.slug = generateSlug(this.name);
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Date();
        // Cập nhật lại slug khi cập nhật tên
        if (this.name != null) {
            this.slug = generateSlug(this.name);
        }
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEntity that = (CourseEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(art, that.art) && Objects.equals(slug, that.slug) && Objects.equals(description, that.description) && Objects.equals(content, that.content) && Objects.equals(timeSaleoff, that.timeSaleoff) && Objects.equals(duration, that.duration) && Objects.equals(lectures, that.lectures) && Objects.equals(language, that.language) && Objects.equals(skillLevel, that.skillLevel) && Objects.equals(deadline, that.deadline) && Objects.equals(cartificate, that.cartificate) && Objects.equals(readmore, that.readmore) && Objects.equals(require, that.require) && Objects.equals(price, that.price) && Objects.equals(percentSale, that.percentSale) && Objects.equals(status, that.status) && Objects.equals(totalRating, that.totalRating) && Objects.equals(totalStart, that.totalStart) && Objects.equals(totalReview, that.totalReview) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, art, slug, description, content, timeSaleoff, duration, lectures, language, skillLevel, deadline, cartificate, readmore, require, price, percentSale, status, totalRating, totalStart, totalReview, createdAt, updatedAt);
    }

    @OneToMany(mappedBy = "courseByCourseId")
    public Collection<CartEntity> getCartsById() {
        return cartsById;
    }

    public void setCartsById(Collection<CartEntity> cartsById) {
        this.cartsById = cartsById;
    }

    @OneToMany(mappedBy = "courseByCourseId")
    public Collection<CartDetailEntity> getCartDetailsById() {
        return cartDetailsById;
    }
    public void setCartDetailsById(Collection<CartDetailEntity> cartDetailsById) {
        this.cartDetailsById = cartDetailsById;
    }

    @ManyToOne
    @JoinColumn(name = "CATEGORIES_ID", referencedColumnName = "ID")
    public CategoriesEntity getCategoriesByCategoriesId() {
        return categoriesByCategoriesId;
    }
    public void setCategoriesByCategoriesId(CategoriesEntity categoriesByCategoriesId) {
        this.categoriesByCategoriesId = categoriesByCategoriesId;
    }

    @ManyToOne
    @JoinColumn(name = "RATING_COMMENT_ID", referencedColumnName = "ID")
    public RatingCommentsEntity getRatingCommentsByRatingCommentId() {
        return ratingCommentsByRatingCommentId;
    }

    public void setRatingCommentsByRatingCommentId(RatingCommentsEntity ratingCommentsByRatingCommentId) {
        this.ratingCommentsByRatingCommentId = ratingCommentsByRatingCommentId;
    }

    @OneToMany(mappedBy = "courseByCourseId")
    public Collection<CoursePartEntity> getCoursePartsById() {
        return coursePartsById;
    }

    public void setCoursePartsById(Collection<CoursePartEntity> coursePartsById) {
        this.coursePartsById = coursePartsById;
    }

    @OneToMany(mappedBy = "courseByCourseId")
    public Collection<InstructorEntity> getInstructorsById() {
        return instructorsById;
    }

    public void setInstructorsById(Collection<InstructorEntity> instructorsById) {
        this.instructorsById = instructorsById;
    }

    @OneToMany(mappedBy = "courseByCourseId")
    public Collection<LessonEntity> getLessonsById() {
        return lessonsById;
    }

    public void setLessonsById(Collection<LessonEntity> lessonsById) {
        this.lessonsById = lessonsById;
    }

    @OneToMany(mappedBy = "courseByCourseId")
    public Collection<RatingCommentsEntity> getRatingCommentsById() {
        return ratingCommentsById;
    }

    public void setRatingCommentsById(Collection<RatingCommentsEntity> ratingCommentsById) {
        this.ratingCommentsById = ratingCommentsById;
    }
}
