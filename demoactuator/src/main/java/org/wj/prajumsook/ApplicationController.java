package org.wj.prajumsook;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("product")
public class ApplicationController {

    @GetMapping
    public List<String> getProducts() {
        return Arrays.asList("Product 001", "Product 002", "Product 003");
    }

    @PostMapping
    public String saveProduct(@RequestBody String name) {
        return "New item: " + name;
    }
}
