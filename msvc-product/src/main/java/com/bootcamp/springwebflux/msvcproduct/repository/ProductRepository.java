package com.bootcamp.springwebflux.msvcproduct.repository;

import com.bootcamp.springwebflux.msvcproduct.models.documents.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
}

