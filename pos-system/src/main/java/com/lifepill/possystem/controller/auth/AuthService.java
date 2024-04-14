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

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest registerRequest) {
        if (employerRepository.existsById(registerRequest.getEmployerId()) || employerRepository.existsAllByEmployerEmail(registerRequest.getEmployerEmail())) {
            throw new EntityDuplicationException("Employer already exists");
        } else {
            Branch branch = branchRepository.findById(registerRequest.getBranchId())
                    .orElseThrow(() -> new NotFoundException("Branch not found with ID: " + registerRequest.getBranchId()));

            // Encode the password before saving
            String encodedPassword = passwordEncoder.encode(registerRequest.getEmployerPassword());

            var employer = modelMapper.map(registerRequest, Employer.class);
            employer.setBranch(branch);
            employer.setEmployerPassword(encodedPassword); // Set the encoded password

            var savedEmployer = employerRepository.save(employer);
            String jwtToken = jwtService.generateToken(savedEmployer);

            return AuthenticationResponse.builder().accessToken(jwtToken).build();
        }
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
      /*  FirstStep
        We need to validate our request (validate whether password & username is correct)
        Verify whether user present in the database
        Which AuthenticationProvider -> DaoAuthenticationProvider (Inject)
        We need to authenticate using authenticationManager injecting this authenticationProvider
        SecondStep
        Verify whether userName and password is correct => UserNamePasswordAuthenticationToken
        Verify whether user present in db
        generateToken
        Return the token*/
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmployerEmail(),
                        request.getEmployerPassword()
                )
        );
        var user = employerRepository.findByEmployerEmail(request.getEmployerEmail())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();

    }

}

