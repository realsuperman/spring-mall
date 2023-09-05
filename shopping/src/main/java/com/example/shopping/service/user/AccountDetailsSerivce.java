package com.example.shopping.service.user;

import com.example.shopping.dao.user.ConsumerDao;
import com.example.shopping.domain.user.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountDetailsSerivce implements UserDetailsService {

    private final ConsumerDao consumerDao;

    public UserDetails loadUserByUsername(String userEamil) throws UsernameNotFoundException {

        Consumer consumer = consumerDao.selectOne(userEamil);
        if (consumer == null) {
            throw new UsernameNotFoundException("사용자 정보 조회에 실패하였습니다.");
        }
        if (consumer.getIsAdmin() == 0) {
            return new AccountDetails(consumer, "USER");
        } else {
            return new AccountDetails(consumer, "ADMIN");
        }
    }
}
