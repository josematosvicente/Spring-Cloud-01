package com.bootcamp.springwebflux.msvcadministration.controllers;

import com.bootcamp.springwebflux.msvcadministration.mapper.AdministrationMapper;
import com.bootcamp.springwebflux.msvcadministration.services.AdministrationAccountService;
import com.bootcamp.springwebflux.msvcadministration.services.AdministrationCreditService;
import com.msvc.specification.api.AdministrativeApi;
import com.msvc.specification.api.dto.AdministrativeAccountDto;
import com.msvc.specification.api.dto.CreditDto;
import com.msvc.specification.api.dto.NewAdministrativeAccountDto;
import com.msvc.specification.api.dto.NewCreditDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
public class AdministrationController implements AdministrativeApi {

    @Autowired
    private AdministrationAccountService administrationAccountService;

    @Autowired
    private AdministrationCreditService administrationCreditService;

    @Autowired
    private AdministrationMapper administrationMapper;

    @Override
    public Mono<ResponseEntity<AdministrativeAccountDto>> addAccount(Mono<NewAdministrativeAccountDto> newAdministrativeAccountDto, ServerWebExchange exchange) {

        return newAdministrativeAccountDto.flatMap(newAdministrativeAccountDto1 -> administrationAccountService.postAccount(newAdministrativeAccountDto1)
                .map(account ->
                        ResponseEntity.created(URI.create("/administrative/accounts/".concat(account.getId())))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(administrationMapper.toAdministrativeAccountDto(account))));
    }

    @Override
    public Mono<ResponseEntity<CreditDto>> addCredit(Mono<NewCreditDto> newCreditDto, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteAccount(String id, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteCredit(String id, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<AdministrativeAccountDto>> findAccountById(String id, ServerWebExchange exchange) {
        return administrationAccountService.findAccountById(id).map(account -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(administrationMapper.toAdministrativeAccountDto(account))
        ).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<Flux<AdministrativeAccountDto>>> findAccounts(ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<CreditDto>> findCreditById(String id, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Flux<CreditDto>>> findCredits(ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Void>> updateAccount(String id, Mono<AdministrativeAccountDto> administrativeAccountDto, ServerWebExchange exchange) {
        return 
        administrativeAccountDto.flatMap(accountDto -> 
        administrationAccountService.findAccountById(id).flatMap(account -> {
                account.setBalance(accountDto.getBalance());
                return administrationAccountService.save(account).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
            }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND))
        
        );
    }

    @Override
    public Mono<ResponseEntity<Void>> updateCredit(String id, Mono<CreditDto> creditDto, ServerWebExchange exchange) {
        return null;
    }
}
