package com.edigest.myfirstproject.service;

import com.edigest.myfirstproject.entity.userEntry;
import com.edigest.myfirstproject.repository.userEntryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class UserDetailsServiceImpTest {

    @InjectMocks
    private userEntryRepository userEntryRepository;

    @Mock
    private UserDetailServicimpl userDetailServicimpl;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Disabled
    @Test
    void loadUserByUsername(){
        when(userEntryRepository.findByusername(ArgumentMatchers.anyString())).thenReturn((userEntry) User.builder().username("ram").password("werwer").roles(String.valueOf(new ArrayList<>())).build());
        UserDetails user = userDetailServicimpl.loadUserByUsername("ram");
        Assertions.assertNotNull(user);

    }
}
