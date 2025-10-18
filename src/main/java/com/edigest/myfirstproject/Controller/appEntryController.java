package com.edigest.myfirstproject.Controller;

import com.edigest.myfirstproject.entity.journalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;


import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/journal")
public class appEntryController {

    //table to store
    private Map<Long, journalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<journalEntry> getAll(){
     return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody journalEntry myEntry){
       journalEntries.put(myEntry.getId(), myEntry);
       return true;
    }

    @GetMapping("id/{myId}")
    public journalEntry getJournalEntryById(@PathVariable Long myId){
        return  journalEntries.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteEntryById(@PathVariable Long myId){
      journalEntries.remove(myId);
      return true;
    }

    @PutMapping("id/{id}")
    public journalEntry updateJournalById(@PathVariable Long id, @RequestBody journalEntry myEntry ){
        return journalEntries.put(id, myEntry);
    }

}
