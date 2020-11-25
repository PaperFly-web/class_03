package com.paperfly.classUtils.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启注解方式
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //设置权限管理规则
        http.authorizeRequests()
               .antMatchers("/people/**").hasRole("admin")
                .antMatchers("/admin/**").hasRole("admin");//只有拥有vip1的角色才能访问/level1/**
        http.csrf().disable();//关闭网站攻击

        http.formLogin();

    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("paperfly")
                .password(new BCryptPasswordEncoder().encode("paperfly")).roles("admin");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}