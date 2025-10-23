package com.edigest.myfirstproject.repository;

import com.edigest.myfirstproject.entity.journalEntry;
import com.edigest.myfirstproject.entity.userEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface userEntryRepository extends MongoRepository<userEntry, ObjectId> {
    userEntry findByusername(String username);
}
