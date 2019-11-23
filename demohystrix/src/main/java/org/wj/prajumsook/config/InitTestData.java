package org.wj.prajumsook.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.wj.prajumsook.entity.Post;
import org.wj.prajumsook.service.PostService;

import java.util.UUID;

@Component
@Profile("test")
@Log4j2
public class InitTestData implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private PostService service;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        service.deleteAll();
        service.save(new Post(UUID.randomUUID().toString(), "Post Nr: 1 title", "Post Nr: 1 content"));
        service.save(new Post(UUID.randomUUID().toString(), "Post Nr: 2 title", "Post Nr: 2 content"));
        service.save(new Post(UUID.randomUUID().toString(), "Post Nr: 3 title", "Post Nr: 3 content"));
        service.save(new Post(UUID.randomUUID().toString(), "Post Nr: 4 title", "Post Nr: 4 content"));
        service.save(new Post(UUID.randomUUID().toString(), "Post Nr: 5 title", "Post Nr: 5 content"));

        log.info("5 Post saved");
    }
}
