package com.bootcamp.springwebflux.msvcproduct.controllers;

import com.bootcamp.springwebflux.msvcproduct.mapper.ProductMapper;
import com.bootcamp.springwebflux.msvcproduct.services.ProductService;
import com.msvc.specification.api.ProductsApi;
import com.msvc.specification.api.dto.NewProductDto;
import com.msvc.specification.api.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
public class ProductController implements ProductsApi {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Mono<ResponseEntity<ProductDto>> addProduct(Mono<NewProductDto> newProduct, ServerWebExchange exchange) {
        return newProduct.flatMap(newProductDto ->  productService.save(productMapper.toModel(newProductDto)))
                .map(product ->
                        ResponseEntity.created(URI.create("/api/products/".concat(product.getId())))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(productMapper.toDto(product)));

    }

    @Override
    public Mono<ResponseEntity<Void>> deleteProduct(String id, ServerWebExchange exchange) {
        return productService.findById(id).flatMap(product -> {
            return productService.delete(product).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
        }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

    @Override
    public Mono<ResponseEntity<ProductDto>> findProductById(String id, ServerWebExchange exchange) {
        return productService.findById(id).map(client -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productMapper.toDto(client))
        ).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<Flux<ProductDto>>> findProducts(ServerWebExchange exchange) {

        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.findAll().map(productMapper::toDto)));
    }

    @Override
    public Mono<ResponseEntity<ProductDto>> updateProduct(String id, Mono<NewProductDto> newProductDto, ServerWebExchange exchange) {
        return newProductDto.flatMap(productDto ->
                productService.findById(id).flatMap(product -> {
                    product.setName(productDto.getName());
                    product.setType(productDto.getType());
                    product.setCategory(productDto.getCategory());
                    product.setCommission(productDto.getCommission());
                    product.setLimitDeposit(productDto.getLimitDeposit());
                    product.setLimitWithdrawal(productDto.getLimitWithdrawal());
                    product.setLine(productDto.getLine());
                    return productService.save(product);
                })).map(product -> ResponseEntity.created(URI.create("/api/products/".concat(product.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(productMapper.toDto(product)));
    }
}
