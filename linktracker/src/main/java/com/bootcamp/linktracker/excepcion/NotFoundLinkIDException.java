package com.bootcamp.linktracker.excepcion;

import org.springframework.http.HttpStatus;

public class NotFoundLinkIDException extends ExceptionBase {
    public NotFoundLinkIDException(Integer id){
        super("NotFoundLinkID","No se encontro el link id" + id, HttpStatus.NOT_FOUND );
    }
}