package org.wj.prajumsook;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wj.prajumsook.entity.Product;
import org.wj.prajumsook.repository.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job processJob;

    @GetMapping
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @GetMapping("/run-job")
    public String runJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("unique", System.nanoTime())
                .toJobParameters();
        jobLauncher.run(processJob, jobParameters);

        return "Batch job has been invoked.";
    }
}
