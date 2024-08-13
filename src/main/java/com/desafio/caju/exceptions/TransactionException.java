package com.desafio.caju.exceptions;

import lombok.Getter;

@Getter
public class TransactionException extends RuntimeException {

    private final String code;
    private final String message;

    public TransactionException(String code, String message) {
        super(message);
        this.code = code;
        this.message =  message;
    }

}

