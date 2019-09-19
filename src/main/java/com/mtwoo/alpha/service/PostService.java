package com.mtwoo.alpha.service;

import com.mtwoo.alpha.domain.Post;
import com.mtwoo.alpha.domain.PostTag;
import com.mtwoo.alpha.domain.Tag;

public interface PostService {

    boolean hasPostById(int id);
    Post savePost(Post post);
    Post getPostById(int id);
    void deleteById(int id);

    void savePostWitTag(Post post, Tag tag, PostTag postTag);
}
