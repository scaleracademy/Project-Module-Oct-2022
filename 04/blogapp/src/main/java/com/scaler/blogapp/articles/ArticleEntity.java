package com.scaler.blogapp.articles;

import com.scaler.blogapp.comments.CommentEntity;
import com.scaler.blogapp.users.UserEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "articles")
@Data
public class ArticleEntity {

    /*
    ASSIGNMENT 04: Complete this entity class based on this
    https://github.com/scaleracademy/project-module-requirement-docs/blob/main/blogging-app/SCHEMA.md
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "article_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity author;

    @OneToMany(mappedBy = "commentId")
    List<CommentEntity> comments;

    @ManyToMany
            @JoinTable(name = "likes",
                    joinColumns = @JoinColumn(name = "article_id"),
                    inverseJoinColumns = @JoinColumn(name = "user_id"))
    List<UserEntity> likedUsers;
}
