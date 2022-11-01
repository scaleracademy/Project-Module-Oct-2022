package com.scaler.blogapp.comments;

import com.scaler.blogapp.BaseModel;
import com.scaler.blogapp.articles.ArticleEntity;
import com.scaler.blogapp.users.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity extends BaseModel {

    /*
    ASSIGNMENT 04: Complete this entity class based on this
    https://github.com/scaleracademy/project-module-requirement-docs/blob/main/blogging-app/SCHEMA.md
     */
    @Column(nullable = false)
    String text;

    @ManyToOne
    @Column(nullable = false)
    ArticleEntity article;


    @ManyToOne
    @Column(nullable = false)
    UserEntity author;


}
