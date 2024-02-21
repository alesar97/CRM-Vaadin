package com.example.application.security;

import com.example.application.views.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {

//    @Override
//    protected void configure(WebSecurity web) throws Exception {
//        super.configure(web);
//        setLoginView(LoginView.class);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        web.ignoring().antMatchers();
//        super.configure(http);
//    }
}
