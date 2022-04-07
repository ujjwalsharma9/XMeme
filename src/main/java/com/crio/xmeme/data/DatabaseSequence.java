package com.crio.xmeme.data;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "database_sequences")
@Getter
@Setter
public class DatabaseSequence {

    @Id
    private String id;

    private int seq;

    //getters and setters omitted
}