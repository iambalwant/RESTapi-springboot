package com.edigest.myfirstproject.repository;

import com.edigest.myfirstproject.entity.journalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

//This interface acts as the Data Access Layer (Repository) for your MongoDB.
//
//MongoRepository<journalEntry, String> means:
//The entity class is journalEntry
//The type of the @Id field is String

public interface journalEntryRepository extends MongoRepository<journalEntry, ObjectId> {

}
