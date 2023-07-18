package com.refactorizando.example.elasticsearch.controller;

import com.refactorizando.example.elasticsearch.entity.User;
import com.refactorizando.example.elasticsearch.service.UserRepositoryService;
import com.refactorizando.example.elasticsearch.service.UserRestTemplateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
@AllArgsConstructor
public class UserController {

    private final UserRestTemplateService userRestTemplateService;

    private final UserRepositoryService userRepositoryService;

    @GetMapping()
    public ResponseEntity<List<User>> fetchByName(@RequestParam(value = "name", required = false) String name) {
        log.info("searching by name {}",name);
        List<User> users = userRestTemplateService.findUserByName(name) ;
        log.info("users {}",users);
        return ResponseEntity.ok(users);
    }


    @GetMapping("/query")
    public ResponseEntity<List<User>> findByName(@RequestParam(value = "q", required = false) String query) {
        log.info("searching by name {}",query);
        List<User> users = userRestTemplateService.processSearch(query) ;
        log.info("users {}",users);
        return ResponseEntity.ok(users);
    }

    @PostMapping()
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        log.info("Save a new user {} ", user);
        return ResponseEntity.ok(userRepositoryService.createUserIndex(user));
    }

}
