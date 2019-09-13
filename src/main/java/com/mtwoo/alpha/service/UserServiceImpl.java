package com.mtwoo.alpha.service;

import com.mtwoo.alpha.dao.UserRepository;
import com.mtwoo.alpha.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
