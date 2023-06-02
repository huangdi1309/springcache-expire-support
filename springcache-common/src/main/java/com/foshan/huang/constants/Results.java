package com.foshan.huang.constants;

import com.foshan.huang.constants.enums.ResponseStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Results<T> {

    private String code;

    private String message;

    private T data;

    public static <T> Results<T> success() {
        return success(null);
    }

    public static <T> Results<T> success(T data) {
        return Results.<T>builder().data(data).code(ResponseStatus.SUCCESS.getCode())
                .message(ResponseStatus.SUCCESS.getMessage()).build();
    }

    public static <T> Results<T> fail(String message) {
        return Results.<T>builder().data(null).code(ResponseStatus.FAIL.getCode()).message(message).build();
    }

    public static <T> Results<T> fail(String code, String message) {
        return Results.<T>builder().data(null).code(code).message(message).build();
    }

}
