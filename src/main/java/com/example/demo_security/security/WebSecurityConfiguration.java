package com.example.demo_security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private DataSource datasource;



    @Autowired
    WebSecurityConfiguration(DataSource datasource){
        this.datasource=datasource;
    };


    //create passwordEncoder
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }




    @Autowired
protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//this is the simplest in memory authentication
        /*    auth.inMemoryAuthentication()
            .withUser("user").password("{noop}password").roles("USER")
            .and()
            .withUser("admin").password("{noop}password").roles("USER", "ADMIN");*/

        auth.jdbcAuthentication().dataSource(datasource);
}

@Override
protected void configure (HttpSecurity http) throws Exception{

        http

                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/users_only/**").hasRole("USER")
                .antMatchers("/secure").hasRole("ADMIN")
                .antMatchers("/users/**").hasRole("ADMIN")
                .antMatchers("/all").permitAll()
                .antMatchers(HttpMethod.GET, "/authenticated/**").authenticated()
                //.anyRequest().permitAll() dan mag alles!!!
                .and()
                .csrf().disable()
                .formLogin().disable();
}


}
