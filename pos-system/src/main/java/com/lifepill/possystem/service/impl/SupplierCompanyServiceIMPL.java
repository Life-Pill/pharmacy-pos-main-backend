package com.lifepill.possystem.service.impl;

import com.lifepill.possystem.dto.SupplierCompanyDTO;
import com.lifepill.possystem.entity.SupplierCompany;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.repo.supplierRepository.SupplierCompanyRepository;
import com.lifepill.possystem.service.SupplierCompanyService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of the service layer for managing supplier companies.
 */
@Service
@Transactional
@AllArgsConstructor
public class SupplierCompanyServiceIMPL implements SupplierCompanyService {

    private SupplierCompanyRepository supplierCompanyRepository;
    private ModelMapper modelMapper;

    /**
     * Retrieves all supplier companies.
     *
     * @return A list of SupplierCompanyDTO objects representing all supplier companies.
     */
    public List<SupplierCompanyDTO> getAllSupplierCompanies() {
        List<SupplierCompany> companies = supplierCompanyRepository.findAll();
        return companies.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Converts a SupplierCompany entity to its DTO representation.
     *
     * @param company The SupplierCompany entity to be converted.
     * @return The corresponding SupplierCompanyDTO object.
     */
    private SupplierCompanyDTO convertToDTO(SupplierCompany company) {
        return modelMapper.map(company, SupplierCompanyDTO.class);
    }

    public SupplierCompanyDTO saveSupplierCompany(SupplierCompanyDTO supplierCompanyDTO) {
        SupplierCompany company = modelMapper.map(supplierCompanyDTO, SupplierCompany.class);
        SupplierCompany savedCompany = supplierCompanyRepository.save(company);
        return modelMapper.map(savedCompany, SupplierCompanyDTO.class);
    }

    /**
     * Saves a new SupplierCompany.
     *
     * @param updatedCompanyDTO The DTO containing information about the SupplierCompany to be saved.
     * @return The DTO representation of the saved SupplierCompany.
     */
    public SupplierCompanyDTO updateSupplierCompanyById(long id, SupplierCompanyDTO updatedCompanyDTO) {
        Optional<SupplierCompany> optionalCompany = supplierCompanyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            SupplierCompany existingCompany = optionalCompany.get();
            // Update existingCompany with values from updatedCompanyDTO
            existingCompany.setCompanyName(updatedCompanyDTO.getCompanyName());
            existingCompany.setCompanyAddress(updatedCompanyDTO.getCompanyAddress());
            existingCompany.setCompanyContact(updatedCompanyDTO.getCompanyContact());
            existingCompany.setCompanyEmail(updatedCompanyDTO.getCompanyEmail());
            existingCompany.setCompanyDescription(updatedCompanyDTO.getCompanyDescription());
            existingCompany.setCompanyStatus(updatedCompanyDTO.getCompanyStatus());
            existingCompany.setCompanyRating(updatedCompanyDTO.getCompanyRating());
            existingCompany.setCompanyBank(updatedCompanyDTO.getCompanyBank());
            existingCompany.setCompanyAccountNumber(updatedCompanyDTO.getCompanyAccountNumber());

            SupplierCompany savedCompany = supplierCompanyRepository.save(existingCompany);
            return modelMapper.map(savedCompany, SupplierCompanyDTO.class);
        } else {
            throw new NotFoundException("Supplier Company not found with id: " + id);
        }
    }

    /**
     * Deletes a SupplierCompany by its ID.
     *
     * @param id The ID of the SupplierCompany to be deleted.
     * @throws NotFoundException If no SupplierCompany is found with the given ID.
     */
    public void deleteSupplierCompanyById(long id) {
        Optional<SupplierCompany> optionalCompany = supplierCompanyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            supplierCompanyRepository.deleteById(id);
        } else {
            throw new NotFoundException("Supplier Company not found with id: " + id);
        }
    }

    /**
     * Retrieves a SupplierCompany by its ID.
     *
     * @param id The ID of the SupplierCompany to retrieve.
     * @return The SupplierCompanyDTO representing the retrieved SupplierCompany.
     * @throws NotFoundException If no SupplierCompany is found with the given ID.
     */
    public SupplierCompanyDTO getSupplierCompanyById(long id) {
        SupplierCompany company = supplierCompanyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Supplier Company not found with id: " + id));
        return modelMapper.map(company, SupplierCompanyDTO.class);
    }
}
