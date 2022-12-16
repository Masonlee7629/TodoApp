package com.codestates.todoapp.exception;

import lombok.Getter;

public enum ExceptionCode {
    TODO_EXISTS(409, "Todo exists"),
    TODO_NOT_FOUND(404, "Todo not found");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
