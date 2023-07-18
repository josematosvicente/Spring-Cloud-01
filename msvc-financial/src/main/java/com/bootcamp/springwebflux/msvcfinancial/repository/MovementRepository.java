package com.bootcamp.springwebflux.msvcfinancial.repository;

import org.springframework.stereotype.Repository;

import com.bootcamp.springwebflux.msvcfinancial.models.documents.Movement;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

@Repository
public interface MovementRepository extends ReactiveMongoRepository<Movement, String> {

}
