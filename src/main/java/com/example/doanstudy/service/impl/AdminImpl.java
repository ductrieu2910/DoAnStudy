package com.example.doanstudy.service.impl;

import com.example.doanstudy.model.entity.AdminEntity;
import com.example.doanstudy.repository.AdminRepository;
import com.example.doanstudy.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminImpl implements AdminService {

    private AdminRepository adminRepository;
    @Override
    public List<AdminEntity> findAllByUsername(String username) {
        try {
            return adminRepository.findByUsername("%" + username + "%");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public AdminEntity findByUserName(String username) {
        try {
            return adminRepository.findFirstByUsername(username);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public AdminEntity findByPassWord(String password) {
        try{
            return adminRepository.findByPassword(password);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminEntity entity = findByUserName(username);
        if (entity != null)
            return User.withUsername(entity.getUsername()).password(entity.getPassword()).roles().build();
        throw new UsernameNotFoundException("Username not found!");
    }
}
