package com.rrdlabs.dukaanmanager.controllers;

import com.rrdlabs.dukaanmanager.entities.Consignment;
import com.rrdlabs.dukaanmanager.services.ConsignmentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/consignment")
public class ConsignmentController {

    private final ConsignmentService consignmentService;

    ConsignmentController(ConsignmentService consignmentService) {
        this.consignmentService = consignmentService;
    }

    @GetMapping("/all")
    public List<Consignment> getAllConsignments() {
        return consignmentService.getAllConsignments();
    }

    @GetMapping("/{id}")
    public Consignment getConsignmentById(@PathVariable Long id) {
        return consignmentService.getConsignment(id);
    }

    @PostMapping
    public Consignment recordConsignment(@Valid @RequestBody Consignment consignment) {
        return consignmentService.createConsignment(consignment);
    }
}
