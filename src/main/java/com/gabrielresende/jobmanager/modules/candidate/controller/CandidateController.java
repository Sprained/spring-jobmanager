package com.gabrielresende.jobmanager.modules.candidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielresende.jobmanager.exceptions.UserFoundException;
import com.gabrielresende.jobmanager.modules.candidate.CandidateEntity;
import com.gabrielresende.jobmanager.modules.candidate.CandidateRepository;
import com.gabrielresende.jobmanager.modules.candidate.useCases.CreateCandidateUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @PostMapping("")
    public ResponseEntity create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            this.createCandidateUseCase.execute(candidateEntity);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
