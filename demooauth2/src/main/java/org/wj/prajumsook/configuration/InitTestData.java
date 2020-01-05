package org.wj.prajumsook.configuration;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;
import org.wj.prajumsook.entity.AppUser;
import org.wj.prajumsook.entity.Post;
import org.wj.prajumsook.repository.AppUserRepository;
import org.wj.prajumsook.repository.PostRepository;

@Component
@Log4j2
public class InitTestData implements ApplicationListener<ApplicationContextEvent> {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public void onApplicationEvent(ApplicationContextEvent applicationContextEvent) {
        postRepository.save(new Post(0, "Post title nr1", "Post content nr1"));
        postRepository.save(new Post(0, "Post title nr2", "Post content nr2"));
        postRepository.save(new Post(0, "Post title nr3", "Post content nr3"));
        log.info("3 posts saved.");

        appUserRepository.save(
                new AppUser("wj@prajumsook.com", "passw0rd", "USER")
        );
        log.info("User saved.");
    }
}
