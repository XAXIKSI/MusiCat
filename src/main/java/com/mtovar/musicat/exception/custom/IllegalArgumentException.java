package com.mtovar.musicat.exception.custom;

public class IllegalArgumentException extends RuntimeException {
    public IllegalArgumentException() {}
    public IllegalArgumentException(String message) {
        super(message);
    }
}
