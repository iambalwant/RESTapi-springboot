package com.edigest.myfirstproject.service;

import com.edigest.myfirstproject.entity.userEntry;
import com.edigest.myfirstproject.repository.userEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//here we are going to write our business service
@Service
@Slf4j //for logging
public class userService {

    @Autowired
    private userEntryRepository userEntryRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//    private static  final Logger logger = LoggerFactory.getLogger(userService.class);



    public void saveEntry(userEntry userEntry){
        userEntryRepository.save(userEntry);
    }
    public boolean saveNewUser(userEntry userEntry){
        try {
            userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
            userEntry.setRoles(List.of("USER"));
            userEntryRepository.save(userEntry);
            return true;
        } catch (Exception e) {
            //for instance on logger
//            logger.error("Error for {} :",userEntry.getUsername(),e);
            //for slf4 annotation
            log.error("Error for {} :",userEntry.getUsername(),e);
            return false;
        }
    }
    public void saveNewAdmin(userEntry userEntry){
        userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
        userEntry.setRoles(List.of("USER","ADMIN"));
        userEntryRepository.save(userEntry);
    }

    public List<userEntry> getALL(){
        return userEntryRepository.findAll();
    }
    public Optional<userEntry> findById(ObjectId id){
        return userEntryRepository.findById(id);
    }
    public void deleteById(ObjectId id){userEntryRepository.deleteById(id);}
    public userEntry findByusername(String username){
        return userEntryRepository.findByusername(username);
    }
}


//controller ---> service --> repository