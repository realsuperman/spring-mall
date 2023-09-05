package com.example.shopping.service.user;

import com.example.shopping.domain.user.Consumer;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

@Getter
public class AccountDetails extends User {

    private final Consumer consumer;

    AccountDetails(Consumer consumer, String role) {
        super(consumer.getUserEmail(), consumer.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_" + role)));
        this.consumer = consumer;
    }

}
