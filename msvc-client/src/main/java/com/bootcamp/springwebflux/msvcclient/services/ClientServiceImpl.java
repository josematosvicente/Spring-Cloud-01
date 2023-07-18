package com.bootcamp.springwebflux.msvcclient.services;

import com.bootcamp.springwebflux.msvcclient.models.documents.Client;
import com.bootcamp.springwebflux.msvcclient.repository.ClientRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService{

    Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);
    @Autowired
    private ClientRepository clientRepository;

    @Override
    @RequestMapping(value = "/")
    @HystrixCommand(fallbackMethod = "fallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public Flux<Client> findAll() {
        logger.info("ClientServiceImpl: findAll");
        return clientRepository.findAll();
    }

    @Override
    public Mono<Client> findById(String id) {
        logger.info("ClientServiceImpl: findById");
        return clientRepository.findById(id);
    }

    @Override
    public Mono<Client> save(Client client) {
        logger.info("ClientServiceImpl: save");
        return clientRepository.save(client);
    }

    @Override
    public Mono<Void> delete(Client client) {
        logger.info("ClientServiceImpl: delete");
        return clientRepository.delete(client);
    }

    private Flux<Client> fallbackMethod() {
        return Flux.empty();
    }

}
