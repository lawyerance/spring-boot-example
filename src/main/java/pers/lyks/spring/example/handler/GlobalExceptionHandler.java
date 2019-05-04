package pers.lyks.spring.example.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pers.lyks.spring.example.bean.CommonResponse;

/**
 * @author lawyerance
 * @version 1.0 2018-08-03
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public CommonResponse handleException(Exception ex) {
        CommonResponse result = new CommonResponse();
        result.setCode(19999);
        result.setData(ex.getCause());
        return result;
    }
}
