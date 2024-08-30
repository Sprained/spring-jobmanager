package com.gabrielresende.jobmanager.modules.company.usecases;

import javax.naming.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gabrielresende.jobmanager.modules.company.dto.AuthCompanyDTO;
import com.gabrielresende.jobmanager.modules.company.entities.CompanyEntity;
import com.gabrielresende.jobmanager.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthCompanyUseCase(CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        CompanyEntity company =
                this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(() -> {
                    throw new UsernameNotFoundException("User not found");
                });

        boolean passwordMatchs = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if(!passwordMatchs) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256("teste");
        JWT.create().withIssuer("javagas").withSubject(company.getId().toString());
    }
}
