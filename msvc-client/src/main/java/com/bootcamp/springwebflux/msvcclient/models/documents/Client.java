package com.bootcamp.springwebflux.msvcclient.models.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "client")
public class Client {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String type;

    public Client() {

    }
}
