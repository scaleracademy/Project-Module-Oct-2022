package com.scaler.authdemo.security.authtoken;

import com.scaler.authdemo.users.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "auth_tokens")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID token;

    @ManyToOne
    private UserEntity user;
}
