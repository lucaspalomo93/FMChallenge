package com.challenge.challenge.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{


    @Autowired
    private UserDetailsService uS;

    @Autowired
    private PasswordEncoder pE;

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(uS).passwordEncoder(pE);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.
        cors()
        .and()
        .csrf().disable()
        .authorizeRequests()
            .antMatchers("/operator/update", "/operator/delete/**")
                .hasRole("ADMIN")
            .antMatchers("/operator/list/**")
                .hasAnyRole("USER","ADMIN")
            .antMatchers("/operator/create").permitAll()
            .anyRequest().permitAll().and().httpBasic()
            ;
    }
}
