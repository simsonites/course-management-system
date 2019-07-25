package com.softpager.cms.configs;

import com.softpager.cms.services.CMSUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class CMSSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private CMSUserDetailService cmsUserDetailService;


    @Autowired
      protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(cmsUserDetailService);
    }



/*
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
*/





    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(
                "/",
                "/users/**",
                "/roles/**",
                "/courses","/courses/course",
                "/courses/get-courses",
                "/courses/remove-instructor-course",
                "/courses/assign-multiple-courses",
                "/students/**",
                "/photo/**",
                "/about",
                "/admin/**",
                "/instructors/**",
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

}
