//package com.example.shopping.exception;
//
//import org.springframework.boot.autoconfigure.web.ServerProperties;
//import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
//import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
//import org.springframework.boot.web.error.ErrorAttributeOptions;
//import org.springframework.boot.web.servlet.error.ErrorAttributes;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//import java.util.Map;
//
///**
// * BasicErrorController의 경우 에러를 퉁쳐서 처리하는 개념은 없음 예를 들어,
// * 400번이면 400.jsp를 찾고 401번이면 401.jsp를 찾음 그러나 408.jsp를 만들지 않은 상태에서 408번 상태코드가 전파되면
// * 기본적으로 white label 페이지를 보여주게됨 이랬을 때 이렇게 등록 안된 것들을 특정 예외 페이지로 퉁치고 싶은 경우
// * 아래처럼 BasicErrorContorller를 확장하면 됨
// */
//@Controller
//public class CustomErrorController extends BasicErrorController {
//
//    public CustomErrorController(ErrorAttributes errorAttributes,
//                                 ServerProperties serverProperties,
//                                 List<ErrorViewResolver> errorViewResolvers) {
//        super(errorAttributes, serverProperties.getError(), errorViewResolvers);
//    }
//
//    private static final String prefix = "error/";
//
//    @Override
//    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
//        HttpStatus hs = getStatus(request);
//        ModelAndView mv = new ModelAndView(prefix+HttpStatus.BAD_REQUEST.value());
//
//        switch (hs){
//            case NOT_FOUND:
//                mv.setViewName(prefix+HttpStatus.NOT_FOUND.value());
//                break;
//            case INTERNAL_SERVER_ERROR:
//                mv.setViewName(prefix+HttpStatus.INTERNAL_SERVER_ERROR.value());
//                break;
//        }
//
//        return mv;
//    }
//
//    @Override
//    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
//        HttpStatus status = getStatus(request);
//        if (status == HttpStatus.NO_CONTENT) {
//            return new ResponseEntity<>(status);
//        }
//        Map<String, Object> body = getErrorAttributes(request, ErrorAttributeOptions.defaults());
//        return new ResponseEntity<>(body, status);
//    }
//}