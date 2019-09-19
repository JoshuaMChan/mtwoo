package com.mtwoo.alpha.service;

import com.mtwoo.alpha.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    boolean hasUserByEmail(String email);
    boolean hasUserById(int id);
    User saveUser(User user);
    User getUserByEmail(String email);
}
