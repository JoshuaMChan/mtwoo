package com.mtwoo.alpha.service;

import com.mtwoo.alpha.domain.Post;

import java.util.Date;

public interface PostService {

    Post getPostByUserIdAndCreatedDate(int userId, Date createdDate);
    Post savePost(Post post);
}
