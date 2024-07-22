package com.gabrielresende.jobmanager.modules.company.usecases;

import org.springframework.stereotype.Service;

import com.gabrielresende.jobmanager.exceptions.UserFoundException;
import com.gabrielresende.jobmanager.modules.company.entities.CompanyEntity;
import com.gabrielresende.jobmanager.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

    private final CompanyRepository companyRepository;

    public CreateCompanyUseCase(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void execute(CompanyEntity companyEntity) {
        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent(user -> {
                    throw new UserFoundException();
                });

        this.companyRepository.save(companyEntity);
    }
}
