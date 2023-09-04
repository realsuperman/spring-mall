package com.example.shopping.config;

import com.example.shopping.exception.CustomAccessDeniedHandler;
import com.example.shopping.exception.CustomAuthenticationEntryPoint;
import com.example.shopping.service.user.AccountDetailsSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AccountDetailsSerivce accountDetailsSerivce;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    @Order(0)
    public SecurityFilterChain resources(HttpSecurity http) throws Exception {
        return http.requestMatchers(matchers -> matchers.antMatchers("/webapp/static/**")).authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll()).build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable().exceptionHandling().accessDeniedHandler(customAccessDeniedHandler).authenticationEntryPoint(customAuthenticationEntryPoint)
                .and().sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/")
                .sessionRegistry(sessionRegistry());

        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/item").hasRole("ADMIN")
                .antMatchers("/", "/status", "/static/**", "/categories", "/itemDetail", "/itemJson", "/user/sign-page", "/user/sign-up", "/user/login").permitAll()
                .antMatchers(HttpMethod.GET, "/item").permitAll()
                .anyRequest().authenticated();

        http.formLogin().loginPage("/user/sign-page").permitAll()
                .and()
                .logout().logoutUrl("/user/logout").logoutSuccessUrl("/");

        http.addFilterBefore(new CustomAuthenticationFilter(accountDetailsSerivce), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
