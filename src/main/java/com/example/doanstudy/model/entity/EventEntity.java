package com.example.doanstudy.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "EVENT", schema = "SYSTEM", catalog = "")
public class EventEntity {
    private String id;
    private String name;
    private Time eventDate;
    private Timestamp eventTime;
    private String address;
    private Timestamp countTime;
    private String art;
    private String title;
    private String description;
    private String timeSaleoff;
    private Long bookSlot;
    private Long totalSlot;
    private String readmore;
    private String requite;
    private String speakers;
    private Long price;
    private Long salePrice;
    private Long percentSale;
    private String status;
    private Long totalRating;
    private Long totalStart;
    private Long totalReview;
    private Time createdAt;
    private Time updatedAt;
    private CategoriesEntity categoriesByCategoriesId;
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
    @Column(name = "EVENT_DATE", nullable = true)
    public Time getEventDate() {
        return eventDate;
    }

    public void setEventDate(Time eventDate) {
        this.eventDate = eventDate;
    }

    @Basic
    @Column(name = "EVENT_TIME", nullable = true)
    public Timestamp getEventTime() {
        return eventTime;
    }

    public void setEventTime(Timestamp eventTime) {
        this.eventTime = eventTime;
    }

    @Basic
    @Column(name = "ADDRESS", nullable = true, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "COUNT_TIME", nullable = true)
    public Timestamp getCountTime() {
        return countTime;
    }

    public void setCountTime(Timestamp countTime) {
        this.countTime = countTime;
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
    @Column(name = "TITLE", nullable = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    @Column(name = "TIME_SALEOFF", nullable = true, length = 255)
    public String getTimeSaleoff() {
        return timeSaleoff;
    }

    public void setTimeSaleoff(String timeSaleoff) {
        this.timeSaleoff = timeSaleoff;
    }

    @Basic
    @Column(name = "BOOK_SLOT", nullable = true, precision = 0)
    public Long getBookSlot() {
        return bookSlot;
    }

    public void setBookSlot(Long bookSlot) {
        this.bookSlot = bookSlot;
    }

    @Basic
    @Column(name = "TOTAL_SLOT", nullable = true, precision = 0)
    public Long getTotalSlot() {
        return totalSlot;
    }

    public void setTotalSlot(Long totalSlot) {
        this.totalSlot = totalSlot;
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
    @Column(name = "REQUITE", nullable = true)
    public String getRequite() {
        return requite;
    }

    public void setRequite(String requite) {
        this.requite = requite;
    }

    @Basic
    @Column(name = "SPEAKERS", nullable = true, length = 255)
    public String getSpeakers() {
        return speakers;
    }

    public void setSpeakers(String speakers) {
        this.speakers = speakers;
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
    @Column(name = "SALE_PRICE", nullable = true, precision = 0)
    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
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
        EventEntity that = (EventEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(eventDate, that.eventDate) && Objects.equals(eventTime, that.eventTime) && Objects.equals(address, that.address) && Objects.equals(countTime, that.countTime) && Objects.equals(art, that.art) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(timeSaleoff, that.timeSaleoff) && Objects.equals(bookSlot, that.bookSlot) && Objects.equals(totalSlot, that.totalSlot) && Objects.equals(readmore, that.readmore) && Objects.equals(requite, that.requite) && Objects.equals(speakers, that.speakers) && Objects.equals(price, that.price) && Objects.equals(salePrice, that.salePrice) && Objects.equals(percentSale, that.percentSale) && Objects.equals(status, that.status) && Objects.equals(totalRating, that.totalRating) && Objects.equals(totalStart, that.totalStart) && Objects.equals(totalReview, that.totalReview) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, eventDate, eventTime, address, countTime, art, title, description, timeSaleoff, bookSlot, totalSlot, readmore, requite, speakers, price, salePrice, percentSale, status, totalRating, totalStart, totalReview, createdAt, updatedAt);
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
}
