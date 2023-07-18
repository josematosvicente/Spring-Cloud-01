package com.refactorizando.example.elasticsearch.service;

import com.refactorizando.example.elasticsearch.entity.User;
import com.refactorizando.example.elasticsearch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserRepositoryService {

    private final UserRepository userRepository;

    public void createUserIndexBulk(final List<User> users) {
        userRepository.saveAll(users);
    }

    public User createUserIndex(final User user) {
        return userRepository.save(user);
    }

    public List<User> findUsers() {

        return StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(userRepository.findAll().iterator(), 0), false)
                .collect(Collectors.toList());
    }

    public User findUserByName(String name) {

        return userRepository.findByName(name);
    }
}
