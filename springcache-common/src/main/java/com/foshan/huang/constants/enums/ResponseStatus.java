package com.foshan.huang.constants.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatus {

    SUCCESS("200", "SUCCESS"),
    FAIL("fail", "FAILED"),
    CODE_001("10001", "业务错误"),

    ;
    private final String code;

    private final String message;



}
