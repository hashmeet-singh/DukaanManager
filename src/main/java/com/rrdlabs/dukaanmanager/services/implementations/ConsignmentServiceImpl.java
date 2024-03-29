package com.rrdlabs.dukaanmanager.services.implementations;

import com.rrdlabs.dukaanmanager.entities.Consignment;
import com.rrdlabs.dukaanmanager.entities.Product;
import com.rrdlabs.dukaanmanager.entities.Supplier;
import com.rrdlabs.dukaanmanager.entities.dto.ConsignmentRequestDto;
import com.rrdlabs.dukaanmanager.exceptions.RecordNotFoundException;
import com.rrdlabs.dukaanmanager.repositories.ConsignmentRepository;
import com.rrdlabs.dukaanmanager.services.ConsignmentService;
import com.rrdlabs.dukaanmanager.services.ProductService;
import com.rrdlabs.dukaanmanager.services.SupplierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ConsignmentServiceImpl implements ConsignmentService {

    private final ConsignmentRepository consignmentRepository;
    private final ProductService productService;
    private final SupplierService supplierService;

    public ConsignmentServiceImpl(ConsignmentRepository consignmentRepository, ProductService productService, SupplierService supplierService) {
        this.consignmentRepository = consignmentRepository;
        this.productService = productService;
        this.supplierService = supplierService;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<Consignment> createConsignment(final ConsignmentRequestDto consignmentRequest) {
        List<Consignment> createdConsignmets = new ArrayList<>();
        Supplier supplier = supplierService.getSupplier(consignmentRequest.getSupplierId());
        consignmentRequest.getLineItems().forEach(lineItemDto -> {
            Product product = productService.getProduct(lineItemDto.getProductId());
            Consignment consignment = new Consignment(product, supplier, consignmentRequest.getDeliveryDate(), lineItemDto.getQuantity(), lineItemDto.getPrice(), new Timestamp(System.currentTimeMillis()));
            createdConsignmets.add(consignmentRepository.save(consignment));
            productService.adjustProductQuantity(lineItemDto.getProductId(), lineItemDto.getQuantity());
        });

        return createdConsignmets;
    }

    @Override
    public Consignment getConsignment(Long consignmentId) {
        return consignmentRepository
                .findById(consignmentId)
                .orElseThrow(() -> new RecordNotFoundException("Invalid Consignment Id: " + consignmentId));
    }

    @Override
    public List<Consignment> getAllConsignments() {
        return consignmentRepository.findAll();
    }

    @Override
    public List<Consignment> getSupplierConsignments(Long supplierId) {
        return null;
    }
}
