package com.example.busyshop.exception;

public class EmptyCartException extends RuntimeException{
    public EmptyCartException(String message){
        super(message);
    }
}
