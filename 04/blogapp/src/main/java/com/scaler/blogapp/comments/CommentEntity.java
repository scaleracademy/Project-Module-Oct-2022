package com.scaler.blogapp.comments;

import com.scaler.blogapp.articles.ArticleEntity;
import com.scaler.blogapp.common.BaseEntity;
import com.scaler.blogapp.users.UsersEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "comments")
public class CommentEntity extends BaseEntity {
    @Column(nullable = false)
    private String body;

    @ManyToOne
    private UsersEntity commenter;

    @ManyToOne
    private ArticleEntity article;
}
