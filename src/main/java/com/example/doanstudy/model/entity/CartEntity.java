package com.example.doanstudy.model.entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "CART", schema = "SYSTEM", catalog = "")
public class CartEntity {
    private String id;
    private Long price;
    private Long quantity;
    private String art;
    private String courseName;
    private Long totalDiscount;
    private Long totalPrice;
    private String statusPayment;
    private Time createdAt;
    private Time updatedAt;
    private UsersEntity usersByUserId;
    private CourseEntity courseByCourseId;

    @Id
    @Column(name = "ID", nullable = false, length = 255)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    @Column(name = "QUANTITY", nullable = true, precision = 0)
    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
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
    @Column(name = "COURSE_NAME", nullable = true, length = 255)
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Basic
    @Column(name = "TOTAL_DISCOUNT", nullable = true, precision = 0)
    public Long getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Long totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    @Basic
    @Column(name = "TOTAL_PRICE", nullable = true, precision = 0)
    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Basic
    @Column(name = "STATUS_PAYMENT", nullable = true, length = 255)
    public String getStatusPayment() {
        return statusPayment;
    }

    public void setStatusPayment(String statusPayment) {
        this.statusPayment = statusPayment;
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
        CartEntity that = (CartEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(price, that.price) && Objects.equals(quantity, that.quantity) && Objects.equals(art, that.art) && Objects.equals(courseName, that.courseName) && Objects.equals(totalDiscount, that.totalDiscount) && Objects.equals(totalPrice, that.totalPrice) && Objects.equals(statusPayment, that.statusPayment) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, quantity, art, courseName, totalDiscount, totalPrice, statusPayment, createdAt, updatedAt);
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID")
    public CourseEntity getCourseByCourseId() {
        return courseByCourseId;
    }

    public void setCourseByCourseId(CourseEntity courseByCourseId) {
        this.courseByCourseId = courseByCourseId;
    }
}
