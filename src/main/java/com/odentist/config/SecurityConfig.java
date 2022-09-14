package com.odentist.config;

import com.odentist.service.AdministratorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Classname SecurityConfig
 * @Date 2022/9/12 20:14
 * @Author jin
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AdministratorsService administratorsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/emp/emps","/emp/toAddPage","/emp/toUpdateEmp/{id}","/emp/addEmp","/emp/updateEmp","/emp/delEmp/{id}").hasAnyAuthority("MAX","MID")
                .antMatchers("/emp/myInfo","/emp/updateMy").hasAuthority("MIN")
                .antMatchers("/register/**").permitAll()
                .antMatchers("/administrators/**").hasAuthority("MAX")
                .and()
                .formLogin()
                .loginPage("/user/toLogin")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/user/toHome")
                .and().rememberMe();
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(administratorsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
