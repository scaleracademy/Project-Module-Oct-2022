package com.scaler.authdemo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO: in prod, CORS and CSRF shouldn't be blanket disabled
        http.cors().disable().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/about").permitAll()
                .antMatchers(HttpMethod.POST, "/users", "/users/login").permitAll()
                .antMatchers("/*/**").authenticated()
                .and()
                .httpBasic();

        super.configure(http);
    }
}
