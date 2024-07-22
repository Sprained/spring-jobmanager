package com.gabrielresende.jobmanager.modules.company.usecases;

import org.springframework.stereotype.Service;

import com.gabrielresende.jobmanager.modules.company.entities.JobEntity;
import com.gabrielresende.jobmanager.modules.company.repositories.JobRepository;

@Service
public class CreateJobUseCase {

    private final JobRepository jobRepository;

    public CreateJobUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    
    public void execute(JobEntity jobEntity) {
        this.jobRepository.save(jobEntity);
    }
}
