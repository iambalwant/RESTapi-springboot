package com.edigest.myfirstproject.service;

import com.edigest.myfirstproject.entity.userEntry;
import com.edigest.myfirstproject.repository.userEntryRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest //to start whole application context similar to @springbootapplication
public class userServiceTests {

    @Autowired
    private userEntryRepository userEntryRepository;

    //to run this before all test
//    @BeforeAll
//    void setup(){
//
//    }
    //similar
//    @AfterAll
//    @AfterEach
//    @BeforeEach


    @Disabled //to disable the test
//    @Test //this annotation to make method test
    //Valuesource work same as test but give extra feature to give multiple value
    @ValueSource(strings = {
            "ram",
            "sham"
    })
    public void testfindByUserName(String name){
        //assert means dawa kar na
        assertNotNull(userEntryRepository.findByusername(name));
        assertEquals(4,2+1);
    }

    @ParameterizedTest // to test multiple value
    @CsvSource({ //parameters for the test "exp,a,b"
            "1,2,2",
            "3,4,-1"
    })
    public void parameterizedTest(int a,int b,int exp){
        assertEquals(exp,a+b);
    }
}
