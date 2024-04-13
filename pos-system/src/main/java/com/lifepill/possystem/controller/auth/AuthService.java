package com.lifepill.possystem.controller.auth;

import com.lifepill.possystem.config.JwtService;
import com.lifepill.possystem.dto.EmployerWithoutImageDTO;
import com.lifepill.possystem.entity.Branch;
import com.lifepill.possystem.entity.Employer;
import com.lifepill.possystem.exception.EntityDuplicationException;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.repo.branchRepository.BranchRepository;
import com.lifepill.possystem.repo.employerRepository.EmployerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private  final EmployerRepository employerRepository;

    private final JwtService jwtService;

    private final BranchRepository branchRepository;

    private final ModelMapper modelMapper;


    public AuthenticationResponse register(RegisterRequest registerRequest) {
        if (employerRepository.existsById(registerRequest.getEmployerId()) || employerRepository.existsAllByEmployerEmail(registerRequest.getEmployerEmail())) {
            throw new EntityDuplicationException("Employer already exists");
        } else {
            Branch branch = branchRepository.findById(registerRequest.getBranchId())
                    .orElseThrow(() -> new NotFoundException("Branch not found with ID: " + registerRequest.getBranchId()));


            var employer = modelMapper.map(registerRequest, Employer.class);
            employer.setBranch(branch);

            var savedEmployer = employerRepository.save(employer);
            String jwtToken = jwtService.generateToken(savedEmployer);

            return AuthenticationResponse.builder().accessToken(jwtToken).build();
        }
    }

}

