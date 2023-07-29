package com.example.busyshop.exception;

public class InsufficientProductAmountException extends RuntimeException{
    public InsufficientProductAmountException(String message){
        super(message);
    }
}
