package com.example.shopping.service.user;


import com.example.shopping.dao.order.OrderDetailDao;
import com.example.shopping.dao.user.ConsumerDao;
import com.example.shopping.dao.user.MembershipDao;
import com.example.shopping.domain.user.Consumer;
import com.example.shopping.domain.user.Membership;
import com.example.shopping.dto.user.*;
import com.example.shopping.exception.MessageException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final ConsumerDao consumerDao;
    private final OrderDetailDao orderDetailDao;
    private final MembershipDao membershipDao;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final ResourceBundle rb = ResourceBundle.getBundle("application", Locale.KOREA);
    private final String alg = rb.getString("encrypt.alg");
    private final String key = rb.getString("encrypt.key");
    private final String iv = key.substring(0, 16);

    public UserService(ConsumerDao consumerDao, OrderDetailDao orderDetailDao, MembershipDao membershipDao, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.consumerDao = consumerDao;
        this.orderDetailDao = orderDetailDao;
        this.membershipDao = membershipDao;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public Consumer readUserOne(String userEamil) {
        return consumerDao.selectOne(userEamil);
    }

    /**
     * 회원가입
     *
     * @param signUpDto
     * @return
     */
    @Transactional
    public void signUp(SignUpRequest signUpDto) throws InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {

        isExistEmail(signUpDto.getUserEmail());

        signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Consumer consumer = Consumer.signUpDtoToConsumer(signUpDto);
        consumerDao.insert(consumer);

    }

    private void isExistEmail(String userEmail) {
        if (consumerDao.selectOne(userEmail) != null) {
            throw new MessageException("존재하는 이메일 입니다.");
        }
    }

    /**
     * 로그인
     *
     * @param loginRequest
     * @return LoginResponse
     */
    public LoginResponse login(LoginRequest loginRequest) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserEmail(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        Consumer consumer = ((AccountDetails) authentication.getPrincipal()).getConsumer();
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // exception 처리 추가하기
        //  email, pass 틀릴 시, authentication.BadCredentialsException 를 발생

        if (consumer.getIsAdmin() == 1) {
            return new LoginResponse(consumer);
        }

        Membership membership = getUserMemberShip(getConsumerTotalBuyPrice(consumer.getConsumerId()));

        return new LoginResponse(consumer, membership.getGrade(), membership.getDiscountRate());

    }

    // 유저의 총 구매 가격 조회
    private long getConsumerTotalBuyPrice(Long consumerId) {

        Long price = orderDetailDao.getConsumerTotalBuyPrice(consumerId);
        return price == null ? 0 : price;
    }

    // 멤버십 조회
    private Membership getUserMemberShip(long totalPrice) {
        return membershipDao.selectMembershipByPrice(totalPrice);
    }

    /**
     * 유저 정보 수정
     *
     * @param updateUserRequest
     * @return
     */
    @Transactional
    public void updateUserInfo(String email, UpdateUserRequest updateUserRequest) {
        consumerDao.updateUserInfo(Consumer.updateUserInfoDtoToConsumer(email, updateUserRequest));
    }

    /**
     * 비밀번호 수정
     *
     * @param updatePasswordRequest
     * @return
     */

    @Transactional
    public void updatePassword(String email, UpdatePasswordRequest updatePasswordRequest) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException {


        passwordEncoder.matches(updatePasswordRequest.getOriginalPassword(), consumerDao.selectOne(email).getPassword());

        if (!passwordEncoder.matches(updatePasswordRequest.getOriginalPassword(), consumerDao.selectOne(email).getPassword())) {
            throw new MessageException("비밀번호가 일치하지 않습니다.");
        }
        updatePasswordRequest.setUpdatePassword(passwordEncoder.encode(updatePasswordRequest.getUpdatePassword()));

        consumerDao.updatePassword(Consumer.updateUserPassDtoToConsumer(email, updatePasswordRequest));
    }
}