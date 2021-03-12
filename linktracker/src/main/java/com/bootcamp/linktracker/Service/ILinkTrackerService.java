package com.bootcamp.linktracker.Service;

import com.bootcamp.linktracker.DTO.LinkDTO;
import com.bootcamp.linktracker.excepcion.InvalidUrlException;
import com.bootcamp.linktracker.excepcion.NotFoundLinkIDException;

import java.io.IOException;

public interface ILinkTrackerService {

    LinkDTO newLink(LinkDTO link) throws InvalidUrlException, NotFoundLinkIDException;
    int getAccesos(int linkId) throws NotFoundLinkIDException;
    String invalidateLink(int linkid) throws NotFoundLinkIDException;
}
