package com.example.shopping.config;

import com.example.shopping.domain.user.Consumer;
import com.example.shopping.service.user.AccountDetailsSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequiredArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final AccountDetailsSerivce accountDetailsSerivce;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 유저 정보
        Consumer consumer = getConsumer(request);

        if (consumer != null) {
            UserDetails userDetails = accountDetailsSerivce.loadUserByUsername(consumer.getUserEmail());
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private Consumer getConsumer(ServletRequest request) {
        HttpSession session = ((HttpServletRequest) request).getSession(false);

        return session != null && session.getAttribute("login_user") != null ? (Consumer) session.getAttribute("login_user") : null;
    }
}
