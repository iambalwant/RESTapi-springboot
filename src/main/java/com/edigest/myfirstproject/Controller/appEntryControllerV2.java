package com.edigest.myfirstproject.Controller;

import com.edigest.myfirstproject.entity.journalEntry;
import com.edigest.myfirstproject.entity.userEntry;
import com.edigest.myfirstproject.service.journalEntryService;
import com.edigest.myfirstproject.service.userService;
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

    @Autowired
    private userService userService;

    @GetMapping("{username}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String username){
        userEntry user = userService.findByusername(username);
        List<journalEntry> all = user.getJournalEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    //ResponseEntity<journalEntry> we can give <?> also if we don't know the enties
    @PostMapping("{username}")
    public ResponseEntity<journalEntry> createEntry(@RequestBody journalEntry myEntry,@PathVariable String username){
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry, username);
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

    @DeleteMapping("id/{username}/{myId}")
    public boolean deleteEntryById(@PathVariable ObjectId myId,@PathVariable String username){
        journalEntryService.deleteById(myId, username);
        return true;
    }

    @PutMapping("id/{username}/{id}")
    public journalEntry updateJournalById(
            @PathVariable ObjectId id,
            @PathVariable String username,
            @RequestBody journalEntry newEntry
    ){
        journalEntry old = journalEntryService.findById(id).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
        }

        journalEntryService.saveEntry(old, username);
        return null;
    }

}
