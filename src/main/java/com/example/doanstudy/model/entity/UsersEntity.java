package com.example.doanstudy.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Time;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "USERS", schema = "SYSTEM", catalog = "")
public class UsersEntity {
    private String id;
    private String name;
    private String email;
    private String password;
    private String gender;
    private String phone;
    private String address;
    private String forgetToken;
    private String activeToken;
    private String status;
    private Time createdAt;
    private Time updatedAt;
    private Collection<CartEntity> cartsById;
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
    }

    @Basic
    @Column(name = "EMAIL", nullable = true, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = true, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "GENDER", nullable = true, length = 10)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "PHONE", nullable = true, length = 15)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
    @Column(name = "FORGET_TOKEN", nullable = true, length = 255)
    public String getForgetToken() {
        return forgetToken;
    }

    public void setForgetToken(String forgetToken) {
        this.forgetToken = forgetToken;
    }

    @Basic
    @Column(name = "ACTIVE_TOKEN", nullable = true, length = 255)
    public String getActiveToken() {
        return activeToken;
    }

    public void setActiveToken(String activeToken) {
        this.activeToken = activeToken;
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
        UsersEntity that = (UsersEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(gender, that.gender) && Objects.equals(phone, that.phone) && Objects.equals(address, that.address) && Objects.equals(forgetToken, that.forgetToken) && Objects.equals(activeToken, that.activeToken) && Objects.equals(status, that.status) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, gender, phone, address, forgetToken, activeToken, status, createdAt, updatedAt);
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<CartEntity> getCartsById() {
        return cartsById;
    }

    public void setCartsById(Collection<CartEntity> cartsById) {
        this.cartsById = cartsById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<RatingCommentsEntity> getRatingCommentsById() {
        return ratingCommentsById;
    }

    public void setRatingCommentsById(Collection<RatingCommentsEntity> ratingCommentsById) {
        this.ratingCommentsById = ratingCommentsById;
    }
}
