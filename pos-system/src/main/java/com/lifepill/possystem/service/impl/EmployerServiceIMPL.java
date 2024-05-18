package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.dto.EmployerBankDetailsDTO;
import com.lifepill.possystem.dto.EmployerWithBankDTO;
import com.lifepill.possystem.dto.EmployerDTO;
import com.lifepill.possystem.dto.EmployerWithoutImageDTO;
import com.lifepill.possystem.dto.requestDTO.EmployerUpdate.*;
import com.lifepill.possystem.entity.Branch;
import com.lifepill.possystem.entity.EmployerBankDetails;
import com.lifepill.possystem.entity.Employer;
import com.lifepill.possystem.entity.enums.Role;
import com.lifepill.possystem.exception.EntityDuplicationException;
import com.lifepill.possystem.exception.EntityNotFoundException;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.repo.branchRepository.BranchRepository;
import com.lifepill.possystem.repo.employerRepository.EmployerBankDetailsRepository;
import com.lifepill.possystem.repo.employerRepository.EmployerRepository;
import com.lifepill.possystem.service.EmployerService;
import com.lifepill.possystem.util.mappers.EmployerMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class EmployerServiceIMPL implements EmployerService {

    private EmployerRepository employerRepository;
    private EmployerBankDetailsRepository cashierBankDetailsRepo;
    private BranchRepository branchRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private EmployerMapper employerMapper;

    /**
     * Saves an employer with image.
     *
     * @param employerDTO The employer data transfer object.
     * @return A message indicating the success of the operation.
     * @throws EntityDuplicationException If the employer already exists.
     * @throws NotFoundException         If the associated branch is not found.
     */
    @Override
    public String saveEmployer(EmployerDTO employerDTO){
        // check if the cashier already exists email or id
        if (employerRepository.existsById(employerDTO.getEmployerId()) || employerRepository.existsAllByEmployerEmail(employerDTO.getEmployerEmail())) {
            throw new EntityDuplicationException("Employer already exists");
        } else {
            // Retrieve the Branch entity by its ID
            Branch branch = branchRepository.findById(employerDTO.getBranchId())
                    .orElseThrow(() -> new NotFoundException("Branch not found with ID: " + employerDTO.getBranchId()));

            // Map EmployerDTO to Employer entity
            Employer employer = modelMapper.map(employerDTO, Employer.class);

            // Set the Branch entity to the Employer
            employer.setBranch(branch);

            // Save the Employer entity
            employerRepository.save(employer);
            return "Employer Saved";
        }
    }

    /**
     * Saves an employer without image.
     *
     * @param employerWithoutImageDTO The employer data transfer object without an image.
     * @return A message indicating the success of the operation.
     * @throws EntityDuplicationException If the employer already exists.
     * @throws NotFoundException         If the associated branch is not found.
     */
    @Override
    public String saveEmployerWithoutImage(EmployerWithoutImageDTO employerWithoutImageDTO) {
        // check if the employer already exists email or id
        if (employerRepository.existsById(employerWithoutImageDTO.getEmployerId())
                || employerRepository.existsAllByEmployerEmail(employerWithoutImageDTO.getEmployerEmail())) {
            throw new EntityDuplicationException("Employer already exists");
        } else {

            // Retrieve the Branch entity by its ID
            Branch branch = branchRepository.findById(employerWithoutImageDTO.getBranchId())
                    .orElseThrow(() -> new NotFoundException("Branch not found with ID: " + employerWithoutImageDTO.getBranchId()));

            // Map EmployerDTO to Employer entity
            Employer employer = modelMapper.map(employerWithoutImageDTO, Employer.class);

            // Set the Branch entity to the Employer
            employer.setBranch(branch);

            String savedEmployer = String.valueOf(employerRepository.findByEmployerEmail(employerWithoutImageDTO.getEmployerEmail()));
            if (savedEmployer != null) {
                employerRepository.save(employer);
                long employerId = employer.getEmployerId();
                employerWithoutImageDTO.setEmployerId(employerId);

            } else {
                throw new NotFoundException("Employer not found after saving");
            }
            return "Employer Saved";
        }
    }
    /**
     * Updates details of an employer.
     *
     * @param employerId                The ID of the employer to update.
     * @param employerAllDetailsUpdateDTO The DTO containing updated employer details.
     * @return A message indicating the success of the operation.
     * @throws NotFoundException If the employer with the given ID is not found.
     */
    @Override
    public String updateEmployer(Long employerId, EmployerAllDetailsUpdateDTO employerAllDetailsUpdateDTO) {
        // Check if the employer exists
        Employer existingEmployer = employerRepository.findById(employerId)
                .orElseThrow(() -> new NotFoundException("Employer not found with ID: " + employerId));

        // Check if the email is already associated with another employer
        if (!existingEmployer.getEmployerEmail().equals(employerAllDetailsUpdateDTO.getEmployerEmail()) &&
                employerRepository.existsAllByEmployerEmail(employerAllDetailsUpdateDTO.getEmployerEmail())) {
            throw new EntityDuplicationException("Email already exists");
        }

        // Map updated details to existing employer
        modelMapper.map(employerAllDetailsUpdateDTO, existingEmployer);

        // If the password is provided, encode it before updating
        if (employerAllDetailsUpdateDTO.getEmployerPassword() != null) {

            String encodedPassword = passwordEncoder.encode(employerAllDetailsUpdateDTO.getEmployerPassword());
            existingEmployer.setEmployerPassword(encodedPassword);
        }

        // If the branch ID is provided, update the branch
        if (employerAllDetailsUpdateDTO.getBranchId() != 0) {
            Branch branch = branchRepository.findById(employerAllDetailsUpdateDTO.getBranchId())
                    .orElseThrow(() -> new NotFoundException("Branch not found with ID: " + employerAllDetailsUpdateDTO.getBranchId()));
            existingEmployer.setBranch(branch);
        }

        // Save the updated employer
        employerRepository.save(existingEmployer);

        return "Employer updated successfully";
    }

    /**
     * Updates account details of an employer.
     *
     * @param employerUpdateAccountDetailsDTO The DTO containing updated employer account details.
     * @return A message indicating the success of the operation.
     * @throws NotFoundException If the employer with the given ID is not found.
     */
    @Override
    public String updateEmployerAccountDetails(EmployerUpdateAccountDetailsDTO employerUpdateAccountDetailsDTO) {
        if (employerRepository.existsById(employerUpdateAccountDetailsDTO.getEmployerId())){
            Employer employer = employerRepository.getReferenceById(employerUpdateAccountDetailsDTO.getEmployerId());

            employer.setEmployerFirstName(employerUpdateAccountDetailsDTO.getEmployerFirstName());
            employer.setEmployerLastName(employerUpdateAccountDetailsDTO.getEmployerLastName());
            employer.setGender(employerUpdateAccountDetailsDTO.getGender());
            employer.setEmployerAddress(employerUpdateAccountDetailsDTO.getEmployerAddress());
            employer.setDateOfBirth(employerUpdateAccountDetailsDTO.getDateOfBirth());

            employerRepository.save(employer);

            System.out.println(employer);

            return "Successfully Update employer account details";
        }else {
            throw new NotFoundException("No data found for that id");
        }
    }

    /**
     * Updates the password of an employer.
     *
     * @param employerPasswordResetDTO The DTO containing the employer ID and the new password.
     * @return A message indicating the success of the operation.
     * @throws NotFoundException If the employer with the given ID is not found.
     */
    @Override
    public String updateEmployerPassword(EmployerPasswordResetDTO employerPasswordResetDTO) {
        if (employerRepository.existsById(employerPasswordResetDTO.getEmployerId())){
            Employer employer = employerRepository.getReferenceById(employerPasswordResetDTO.getEmployerId());

            employer.setEmployerPassword(employerPasswordResetDTO.getEmployerPassword());
            employerRepository.save(employer);

            System.out.println(employer);

            return "Successfully Reset employer password";
        }else {
            throw new NotFoundException("No data found for that id");
        }
    }

    /**
     * Updates the recent PIN of an employer.
     *
     * @param employerRecentPinUpdateDTO The DTO containing the employer ID and the new PIN.
     * @return A message indicating the success of the operation.
     * @throws NotFoundException If the employer with the given ID is not found.
     */
    @Override
    public String updateRecentPin(EmployerRecentPinUpdateDTO employerRecentPinUpdateDTO) {
        if (employerRepository.existsById(employerRecentPinUpdateDTO.getEmployerId())){
            Employer employer = employerRepository.getReferenceById(employerRecentPinUpdateDTO.getEmployerId());

            employer.setPin(employerRecentPinUpdateDTO.getPin());
            employerRepository.save(employer);

            System.out.println(employer);

            return "Successfully Reset employer PIN";
        }else {
            throw new NotFoundException("No data found for that id");
        }
    }

    /**
     * Updates the bank account details of an employer.
     *
     * @param employerUpdateBankAccountDTO The DTO containing the updated bank account details.
     * @return A DTO containing the updated employer data along with bank account details.
     * @throws NotFoundException If the employer with the given ID is not found.
     */
    @Override
    public EmployerWithBankDTO updateEmployerBankAccountDetails(EmployerUpdateBankAccountDTO employerUpdateBankAccountDTO) {
        long employerId = employerUpdateBankAccountDTO.getEmployerId();

        if (employerRepository.existsById(employerId)) {
            Employer employer = employerRepository.getReferenceById(employerId);

            // Check if the employer already has bank details
            EmployerBankDetails existingBankDetails = cashierBankDetailsRepo.findById(employerId).orElse(null);


            if (existingBankDetails != null) {
                // Update existing bank details
                existingBankDetails.setBankName(employerUpdateBankAccountDTO.getBankName());
                existingBankDetails.setBankBranchName(employerUpdateBankAccountDTO.getBankBranchName());
                existingBankDetails.setBankAccountNumber(employerUpdateBankAccountDTO.getBankAccountNumber());
                existingBankDetails.setEmployerDescription(employerUpdateBankAccountDTO.getEmployerDescription());
                existingBankDetails.setMonthlyPayment(employerUpdateBankAccountDTO.getMonthlyPayment());
                existingBankDetails.setMonthlyPaymentStatus(employerUpdateBankAccountDTO.isMonthlyPaymentStatus());

                cashierBankDetailsRepo.save(existingBankDetails);
            } else {
                // Create new bank details if not present
                EmployerBankDetails newBankDetails = new EmployerBankDetails();
                //   newBankDetails.setEmployer(employer);
                newBankDetails.setBankName(employerUpdateBankAccountDTO.getBankName());
                newBankDetails.setBankBranchName(employerUpdateBankAccountDTO.getBankBranchName());
                newBankDetails.setBankAccountNumber(employerUpdateBankAccountDTO.getBankAccountNumber());
                newBankDetails.setEmployerDescription(employerUpdateBankAccountDTO.getEmployerDescription());
                newBankDetails.setMonthlyPayment(employerUpdateBankAccountDTO.getMonthlyPayment());
                newBankDetails.setMonthlyPaymentStatus(employerUpdateBankAccountDTO.isMonthlyPaymentStatus());

                cashierBankDetailsRepo.save(newBankDetails);
            }

            // Return employer data along with bank account details
            EmployerWithBankDTO employerWithBankDTO = new EmployerWithBankDTO();
            employerWithBankDTO.setEmployerId(employerId);
            employerWithBankDTO.setEmployerNic(employer.getEmployerNic());
            employerWithBankDTO.setEmployerPhone(employer.getEmployerPhone());
            employerWithBankDTO.setEmployerEmail(employer.getEmployerEmail());
            employerWithBankDTO.setEmployerSalary(employer.getEmployerSalary());
            employerWithBankDTO.setRole(employer.getRole());
            employerWithBankDTO.setEmployerFirstName(employer.getEmployerFirstName());
            employerWithBankDTO.setEmployerLastName(employer.getEmployerLastName());
            employerWithBankDTO.setGender(employer.getGender());
            employerWithBankDTO.setEmployerAddress(employer.getEmployerAddress());
            employerWithBankDTO.setDateOfBirth(employer.getDateOfBirth());
            employerWithBankDTO.setBranchId(employer.getBranch().getBranchId());
            employerWithBankDTO.setProfileImage(employer.getProfileImage());
            employerWithBankDTO.setEmployerNicName(employer.getEmployerNicName());
            employerWithBankDTO.setPin(employer.getPin());

            Employer employerBankDetailsId = employerRepository.getReferenceById(employerId);

            employerWithBankDTO.setEmployerBankDetails(employerBankDetailsId.getEmployerBankDetails());
            //  System.out.println(employerBankDetailsId.getEmployerBankDetails());

            return employerWithBankDTO;
        } else {
            throw new NotFoundException("No data found for that employer ID");
        }
    }

    /**
     * Retrieves an employer by their ID.
     *
     * @param employerId The ID of the employer to retrieve.
     * @return The DTO representing the employer.
     * @throws NotFoundException If no employer is found with the specified ID.
     */
    @Override
    public EmployerDTO getEmployerById(long employerId) {
        if (employerRepository.existsById(employerId)){
            Employer employer = employerRepository.getReferenceById(employerId);
            EmployerDTO employerDTO = modelMapper.map(employer, EmployerDTO.class);
            return employerDTO;
        }else {
            throw  new NotFoundException("No employer found for that id");
        }
    }

    /**
     * Retrieves an employer by their ID along with their image.
     *
     * @param employerId The ID of the employer to retrieve.
     * @return The DTO representing the employer with their image.
     * @throws NotFoundException If no employer is found with the specified ID.
     */
    @Override
    public EmployerDTO getEmployerByIdWithImage(long employerId) {
        if (employerRepository.existsById(employerId)){
            Employer employer = employerRepository.getReferenceById(employerId);

            EmployerDTO employerDTO = modelMapper.map(employer, EmployerDTO.class);

            return employerDTO;
        }else {
            throw  new NotFoundException("No employer found for that id");
        }
    }

    /**
     * Retrieves the image data associated with an employer.
     *
     * @param employerId The ID of the employer whose image data is to be retrieved.
     * @return The byte array representing the image data, or null if no image is found.
     */
    @Override
    public byte[] getImageData(long employerId) {
        Optional<Employer> branchOptional = employerRepository.findById(employerId);
        return branchOptional.map(com.lifepill.possystem.entity.Employer::getProfileImage).orElse(null);
    }

    @Override
    public String updateEmployerBankAccountDetailsByCashierId(long employerId, EmployerUpdateBankAccountDTO employerUpdateBankAccountDTO) {
        return "";
    }

    /**
     * Deletes an employer with the specified ID.
     *
     * @param employerId The ID of the employer to be deleted.
     * @return A message indicating the successful deletion of the employer.
     * @throws NotFoundException If no employer is found with the specified ID.
     */
    @Override
    public String deleteEmployer(long employerId) {
        if (employerRepository.existsById(employerId)){
            employerRepository.deleteById(employerId);

            return "deleted succesfully : "+ employerId;
        }else {
            throw new NotFoundException("No employer found for that id");
        }
    }

    /**
     * Retrieves all employer bank account details.
     *
     * @return A list of DTOs containing employer bank account details.
     * @throws NotFoundException If no employer bank details are found.
     */
    @Override
    public List<EmployerUpdateBankAccountDTO> getAllEmployerBankDetails() {
        List<EmployerBankDetails> getAllCashiersBankDetails = cashierBankDetailsRepo.findAll();

        if (!getAllCashiersBankDetails.isEmpty()){
            List<EmployerUpdateBankAccountDTO> cashierUpdateBankAccountDTOList = new ArrayList<>();
            for (EmployerBankDetails employerBankDetails : getAllCashiersBankDetails){
                EmployerUpdateBankAccountDTO cashierUpdateBankAccountDTO = new EmployerUpdateBankAccountDTO(
                        employerBankDetails.getEmployerBankDetailsId(),
                        employerBankDetails.getBankName(),
                        employerBankDetails.getBankBranchName(),
                        employerBankDetails.getBankAccountNumber(),
                        employerBankDetails.getEmployerDescription(),
                        employerBankDetails.getMonthlyPayment(),
                        employerBankDetails.getMonthlyPaymentStatus(),
                        employerBankDetails.getEmployerId()
                );
                cashierUpdateBankAccountDTOList.add(cashierUpdateBankAccountDTO);
            }
            return cashierUpdateBankAccountDTOList;
        }else {
            throw new NotFoundException("No Employer Bank Details Found");
        }
    }

    /**
     * Retrieves all employers.
     *
     * @return A list of DTOs containing employer details.
     * @throws NotFoundException If no employers are found.
     */
    @Override
    public List<EmployerDTO> getAllEmployer() {
        List<Employer> getAllEmployers = employerRepository.findAll();

        if (getAllEmployers.size() > 0){
            List<EmployerDTO> employerDTOList = new ArrayList<>();
            for (Employer employer : getAllEmployers){
                EmployerDTO employerDTO = modelMapper.map(employer, EmployerDTO.class);
                employerDTOList.add(employerDTO);
            }
            return employerDTOList;
        }else {
            throw new NotFoundException("No Employer Found");
        }
    }

    /**
     * Retrieves all employers with the specified active state.
     *
     * @param activeState The active state of the employers to retrieve.
     * @return A list of DTOs containing employer details.
     * @throws NotFoundException If no employers are found with the specified active state.
     */
    @Override
    public List<EmployerDTO> getAllEmployerByActiveState(boolean activeState) {
        List<Employer> getAllEmployers = employerRepository.findByIsActiveStatusEquals(activeState);
        if (getAllEmployers.size() > 0){
            List<EmployerDTO> employerDTOList = new ArrayList<>();
            for (Employer employer : getAllEmployers){
                EmployerDTO employerDTO = modelMapper.map(employer, EmployerDTO.class);
                employerDTOList.add(employerDTO);
            }
            return employerDTOList;
        }else {
            // throw new RuntimeException("No Employer Found");
            throw new NotFoundException("No Employer Found");
        }
    }

    /**
     * Retrieves a list of employers associated with the specified branch ID.
     *
     * @param branchId  The ID of the branch.
     * @return          A list of EmployerDTO objects representing the employers associated with the branch.
     * @throws NotFoundException  If the branch with the specified ID is not found.
     */
    @Override
    public List<EmployerDTO> getAllEmployerByBranchId(long branchId) {
        // Retrieve the branch by its ID
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new NotFoundException("Branch not found with ID: " + branchId));

        // Retrieve all employers associated with the branch
        List<Employer> employers = employerRepository.findAllByBranch(branch);

        // Map cashier entities to DTOs
        List<EmployerDTO> employerDTOS = employers.stream()
                .map(cashier -> modelMapper.map(cashier, EmployerDTO.class))
                .collect(Collectors.toList());

        return employerDTOS;
    }

    /**
     * Retrieves a list of employers with the specified role.
     *
     * @param role  The role of the employers to retrieve.
     * @return      A list of EmployerDTO objects representing the employers with the specified role.
     */
    @Override
    public List<EmployerDTO> getAllEmployerByRole(Role role) {
        List<Employer> employers = employerRepository.findAllByRole(role);

        return employers.stream()
                .map(employer -> modelMapper.map(employer, EmployerDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves an employer by their username (email).
     *
     * @param username  The username (email) of the employer to retrieve.
     * @return          An EmployerDTO object representing the employer with the specified username,
     *                  or null if no employer is found with the given username.
     */
    @Override
    public EmployerDTO getEmployerByUsername(String username) {
        Optional<Employer> employer = employerRepository.findByEmployerEmail(username);
        if (employer != null) {
            return modelMapper.map(employer, EmployerDTO.class);
        } else {
            // Handle case when employer is not found
            return null; // Or throw an exception, or return a default DTO
        }
    }

    /**
     * Retrieves the bank details of an employer by their ID.
     *
     * @param employerId The ID of the employer whose bank details are to be retrieved.
     * @return EmployerBankDetailsDTO containing the bank details of the specified employer.
     * @throws EntityNotFoundException if no employer is found with the given ID.
     */
    @Override
    public EmployerBankDetailsDTO getEmployerBankDetailsById(long employerId) {
        Employer employer = employerRepository.findById(employerId)
                .orElseThrow(() -> new EntityNotFoundException("Employer not found with id: " + employerId));

        EmployerBankDetails bankDetails = employer.getEmployerBankDetails();
        EmployerBankDetailsDTO bankDetailsDTO = modelMapper.map(bankDetails, EmployerBankDetailsDTO.class);

        return bankDetailsDTO;
    }

    /**
     * Retrieves a list of employers along with their bank details.
     *
     * @return List of EmployerWithBankDTO objects containing details of all employers with their bank details.
     */
    @Override
    public List<EmployerWithBankDTO> getAllEmployersWithBankDetails() {
        List<EmployerWithBankDTO> employersWithBankDetails = new ArrayList<>();

        // Fetch all employers from the database
        List<Employer> employers = employerRepository.findAll();

        // Iterate through each employer and retrieve their bank details
        for (Employer employer : employers) {
            EmployerBankDetails bankDetails = cashierBankDetailsRepo.findByEmployerId(employer.getEmployerId());

            // Map the employer and bank details to DTOs by model mappers
            EmployerWithBankDTO employerWithBankDTO = modelMapper.map(employer, EmployerWithBankDTO.class);

            // Add the employer with bank details DTO to the list
            employersWithBankDetails.add(employerWithBankDTO);
        }
        return employersWithBankDetails;
    }

    /**
     * Retrieves an employer along with their bank details by the employer's ID.
     *
     * @param employerId The ID of the employer whose details are to be retrieved.
     * @return EmployerWithBankDTO containing the details of the specified employer along with their bank details.
     * @throws NotFoundException if no employer is found with the given ID.
     */
    public EmployerWithBankDTO getEmployerWithBankDetailsById(long employerId) {
        // Retrieve the employer data by ID
        Employer employerDTO = employerRepository.findById(employerId)
                .orElseThrow(() -> new NotFoundException("Employer not found for ID: " + employerId));

        // get branch id
        long branchId = employerDTO.getBranch().getBranchId();

        // Map the employer and bank details to DTOs
        EmployerWithBankDTO employerWithBankDTO = modelMapper.map(employerDTO, EmployerWithBankDTO.class);

        // set branch id
        employerWithBankDTO.setBranchId(branchId);

        return employerWithBankDTO;
    }
}