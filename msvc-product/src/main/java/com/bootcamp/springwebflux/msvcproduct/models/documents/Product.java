package com.bootcamp.springwebflux.msvcproduct.models.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "product")
public class Product {

    @Id
    private String id;

    private String name;
    private String type;
    private String category;
    private Double commission;
    private Double limitDeposit;
    private Double limitWithdrawal;
    private Double line;
    private Date createAt;

    public Product() {

    }
}