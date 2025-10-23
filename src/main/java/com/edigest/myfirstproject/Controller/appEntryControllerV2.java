package com.edigest.myfirstproject.Controller;

import com.edigest.myfirstproject.entity.journalEntry;
import com.edigest.myfirstproject.service.journalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping
    public boolean createEntry(@RequestBody journalEntry myEntry){
       myEntry.setDate(LocalDateTime.now());
       journalEntryService.saveEntry(myEntry);
       return true;
    }

    @GetMapping("id/{myId}")
    public journalEntry getJournalEntryById(@PathVariable ObjectId myId){
        return  journalEntryService.findById(myId).orElse(null);
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
