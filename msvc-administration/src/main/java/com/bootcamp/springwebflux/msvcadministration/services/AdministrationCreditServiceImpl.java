package com.bootcamp.springwebflux.msvcadministration.services;

import com.bootcamp.springwebflux.msvcadministration.models.documents.Account;
import com.msvc.specification.api.dto.CreditDto;
import com.msvc.specification.api.dto.NewAdministrativeAccountDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AdministrationCreditServiceImpl implements AdministrationCreditService{
    @Override
    public Mono<CreditDto> postCredit(NewAdministrativeAccountDto newAdministrativeAccountDto) {
        return null;
    }
}
