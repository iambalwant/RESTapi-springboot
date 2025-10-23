package com.edigest.myfirstproject.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@Document tells Spring Boot that this class represents a MongoDB collection.
@Document(collection = "users")
@Data
@NoArgsConstructor
public class userEntry {
    @Id
    private ObjectId id;
    @Indexed(unique = true) //it will not create automatically set true in application.property
    @NonNull
    private String username;
    @NonNull
    private String password;
    @DBRef
    private List<journalEntry> journalEntries = new ArrayList<>();
}
