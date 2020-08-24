package com.wufan.security.authentication.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码相关异常类
 * @author wufan
 * @date 2020/4/11 0011 2:37
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
