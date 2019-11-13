package org.wj.prajumsook.swagger.doc.demoswaggerdoc;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<User> getUsers() {
        Iterable<User> it = userRepository.findAll();
        Iterator iterator = it.iterator();
        List<User> users = new ArrayList<>();
        while (iterator.hasNext()) {
            User user = (User) iterator.next();
            users.add(user);
        }

        return users;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable(value = "id") Integer id) {
        return userRepository.findById(id).get();
    }

    @PostMapping
    public User postUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping
    public User putUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping
    public Boolean deleteUser(@PathVariable(name = "id") Integer id) {
        userRepository.deleteById(id);

        return true;
    }
}
