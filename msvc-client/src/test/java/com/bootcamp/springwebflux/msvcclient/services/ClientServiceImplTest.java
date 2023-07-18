package com.bootcamp.springwebflux.msvcclient.services;

import com.bootcamp.springwebflux.msvcclient.models.documents.Client;
import com.bootcamp.springwebflux.msvcclient.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class ClientServiceImplTest {
    @Mock
    private ClientRepository oClientRepository;
    @InjectMocks
    private ClientServiceImpl oClientServiceImpl;

    private WebTestClient oWebTestClient;
    private Client oClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        oClient = new Client();
        oClient.setId(new String());
        oClient.setFirstName("Primer Nombre");
        oClient.setLastName("Primer Apellido");
        oClient.setType("Tipo test");
    }

    @Test
    void findAll() {
        when(oClientRepository.findAll()).thenReturn(Flux.just(oClient));
        assertNotNull(oClientServiceImpl.findAll());
    }

    @Test
    void findById() {
        when(oClientRepository.findById(new String())).thenReturn(Mono.just(oClient));
        assertNotNull(oClientServiceImpl.findById(new String()));
    }

    @Test
    void save() {
        when(oClientRepository.save(any(Client.class))).thenReturn(Mono.just(oClient));
        assertNotNull(oClientServiceImpl.save(new Client()));
    }

    @Test
    void delete() {
        when(oClientRepository.findById(new String())).thenReturn(Mono.just(oClient));
        oClientServiceImpl.delete(oClient);
    }
}