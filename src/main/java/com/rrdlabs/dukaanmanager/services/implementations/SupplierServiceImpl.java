package com.rrdlabs.dukaanmanager.services.implementations;

import com.rrdlabs.dukaanmanager.entities.Supplier;
import com.rrdlabs.dukaanmanager.exceptions.KeyColumnDuplicationException;
import com.rrdlabs.dukaanmanager.exceptions.RecordNotFoundException;
import com.rrdlabs.dukaanmanager.repositories.SupplierRepository;
import com.rrdlabs.dukaanmanager.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier getSupplier(Long id) {
        return supplierRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Invalid Supplier Id:" + id));
    }

    @Override
    public void validateSupplier(Supplier supplier) {
        if (!supplier.getEmail().isEmpty() && !supplierRepository.findByEmail(supplier.getEmail()).isEmpty()) {
            throw new KeyColumnDuplicationException("Email address: " + supplier.getEmail() + " is already registered.");
        }

        if (!supplierRepository.findByPhoneNo(supplier.getPhoneNo()).isEmpty()) {
            throw new KeyColumnDuplicationException("Phone Number: " + supplier.getPhoneNo() + " is already registered.");
        }
    }

    @Override
    @Transactional
    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier updateSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public void deleteSupplier(Long supplierId) {
        supplierRepository.deleteById(supplierId);
    }
}
