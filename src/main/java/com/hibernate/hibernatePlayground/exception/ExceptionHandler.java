package com.hibernate.hibernatePlayground.exception;

public class ExceptionHandler extends Exception{

    public ExceptionHandler(){
    }

    public ExceptionHandler(String message){
        super(message);
    }
    public ExceptionHandler(String message, Throwable cause){
        super(message, cause);
    }

    public ExceptionHandler(Throwable cause){
        super(cause);
    }




}
