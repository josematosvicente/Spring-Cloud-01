package com.bootcamp.springwebflux.msvcproduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.msvc.specification.api.dto.NewProductDto;

import reactor.core.publisher.Mono;

@SpringBootApplication
public class MsvcProductApplication implements CommandLineRunner{

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Override
    public void run(String... args) throws Exception {

        // mongoTemplate.dropCollection("product");

        NewProductDto newProductDto = new NewProductDto();
        newProductDto.setName("Cuenta Bancaria de Ahorro");
        newProductDto.setType("Personal");
        newProductDto.setCategory("Pasivo");
        newProductDto.setCommission(Double.valueOf(100.0));
        newProductDto.setLimitDeposit(Double.valueOf(5000.0));
        newProductDto.setLimitWithdrawal(Double.valueOf(1000.0));
        newProductDto.setLine(null);

        mongoTemplate.save(newProductDto);

    }

    public static void main(String[] args) {
        SpringApplication.run(MsvcProductApplication.class, args);
    }

}
