package ru.icoltd.springdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // add users for in memory authentication
        UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(users.username("Paul").password("test505").roles("EMPLOYEE"))
                .withUser(users.username("Angela").password("test505").roles("EMPLOYEE", "MANAGER"))
                .withUser(users.username("John").password("test505").roles("EMPLOYEE", "ADMIN"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // secures all REST endpoints under '/api/customers'
        http.authorizeRequests()
                .antMatchers("/api/customers/**").authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable()   // http://www.tothenew.com/blog/fortifying-your-rest-api-using-spring-security/
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // http://www.baeldung.com/spring-security-session
    }
}
