package com.edigest.myfirstproject.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class healthCheck {

    //GET request to check server is running
      @GetMapping("/health-check")
      public String HealthCheck(){
          return "Ok";
      }

}
