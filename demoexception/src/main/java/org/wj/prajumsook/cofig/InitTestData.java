package org.wj.prajumsook.cofig;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.wj.prajumsook.entity.Book;
import org.wj.prajumsook.service.BookService;

import java.util.UUID;

@Log4j2
@Component
@Profile("local")
public class InitTestData implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private BookService service;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        service.deleteAll();
        service.save(new Book(UUID.randomUUID().toString(), "Book Title", "Book author"));
        log.info("One book saved");
    }
}
