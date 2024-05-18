package com.lifepill.possystem.service;

import com.lifepill.possystem.dto.EmployerBankDetailsDTO;
import com.lifepill.possystem.dto.EmployerDTO;
import com.lifepill.possystem.dto.EmployerWithBankDTO;
import com.lifepill.possystem.dto.EmployerWithoutImageDTO;
import com.lifepill.possystem.dto.requestDTO.EmployerUpdate.EmployerAllDetailsUpdateDTO;
import com.lifepill.possystem.dto.requestDTO.EmployerUpdate.EmployerUpdateAccountDetailsDTO;
import com.lifepill.possystem.dto.requestDTO.EmployerUpdate.EmployerUpdateBankAccountDTO;
import com.lifepill.possystem.entity.Employer;
import com.lifepill.possystem.entity.EmployerBankDetails;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.repo.employerRepository.EmployerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployerServiceTest {

}
