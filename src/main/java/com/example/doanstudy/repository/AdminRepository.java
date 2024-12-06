package com.example.doanstudy.repository;

import com.example.doanstudy.model.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity , String> , JpaSpecificationExecutor<AdminEntity> {
    AdminEntity findFirstByUsername( String username);

    @Query(value = "select u from AdminEntity u where u.username like ?1")
    List<AdminEntity> findByUsername(String username);

    AdminEntity findByPassword(String password);
}
