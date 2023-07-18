package com.bootcamp.springwebflux.msvcfinancial.webclient;

import com.msvc.specification.api.dto.AdministrativeAccountDto;
import com.msvc.specification.api.dto.NewProductDto;
import com.msvc.specification.api.dto.ProductDto;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * AdministrativeWebClient.
 */
@Slf4j
@Service
public class MsvcAdministrativeWebClient {

    /**
     * Logger.
     */
    // private Logger logger = LoggerFactory.getLogger(MsvcProductWebClient.class);

    /**
     * WebClient.
     */
    private WebClient webclient = WebClient.create("http://localhost:8080");

    /**
     * This method getAccount by id.
     * @return AdministrativeAccountDto
     */
    public Mono<AdministrativeAccountDto> getAccount(final String id) {
        log.info(String.format("Calling getAccount (%s)", id));

        return webclient.get()
                .uri("/administrative/accounts/{id}", id)
                .retrieve()
                .bodyToMono(AdministrativeAccountDto.class);
    }

    /**
     * This method updateBalance by AdministrativeAccountDto.
     * @return AdministrativeAccountDto
     */
    public Mono<Void> updateBalance(final String id, final AdministrativeAccountDto account) {
        log.info(String.format("Calling updateBalance (%s)", id));

        return webclient.put()
                .uri("/administrative/accounts/{id}", id)
                .body(Mono.just(account), AdministrativeAccountDto.class)
                .retrieve()
                .bodyToMono(Void.class);
    }

    /**
     * This method save NewProductDto.
     * @return ProductDto
     */
    public Mono<ProductDto> postProduct(final NewProductDto newProductDto) {
        log.info(String.format("Calling postProduct (%s)", newProductDto));

        return webclient.post()
                .uri("/products")
                .body(Mono.just(newProductDto), NewProductDto.class)
                .retrieve()
                .bodyToMono(ProductDto.class);
    }

}
