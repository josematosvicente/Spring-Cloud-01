package com.bootcamp.springwebflux.msvcclient.repository;

import com.bootcamp.springwebflux.msvcclient.models.documents.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client, String> {
}
