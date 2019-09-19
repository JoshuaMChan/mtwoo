package com.mtwoo.alpha.controller;

import com.mtwoo.alpha.api.request.TagRequest;
import com.mtwoo.alpha.api.response.TagResponse;
import com.mtwoo.alpha.domain.Post;
import com.mtwoo.alpha.domain.Tag;
import com.mtwoo.alpha.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getTag(@RequestParam int id){
        if(!tagService.hasTagById(id)) return new ResponseEntity<>("Tag not found", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(new TagResponse(id, tagService.getTagById(id).getName()));
    }

    @PostMapping
    public ResponseEntity<?> saveTag(@Valid @RequestBody TagRequest tagRequest){
        if(!tagService.hasTagByName(tagRequest.getName()))return new ResponseEntity<>("Tag existed", HttpStatus.CONFLICT);
        return ResponseEntity.ok(new TagResponse());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeTag(@RequestParam int id){
        if(!tagService.hasTagById(id)) return new ResponseEntity<>("Tag not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
