package com.bootcamp.springwebflux.msvcclient.controllers;

import com.bootcamp.springwebflux.msvcclient.mapper.ClientMapper;
import com.bootcamp.springwebflux.msvcclient.models.documents.Client;
import com.bootcamp.springwebflux.msvcclient.services.ClientServiceImpl;
import com.msvc.specification.api.dto.ClientDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClientControllerTest {
    @MockBean
    private ClientServiceImpl oClientServiceImpl;
    @Autowired
    private WebTestClient oWebTestClient;
    private Client oClient;

    @MockBean
    ClientMapper clientMapper;

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
    void addClient() {
        when(oClientServiceImpl.save(any(Client.class))).thenReturn(Mono.just(oClient));
        Mockito.when(clientMapper.toModel(any()))
                .thenReturn(oClient);
        oWebTestClient.post()
                .uri("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(oClient))
                .exchange()
                .expectStatus().isCreated();
        verify(oClientServiceImpl,times(1)).save(oClient);
    }

    @Test
    void deleteClient() {
    }

    @Test
    void findClientById() {
        when(oClientServiceImpl.save(any(Client.class))).thenReturn(Mono.just(oClient));
        Mockito.when(clientMapper.toModel(any()))
                .thenReturn(oClient);
        oWebTestClient.post()
                .uri("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(oClient))
                .exchange()
                .expectStatus().isCreated();
        verify(oClientServiceImpl,times(1)).save(oClient);
    }

    @Test
    void findClients() {
        when(oClientServiceImpl.save(any(Client.class))).thenReturn(Mono.just(oClient));
        Mockito.when(clientMapper.toModel(any()))
                .thenReturn(oClient);
        oWebTestClient.post()
                .uri("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(oClient))
                .exchange()
                .expectStatus().isCreated();
        verify(oClientServiceImpl,times(1)).save(oClient);
    }

    @Test
    void updateClient() {
    }
}