package com.scaler.blogapp.articles;

import com.scaler.blogapp.comments.CommentEntity;
import com.scaler.blogapp.common.BaseEntity;
import com.scaler.blogapp.users.UsersEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "articles")
public class ArticleEntity extends BaseEntity {

    private String slug;
    private String title;
    private String description;
    private String body;

    @ManyToOne
    private UsersEntity author;

    @OneToMany(mappedBy = "article")
    private List<CommentEntity> comments;

    @ManyToMany
    private Set<UsersEntity> fans;
}
