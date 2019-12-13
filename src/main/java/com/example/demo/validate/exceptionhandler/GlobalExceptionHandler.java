package com.example.demo.validate.exceptionhandler;

import com.example.demo.validate.model.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yao
 * @date 2019/12/13
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return ResultVO.error(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(BindException.class)
    public ResultVO handleException(BindException e) {
        log.error(e.getMessage(), e);
        return ResultVO.error(e.getBindingResult().getFieldError().getDefaultMessage());
    }
}
