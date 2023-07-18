package com.bootcamp.springwebflux.msvcadministration.webclient;

import com.msvc.specification.api.dto.ClientDto;
import com.msvc.specification.api.dto.NewClientDto;
import com.msvc.specification.api.dto.NewProductDto;
import com.msvc.specification.api.dto.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class MsvcClientWebClient {

    Logger logger = LoggerFactory.getLogger(MsvcProductWebClient.class);

    WebClient webclient = WebClient.create("http://localhost:8080");

    public Mono<ClientDto> getClientById(String id) {
        logger.info(String.format("Calling getClient (%s)", id));

        return webclient.get()
                .uri("/clients/{id}", id)
                .retrieve()
                .bodyToMono(ClientDto.class);
    }

    public Mono<ClientDto> postClient(NewClientDto newClientDto) {
        logger.info(String.format("Calling postClient (%s)", newClientDto));

        return webclient.post()
                .uri("/clients")
                .body(Mono.just(newClientDto), NewClientDto.class)
                .retrieve()
                .bodyToMono(ClientDto.class);
    }
}
