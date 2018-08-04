package pers.lyks.spring.example.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * Common restful result object.
 *
 * @author lawyerance
 * @version 1.0 2018-08-03
 */
public class CommonResponse implements Serializable {
    private int code;
    private String message;
    private Object data;
    @JsonIgnore
    private Object[] params;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
