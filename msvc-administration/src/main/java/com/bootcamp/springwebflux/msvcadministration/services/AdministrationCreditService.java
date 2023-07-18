package com.bootcamp.springwebflux.msvcadministration.services;

import com.bootcamp.springwebflux.msvcadministration.models.documents.Account;
import com.msvc.specification.api.dto.CreditDto;
import com.msvc.specification.api.dto.NewAdministrativeAccountDto;
import reactor.core.publisher.Mono;

public interface AdministrationCreditService {

    public Mono<CreditDto> postCredit(NewAdministrativeAccountDto newAdministrativeAccountDto);
}
