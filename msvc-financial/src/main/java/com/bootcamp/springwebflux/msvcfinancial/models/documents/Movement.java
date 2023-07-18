package com.bootcamp.springwebflux.msvcfinancial.models.documents;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document(collection = "movements")
public class Movement {

    @Id
    private String id;

    private String accountId;
    private String type;
    private Double amount;
    private Date createAt;
}
