package com.bootcamp.springwebflux.msvcclient.mapper;
 
import org.mapstruct.Mapper;

import com.bootcamp.springwebflux.msvcclient.models.documents.Client;
import com.msvc.specification.api.dto.ClientDto;
import com.msvc.specification.api.dto.NewClientDto;

 
@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDto toDto(Client value);

    Client toModel(NewClientDto value);

}