package com.example.doanstudy.service;


import com.example.doanstudy.model.entity.AdminEntity;
import com.example.doanstudy.service.impl.AdminImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AdminService extends UserDetailsService {
    List<AdminEntity> findAllByUsername(String username);
    AdminEntity findByUserName(String username);
    AdminEntity findByPassWord(String password);
}
