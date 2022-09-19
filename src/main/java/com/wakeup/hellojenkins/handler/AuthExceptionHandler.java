package com.wakeup.hellojenkins.handler;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class AuthExceptionHandler {


    @ResponseBody
    @ExceptionHandler({Exception.class})
    public Map<String, String> exceptionHandler(BindException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        // 使用stream api
        Map<String, String> map = bindingResult.getFieldErrors()

                .stream()

                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        // 上面那种是种简写，如果你不熟悉，这样也可以
        // Map<String, String> map = new HashMap<>();

        // bindingResult.getFieldErrors().forEach(fieldError -> {

        //     String field = fieldError.getField();

        //     String message = fieldError.getDefaultMessage();

        //     map.put(field, message);

        // });

        return map;

    }



}