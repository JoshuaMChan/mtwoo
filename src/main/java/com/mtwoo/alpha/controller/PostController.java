package com.mtwoo.alpha.controller;

import com.mtwoo.alpha.api.request.PostRequest;
import com.mtwoo.alpha.api.response.PostResponse;
import com.mtwoo.alpha.dao.PostRepository;
import com.mtwoo.alpha.domain.Post;
import com.mtwoo.alpha.service.PostService;
import com.mtwoo.alpha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @PostMapping("/")
    public ResponseEntity<?> post(@Valid @RequestBody PostRequest postRequest){
        Post p = new Post();
        p.setTitle(postRequest.getTitle());
        p.setContext(postRequest.getContext());
        p.setUser(userService.getUserByEmail(postRequest.getUserEmail()));
        System.out.println(p.getUser().getId());
        postService.savePost(p);
        return ResponseEntity.ok(new PostResponse(true, "Posted Successfully."));
    }
}
