package com.bootcamp.linktracker.Service;

import com.bootcamp.linktracker.DTO.LinkDTO;
import com.bootcamp.linktracker.Repository.ILinkRepository;
import com.bootcamp.linktracker.excepcion.NotFoundLinkIDException;
import com.bootcamp.linktracker.excepcion.WrongPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LinkRedirectorService implements ILinkRedirectorService {

    @Autowired
    ILinkRepository iLinkRepository;

    @Override
    public String redirectToUrl(int id,String password) throws WrongPasswordException, NotFoundLinkIDException {

        LinkDTO link = new LinkDTO();
        link.setId(id);
        link = iLinkRepository.findOrCreateLink(link);
        if(!link.getPassword().equals(password)){

            throw new WrongPasswordException();

        }
        link.setAccesos(link.getAccesos()+1);
        return link.getUrl();

    }
}
