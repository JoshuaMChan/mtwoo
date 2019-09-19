package com.mtwoo.alpha.controller;

import com.mtwoo.alpha.api.request.LoginRequest;
import com.mtwoo.alpha.api.request.SignUpRequest;
import com.mtwoo.alpha.api.response.JwtAuthenticationResponse;
import com.mtwoo.alpha.security.JwtUtil;
import com.mtwoo.alpha.dao.UserDAO;
import com.mtwoo.alpha.domain.User;
import com.mtwoo.alpha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
    }

    private  ResponseEntity<JwtAuthenticationResponse> authenticateUser(String email, String password){
        UsernamePasswordAuthenticationToken t = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(t);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.provideToken(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest newUser) {

        if (userService.hasUserByEmail(newUser.getEmail())) {
            return new ResponseEntity("Email used.", HttpStatus.CONFLICT);
        }

        User user = new User(newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));
        userService.saveUser(user);

        return authenticateUser(newUser.getEmail(), newUser.getPassword());
    }

}
