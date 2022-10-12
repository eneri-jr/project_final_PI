package br.com.pi.serviceSchedulingApi.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/v1/**")
                .permitAll()
                .antMatchers(HttpMethod.DELETE, "/v1/**")
                .permitAll()
                .antMatchers(HttpMethod.PUT, "/v1/**")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/v1/**")
                .permitAll();
    }
}
