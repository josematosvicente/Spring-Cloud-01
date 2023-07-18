package com.bootcamp.springwebflux.msvcadministration.services;

import com.bootcamp.springwebflux.msvcadministration.models.documents.Account;
import com.msvc.specification.api.dto.NewAdministrativeAccountDto;

import reactor.core.publisher.Mono;

public interface AdministrationAccountService {


    public Mono<Account> save(Account account);
    public Mono<Account> postAccount(NewAdministrativeAccountDto newAdministrativeAccountDto);
    Mono<Account> findAccountById(String id);
}
