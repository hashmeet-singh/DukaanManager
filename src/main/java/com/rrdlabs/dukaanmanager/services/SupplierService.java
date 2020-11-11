package com.rrdlabs.dukaanmanager.services;

import com.rrdlabs.dukaanmanager.entities.Supplier;

import java.util.List;

public interface SupplierService {
    List<Supplier> getAllSuppliers();

    Supplier getSupplier(Long id);

    boolean validateSupplier(Supplier supplier);

    Supplier createSupplier(Supplier supplier);
}
