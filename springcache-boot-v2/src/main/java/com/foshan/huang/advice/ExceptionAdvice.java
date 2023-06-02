package com.foshan.huang.advice;


import com.foshan.huang.constants.BizException;
import com.foshan.huang.constants.Results;
import com.foshan.huang.constants.enums.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionAdvice<T> {

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseEntity<Results> handleException(Exception exception) {
        if (exception instanceof BizException) {
            BizException bizException = (BizException)exception;
            Results<Object> response = Results.fail(bizException.getCode(), bizException.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(Results.fail(ResponseStatus.FAIL.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
