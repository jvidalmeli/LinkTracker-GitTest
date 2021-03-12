package com.bootcamp.linktracker.Repository;

import com.bootcamp.linktracker.DTO.LinkDTO;
import com.bootcamp.linktracker.excepcion.NotFoundLinkIDException;

import java.io.IOException;

public interface ILinkRepository {

    LinkDTO findOrCreateLink(LinkDTO link) throws NotFoundLinkIDException;
    String invalidateLink(int linkid) throws NotFoundLinkIDException;

}
