package com.edigest.myfirstproject.repository;

import com.edigest.myfirstproject.entity.journalEntry;
import com.edigest.myfirstproject.entity.userEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface userEntryRepository extends MongoRepository<userEntry, ObjectId> {

//Spring Data automatically generates queries based on the method name.
//The pattern is: findBy<FieldName><Condition>() : <Condition> → optional (like And, Or, Between, etc.)
//Example:
//List<userEntry> findByUsernameOrPassword(String username, String password);
//Finds all users where either username or password matches.
//Onw for example :
//List<userEntry> findByAgeGreaterThan(int age);
//List<userEntry> findByAgeLessThan(int age);
//List<userEntry> findByAgeBetween(int start, int end);
//Automatically generates queries like { "age": { "$gt": 18 } }
    userEntry findByusername(String username);
}
