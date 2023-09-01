package com.example.shopping.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(MessageException.class)
    @ResponseBody
    public ResponseEntity<String> handleMessageException(MessageException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(DataAccessException.class) // DB에서 발생하는 런타임 예외 캐치
    public ResponseEntity<String> handleDataAccessException(DataAccessException ex) {
        /*if (ex instanceof DataIntegrityViolationException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data Integrity Violation");
        } else if (ex instanceof DuplicateKeyException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate Key");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }*/
        return ResponseEntity.badRequest().body("DB 작업 중 에러가 발생했습니다."); // TODO 페이지로 떨굴까?
    }
}