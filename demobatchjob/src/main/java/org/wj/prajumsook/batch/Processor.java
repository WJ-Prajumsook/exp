package org.wj.prajumsook.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wj.prajumsook.entity.Product;
import org.wj.prajumsook.repository.ProductRepository;

import java.util.Optional;

/**
 * check if data is exist, then update price
 */
@Component
public class Processor implements ItemProcessor<Product, Product> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product process(Product product) throws Exception {
        Optional<Product> fromDb = productRepository.findById(product.getId());
        if(fromDb.isPresent()) {
            Product productFromDb = fromDb.get();
            // If exist we update the price
            productFromDb.setPrice(product.getPrice());
            return productFromDb;
        }

        return product;
    }
}
