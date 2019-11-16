package org.wj.prajumsook.controller;

import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.wj.prajumsook.entity.User;
import org.wj.prajumsook.entity.UserService;

@Log4j2
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Publisher<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Publisher<User> getById(@PathVariable(value = "id")String id) {
        return userService.findById(id);
    }

    @PostMapping
    public Publisher<User> createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    public Publisher<User> deleteById(@PathVariable(value = "id") String id) {
        return userService.delete(id);
    }

    @PutMapping
    public Publisher<User> updateUser(@RequestBody User user) {
        return userService.save(user);
    }
}
