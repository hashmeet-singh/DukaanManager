package com.rrdlabs.dukaanmanager.services;

import com.rrdlabs.dukaanmanager.entities.Consignment;
import com.rrdlabs.dukaanmanager.entities.dto.ConsignmentRequestDto;

import java.util.List;

public interface ConsignmentService {
    List<Consignment> createConsignment(ConsignmentRequestDto consignment);

    Consignment getConsignment(Long consignmentId);

    List<Consignment> getAllConsignments();

    List<Consignment> getSupplierConsignments(Long supplierId);


}
