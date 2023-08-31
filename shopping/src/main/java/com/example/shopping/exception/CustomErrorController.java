package com.example.shopping.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CustomErrorController extends BasicErrorController {
    @Value("${spring.mvc.view.prefix}") // 프로퍼티 키에 해당하는 값 주입
    private String prefix;
    private final String suffix = ".html";


    public CustomErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorProperties, errorViewResolvers);
    }

    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {

        HttpStatus hs = getStatus(request);
        ModelAndView mv = new ModelAndView(prefix+"/error/500"+suffix);

        switch (hs){
            case NOT_FOUND:
                mv.setViewName(prefix+"/error/404"+suffix);
                break;
        }

        return mv;
    }

}
