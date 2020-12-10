package com.rrdlabs.dukaanmanager.services.implementations;

import com.rrdlabs.dukaanmanager.entities.Consignment;
import com.rrdlabs.dukaanmanager.entities.Product;
import com.rrdlabs.dukaanmanager.exceptions.RecordNotFoundException;
import com.rrdlabs.dukaanmanager.repositories.ConsignmentRepository;
import com.rrdlabs.dukaanmanager.services.ConsignmentService;
import com.rrdlabs.dukaanmanager.services.ProductService;
import com.rrdlabs.dukaanmanager.services.SupplierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
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
    @Transactional
    public Consignment createConsignment(Consignment consignment) {
//        Supplier supplier = supplierService.getSupplier(consignment.getSupplier().getId());
        Product product = productService.getProduct(consignment.getProduct().getId());
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        consignment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        product.setQuantity(product.getQuantity() + consignment.getDeliveryQuantity());
        consignment = consignmentRepository.save(consignment);
        productService.updateProduct(product);

        return consignment;
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
