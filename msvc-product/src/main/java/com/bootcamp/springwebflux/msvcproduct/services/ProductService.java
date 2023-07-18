package com.bootcamp.springwebflux.msvcproduct.services;

import com.bootcamp.springwebflux.msvcproduct.models.documents.Product;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    public Flux<Product> findAll();
    public Mono<Product> findById(String id);
    public Mono<Product> save(Product product);
    public Mono<Void> delete (Product product);

}
