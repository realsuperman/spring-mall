package com.example.shopping.controller.user;

import com.example.shopping.domain.user.Consumer;
import com.example.shopping.dto.user.*;
import com.example.shopping.exception.MessageException;
import com.example.shopping.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController extends HttpServlet {

    private final UserService userService;


    /**
     * 로그인 post
     * 회원가입 post
     * 로그인 페이지 get "userLoginRegister"
     * 로그아웃 get
     * 회원정보 비밀번호 수정 fetch
     * 회원정보 주소, 폰번호 수정 fetch
     * validation post
     */

    @GetMapping("/sign-page")
    private String getSignPage() {
        return "userLoginRegister";
    }

    @GetMapping("/my-page")
    private String getMyPage() {
        return "myPage";
    }

    @GetMapping("/my-info")
    private String getMyPageInfo() {
        return "myPageUpdate";
    }

    @PostMapping("/login")
    private String login(HttpSession httpSession, @Valid LoginRequest loginRequest, Model model) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        try {
            LoginResponse loginResponse = userService.login(loginRequest);
            httpSession.setAttribute("login_user", loginResponse.getLoginUser());

            if (loginResponse.getLoginUser().getIsAdmin() == 0) {
                httpSession.setAttribute("grade", loginResponse.getGrade());
                httpSession.setAttribute("discount_rate", loginResponse.getDiscountRate());
            }
            return "redirect:/";
        } catch (MessageException e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "userLoginRegister";
        }
    }

    @PostMapping("/sign-up")
    private String signUp(HttpSession httpSession, @Valid SignUpRequest signUpRequest, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {

        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("errorMsg", bindingResult.getFieldErrors().get(0).getDefaultMessage());
                return "userLoginRegister";
            }
            userService.signUp(signUpRequest);

            return "redirect:/";
        } catch (MessageException e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "userLoginRegister";
        }
    }

    @GetMapping("/logout")
    private String logout(HttpSession httpSession, Model model) {
        if (httpSession != null) {
            httpSession.invalidate(); // 세션 무효화
        }
        return "redirect:/";
    }

    @PostMapping("/pass")
    private String updateUserPassword(HttpSession httpSession, @Valid UpdatePasswordRequest updatePasswordRequest, Model model) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        try {
            String email = ((Consumer) httpSession.getAttribute("login_user")).getUserEmail();
            userService.updatePassword(email, updatePasswordRequest);
            Consumer updatedConsumer = userService.readUserOne(email);
            httpSession.setAttribute("login_user", updatedConsumer);

            return "myPage";
        } catch (MessageException e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "myPageUpdate";
        }
    }

    @PostMapping("/info")
    private String updateUserInfo(HttpSession httpSession, @Valid UpdateUserRequest updateUserRequest, Model model) {

        Consumer sessionConsumer = (Consumer) httpSession.getAttribute("login_user");
        String email = ((Consumer) httpSession.getAttribute("login_user")).getUserEmail();

        String updatePhoneNumber = updateUserRequest.getUpdatePhoneNumber();
        String updateAddress = updateUserRequest.getUpdateAddress() + " " + updateUserRequest.getUpdateAddressDetail();

        if (updatePhoneNumber.equals(sessionConsumer.getPhoneNumber())) {
            updateUserRequest.setUpdatePhoneNumber(null);
        }
        if (updateAddress.equals(sessionConsumer.getAddress())) {
            updateUserRequest.setUpdateAddress(null);
        }
        userService.updateUserInfo(email, updateUserRequest);
        Consumer consumer = userService.readUserOne(email);
        httpSession.setAttribute("login_user", consumer);

        return "myPage";
    }

}
