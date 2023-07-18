package com.refactorizando.example.elasticsearch.repository;

import com.refactorizando.example.elasticsearch.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface UserRepository extends ElasticsearchRepository<User, String> {

    User findByName(String name);

}
