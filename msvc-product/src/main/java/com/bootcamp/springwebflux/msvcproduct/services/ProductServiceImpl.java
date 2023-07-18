package com.bootcamp.springwebflux.msvcproduct.services;


import com.bootcamp.springwebflux.msvcproduct.models.documents.Product;
import com.bootcamp.springwebflux.msvcproduct.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Flux<Product> findAll() {
        logger.info("ProductServiceImpl: findAll");
        return productRepository.findAll();
    }

    @Override
    public Mono<Product> findById(String id) {
        logger.info("ProductServiceImpl: findById");
        return productRepository.findById(id);
    }

    @Override
    public Mono<Product> save(Product product) {
        logger.info("ProductServiceImpl: save");
        return productRepository.save(product);
    }

    @Override
    public Mono<Void> delete(Product product) {
        logger.info("ProductServiceImpl: delete");
        return productRepository.delete(product);
    }


}
