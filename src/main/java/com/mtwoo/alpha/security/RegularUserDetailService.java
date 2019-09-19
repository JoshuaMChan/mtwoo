package com.mtwoo.alpha.security;

import com.mtwoo.alpha.dao.UserDAO;
import com.mtwoo.alpha.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RegularUserDetailService implements UserDetailsService {
    @Autowired
    UserDAO userDAO;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email){
        User user = userDAO.findByEmail(email);
        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(int id){
        User user = userDAO.findById(id).orElseThrow(()-> new UsernameNotFoundException("Username" + id + " not found"));
        return UserPrincipal.create(user);
    }
}
