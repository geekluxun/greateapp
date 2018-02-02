package com.geekluxun.greateapp.spring.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * Project: greateapp
 * Author: luxun
 * Date: 2018/2/2 10:34
 * Description:
 */
@Service
public class BatchExmaple {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    @Qualifier(value = "messageJob")
    Job messageJob;


    public void taskExecute() {

        SimpleJobLauncher launcher = new SimpleJobLauncher();
        launcher.setJobRepository(jobRepository);
        launcher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        try {
            launcher.run(messageJob, new JobParameters());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
