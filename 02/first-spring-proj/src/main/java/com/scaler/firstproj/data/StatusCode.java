package com.scaler.firstproj.data;

public enum StatusCode {

    OK(200),
    Created(201),
    Deleted(204),
    BadRequest(400),
    NotFound(404);

    private int statusCode;

    StatusCode(int statusCode){
        this.statusCode = statusCode;
    }
}
