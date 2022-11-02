package com.scaler.blogapp.comments;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.scaler.blogapp.articles.ArticleEntity;
import com.scaler.blogapp.users.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {

    /*
    ASSIGNMENT 04: Complete this entity class based on this
    https://github.com/scaleracademy/project-module-requirement-docs/blob/main/blogging-app/SCHEMA.md
     */

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Lob
    private String content;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) // TODO: not sure about ondelete action
    @JsonIgnore
    private UserEntity user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) // TODO: not sure about ondelete action
    @JsonIgnore
    private ArticleEntity article;
}
