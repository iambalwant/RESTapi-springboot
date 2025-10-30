package com.edigest.myfirstproject.repository;

import com.edigest.myfirstproject.entity.userEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {


    @Autowired
    private MongoTemplate mongoTemplate;

    public List<userEntry> getUserForSA(){
        Query query = new Query();
        Criteria criteria = new Criteria();

//        query.addCriteria(Criteria.where("email").regex(""));

        query.addCriteria(criteria.orOperator(
           (Criteria.where("name").is("vipul")),
           (Criteria.where("sentimentAnalysis").is(true))
        ));

        List<userEntry> User = mongoTemplate.find(query, userEntry.class);
        return User;
    }

}

