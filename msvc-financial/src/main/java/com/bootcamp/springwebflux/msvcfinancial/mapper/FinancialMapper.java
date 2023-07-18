package com.bootcamp.springwebflux.msvcfinancial.mapper;

import org.mapstruct.Mapper;

import com.bootcamp.springwebflux.msvcfinancial.models.documents.Movement;
import com.msvc.specification.api.dto.MovementDto;

@Mapper(componentModel = "spring")
public interface FinancialMapper {

    Movement toModel(MovementDto value);

}
