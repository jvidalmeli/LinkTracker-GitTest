package com.bootcamp.linktracker.excepcion;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter @Getter
public class ExceptionBase extends Exception {

    private String name;
    private String descripction;
    private HttpStatus status;

    public ExceptionBase(String name, String descripction, HttpStatus status){
        this.name = name;
        this.descripction = descripction;
        this.status = status;
    }
}
