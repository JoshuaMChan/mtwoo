package com.mtwoo.alpha.controller;

import com.mtwoo.alpha.api.request.LoginRequest;
import com.mtwoo.alpha.api.request.SignUpRequest;
import com.mtwoo.alpha.api.response.JwtAuthenticationResponse;
import com.mtwoo.alpha.api.response.SignUpResponse;
import com.mtwoo.alpha.config.security.JwtUtil;
import com.mtwoo.alpha.dao.UserRepository;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken t = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(t);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.provideToken(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest newUser) {

        if (userRepository.existsByEmail(newUser.getEmail())) {
            return new ResponseEntity(new SignUpResponse(false, "Email used"), HttpStatus.BAD_REQUEST);
        }
        User user = new User(newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));
        User res = userRepository.save(user);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/user?email={email}")
                .buildAndExpand(res.getEmail()).toUri();

        return ResponseEntity.created(uri).body(new SignUpResponse(true, "Sign up sucessfully"));
    }

}
