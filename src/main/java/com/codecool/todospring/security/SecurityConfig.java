package com.codecool.todospring.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/list").permitAll() // allowed by anyone
                //.antMatchers(HttpMethod.GET, "/vehicles/**").authenticated() // allowed only when signed in
                //.antMatchers(HttpMethod.DELETE, "/vehicles/**").hasRole("ADMIN") // allowed if signed in with ADMIN role
                .anyRequest().denyAll(); // anything else is denied
                //.and()
                //.addFilterBefore(new JwtTokenFilter(jwtTokenServices), UsernamePasswordAuthenticationFilter.class);
    }
}
