package com.bootcamp.springwebflux.msvcfinancial.webclient;

import com.msvc.specification.api.dto.NewProductDto;
import com.msvc.specification.api.dto.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Service ProductWebClient.
 */
@Service
public class MsvcProductWebClient {

    /**
     * Logger.
     */
    private Logger logger = LoggerFactory.getLogger(MsvcProductWebClient.class);

    /**
     * WebClient.
     */
    private WebClient webclient = WebClient.create("http://localhost:8080");

    /**
     * This method get Product.
     * @return Product
     */
    public final Mono<ProductDto> getProduct(final String id) {
        logger.info(String.format("Calling getProduct (%s)", id));

        return webclient.get()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(ProductDto.class);
    }

    /**
     * This method update Product.
     * @return Product
     */
    public final Mono<ProductDto> postProduct(final NewProductDto newProductDto) {
        logger.info(String.format("Calling postProduct (%s)", newProductDto));

        return webclient.post()
                .uri("/products")
                .body(Mono.just(newProductDto), NewProductDto.class)
                .retrieve()
                .bodyToMono(ProductDto.class);
    }

}
