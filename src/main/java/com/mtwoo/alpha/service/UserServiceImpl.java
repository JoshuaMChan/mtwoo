package com.mtwoo.alpha.service;

import com.mtwoo.alpha.dao.UserDAO;
import com.mtwoo.alpha.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDAO userDAO;

    @Override
    public boolean hasUserByEmail(String email) {
        return userDAO.existsByEmail(email);
    }

    @Override
    public boolean hasUserById(int id) {
        return userDAO.existsById(id);
    }

    @Override
    public User saveUser(User user) {
        return userDAO.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.findByEmail(email);
    }
}
