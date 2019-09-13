package com.mtwoo.alpha.dao;

import com.mtwoo.alpha.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findByUserIdAndCreateDate(int userId, Date createdDate);
}
