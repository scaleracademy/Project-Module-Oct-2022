package com.scaler.blogapp.users;

import com.scaler.blogapp.BaseModel;
import com.scaler.blogapp.articles.ArticleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseModel {

    /*
    ASSIGNMENT 04: Complete this entity class based on this
    https://github.com/scaleracademy/project-module-requirement-docs/blob/main/blogging-app/SCHEMA.md
     */
    @Column(nullable = false)
    String name;

    @OneToMany(mappedBy = "author")
    List<ArticleEntity> userArticles;


    @ManyToMany
    List<UserEntity> followers;

    @ManyToMany
    List<UserEntity> followee;

    @ManyToMany(mappedBy = "likedUsers")
    List<ArticleEntity> likedArticles;

}
