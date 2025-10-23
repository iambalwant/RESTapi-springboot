package com.edigest.myfirstproject.service;

import com.edigest.myfirstproject.entity.userEntry;
import com.edigest.myfirstproject.repository.userEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

//here we are going to write our business service
@Component
public class userService {

    @Autowired
    private userEntryRepository userEntryRepository;

    public void saveEntry(userEntry userEntry){
        userEntryRepository.save(userEntry);
    }
    public List<userEntry> getALL(){
        return userEntryRepository.findAll();
    }
    public Optional<userEntry> findById(ObjectId id){
        return userEntryRepository.findById(id);
    }
    public void deleteById(ObjectId id){userEntryRepository.deleteById(id);}
    public userEntry findByusername(String username){
        return userEntryRepository.findByusername(username);
    }
}


//controller ---> service --> repository