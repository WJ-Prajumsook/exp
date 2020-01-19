package org.wj.prajumsook.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.wj.prajumsook.entity.Product;

@Configuration
public class BatchConfiguration {

    @Value("${data.file}")
    private Resource resource;
    @Autowired
    private Writer writer;
    @Autowired
    private Processor processor;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job processJob() {
        Step step = stepBuilderFactory.get("step-1")
                .<Product, Product> chunk(1)
                .reader(new Reader(resource))
                .processor(processor)
                .writer(writer)
                .build();
        Job job = jobBuilderFactory.get("process-job")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .start(step)
                .build();

        return job;
    }

    @Bean
    public JobCompleteListener listener() {
        return new JobCompleteListener();
    }
}
