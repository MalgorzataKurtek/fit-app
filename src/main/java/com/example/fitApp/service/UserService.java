package com.example.fitApp.service;

import com.example.fitApp.dto.UserDTO;
import com.example.fitApp.entity.User;
import jakarta.transaction.Transactional;

@Transactional
public interface UserService {
    void saveUser(UserDTO userDTO);

    User findUserByEmail(String email);
}
