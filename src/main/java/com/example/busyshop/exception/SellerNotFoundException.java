package com.example.busyshop.exception;

public class SellerNotFoundException extends RuntimeException{
    public SellerNotFoundException(String message){
        super(message);
    }
}
