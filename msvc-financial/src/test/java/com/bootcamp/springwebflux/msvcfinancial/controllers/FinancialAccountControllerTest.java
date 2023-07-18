package com.bootcamp.springwebflux.msvcfinancial.controllers;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.bootcamp.springwebflux.msvcfinancial.mapper.FinancialMapper;
import com.bootcamp.springwebflux.msvcfinancial.models.documents.Movement;
import com.bootcamp.springwebflux.msvcfinancial.services.FinancialAccountService;
import com.msvc.specification.api.dto.AccountDto;
import com.msvc.specification.api.dto.MovementDto;

import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FinancialAccountControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    FinancialMapper financialMapper;

    @MockBean
    FinancialAccountService financialAccountService;

    @Test
    void testBalanceAccount() {

    }

    @Test
    void testBalanceCredit() {

    }

    @Test
    void testMovement() {
        Movement movement = new Movement();
        movement.setAmount(Double.valueOf(100));
        movement.setType("DEPOSIT");
        movement.setId("632767dfb8cc775d5e1f5743");

        MovementDto movementDto = new MovementDto();
        movementDto.setAmount(Double.valueOf(100));
        movementDto.setType("DEPOSIT");

        AccountDto accountDto = new AccountDto();
        accountDto.setBalance(Double.valueOf(100));

        Mockito.when(financialAccountService.save("1234",movement))
            .thenReturn(Mono.just(accountDto));

        Mockito.when(financialMapper.toModel(movementDto))
            .thenReturn(movement);
    
        webClient.post()
        .uri("/finantial/account/{id}/movement","1234")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .body(Mono.just(movementDto), Movement.class)
        .exchange()
        .expectStatus().isCreated();
    
        Mockito.verify(financialAccountService).save("1234",movement);
    }

    @Test
    void testMovementCredit() {

    }
}
