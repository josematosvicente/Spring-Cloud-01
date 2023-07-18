package com.bootcamp.springwebflux.msvcfinancial.services;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.springwebflux.msvcfinancial.models.documents.Movement;
import com.bootcamp.springwebflux.msvcfinancial.repository.MovementRepository;
import com.bootcamp.springwebflux.msvcfinancial.webclient.MsvcAdministrativeWebClient;
import com.msvc.specification.api.dto.AccountDto;
import com.msvc.specification.api.dto.AdministrativeAccountDto;

import reactor.core.publisher.Mono;

/**
 * FinancialAccountService.
 */
@Service
public class FinancialAccountServiceImpl implements FinancialAccountService {

    /**
     * Logger.
     */
    Logger logger = LoggerFactory.getLogger(FinancialAccountServiceImpl.class);

    /**
     * MovementRepository.
     */
    @Autowired
    private MovementRepository movementRepository;

    /**
     * MsvcAdministrativeWebClient.
     */
    @Autowired
    private MsvcAdministrativeWebClient msvcAdministrativeClient;

    /**
     * This method save Movement.
     * @return AccountDto
     */
    @Override
    public Mono<AccountDto> save(final String id, final Movement movement) {
        logger.info("save(Movements getAccountId): " + id);
        movement.setAccountId(id);
        movement.setCreateAt(new Date());
        AdministrativeAccountDto account = new AdministrativeAccountDto();
        return Mono.just(movement)
            .flatMap(mov-> msvcAdministrativeClient.getAccount(id))
            .flatMap(acc -> validate(acc, movement)
                .flatMap(acv -> movementRepository.save(movement))
                .flatMap(bal-> 
                    {
                        acc.setBalance(acc.getBalance() == null ? 0 : acc.getBalance());
                        if(movement.getType().equals("DEPOSIT")){
                            account.setBalance(acc.getBalance() + movement.getAmount());
                        } else {
                            account.setBalance(acc.getBalance() - movement.getAmount());
                        }
                        acc.setBalance(account.getBalance());
                        return msvcAdministrativeClient.updateBalance(id, account).then(Mono.just(acc));
                    }
                ).map(accountMap -> {
                    AccountDto accountDto = new AccountDto();
                    accountDto.setBalance(accountMap.getBalance());
                    return accountDto;
                })
            );
    }

    public Mono<AdministrativeAccountDto> validate(AdministrativeAccountDto administrativeAccountDto,
            Movement movement) {
                return validateBalance(administrativeAccountDto, movement)
                    .flatMap(val -> validateBalance(administrativeAccountDto, movement));
            }

    public Mono<AdministrativeAccountDto> validateBalance(AdministrativeAccountDto administrativeAccountDto,
            Movement movement) {
        return (administrativeAccountDto.getBalance() < movement.getAmount()
                &&
                !movement.getType().equals("DEPOSIT")) ?
                    Mono.error(() -> new RuntimeException("No tiene saldo disponible para el retiro"))
                :
                    Mono.just(administrativeAccountDto);
    }
}
