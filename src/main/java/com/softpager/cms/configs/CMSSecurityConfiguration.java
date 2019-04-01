package com.softpager.cms.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class CMSSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource myDataSource;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(myDataSource)
                .usersByUsernameQuery("select email as principal," +
                        " password as credentials, true from users where email=?")
                .authoritiesByUsernameQuery("select user_email as principal, " +
                        "roles as roles from user_roles where user_email=? ")
                .passwordEncoder(passwordEncoder).rolePrefix("ROLE_");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/","/photo/**", "/users/**","/instructors/**", "/students/**", "/courses/**",
                "/registration/**", "/css/**", "/webjars/**")
                .permitAll()
                .anyRequest().authenticated().and().formLogin().loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/users/profile").and().logout().logoutSuccessUrl("/");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
