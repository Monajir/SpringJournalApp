package com.JournalApp.Journal.entity;

import com.JournalApp.Journal.enums.Sentiment;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entry")
//@Getter
//@Setter
@Data// It has getr, setr, no arg, all arg, equals(), hashCode(), toString() which are created during compilation as per requirement
@NoArgsConstructor
public class JournalEntry {

    @Id
    private ObjectId id;

    private String title;

    private String content;

    private LocalDateTime date;

    private Sentiment sentiment;
}
