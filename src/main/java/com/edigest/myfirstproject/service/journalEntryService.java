package com.edigest.myfirstproject.service;

import com.edigest.myfirstproject.entity.journalEntry;
import com.edigest.myfirstproject.repository.journalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

//here we are going to write our business service
@Component
public class journalEntryService {

    @Autowired
    private journalEntryRepository journalEntryRepository;

    public void saveEntry(journalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<journalEntry> getALL(){
        return journalEntryRepository.findAll();
    }
//option means data may be there or not kind of ? in Js
    //.findById(id).orElse(null) is same
    public Optional<journalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }
    public void deleteById(ObjectId id){
        journalEntryRepository.deleteById(id);
    }

}


//controller ---> service --> repository