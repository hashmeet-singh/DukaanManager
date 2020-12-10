package com.rrdlabs.dukaanmanager.controllers;

import com.rrdlabs.dukaanmanager.entities.Consignment;
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

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{supplier_id}")
    public Supplier getSupplierById(@PathVariable(name = "supplier_id") Long id) {
        return supplierService.getSupplier(id);
    }

    // TODO: 10-12-2020 Implement logic for API
    @GetMapping("/{supplier_id}/consignments")
    public List<Consignment> getSupplierConsignments(@PathVariable(name = "supplier_id") Long id) {
        return null;
    }

    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@Valid @RequestBody Supplier supplier) {
        supplierService.validateSupplier(supplier);
        Supplier createdSupplier = supplierService.createSupplier(supplier);
        return new ResponseEntity<>(createdSupplier, HttpStatus.CREATED);
    }

    @PutMapping("/{supplier_id}")
    public Supplier updateSupplier(@PathVariable(name = "supplier_id") Long id, @Valid @RequestBody Supplier supplier) {
        supplierService.getSupplier(id);
        supplier.setId(id);
        return supplierService.updateSupplier(supplier);
    }

    @DeleteMapping("/{supplier_id}")
    public void deleteSupplier(@PathVariable(name = "supplier_id") Long id) {
        supplierService.deleteSupplier(id);
    }
}
