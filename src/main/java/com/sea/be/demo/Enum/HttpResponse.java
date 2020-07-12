package com.sea.be.demo.Enum;

import lombok.Getter;

@Getter
public enum HttpResponse {
    SUCCESS(200),
    UNAUTHORIZED(401),
    UNPROCESSABLE_ENTITY(422);

    private int code;

    private HttpResponse(int code) {
        this.code = code;
    }
}
