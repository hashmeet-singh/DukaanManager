package com.rrdlabs.dukaanmanager.controllers;

import com.rrdlabs.dukaanmanager.entities.Consignment;
import com.rrdlabs.dukaanmanager.entities.dto.ConsignmentRequestDto;
import com.rrdlabs.dukaanmanager.services.ConsignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/consignments")
public class ConsignmentController {

    private final ConsignmentService consignmentService;

    ConsignmentController(ConsignmentService consignmentService) {
        this.consignmentService = consignmentService;
    }

    @GetMapping
    public List<Consignment> getAllConsignments() {
        return consignmentService.getAllConsignments();
    }

    @GetMapping("/{consignment_id}")
    public Consignment getConsignmentById(@PathVariable(name = "consignment_id") Long id) {
        return consignmentService.getConsignment(id);
    }

    @PostMapping
    public ResponseEntity<List<Consignment>> recordConsignment(@Valid @RequestBody ConsignmentRequestDto consignment) {
        List<Consignment> createdConsignment = consignmentService.createConsignment(consignment);
        return new ResponseEntity<>(createdConsignment, HttpStatus.CREATED);
    }
}
