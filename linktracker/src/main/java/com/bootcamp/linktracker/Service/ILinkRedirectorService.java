package com.bootcamp.linktracker.Service;

import com.bootcamp.linktracker.excepcion.NotFoundLinkIDException;
import com.bootcamp.linktracker.excepcion.WrongPasswordException;

import java.io.IOException;

public interface ILinkRedirectorService {

    public String redirectToUrl(int id,String password) throws WrongPasswordException, NotFoundLinkIDException;

}
