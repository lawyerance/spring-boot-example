package pers.lyks.spring.example.base;

import pers.lyks.spring.example.bean.CommonResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Common base controller, so that other all controller classes must inherit this one.
 *
 * @author lawyerance
 * @version 1.0 2018-08-03
 */
public abstract class BaseController {
    private final static int SUCCESS_CODE = 10000;
    private final static String SUCCESS_MESSAGE = "OK";

    protected <T> CommonResponse<T> success() {
        return success(SUCCESS_MESSAGE);
    }

    protected <T> CommonResponse<T> success(String message) {
        return success(message, null);
    }

    protected <T> CommonResponse<T> success(T data) {
        return success(SUCCESS_MESSAGE, data);
    }

    protected <T> CommonResponse<T> success(String message, T data) {
        return result(SUCCESS_CODE, message, data, null);
    }

    protected void download(File file, HttpServletRequest request, HttpServletResponse response) {

    }

    protected <T> CommonResponse<T> error(int code, String message) {
        return error(code, message, null, null);
    }

    protected <T> CommonResponse<T> error(int code, Object[] params) {
        return error(code, null, params);
    }

    protected <T> CommonResponse<T> error(int code, String message, T data) {
        return error(code, message, data, null);
    }

    protected <T> CommonResponse<T> error(int code, String format, Object[] params) {
        return error(code, format, null, params);
    }

    protected <T> CommonResponse<T> error(int code, String message, T data, Object[] params) {
        return result(code, message, data, params);
    }

    private <T> CommonResponse<T> result(int code, String message, T data, Object[] params) {
        CommonResponse<T> result = new CommonResponse<>();
        result.setCode(code);
        result.setMessage(message);
        if (data != null) {
            result.setData(data);
        }
        if (params != null && params.length > 0) {
            result.setParams(params);
        }
        return result;
    }
}

