package com.scaler.blogapp.users;

import com.scaler.blogapp.articles.ArticleEntity;
import com.scaler.blogapp.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "users")
public class UsersEntity extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany
    private Set<UsersEntity> followers;
    // todo: learn more about 'JoinTable'

    @ManyToMany(mappedBy = "fans")
    private Set<ArticleEntity> favourites;
}
