package com.rrdlabs.dukaanmanager.controllers;

import com.rrdlabs.dukaanmanager.entities.Supplier;
import com.rrdlabs.dukaanmanager.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/all")
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public Supplier getSupplierById(@PathVariable int id) {
        return supplierService.getSupplier(id);
    }

    @PostMapping("/add")
    public ResponseEntity createUser(@Valid @RequestBody Supplier supplier) {
        if (supplierService.validateSupplier(supplier)) {
            Supplier createdSupplier = supplierService.createSupplier(supplier);
            return ResponseEntity.ok("Supplier created successfully with Id: " + createdSupplier.getId());
        }

        return new ResponseEntity("Validation Failed", HttpStatus.BAD_REQUEST);
    }
}
