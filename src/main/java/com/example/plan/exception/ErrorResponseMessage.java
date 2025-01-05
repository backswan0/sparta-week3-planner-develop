package com.example.plan.exception;

public record ErrorResponseMessage(
    int status,
    String message
) {

}