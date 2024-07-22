package com.gabrielresende.jobmanager.modules.company.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielresende.jobmanager.modules.company.entities.JobEntity;
import com.gabrielresende.jobmanager.modules.company.usecases.CreateJobUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {
    
    private final CreateJobUseCase createJobUseCase;

    public JobController(CreateJobUseCase createJobUseCase) {
        this.createJobUseCase = createJobUseCase;
    }

    @PostMapping("")
    public void create(@Valid @RequestBody JobEntity jobEntity) {
        this.createJobUseCase.execute(jobEntity);
    }
}
