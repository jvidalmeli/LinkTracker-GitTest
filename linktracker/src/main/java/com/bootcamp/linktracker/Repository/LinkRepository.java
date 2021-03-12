package com.bootcamp.linktracker.Repository;

import com.bootcamp.linktracker.DTO.LinkDTO;
import com.bootcamp.linktracker.excepcion.NotFoundLinkIDException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;
import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class LinkRepository implements ILinkRepository {


    ArrayList<LinkDTO> links = new ArrayList<>();

    public LinkDTO findOrCreateLink(LinkDTO link) throws NotFoundLinkIDException {

        LinkDTO link_found = null;

        if(!links.isEmpty()){

            link_found = findLink(link);
        }

        if(link_found==null || links.isEmpty()){
            int id = links.size();
            link.setId(id);
            links.add(link);
            link_found = link;
        }

        return link_found;

    }

    public String invalidateLink(int linkid) throws NotFoundLinkIDException{

        if (!linkExists(linkid)) {
            throw new NotFoundLinkIDException(linkid);
        }
        LinkDTO link = new LinkDTO();
        link.setId(linkid);


        link = findLink(link);
        link.setInvalido(true);

        return String.valueOf(link.isInvalido());
    }

    public boolean linkExists(int linkid){

        boolean exists = false;
        for (LinkDTO link:links) {

            if(link.getId()==linkid){
                exists=true;
            }
        }
        return exists;
    }

    public LinkDTO findLink(LinkDTO link) {

        for (LinkDTO l : links) {

            if (link.getUrl()==null){
                //Si esta vacia busco por id
                if(l.getId()== link.getId() && !link.isInvalido()) {
                    return l;
                }

            }

            else if (link.getId()==-1){
                if (l.getUrl().equals(link.getUrl())&& !link.isInvalido()){
                    return l;
                }

            }

        }

        return null;
    }

}


//    @Override
//    public int findLinkByUrl(String url) throws IOException {
//        List<LinkDTO> linksInfo = null;
//        linksInfo = loadDataBase();
//        LinkDTO result = null;
//        if(linksInfo != null){
//            Optional<LinkDTO> item = linksInfo.stream()
//                    .filter(ingredientInfo -> ingredientInfo.getUrl().equals(url))
//                    .findFirst();
//            if (item.isPresent()) {
//                result = item.get();
//            }else{
//               result = saveNewLink(url,linksInfo.size());
//            }
//        }
//        else{
//            result = saveNewLink(url,0);
//        }
//        return result.getId();
//    }
//
//    public LinkDTO saveNewLink(String url,int id) throws IOException {
//        JsonParser parser = new JsonParser();
//        FileReader fr = new FileReader("src/main/resources/links.json");
//        JsonElement datos = parser.parse(fr);
//        dumpJSONElement(datos);
//
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        Writer writer = new FileWriter("src/main/resources/links.json");
//        LinkDTO new_link = new LinkDTO();
//        new_link.setUrl(url);
//        new_link.setId(id);
//
//        writer.write(gson.toJson(new_link));
//
//        return new_link;
//    }
//
//
//
//    public List<LinkDTO> loadDataBase(){
//
//        File file = null;
//
//        try{
//            file = new File("src/main/resources/links.json");
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        TypeReference<List<LinkDTO>> typeRef = new TypeReference<>() {};
//
//        List<LinkDTO> linksInfo = null;
//        try{
//            linksInfo = objectMapper.readValue(file,typeRef);
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//
//        return linksInfo;
//    }
