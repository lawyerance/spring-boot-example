package pers.lyks.spring.example.base;

import pers.lyks.spring.example.bean.CommonResponse;

/**
 * Common base controller, so that other all controller classes must inherit this one.
 *
 * @author lawyerance
 * @version 1.0 2018-08-03
 */
public abstract class BaseController {
    private final static int SUCCESS_CODE = 10000;


    protected CommonResponse success() {
        return success(null);
    }

    protected CommonResponse success(String message) {
        return success(message, null);
    }

    protected CommonResponse success(Object data) {
        return success(null, data);
    }

    protected CommonResponse success(String message, Object data) {
        return result(SUCCESS_CODE, message, data, null);
    }

    protected CommonResponse error(int code, String message) {
        return error(code, message, null, null);
    }

    protected CommonResponse error(int code, Object[] params) {
        return error(code, null, params);
    }

    protected CommonResponse error(int code, String message, Object data) {
        return error(code, message, data, null);
    }

    protected CommonResponse error(int code, String format, Object[] params) {
        return error(code, format, null, params);
    }

    protected CommonResponse error(int code, String message, Object data, Object[] params) {
        return result(code, message, data, params);
    }

    private CommonResponse result(int code, String message, Object data, Object[] params) {
        CommonResponse result = new CommonResponse();
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

