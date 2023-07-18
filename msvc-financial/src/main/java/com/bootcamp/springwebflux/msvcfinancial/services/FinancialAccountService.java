package com.bootcamp.springwebflux.msvcfinancial.services;

import com.bootcamp.springwebflux.msvcfinancial.models.documents.Movement;
import com.msvc.specification.api.dto.AccountDto;

import reactor.core.publisher.Mono;

public interface FinancialAccountService {

    Mono<AccountDto> save(String id, Movement movement);
}
