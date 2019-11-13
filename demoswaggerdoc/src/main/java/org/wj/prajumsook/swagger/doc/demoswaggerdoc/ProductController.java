package org.wj.prajumsook.swagger.doc.demoswaggerdoc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @GetMapping
    public String getProduct() {
        return "Product here";
    }
}
