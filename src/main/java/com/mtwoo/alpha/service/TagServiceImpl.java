package com.mtwoo.alpha.service;

import com.mtwoo.alpha.dao.PostTagDAO;
import com.mtwoo.alpha.dao.TagDAO;
import com.mtwoo.alpha.domain.Post;
import com.mtwoo.alpha.domain.PostTag;
import com.mtwoo.alpha.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    TagDAO tagDAO;

    @Autowired
    PostTagDAO postTagDAO;

    @Override
    public boolean hasTagById(int id) {
        return tagDAO.existsById(id);
    }

    @Override
    public boolean hasTagByName(String name) {
        return tagDAO.existsByName(name);
    }

    @Override
    public void saveTagWitPost(Tag tag, Post post) {
        if(!tagDAO.existsByName(tag.getName()))
            tagDAO.save(tag);
        else{
            tag = tagDAO.findByName(tag.getName());
        }
        postTagDAO.save(new PostTag(post, tag));
    }

    @Override
    public Tag getTagById(int id) {
        return tagDAO.getOne(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDAO.findByName(name);
    }

    @Override
    public void removeTagById(int id) {
        tagDAO.deleteById(id);
    }

    @Override
    public Tag saveTag(Tag tag) {
        if(tagDAO.existsByName(tag.getName()))
            return tagDAO.save(tag);
        return null;
    }
}
