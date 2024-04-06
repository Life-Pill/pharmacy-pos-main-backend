package com.lifepill.possystem.service.impl;


import com.lifepill.possystem.dto.SupplierDTO;
import com.lifepill.possystem.entity.Supplier;
import com.lifepill.possystem.entity.SupplierCompany;
import com.lifepill.possystem.exception.EntityDuplicationException;
import com.lifepill.possystem.exception.NotFoundException;
import com.lifepill.possystem.repo.supplierRepository.SupplierCompanyRepository;
import com.lifepill.possystem.repo.supplierRepository.SupplierRepository;
import com.lifepill.possystem.service.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SupplierServiceIMPL implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplierCompanyRepository supplierCompanyRepository;


    @Autowired
    private ModelMapper modelMapper;

    public List<SupplierDTO> getAllSuppliers() {
        List<Supplier> getAllSuppliers = supplierRepository.findAll();

        if (!getAllSuppliers.isEmpty()){
            List<SupplierDTO> supplierDTOList = new ArrayList<>();
            for (Supplier supplier: getAllSuppliers){
                SupplierDTO supplierDTO = modelMapper.map(supplier, SupplierDTO.class);
                supplierDTOList.add(supplierDTO);
            }
            return supplierDTOList;
        }else {
            throw new NotFoundException("No Supplier Found");
        }
    }

    public SupplierDTO saveSupplier(SupplierDTO supplierDTO) {
        if (supplierRepository.existsById(supplierDTO.getSupplierId()) || supplierRepository.existsAllBySupplierEmail(supplierDTO.getSupplierEmail())) {
            throw new EntityDuplicationException("Supplier already exists");
        } else {
            // Retrieve the Supplier Company entity by its ID
            SupplierCompany supplierCompany = supplierCompanyRepository.findById(supplierDTO.getCompanyId())
                    .orElseThrow(() -> new NotFoundException("Supplier Company not found with ID: " + supplierDTO.getCompanyId()));

            // Map EmployerDTO to Supplier entity
            Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);

            // Set the Branch entity to the Supplier entity
            supplier.setSupplierCompany(supplierCompany);

            // Save the Supplier entity
            supplierRepository.save(supplier);

            // In return company id is not showing but it went to the database
            return modelMapper.map(supplier, SupplierDTO.class);
        }
    }

    public SupplierDTO updateSupplierById(long id, SupplierDTO updatedSupplierDTO) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            Supplier existingSupplier = optionalSupplier.get();
            // Update existingSupplier with values from updatedSupplierDTO
            existingSupplier.setSupplierName(updatedSupplierDTO.getSupplierName());
            existingSupplier.setSupplierAddress(updatedSupplierDTO.getSupplierAddress());
            existingSupplier.setSupplierPhone(updatedSupplierDTO.getSupplierPhone());
            existingSupplier.setSupplierEmail(updatedSupplierDTO.getSupplierEmail());
            existingSupplier.setSupplierDescription(updatedSupplierDTO.getSupplierDescription());
            existingSupplier.setSupplierImage(updatedSupplierDTO.getSupplierImage());
            existingSupplier.setSupplierRating(updatedSupplierDTO.getSupplierRating());

            Supplier savedSupplier = supplierRepository.save(existingSupplier);
            return modelMapper.map(savedSupplier, SupplierDTO.class);
        } else {
            throw new NotFoundException("Supplier not found with id: " + id);
        }
    }

    public void deleteSupplierById(long id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            supplierRepository.deleteById(id);
        } else {
            throw new NotFoundException("Supplier not found with id: " + id);
        }
    }

    public SupplierDTO getSupplierById(long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Supplier not found with id: " + id));
        return modelMapper.map(supplier, SupplierDTO.class);
    }


}
