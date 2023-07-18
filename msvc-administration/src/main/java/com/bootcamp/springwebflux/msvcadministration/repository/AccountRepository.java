package com.bootcamp.springwebflux.msvcadministration.repository;

import com.bootcamp.springwebflux.msvcadministration.models.documents.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends ReactiveMongoRepository<Account, String> {
}
