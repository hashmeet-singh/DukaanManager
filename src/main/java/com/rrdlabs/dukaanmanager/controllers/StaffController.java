package com.rrdlabs.dukaanmanager.controllers;

import com.rrdlabs.dukaanmanager.entities.Customer;
import com.rrdlabs.dukaanmanager.entities.Staff;
import com.rrdlabs.dukaanmanager.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/all")
    public List<Staff> getAllStaffs() {
        return staffService.getAllStaff();
    }

    @GetMapping("/{id}")
    public Staff getStaffById(@PathVariable Long id) {
        return staffService.getStaff(id);
    }

    @PostMapping("/add")
    public ResponseEntity createStaff(@Valid @RequestBody Staff staff) {
        if (staffService.validateStaff(staff)) {
            Staff createdStaff = staffService.createStaff(staff);
            return ResponseEntity.ok("Staff created successfully with Id: " + createdStaff.getId());
        }

        return new ResponseEntity("Validation Failed", HttpStatus.BAD_REQUEST);
    }
}
