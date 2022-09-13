package io.springbatch.springbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

//@Component
public class JobRunner implements ApplicationRunner {

    @Autowired
    private JobLauncher jobLauncher; //job을 실행시킴

    @Autowired
    private Job job;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder().addString("name", "jobParamLec01")
                .toJobParameters(); //BATCH_JOB_INSTANCE, BATCH_JOB_EXECUTION_PARAMS |  재실행하면 동일한 인스턴스이므로 에러발생함

        jobLauncher.run(job, jobParameters);
    }
}
