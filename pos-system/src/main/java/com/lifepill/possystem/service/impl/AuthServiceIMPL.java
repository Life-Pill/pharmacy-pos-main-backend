package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.config.JwtService;
import com.lifepill.possystem.dto.EmployerDTO;
import com.lifepill.possystem.dto.requestDTO.AuthenticationRequestDTO;
import com.lifepill.possystem.dto.requestDTO.RegisterRequestDTO;
import com.lifepill.possystem.dto.responseDTO.AuthenticationResponseDTO;
import com.lifepill.possystem.dto.responseDTO.EmployerAuthDetailsResponseDTO;
import com.lifepill.possystem.entity.Branch;
import com.lifepill.possystem.entity.Employer;
import com.lifepill.possystem.exception.AuthenticationException;
import com.lifepill.possystem.exception.EntityDuplicationException;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.repo.branchRepository.BranchRepository;
import com.lifepill.possystem.repo.employerRepository.EmployerRepository;
import com.lifepill.possystem.service.AuthService;
import com.lifepill.possystem.service.EmployerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceIMPL implements AuthService {

    private  final EmployerRepository employerRepository;

    private final JwtService jwtService;

    private final BranchRepository branchRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final EmployerService employerService;


    public AuthenticationResponseDTO register(RegisterRequestDTO registerRequest) {
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

            return AuthenticationResponseDTO.builder().accessToken(jwtToken).build();
        }
    }


    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        try {
            // Authenticate user using Spring Security's authenticationManager
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmployerEmail(),
                            request.getEmployerPassword()
                    )
            );

            // If authentication is successful, generate JWT token
            var user = employerRepository.findByEmployerEmail(request.getEmployerEmail())
                    .orElseThrow(() -> new AuthenticationException("User not found"));
            String jwtToken = jwtService.generateToken(user);

            // Return the authentication response containing the token
            return AuthenticationResponseDTO.builder().accessToken(jwtToken).build();
        } catch (org.springframework.security.core.AuthenticationException e) {
            // Authentication failed due to incorrect username or password
            throw new AuthenticationException("Incorrect username or password", e);
        }
    }

    @Override
    public EmployerAuthDetailsResponseDTO getEmployerDetails(String username) {
        // Retrieve employer details DTO using EmployerService
        EmployerDTO employerDTO = employerService.getEmployerByUsername(username);

        // Convert EmployerDTO to EmployerAuthDetailsResponseDTO using ModelMapper
        EmployerAuthDetailsResponseDTO employerDetailsResponseDTO = modelMapper.map(employerDTO, EmployerAuthDetailsResponseDTO.class);

        employerDetailsResponseDTO.setActiveStatus(true);

    /*    // Set activeStatus based on whether the user is logged in or not
        if (isLoggedIn(username)) {
            employerDetailsResponseDTO.setActiveStatus(true);
        } else {
            employerDetailsResponseDTO.setActiveStatus(false);
        }
*/
        return employerDetailsResponseDTO;

    }
/*    private boolean isLoggedIn(String username) {
        // Retrieve the currently authenticated user from Spring Security context
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Check if the principal is an instance of UserDetails (indicating the user is authenticated)
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            return userDetails.getUsername().equals(username);
        } else {
            return false; // User is not authenticated
        }
    }*/


}
