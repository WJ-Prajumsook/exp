package org.wj.prajumsook.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ApplicationController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    public List<User> getUser() {
        return userRepository.findAll();
    }

    @PostMapping("/user")
    public User create(@RequestBody User user) {
        return userRepository.create(user);
    }
}
