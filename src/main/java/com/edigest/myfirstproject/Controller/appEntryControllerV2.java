package com.edigest.myfirstproject.Controller;

import com.edigest.myfirstproject.entity.journalEntry;
import com.edigest.myfirstproject.entity.userEntry;
import com.edigest.myfirstproject.service.journalEntryService;
import com.edigest.myfirstproject.service.userService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class appEntryControllerV2 {

//    controller → service → repository → database

    @Autowired
    private journalEntryService journalEntryService;

    @Autowired
    private userService userService;

    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        userEntry user = userService.findByusername(name);
        List<journalEntry> all = user.getJournalEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //ResponseEntity<journalEntry> we can give <?> also if we don't know the enties
    @PostMapping
    public ResponseEntity<journalEntry> createEntry(@RequestBody journalEntry myEntry){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String name = authentication.getName();
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry, name);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(myEntry, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<journalEntry> getJournalEntryById(@PathVariable ObjectId myId){

//        return  journalEntryService.findById(myId).orElse(null);


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        userEntry user = userService.findByusername(name);
        List<journalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).toList();
        if(!collect.isEmpty()){
            Optional<journalEntry> journalEntry = journalEntryService.findById(myId);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("id/{myId}")
    public boolean deleteEntryById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        journalEntryService.deleteById(myId, name);
        return true;
    }

    @PutMapping("id/{id}")
    public journalEntry updateJournalById(
            @PathVariable ObjectId id,
            @RequestBody journalEntry newEntry
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        userEntry user = userService.findByusername(name);
        List<journalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).toList();
        if(!collect.isEmpty()){
            Optional<journalEntry> journalEntry = journalEntryService.findById(id);
            if(journalEntry.isPresent()){
                journalEntry old = journalEntry.get();
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                journalEntryService.saveEntry(old,name);
            }
        }

        return null;
    }

}
