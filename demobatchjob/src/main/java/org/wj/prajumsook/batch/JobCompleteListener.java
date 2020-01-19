package org.wj.prajumsook.batch;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

@Log4j2
public class JobCompleteListener extends JobExecutionListenerSupport {

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus().equals(BatchStatus.COMPLETED)) {
            log.info("Batch job completed.");
        }
    }
}
