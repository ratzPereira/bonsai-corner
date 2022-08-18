package com.ratz.bonsaicorner.config;

import com.ratz.bonsaicorner.security.JwtConfigurer;
import com.ratz.bonsaicorner.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {


  private JwtTokenProvider jwtTokenProvider;

  @Bean
  public PasswordEncoder passwordEncoder() {

    Map<String, PasswordEncoder> encoders = new HashMap<>();

    encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());

    DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);

    passwordEncoder.setDefaultPasswordEncoderForMatches(new Pbkdf2PasswordEncoder());

    return passwordEncoder;
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .httpBasic().disable()
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests().antMatchers("/auth/signIn", "/auth/refresh", "/api-docs/**", "/swagger-ui.html**", "/auth/forgot-password").permitAll()
        .antMatchers("/api/v1/**").authenticated()
        .antMatchers("/auth/password*").authenticated()
        .antMatchers("/users").denyAll()
        .and()
        .cors()
        .and()
        .apply(new JwtConfigurer(jwtTokenProvider));
  }
}
