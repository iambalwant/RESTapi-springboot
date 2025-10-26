package com.edigest.myfirstproject.Controller;


import com.edigest.myfirstproject.entity.userEntry;
import com.edigest.myfirstproject.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private userService userService;

    @GetMapping("/all-user")
    public ResponseEntity<?> getAllUsers(){
        List<userEntry> all = userService.getALL();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(all, HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<?> createNewAdmin(@RequestBody userEntry user){
        userService.saveNewAdmin(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
