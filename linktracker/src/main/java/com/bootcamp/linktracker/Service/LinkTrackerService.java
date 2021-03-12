package com.bootcamp.linktracker.Service;

import com.bootcamp.linktracker.DTO.LinkDTO;

import com.bootcamp.linktracker.Repository.ILinkRepository;
import com.bootcamp.linktracker.excepcion.InvalidUrlException;
import com.bootcamp.linktracker.excepcion.NotFoundLinkIDException;
import org.apache.commons.validator.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LinkTrackerService implements ILinkTrackerService{

    @Autowired
    ILinkRepository iLinkRepository;

    @Override
    public LinkDTO newLink(LinkDTO link) throws InvalidUrlException, NotFoundLinkIDException{

        UrlValidator urlValidator = new UrlValidator();
        LinkDTO link_found = null;

        //Tengo que verficar que la URL que recibi sea valida
        if (urlValidator.isValid(link.getUrl())) {
             link_found = saveUrlandGetId(link);

        } else {//Aca esto deberia funcionar con excepciones. Lo dejo para despues porque no se hacerlo.

            throw new InvalidUrlException(link.getUrl());
        }

        return link_found;
    }

    public LinkDTO saveUrlandGetId(LinkDTO link) throws NotFoundLinkIDException{

        return iLinkRepository.findOrCreateLink(link);

    }

    @Override
    public int getAccesos(int linkId) throws NotFoundLinkIDException {

        LinkDTO link = new LinkDTO();
        link.setId(linkId);
        link = iLinkRepository.findOrCreateLink(link);
        return link.getAccesos();
    }

    @Override
    public String invalidateLink(int linkid) throws NotFoundLinkIDException{

        return iLinkRepository.invalidateLink(linkid);

    }


}
