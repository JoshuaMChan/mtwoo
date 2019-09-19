package com.mtwoo.alpha.controller;

import com.mtwoo.alpha.api.request.PostRequest;
import com.mtwoo.alpha.api.response.PostResponse;
import com.mtwoo.alpha.dao.PostDAO;
import com.mtwoo.alpha.dao.UserDAO;
import com.mtwoo.alpha.domain.Post;
import com.mtwoo.alpha.domain.Tag;
import com.mtwoo.alpha.service.PostService;
import com.mtwoo.alpha.service.TagService;
import com.mtwoo.alpha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Secured({"USER"})
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    TagService tagService;

    @GetMapping("/{id}")
    public ResponseEntity<?> readPost(@RequestParam("id") Integer id){
        if(!postService.hasPostById(id)) return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(new PostResponse(post.getId(), post.getTitle(), post.getContext(), post.getUser().getId()));
    }

    @PostMapping
    public ResponseEntity<?>  newPost(@Valid @RequestBody PostRequest postRequest){
        if(!userService.hasUserByEmail(postRequest.getEmail())) return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        Post post = new Post(postRequest.getTitle(), postRequest.getContext(), userService.getUserByEmail(postRequest.getEmail()));
        postService.savePost(post);
        for(String tagName: postRequest.getTags()){
            tagService.saveTagWitPost(new Tag(tagName), post);
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>  editPost(@Valid @RequestBody PostRequest postRequest, @RequestParam("id") int id){
        if(!userService.hasUserById(id)) return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        Post post = new Post(postRequest.getTitle(), postRequest.getContext(), userService.getUserByEmail(postRequest.getEmail()));
        postService.savePost(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removePost(@PathVariable("id") Integer id) {
        if(!postService.hasPostById(id)) return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
        postService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

