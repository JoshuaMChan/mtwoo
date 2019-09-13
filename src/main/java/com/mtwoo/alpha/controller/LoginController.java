package com.mtwoo.alpha.controller;

import com.mtwoo.alpha.api.request.LoginRequest;
import com.mtwoo.alpha.api.request.SignUpRequest;
import com.mtwoo.alpha.api.response.JwtAuthenticationResponse;
import com.mtwoo.alpha.api.response.UserResponse;
import com.mtwoo.alpha.security.JwtProvider;
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
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    JwtProvider jwtProvider;

    //@GetMapping("/hello")
    @GetMapping("/")
    public String getUser(){
        System.out.println("?12");
        return userService.getUserByEmail("a@gmail.com").getEmail();
    }

    @PostMapping("/signin")
    public ResponseEntity<?>  authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken t = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(t);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.provideToken(authentication);


        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
}

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest newUser){

        if(userRepository.existsByEmail(newUser.getEmail())){
            return new ResponseEntity(new UserResponse(false, "Email used"), HttpStatus.BAD_REQUEST);
        }
        User user = new User(newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));
        //User user = new User(newUser.getEmail(), newUser.getPassword());
        User res = userRepository.save(user);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/users/{email}")
                .buildAndExpand(res.getEmail()).toUri();

        return ResponseEntity.created(uri).body(new UserResponse(true, "Sign up sucessfully"));
    }

}
