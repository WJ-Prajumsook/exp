package org.wj.prajumsook.batch;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.core.io.Resource;
import org.wj.prajumsook.entity.Product;

import java.util.Arrays;

/**
 * Read data from data.txt
 */
public class Reader extends FlatFileItemReader<Product> {

    public Reader(Resource resource) {
        setResource(resource);
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        Range [] ranges = new Range[] {
                new Range(1, 10), // ID
                new Range(11, 36), // NAME
                new Range(37, 51), // DESCRIPTION
                new Range(52, 57) // PRICE
        };
        tokenizer.setColumns(Arrays.asList(ranges).toArray(new Range[ranges.length]));
        tokenizer.setNames(
                new String[] {
                        "id", "name", "description", "price"
                }
        );
        tokenizer.setStrict(false);
        BeanWrapperFieldSetMapper<Product> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Product.class);
        DefaultLineMapper<Product> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(tokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        setLineMapper(defaultLineMapper);
    }
}
