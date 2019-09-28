package pers.lyks.example.resp;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lawyerance
 * @version 1.0 2019-09-28
 */
@Getter
@Setter
public class CommonResponse<T> {
    private int code;
    private String message;
    private T data;
}
