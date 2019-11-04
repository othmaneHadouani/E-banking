package org.sid.othmane.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource ;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username as principal ,pass as credentials,active from users where username =?")
                .authoritiesByUsernameQuery("select username as principal ,role as role from users_roles where username =? ")
                .rolePrefix("ROLE_")
                .passwordEncoder(new MessageDigestPasswordEncoder("MD5"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login");
      http.authorizeRequests().antMatchers("/operations","/consulterCompte").hasRole("USER");
      http.authorizeRequests().antMatchers("/saveOperetion").hasRole("ADMIN");
      http.exceptionHandling().accessDeniedPage("/403");

    }
}

 /*   @Autowired
    private DataSource dataSource ;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}user").roles("USER");
        auth.inMemoryAuthentication()
                .withUser("zaki").password("{noop}1234").roles("USER");
        *//*auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username as principal ,pass as credentials,active from users where username =?")
                .authoritiesByUsernameQuery("select username as principal ,role as role from users_roles where username =? ")
                .rolePrefix("ROLE_")
                .passwordEncoder(new MessageDigestPasswordEncoder("MD5"));*//*
                    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();//.loginPage("/login");
        //http.authorizeRequests().anyRequest().authenticated();
        http.authorizeRequests().antMatchers("/chercherClients","/ operations").hasRole("ADMIN");
        http.exceptionHandling().accessDeniedPage("/403");


    }

}*/
