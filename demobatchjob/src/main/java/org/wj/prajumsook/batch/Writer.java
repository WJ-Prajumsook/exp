package org.wj.prajumsook.batch;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wj.prajumsook.entity.Product;
import org.wj.prajumsook.repository.ProductRepository;

import java.util.List;

/**
 * Write to database
 */
@Component
@Log4j2
public class Writer implements ItemWriter<Product> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void write(List<? extends Product> list) throws Exception {
        log.info(String.format("Product saved: %s", list));
        productRepository.saveAll(list);
    }
}
