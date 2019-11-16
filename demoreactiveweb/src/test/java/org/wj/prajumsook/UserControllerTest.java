package org.wj.prajumsook;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.wj.prajumsook.entity.User;
import org.wj.prajumsook.entity.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@WebFluxTest
public class UserControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UserService userService;

    @Test
    public void getById() {
        log.info("Running get-by-id");
        User user = new User("12345", "John", "john@email.com");
        Mockito.when(userService.findById(user.getId()))
                .thenReturn(Mono.just(user));

        webTestClient.get()
                .uri("/user/"+user.getId())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void getAll() {
        log.info("Running get all user");
        Mockito.when(userService.findAll())
                .thenReturn(Flux.just(new User("1", "John", "john@email.com")));

        webTestClient.get()
                .uri("/user")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].id").isEqualTo("1");
    }

    @Test
    public void createUser() {
        log.info("Running create user");
        User user = new User("12345", "John", "john@email.com");
        Mockito.when(userService.save(Mockito.any(User.class)))
                .thenReturn(Mono.just(user));

        webTestClient.post()
                .uri("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), User.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void delete() {
        log.info("Running delete user");
        User user = new User("12345", "John", "john@email.com");
        Mockito.when(userService.findById(user.getId()))
                .thenReturn(Mono.just(user));
        Mockito.when(userService.delete(user.getId()))
                .thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/user/"+user.getId())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void update() {
        log.info("Running delete user");
        User user = new User("12345xxxx", "John", "john@email.com");

        Mockito.when(userService.findById(user.getId()))
                .thenReturn(Mono.just(user));

        Mockito.when(userService.save(user))
                .thenReturn(Mono.just(user));

        webTestClient.put()
                .uri("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), User.class)
                .exchange()
                .expectStatus().isOk();

    }
}
