package com.bootcamp.springwebflux.msvcadministration.models.documents;

import com.msvc.specification.api.dto.AccountProductDto;
import com.msvc.specification.api.dto.ClientDto;
import com.msvc.specification.api.dto.ProductDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Document(collection = "account")
public class Account {

    @Id
    private String id;

    public Account(List<ClientDto> clientList, ProductDto productDto, Double balance) {
        this.clientList = clientList;
        this.productDto = productDto;
        this.balance = balance;
    }

    public Account(){}

    private List<ClientDto> clientList;
    private ProductDto productDto;

    private Double balance;

}
