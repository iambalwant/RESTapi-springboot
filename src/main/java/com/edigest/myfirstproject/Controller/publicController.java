package com.edigest.myfirstproject.Controller;

import com.edigest.myfirstproject.entity.userEntry;
import com.edigest.myfirstproject.service.UserDetailServicimpl;
import com.edigest.myfirstproject.service.userService;
import com.edigest.myfirstproject.utils.JwtUtil;
import com.sun.net.httpserver.HttpsServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/public")
@Slf4j
public class publicController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServicimpl userDetailServicimpl;

    @Autowired
    private JwtUtil jwtutil;

    @Autowired
    public userService userService;

    //GET request to check server is running
      @GetMapping("/health-check")
      public String HealthCheck(){
          return "Ok";
      }

    @PostMapping("/signup")
    public void signup(@RequestBody userEntry user){
        userService.saveNewUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody userEntry user){
        try {
            //internally our username and password is checked my encoder also
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = userDetailServicimpl.loadUserByUsername(user.getUsername());
            String jwt = jwtutil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (AuthenticationException e) {
            log.error("Exception occur while createAuthenticationToken ", e);
            return new ResponseEntity<>("Incorrect username and password", HttpStatus.BAD_REQUEST);
        }
    }

}
