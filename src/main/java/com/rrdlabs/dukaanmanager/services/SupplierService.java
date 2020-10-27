package com.rrdlabs.dukaanmanager.services;

import com.rrdlabs.dukaanmanager.entities.Supplier;
import com.rrdlabs.dukaanmanager.exceptions.KeyColumnDuplicationException;
import com.rrdlabs.dukaanmanager.exceptions.RecordNotFoundException;
import com.rrdlabs.dukaanmanager.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplier(int id) {
        return supplierRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Invalid Supplier Id:" + id));
    }

    public boolean validateSupplier(Supplier supplier) {
        if (!supplier.getEmail().isEmpty() && !supplierRepository.findByEmail(supplier.getEmail()).isEmpty()) {
            throw new KeyColumnDuplicationException("Email address: " + supplier.getEmail() + " is already registered.");
        }

        if (!supplierRepository.findByPhoneNo(supplier.getPhoneNo()).isEmpty()) {
            throw new KeyColumnDuplicationException("Phone Number: " + supplier.getPhoneNo() + " is already registered.");
        }
        return true;
    }

    public Supplier addSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }
}
