package com.mtwoo.alpha.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "post")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String context;

    @CreatedDate
    @Column(name="created_date")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
