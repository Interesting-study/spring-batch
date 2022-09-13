package io.springbatch.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Map;


// JobInstanceConfiguration, DBConfiguration
@Configuration
@RequiredArgsConstructor
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job BatchJob() {
        return this.jobBuilderFactory.get("Job")
                .start(step1())
                .next(step2())
                .build();
    } //JobExecution

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

                        JobExecution jobExecution = contribution.getStepExecution().getJobExecution();
                        System.out.println("jobExecution = " + jobExecution);

                        System.out.println("step1 has executed");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    } //JobExecution
    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> {
                    //System.out.println("step2 has executed");
                    //throw new RuntimeException("step2 has failed");
                    System.out.println("step2 has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    } //JobExecution

    /*@Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

                        JobParameters jobParameters = contribution.getStepExecution().getJobParameters();
                        String name = jobParameters.getString("name");
                        long seq = jobParameters.getLong("seq");
                        Date date = jobParameters.getDate("date");

                        System.out.println("===========================");
                        System.out.println("name:" + name);
                        System.out.println("seq: " + seq);
                        System.out.println("date: " + date);
                        System.out.println("===========================");

                        Map<String, Object> jobParameters2 = chunkContext.getStepContext().getJobParameters();
                        String name2 = (String)jobParameters2.get("name");
                        long seq2 = (long)jobParameters2.get("seq");

                        System.out.println("step1 has executed");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }*/ //JobParams

   /* @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("step2 was executed");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }*/ //JobParams
}
