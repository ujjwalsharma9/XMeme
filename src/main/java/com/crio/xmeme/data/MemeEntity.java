package com.crio.xmeme.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.validation.constraints.NotNull;


import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Data
@Document(collection = "memes")
@NoArgsConstructor
public class MemeEntity {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String url;

    @NotNull
    private String caption;

    @NotNull
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date createdDate;
    
}