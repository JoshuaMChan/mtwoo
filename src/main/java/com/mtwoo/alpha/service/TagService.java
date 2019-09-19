package com.mtwoo.alpha.service;

import com.mtwoo.alpha.domain.Post;
import com.mtwoo.alpha.domain.PostTag;
import com.mtwoo.alpha.domain.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TagService {
    boolean hasTagById(int id);
    boolean hasTagByName(String name);
    void saveTagWitPost(Tag tag, Post post);
    Tag getTagById(int id);
    Tag getTagByName(String name);
    void removeTagById(int id);
    Tag saveTag(Tag tag);

}
