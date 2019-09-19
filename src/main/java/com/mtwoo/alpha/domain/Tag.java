package com.mtwoo.alpha.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "tag")
@Data
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    @OneToMany(mappedBy = "tag")
    private List<PostTag> postTag;

    public Tag(String name) {
        this.name = name;
    }
}
