package com.foshan.huang.constants;

import lombok.Getter;

@Getter
public class BizException extends RuntimeException {
    private final String code;
    private final String message;

    public BizException(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    public BizException(final String code, final String message, final Throwable cause) {
        super(cause);
        this.code = code;
        this.message = message;
    }

}
