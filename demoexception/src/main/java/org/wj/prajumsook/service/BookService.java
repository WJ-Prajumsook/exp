package org.wj.prajumsook.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.wj.prajumsook.entity.Book;
import org.wj.prajumsook.entity.BookRepository;
import org.wj.prajumsook.exception.BookNotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private BookRepository repository;

    public List<Book> getAll() {
        return repository.findAll();
    }

    public Book getById(String id) {
        return repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Book save(Book book) {
        return repository.save(book);
    }

    public Book deleteById(String id) {
        Integer.parseInt("xxx");
        Book book = getById(id);
        repository.deleteById(book.getId());

        return book;
    }
}
