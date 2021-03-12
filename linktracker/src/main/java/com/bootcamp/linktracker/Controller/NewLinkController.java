package com.bootcamp.linktracker.Controller;

import com.bootcamp.linktracker.DTO.LinkDTO;
import com.bootcamp.linktracker.Service.ILinkRedirectorService;
import com.bootcamp.linktracker.Service.ILinkTrackerService;
import com.bootcamp.linktracker.excepcion.InvalidUrlException;
import com.bootcamp.linktracker.excepcion.NotFoundLinkIDException;
import com.bootcamp.linktracker.excepcion.WrongPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
public class NewLinkController {

    @Autowired
    private ILinkTrackerService iLinkTrackerService;

    @Autowired
    private ILinkRedirectorService iLinkRedirectorService;

    @PostMapping("/newlink")
    public LinkDTO newLink(@RequestBody LinkDTO link) throws InvalidUrlException, NotFoundLinkIDException {

       return iLinkTrackerService.newLink(link);

    }

    @GetMapping("/goto/{linkid}/{password}")
    @ResponseStatus(value = HttpStatus.PERMANENT_REDIRECT)
    public ModelAndView redirectToUrl(@PathVariable int linkid,@PathVariable String password) throws WrongPasswordException, NotFoundLinkIDException {

        String url = iLinkRedirectorService.redirectToUrl(linkid,password);
        return new ModelAndView("redirect:https://" + url);
    }

    @GetMapping("/metrics/{linkid}")
    public int getAccesos(@PathVariable int linkid) throws NotFoundLinkIDException{

        return iLinkTrackerService.getAccesos(linkid);
    }

    @PostMapping("/invalidate/{linkid}")
    public String invalidateLink(@PathVariable Integer linkid)throws NotFoundLinkIDException{

        return iLinkTrackerService.invalidateLink(linkid);

    }
}
