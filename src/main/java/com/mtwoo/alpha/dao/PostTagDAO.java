package com.mtwoo.alpha.dao;

import com.mtwoo.alpha.domain.Post;
import com.mtwoo.alpha.domain.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagDAO extends JpaRepository<PostTag, Integer> {
}
