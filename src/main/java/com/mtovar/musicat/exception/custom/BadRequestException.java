package com.mtovar.musicat.exception.custom;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {}
    public BadRequestException(String message) {
        super(message);
    }
}
