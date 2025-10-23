package com.edigest.myfirstproject.Controller;

import com.edigest.myfirstproject.entity.journalEntry;
import com.edigest.myfirstproject.service.journalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class appEntryControllerV2 {

//    controller → service → repository → database

    @Autowired
    private journalEntryService journalEntryService;

    @GetMapping
    public List<journalEntry> getAll(){
        return journalEntryService.getALL();
    }


    //ResponseEntity<journalEntry> we can give <?> also if we don't know the enties
    @PostMapping
    public ResponseEntity<journalEntry> createEntry(@RequestBody journalEntry myEntry){
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(myEntry, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<journalEntry> getJournalEntryById(@PathVariable ObjectId myId){
//        return  journalEntryService.findById(myId).orElse(null);
        //now we send custom status code also
        Optional<journalEntry> journalEntry = journalEntryService.findById(myId);

        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("id/{myId}")
    public boolean deleteEntryById(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return true;
    }

    @PutMapping("id/{id}")
    public journalEntry updateJournalById(@PathVariable ObjectId id, @RequestBody journalEntry newEntry ){
        journalEntry old = journalEntryService.findById(id).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
        }

        journalEntryService.saveEntry(old);
        return old;
    }

}
