package com.gabrielresende.jobmanager.modules.company.usecases;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gabrielresende.jobmanager.exceptions.UserFoundException;
import com.gabrielresende.jobmanager.modules.company.entities.CompanyEntity;
import com.gabrielresende.jobmanager.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateCompanyUseCase(CompanyRepository companyRepository,
            PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void execute(CompanyEntity companyEntity) {
        this.companyRepository
                .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent(user -> {
                    throw new UserFoundException();
                });

        String password = this.passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);

        this.companyRepository.save(companyEntity);
    }
}
