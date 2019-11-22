package org.wj.prajumsook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wj.prajumsook.entity.Book;
import org.wj.prajumsook.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable(value = "id") String id) {
        return bookService.getById(id);
    }

    @DeleteMapping("/{id}")
    public Book deleteById(@PathVariable(value = "id")String id) {
        return bookService.deleteById(id);
    }
}
