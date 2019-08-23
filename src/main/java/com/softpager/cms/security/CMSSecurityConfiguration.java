package com.softpager.cms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class CMSSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserDetailsService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encodePassword());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(
                "/",
                "/users/**",
                "/roles/**",
                "/courses","/courses/course",
                "/courses/get-courses",
                "/courses/assign-multiple-courses",
                "/students/**",
                "/photo/**",
                "/about",
                "/admin/**",
                "/login",
                "/static/**", "/css/**", "/js/**", "/images/**",
                "/fonts/**", "/webjars/**")
                .permitAll().anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/")
                .and()
                .logout().logoutSuccessUrl("/");
    }


    @Bean
    public PasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

}
