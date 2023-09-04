package com.example.shopping.service.user;


import com.example.shopping.dao.order.OrderDetailDao;
import com.example.shopping.dao.user.ConsumerDao;
import com.example.shopping.dao.user.MembershipDao;
import com.example.shopping.domain.user.Consumer;
import com.example.shopping.domain.user.Membership;
import com.example.shopping.dto.user.*;
import com.example.shopping.exception.MessageException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ConsumerDao consumerDao;
    private final OrderDetailDao orderDetailDao;
    private final MembershipDao membershipDao;
    private final ResourceBundle rb = ResourceBundle.getBundle("application", Locale.KOREA);
    private final String alg = rb.getString("encrypt.alg");
    private final String key = rb.getString("encrypt.key");
    private final String iv = key.substring(0, 16);



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

        signUpDto.setPassword(encrypt(signUpDto.getPassword()));
        Consumer consumer = Consumer.signUpDtoToConsumer(signUpDto);
        consumerDao.insert(consumer);

    }

    private void isExistEmail(String userEmail) {
        if (consumerDao.selectOne(userEmail) != null) {
            throw new MessageException("존재하는 이메일 입니다.");
        }
    }

    // 비밀번호 암호화
    private String encrypt(String originalPassword) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance(alg);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"), new IvParameterSpec(iv.getBytes()));
        byte[] encrypted = cipher.doFinal(originalPassword.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(encrypted);
    }

    // 비밀번호 복호화
    private String decrypt(String cipherPassword) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(alg);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"), new IvParameterSpec(iv.getBytes()));
        byte[] decodedBytes = Base64.getDecoder().decode(cipherPassword);

        return new String(cipher.doFinal(decodedBytes), StandardCharsets.UTF_8);
    }

    /**
     * 로그인
     *
     * @param loginRequest
     * @return LoginResponse
     */
    public LoginResponse login(LoginRequest loginRequest) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        Consumer consumer = consumerDao.selectOne(loginRequest.getUserEmail());

        if (consumer == null) {
            throw new MessageException("존재하지 않는 이메일입니다.");
        }

        if (!loginRequest.getPassword().equals(decrypt(consumer.getPassword()))) {
            throw new MessageException("비밀번호가 일치하지 않습니다.");
        }

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

        if (!updatePasswordRequest.getOriginalPassword().equals(decrypt(consumerDao.selectOne(email).getPassword()))) {
            throw new MessageException("비밀번호가 일치하지 않습니다.");
        }
        updatePasswordRequest.setUpdatePassword(encrypt(updatePasswordRequest.getUpdatePassword()));

        consumerDao.updatePassword(Consumer.updateUserPassDtoToConsumer(email, updatePasswordRequest));
    }
}