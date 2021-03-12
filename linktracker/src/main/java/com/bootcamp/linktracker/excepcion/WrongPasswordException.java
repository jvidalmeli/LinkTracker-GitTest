package com.bootcamp.linktracker.excepcion;
import org.springframework.http.HttpStatus;

public class WrongPasswordException extends ExceptionBase{

    public WrongPasswordException() {
        super("WrongPasswordException", "El password ingresado no es correcto", HttpStatus.FORBIDDEN);
    }
}
