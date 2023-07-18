package com.bootcamp.springwebflux.msvcadministration.mapper;

import com.bootcamp.springwebflux.msvcadministration.models.documents.Account;
import com.msvc.specification.api.dto.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdministrationMapper {

    AccountProductDto toAccountProductDto(ProductDto value);

    NewProductDto toNewProductDto(NewAccountProductDto value);

    NewProductDto toNewProductDto(NewAdministrativeAccountDto value);

    AdministrativeAccountDto toAdministrativeAccountDto(Account value);

    Account toAccount(NewAdministrativeAccountDto value);

}
