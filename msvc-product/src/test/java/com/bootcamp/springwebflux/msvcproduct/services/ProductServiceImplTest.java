package com.bootcamp.springwebflux.msvcproduct.services;

import com.bootcamp.springwebflux.msvcproduct.models.documents.Product;
import com.bootcamp.springwebflux.msvcproduct.repository.ProductRepository;
import jdk.jfr.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProductServiceImplTest {

    @Mock
    private ProductRepository oProductRepository;
    @InjectMocks
    private ProductServiceImpl oProductServiceImpl;

    private Product oProduct;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        oProduct = new Product();
        oProduct.setId(new String());
        oProduct.setName("Producto1");
        oProduct.setType("Tipo1");
        oProduct.setCategory("Categoria1");
        oProduct.setCommission(1.00);
        oProduct.setLimitDeposit(1.00);
        oProduct.setLimitWithdrawal(1.00);
        oProduct.setLine(1.00);
        oProduct.setCreateAt(new Date());

    }

    @Test
    void findAll() {
        when(oProductRepository.findAll()).thenReturn(Flux.just(oProduct));
        assertNotNull(oProductServiceImpl.findAll());
    }

    @Test
    void findById() {
        when(oProductRepository.findById(new String())).thenReturn(Mono.just(oProduct));
        assertNotNull(oProductServiceImpl.findById(new String()));
    }

    @Test
    void save() {
        when(oProductRepository.save(any(Product.class))).thenReturn(Mono.just(oProduct));
        assertNotNull(oProductServiceImpl.save(new Product()));
    }

    @Test
    void delete() {
        when(oProductRepository.findById(new String())).thenReturn(Mono.just(oProduct));
        oProductServiceImpl.delete(oProduct);
    }
}