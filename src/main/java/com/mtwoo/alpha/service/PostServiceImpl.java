package com.mtwoo.alpha.service;

import com.mtwoo.alpha.dao.PostDAO;
import com.mtwoo.alpha.dao.PostTagDAO;
import com.mtwoo.alpha.dao.TagDAO;
import com.mtwoo.alpha.domain.Post;
import com.mtwoo.alpha.domain.PostTag;
import com.mtwoo.alpha.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostDAO postDAO;

    @Autowired
    TagDAO tagDAO;

    @Autowired
    PostTagDAO postTagDAO;

    @Override
    public boolean hasPostById(int id) {
        return  postDAO.existsById(id);
    }

    @Override
    public Post savePost(Post post) {
        return postDAO.save(post);
    }

    @Override
    public Post getPostById(int id) {
        return postDAO.getOne(id);
    }

    @Override
    public void deleteById(int id) {
        postDAO.deleteById(id);
    }

    @Override
    public void savePostWitTag(Post post, Tag tag, PostTag postTag) {
        postDAO.save(post);
        if(!tagDAO.existsByName(tag.getName())) tagDAO.save(tag);
        postTagDAO.save(postTag);
    }
}
