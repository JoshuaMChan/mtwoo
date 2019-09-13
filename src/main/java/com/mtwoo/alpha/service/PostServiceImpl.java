package com.mtwoo.alpha.service;

import com.mtwoo.alpha.dao.PostRepository;
import com.mtwoo.alpha.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepository postRepository;

    @Override
    public Post getPostByUserIdAndCreatedDate(int userId, Date createdDate) {
        return postRepository.findByUserIdAndCreateDate(userId, createdDate);
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }
}
