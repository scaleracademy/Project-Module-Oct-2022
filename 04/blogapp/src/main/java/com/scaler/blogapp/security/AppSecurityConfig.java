package com.scaler.blogapp.security;

import com.scaler.blogapp.security.jwt.JwtAuthenticationFilter;
import com.scaler.blogapp.security.jwt.JwtAuthenticationManager;
import com.scaler.blogapp.security.jwt.JwtService;
import com.scaler.blogapp.security.sst.SstAuthenticationFilter;
import com.scaler.blogapp.security.sst.SstService;
import com.scaler.blogapp.security.sst.sstAuthenticationManager;
import com.scaler.blogapp.users.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;


@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    private SstAuthenticationFilter sstAuthenticationFilter;

    public AppSecurityConfig(JwtService jwtService, SstService sstService, UsersService usersService) {
        jwtAuthenticationFilter = new JwtAuthenticationFilter(
                new JwtAuthenticationManager(jwtService, usersService)
        );
        sstAuthenticationFilter = new SstAuthenticationFilter(
                new sstAuthenticationManager(sstService, usersService)
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // todo: Enable back CORS and CSRF in prod (deployed as a website-accessed-by-public)
        http.cors().disable().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/about").permitAll()
                .antMatchers(HttpMethod.POST, "/users", "/users/login").permitAll()
                .antMatchers("/*/**").authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, AnonymousAuthenticationFilter.class)
                .addFilterBefore(sstAuthenticationFilter, AnonymousAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                .httpBasic();

        super.configure(http);
    }
}
