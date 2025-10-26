package com.edigest.myfirstproject.Controller;

import com.edigest.myfirstproject.entity.journalEntry;
import com.edigest.myfirstproject.entity.userEntry;
import com.edigest.myfirstproject.repository.userEntryRepository;
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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class userEntryControllerV2 {

    @Autowired
    public userService userService;

    @Autowired
    private userEntryRepository userEntryRepository;


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody userEntry user){


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        userEntry userInDB = userService.findByusername(name);
        if(userInDB != null){
            userInDB.setUsername(user.getUsername());
            userInDB.setPassword(user.getPassword());
            userService.saveNewUser(userInDB);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserByName(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userEntryRepository.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    };

}
