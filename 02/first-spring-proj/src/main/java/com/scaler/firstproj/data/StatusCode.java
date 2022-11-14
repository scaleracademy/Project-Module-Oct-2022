package com.scaler.firstproj.data;

public enum StatusCode {
    OK(200),
    CREATED(201),
    DELETED(204),
    BAD_REQUEST(400),
    NOT_FOUND(404);

    private int code;
    StatusCode(int code) {
        this.code = code;
    }
}
