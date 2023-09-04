package com.example.shopping.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class ExceptionAdvice {
    static final String INTERNAL_SERVER_ERROR = "error/500";

    @ExceptionHandler(MessageException.class)
    @ResponseBody
    public ResponseEntity<String> handleMessageException(MessageException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(DataAccessExceptionWithHttpServletRequest.class) // DB에서 발생하는 런타임 예외 캐치
    public Object handleDataAccessException(DataAccessExceptionWithHttpServletRequest ex) {
        return "XMLHttpRequest".equals(ex.getRequestType()) ? ResponseEntity.internalServerError().body("DB 작업 중 에러가 발생했습니다.")
                : new ModelAndView(INTERNAL_SERVER_ERROR);
    }
}