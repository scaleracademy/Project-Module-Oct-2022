package com.scaler.blogapp.security.authtoken;

import com.scaler.blogapp.users.UsersEntity;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "auth_tokens")
public class AuthTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID token;

    @ManyToOne
    private UsersEntity user;
}
