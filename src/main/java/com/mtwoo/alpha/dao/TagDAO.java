package com.mtwoo.alpha.dao;

import com.mtwoo.alpha.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagDAO extends JpaRepository<Tag, Integer> {
    Tag findByName(String name);
    boolean existsByName(String name);
}
