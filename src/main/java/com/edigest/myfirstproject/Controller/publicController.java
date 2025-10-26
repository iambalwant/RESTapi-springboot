package com.edigest.myfirstproject.Controller;

import com.edigest.myfirstproject.entity.userEntry;
import com.edigest.myfirstproject.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/public")
public class publicController {

    @Autowired
    public userService userService;

    //GET request to check server is running
      @GetMapping("/health-check")
      public String HealthCheck(){
          return "Ok";
      }

    @PostMapping
    public void createUser(@RequestBody userEntry user){
        userService.saveNewUser(user);
    }

}
