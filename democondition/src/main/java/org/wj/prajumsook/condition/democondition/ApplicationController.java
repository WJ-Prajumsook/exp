package org.wj.prajumsook.condition.democondition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wj.prajumsook.condition.democondition.service.UserService;

import java.util.List;

@RestController
public class ApplicationController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public List<String> getUser() {
        return userService.getUser();
    }
}
