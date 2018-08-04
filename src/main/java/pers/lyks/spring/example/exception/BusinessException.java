package pers.lyks.spring.example.exception;

/**
 * Common business exception.
 *
 * @author lawyerance
 * @version 1.0 2018-08-03
 */
public class BusinessException extends RuntimeException {

    /**
     * error code
     */
    private int code;

    public BusinessException(int code) {
        super();
        this.code = code;
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(int code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }

    public BusinessException(int code, Throwable throwable) {
        super(throwable);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
