package com.bootcamp.springwebflux.msvcfinancial.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.bootcamp.springwebflux.msvcfinancial.mapper.FinancialMapper;
import com.bootcamp.springwebflux.msvcfinancial.producer.TopicProducer;
import com.bootcamp.springwebflux.msvcfinancial.services.FinancialAccountService;
import com.msvc.specification.api.FinantialApi;
import com.msvc.specification.api.dto.AccountDto;
import com.msvc.specification.api.dto.MovementDto;

import reactor.core.publisher.Mono;

@RestController
public class FinancialAccountController implements FinantialApi {

    @Autowired
    private FinancialAccountService financialAccountService;

    @Autowired
    private FinancialMapper financialMapper;
    
    @Autowired
    private TopicProducer topicProducer;

    @Override
    public Mono<ResponseEntity<AccountDto>> balanceAccount(final String arg0,
        final @Valid Mono<AccountDto> accountDto,
            final ServerWebExchange arg2) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<AccountDto>> balanceCredit(final String arg0,
        final @Valid Mono<AccountDto> arg1,
            final ServerWebExchange arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Mono<ResponseEntity<AccountDto>> movement(final String id,
        final @Valid Mono<MovementDto> movementDto,
            final ServerWebExchange arg2) {
                topicProducer.send("Mensagem de teste enviada ao tópico");
        return movementDto.flatMap(movement -> financialAccountService.save(id, financialMapper.toModel(movement))
            .map(account ->
                ResponseEntity.created(URI.create("/administrative/accounts/".concat(id)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(account)));
        // return null;
    }

    @Override
    public Mono<ResponseEntity<AccountDto>> movementCredit(final String arg0, final @Valid Mono<MovementDto> arg1,
            final ServerWebExchange arg2) {
        // TODO Auto-generated method stub
        return null;
    }

}
