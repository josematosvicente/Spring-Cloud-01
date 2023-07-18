package com.bootcamp.springwebflux.msvcclient.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.bootcamp.springwebflux.msvcclient.mapper.ClientMapper;
import com.bootcamp.springwebflux.msvcclient.services.ClientService;
import com.msvc.specification.api.ClientsApi;
import com.msvc.specification.api.dto.ClientDto;
import com.msvc.specification.api.dto.NewClientDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@EnableHystrix
public class ClientController implements ClientsApi {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientMapper mapper;

    @Override
    public Mono<ResponseEntity<ClientDto>> addClient(Mono<NewClientDto> newClient, ServerWebExchange exchange) {
        return newClient.flatMap(newClientDTO -> clientService.save(mapper.toModel(newClientDTO)))
                .map(client ->
                        ResponseEntity.created(URI.create("/clients/".concat(client.getId())))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(mapper.toDto(client)));

    }

    @Override
    public Mono<ResponseEntity<Void>> deleteClient(String id, ServerWebExchange exchange) {
        return clientService.findById(id).flatMap(client -> {
            return clientService.delete(client).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
        }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

    @Override
    public Mono<ResponseEntity<ClientDto>> findClientById(String id, ServerWebExchange exchange) {
        return clientService.findById(id).map(client -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.toDto(client))
        ).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<Flux<ClientDto>>> findClients(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(clientService.findAll().map(mapper::toDto)));
    }

    @Override
    public Mono<ResponseEntity<ClientDto>> updateClient(String id, Mono<NewClientDto> newClientDto, ServerWebExchange exchange) {
        return newClientDto.flatMap(clientDto ->
                clientService.findById(id).flatMap(client -> {
                    client.setFirstName(clientDto.getFirstName());
                    client.setLastName(clientDto.getLastName());
                    client.setType(clientDto.getType());
                    return clientService.save(client);
                })).map(client -> ResponseEntity.created(URI.create("/clients/".concat(client.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.toDto(client)));
    }

}
