package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public boolean signin(String email, String password) {
        int count = adminRepository.countByEmailAndPassword(email, password);
        return count > 0;
    }

    public void registerUser(String lastName, String firstName, String email, String password) {
        Admin admin = new Admin();
        admin.setLastName(lastName);
        admin.setFirstName(firstName);
        admin.setEmail(email);
        admin.setPassword(password);
        adminRepository.save(admin);
    }
}
