package com.mtwoo.alpha.service;

import com.mtwoo.alpha.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getUserByEmail(String email);
}
