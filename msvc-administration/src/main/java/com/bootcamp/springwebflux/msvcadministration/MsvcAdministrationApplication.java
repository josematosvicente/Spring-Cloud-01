package com.bootcamp.springwebflux.msvcadministration;

import com.bootcamp.springwebflux.msvcadministration.models.documents.Account;
import com.bootcamp.springwebflux.msvcadministration.repository.AccountRepository;
import com.bootcamp.springwebflux.msvcadministration.services.AdministrationAccountService;
import com.bootcamp.springwebflux.msvcadministration.webclient.MsvcProductWebClient;
import com.msvc.specification.api.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MsvcAdministrationApplication {

    Logger logger = LoggerFactory.getLogger(MsvcAdministrationApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MsvcAdministrationApplication.class, args);
    }

}
