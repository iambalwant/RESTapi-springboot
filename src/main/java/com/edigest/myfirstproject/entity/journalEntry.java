package com.edigest.myfirstproject.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

//@Document tells Spring Boot that this class represents a MongoDB collection.
@Document(collection = "journal_Entries")
//@Getter
//@Setter //by lombok to reduce the boilPlate and install plugin
@Data //Equivalent to @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode
public class journalEntry {
    @Id //to map as primary (unique) key
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;


//----------use to reduce the boilerplate with lombok project------------
//    public LocalDateTime getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }
//
//
//    public ObjectId getId() {
//        return id;
//    }
//
//    public void setId(ObjectId id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
}
