package com.scaler.blogapp.articles;

import com.scaler.blogapp.BaseModel;
import com.scaler.blogapp.comments.CommentEntity;
import com.scaler.blogapp.users.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleEntity extends BaseModel {

    /*
    ASSIGNMENT 04: Complete this entity class based on this
    https://github.com/scaleracademy/project-module-requirement-docs/blob/main/blogging-app/SCHEMA.md
     */
    @Column(nullable=false)
    String Name;

    @Column(nullable=false)
    String Content;

    @ManyToMany
    List<UserEntity> likedUsers;

    @OneToMany
    List<CommentEntity> comments;

    @ManyToOne
    @Column(nullable = false)
    UserEntity author;
}
