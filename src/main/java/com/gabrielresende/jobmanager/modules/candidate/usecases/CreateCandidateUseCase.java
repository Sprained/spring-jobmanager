package com.gabrielresende.jobmanager.modules.candidate.usecases;

import org.springframework.stereotype.Service;

import com.gabrielresende.jobmanager.exceptions.UserFoundException;
import com.gabrielresende.jobmanager.modules.candidate.CandidateEntity;
import com.gabrielresende.jobmanager.modules.candidate.CandidateRepository;

@Service
public class CreateCandidateUseCase {

    private final CandidateRepository candidateRepository;

    public CreateCandidateUseCase(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(),
                candidateEntity.getEmail()).ifPresent(user -> {
                    throw new UserFoundException();
                });
        return this.candidateRepository.save(candidateEntity);
    }
}
