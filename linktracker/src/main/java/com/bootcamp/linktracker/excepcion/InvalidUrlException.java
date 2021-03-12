package com.bootcamp.linktracker.excepcion;


import org.springframework.http.HttpStatus;

public class InvalidUrlException extends ExceptionBase{
    public InvalidUrlException(String url){
        super("Invalid Url","La url:"+ url + " no es valida", HttpStatus.BAD_REQUEST);
    }
}