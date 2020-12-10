package com.rrdlabs.dukaanmanager.services;

import com.rrdlabs.dukaanmanager.entities.Consignment;

import java.util.List;

public interface ConsignmentService {
    Consignment createConsignment(Consignment consignment);

    Consignment getConsignment(Long consignmentId);

    List<Consignment> getAllConsignments();

    List<Consignment> getSupplierConsignments(Long supplierId);


}
