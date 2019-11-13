package org.wj.prajumsook.jdbctemplate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DemojdbctemplateApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Test
    void testFindAll() {
        System.out.println(userRepository.findAll());
    }

    @Test
    void createUser() {
        User user = new User();
        user.setId(0);
        user.setName("Joe");
        user.setEmail("joe@hotmail.com");

        User newUser = userRepository.create(user);
        assertNotNull(newUser);
        assertEquals("Joe", newUser.getName());
    }
}
